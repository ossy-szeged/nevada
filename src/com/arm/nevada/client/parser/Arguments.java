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

package com.arm.nevada.client.parser;

import java.util.ArrayList;
import java.util.List;

import com.arm.nevada.client.interpreter.EnumDataType;

public class Arguments {
	private EnumDataType type;
	private List<Integer> registerIndexes = new ArrayList<Integer>(4);
	private List<EnumVectorRegisterType> vectorRegisterTypes = new ArrayList<EnumVectorRegisterType>(4);
	private int subRegisterIndex;
	private long immediateValue;
	private int alignmentByte = 1;

	public EnumDataType getType() {
		return type;
	}

	public void setType(EnumDataType type) {
		this.type = type;
	}

	public void setRegisterIndexes(List<Integer> registerIndexes) {
		this.registerIndexes = registerIndexes;
	}

	public int getSubRegisterIndex() {
		return subRegisterIndex;
	}

	public void setSubRegisterIndex(int subRegisterIndex) {
		this.subRegisterIndex = subRegisterIndex;
	}

	public long getImmediateValue() {
		return immediateValue;
	}

	public void setImmediateValue(long immediateValue) {
		this.immediateValue = immediateValue;
	}

	public int getAlignmentByte() {
		return alignmentByte;
	}

	public void setAlignmentByte(int alignmentByte) {
		this.alignmentByte = alignmentByte;
	}
	
	public int size() {
		return registerIndexes.size();
	}

	public int getLastIndex() {
		return registerIndexes.size() - 1;
	}

	public int getLastArgument(){
		return registerIndexes.get(size() - 1);
	}
	
	public int getRegisterIndex(int i) {
		return registerIndexes.get(i);
	}

	public EnumVectorRegisterType getVectorRegisterType(int i) {
		return vectorRegisterTypes.get(i);
	}

	public boolean addVectorRegisterType(EnumVectorRegisterType element) {
		return vectorRegisterTypes.add(element);
	}

	public void addVectorRegisterType(int index, EnumVectorRegisterType element) {
		vectorRegisterTypes.add(index, element);
	}
	
	public boolean validateVectorRegisterTypes(EnumArgumentListType[] validTypes) {

		for (EnumArgumentListType type: validTypes) {
			EnumVectorRegisterType[] x = type.getList();
			
			if (x.length != vectorRegisterTypes.size()) {
				return false;
			}
			
			int i;
			for (i=0; (i < vectorRegisterTypes.size()) && (x[i] == getVectorRegisterType(i)); i++);

			if (i == vectorRegisterTypes.size())
				return true;
		}
		return false;
	}
	public boolean add(Integer element) {
		return registerIndexes.add(element);
	}
}