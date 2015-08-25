/*
 * Copyright (C) 2011, 2012 University of Szeged
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *  1. Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY UNIVERSITY OF SZEGED ``AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL UNIVERSITY OF SZEGED OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY
 * OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.arm.nevada.client.interpreter;

import com.arm.nevada.client.interpreter.machine.Machine;
import com.arm.nevada.client.interpreter.machine.NEONRegisterSet;
import com.arm.nevada.client.parser.Arguments;
import com.arm.nevada.client.parser.EnumArgumentListType;
import com.arm.nevada.client.parser.EnumInstruction;
import com.arm.nevada.client.parser.EnumRegisterType;
import com.arm.nevada.client.utils.DataTypeTools;

public class LogicalInstruction extends Instruction {
	private EnumRegisterType registerType;
	private EnumInstruction instructionName;
	
	private Integer destinationIndex, source1Index, source2Index;
	private EnumDataType dataType;
	private boolean immediate;
	private long immedateValue;

	public LogicalInstruction(EnumInstruction instruction, boolean immediate) {
		this.instructionName = instruction;
		this.immediate = immediate;
		validArgumentListTypes = EnumArgumentListType.validArgumentsForLogicalInstructions;
	}

	@Override
	public void bindArguments(Arguments arguments) {
		this.immedateValue = arguments.getImmediateValue();
		this.destinationIndex = arguments.getRegisterIndex(0);
		this.source1Index = arguments.getRegisterIndex(1);

		int x = arguments.getVectorRegisterType(0).getVectorSizeInBytes();
		if ( x == 8) {
			this.registerType = EnumRegisterType.DOUBLE;
		} else if ( x == 16) {
			this.registerType = EnumRegisterType.QUAD;
		}
		
		if (this.immediate) {
			this.source2Index = null;
		} else {
			this.source2Index = arguments.getRegisterIndex(2);
		}
	}

	@Override
	public void execute(Machine machine) {
		int size = 32; // do not change, unless rewrite this method
		NEONRegisterSet neonRS = machine.getNEONRegisterSet();
		int[] op1s = DataTypeTools.getParts(size, neonRS.getRegisterValues(registerType, source1Index));
		int[] op2s;
		int[] dests = DataTypeTools.getParts(size, neonRS.getRegisterValues(registerType, destinationIndex));
		int[] resultWords = new int[op1s.length];
		if (immediate) {
			op2s = new int[op1s.length];
			int[] immParts = DataTypeTools.integerFromLong(immedateValue);
			for (int i = 0; i < op1s.length; i++) {
				op2s[i] = immParts[i % 2];
			}
		} else {
			op2s = DataTypeTools.getParts(32, neonRS.getRegisterValues(registerType, source2Index));
		}

		for (int i = 0; i < op1s.length; i++) {
			resultWords[i] = calculate(op1s[i], op2s[i], dests[i]);
		}

		neonRS.setRegisterValues(registerType, false, destinationIndex, resultWords);
		machine.incrementPCBy4();
		highlightChangedRegisters(machine);
	}

	private int calculate(int n, int m, int d) {
		switch (getInstructionName()) {
		case and:
			return n & m;
		case bic:
			return n & ~m;
		case orr:
			return n | m;
		case eor:
			return n ^ m;
		case orn:
			return n | (~m);
		case bif:
			return (d & m) | (n & ~m);
		case bit:
			return (n & m) | (d & ~m);
		case bsl:
			return (n & d) | (m & ~d);
		default:
		    assert false;
		}
		return 0;
	}
	
	private void highlightChangedRegisters(Machine machine) {
		machine.highlightNEONRegister(registerType, destinationIndex);
	}

	@Override
	public Instruction create() {
		return new LogicalInstruction(this.instructionName, this.immediate);
	}

	@Override
	public EnumInstruction getInstructionName() {
		return instructionName;
	}

	@Override
	public EnumDataType getDataType() {
		return dataType;
	}
}
