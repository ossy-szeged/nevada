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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.arm.nevada.client.interpreter.AbsoluteAndNegateInstruction;
import com.arm.nevada.client.interpreter.ArithmeticInstructions;
import com.arm.nevada.client.interpreter.ComparisonInstruction;
import com.arm.nevada.client.interpreter.ConversationInstruction;
import com.arm.nevada.client.interpreter.CountInstruction;
import com.arm.nevada.client.interpreter.EnumDataType;
import com.arm.nevada.client.interpreter.Instruction;
import com.arm.nevada.client.interpreter.LogicalInstruction;
import com.arm.nevada.client.interpreter.MemoryInstruction;
import com.arm.nevada.client.interpreter.MinimumAndMaximumInstruction;
import com.arm.nevada.client.interpreter.MoveFPSCAndRAPSR;
import com.arm.nevada.client.interpreter.MoveInstruction;
import com.arm.nevada.client.interpreter.MultiplyInstruction;
import com.arm.nevada.client.interpreter.ReciprocalSqrtReciprocalEstimate;
import com.arm.nevada.client.interpreter.ReciprocalSqrtReciprocalStep;
import com.arm.nevada.client.interpreter.ReverseInstruction;
import com.arm.nevada.client.interpreter.ShiftInstruction;
import com.arm.nevada.client.interpreter.TableInstruction;
import com.arm.nevada.client.interpreter.VdupInstruction;
import com.arm.nevada.client.interpreter.VextInstruction;
import com.arm.nevada.client.interpreter.VmovInstruction;
import com.arm.nevada.client.interpreter.VswpInstruction;
import com.arm.nevada.client.interpreter.VtrnInstruction;
import com.arm.nevada.client.interpreter.ZipInstruction;

/**
 * This helper class for defining the list of valid instructions also the required parameters.
 * 
 */
public class InstructionFormats {

		private static final HashMap<EnumInstruction, List<InstructionForm>> instructionList;

		static {
			instructionList = new HashMap<EnumInstruction, List<InstructionForm>>();
			fillInstructions00();
			fillInstructions01();
			
			fillInstructionsNEW01();
			fillInstructionsNEW02();
		}

		private static void fillInstructionsNEW01(){
			//EnumInstruction x = EnumInstruction.add;
			ArrayList<EnumInstruction> list = new ArrayList<EnumInstruction>(Arrays.asList(
					EnumInstruction.add,
					EnumInstruction.fadd,
					EnumInstruction.addhn,
					EnumInstruction.addhn2,
					EnumInstruction.uaddl,
					EnumInstruction.uaddl2,
					EnumInstruction.saddl,
					EnumInstruction.saddl2,
					EnumInstruction.uaddw,
					EnumInstruction.uaddw2,
					EnumInstruction.saddw,
					EnumInstruction.saddw2,
					EnumInstruction.uhadd,
					EnumInstruction.shadd,
					EnumInstruction.uhsub,
					EnumInstruction.shsub,
					EnumInstruction.addp,
					EnumInstruction.faddp,
					EnumInstruction.raddhn,
					EnumInstruction.raddhn2,
					EnumInstruction.urhadd,
					EnumInstruction.srhadd,
					EnumInstruction.rsubhn,
					EnumInstruction.rsubhn2,
					EnumInstruction.uqadd,
					EnumInstruction.sqadd,
					EnumInstruction.uqsub,
					EnumInstruction.sqsub,
					EnumInstruction.sub,
					EnumInstruction.fsub,
					EnumInstruction.subhn,
					EnumInstruction.subhn2,
					EnumInstruction.usubl,
					EnumInstruction.usubl2,
					EnumInstruction.ssubl,
					EnumInstruction.ssubl2,
					EnumInstruction.usubw,
					EnumInstruction.usubw2,
					EnumInstruction.ssubw,
					EnumInstruction.ssubw2
					));
			for ( EnumInstruction x : list){
				append(new ArithmeticInstructions(x), Space.p(), V_BHSD.p(), Comma.p(), V_BHSD.p(), Comma.p(), V_BHSD.p());	
			}

			ArrayList<EnumInstruction> list2 = new ArrayList<EnumInstruction>(Arrays.asList(
					EnumInstruction.uadalp,
					EnumInstruction.sadalp,
					EnumInstruction.uaddlp,
					EnumInstruction.saddlp
					));

			for ( EnumInstruction x : list2){
				append(new ArithmeticInstructions(x), Space.p(), V_BHSD.p(), Comma.p(), V_BHSD.p());	
			}

			
		}
		
		private static void fillInstructionsNEW02(){
			ArrayList<EnumInstruction> list = new ArrayList<EnumInstruction>(Arrays.asList( 
					EnumInstruction.and,
					EnumInstruction.bic,
					EnumInstruction.bif,
					EnumInstruction.bit,
					EnumInstruction.bsl,
					EnumInstruction.eor,
					EnumInstruction.orr,
					EnumInstruction.orn
					));

			for ( EnumInstruction x : list){
				append(new LogicalInstruction(x, false), Space.p(), V_BHSD.p(), Comma.p(), V_BHSD.p(), Comma.p(), V_BHSD.p());	
			}

		}

			
		private static void fillInstructions00(){
/*
			//ARITHMETIC2
			//add int
			append(new ArithmeticInstructions(EnumInstruction.add, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Q.p());
			append(new ArithmeticInstructions(EnumInstruction.add, EnumRegisterType.QUAD), Space.p(), V128BHSD.p(), Comma.p(), V128BHSD.p(), Comma.p(), V128BHSD.p());
			
			//add float
			append(new ArithmeticInstructions(EnumInstruction.add, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Q.p());
			
			//sub int
			append(new ArithmeticInstructions(EnumInstruction.sub, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Q.p());
			
			//sub float
			append(new ArithmeticInstructions(EnumInstruction.sub, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Q.p());
			
			//addhn
			append(new ArithmeticInstructions(EnumInstruction.addhn, EnumRegisterType.DOUBLE), Space.p(), D.p(), Comma.p(), Q.p(), Comma.p(), Q.p());
			
			//subhn
			append(new ArithmeticInstructions(EnumInstruction.subhn, EnumRegisterType.DOUBLE), Space.p(), D.p(), Comma.p(), Q.p(), Comma.p(), Q.p());

			//addl
			append(new ArithmeticInstructions(EnumInstruction.addl, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), D.p(), Comma.p(), D.p());
			
			//addw
			append(new ArithmeticInstructions(EnumInstruction.addw, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), D.p());

			//subl
			append(new ArithmeticInstructions(EnumInstruction.subl, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), D.p(), Comma.p(), D.p());
			
			//subw
			append(new ArithmeticInstructions(EnumInstruction.subw, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), D.p());
			
			//hadd
			append(new ArithmeticInstructions(EnumInstruction.hadd, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Q.p());
			
			//hsub
			append(new ArithmeticInstructions(EnumInstruction.hsub, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Q.p());
			
			//padal
			append(new ArithmeticInstructions(EnumInstruction.padal, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p());
			
			//padd integer
			append(new ArithmeticInstructions(EnumInstruction.padd, EnumRegisterType.DOUBLE), Space.p(), D.p(), Comma.p(), D.p(), Comma.p(), D.p());
			
			//vpadd float
			append(new ArithmeticInstructions(EnumInstruction.padd, EnumRegisterType.DOUBLE), Space.p(), D.p(), Comma.p(), D.p(), Comma.p(), D.p());
			
			//vpaddl
			append(new ArithmeticInstructions(EnumInstruction.paddl, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p());
			
			//vraddhn
			append(new ArithmeticInstructions(EnumInstruction.raddhn, EnumRegisterType.DOUBLE), Space.p(), D.p(), Comma.p(), Q.p(), Comma.p(), Q.p());
			
			//vrsubn
			append(new ArithmeticInstructions(EnumInstruction.rsubhn, EnumRegisterType.DOUBLE), Space.p(), D.p(), Comma.p(), Q.p(), Comma.p(), Q.p());
			
			//vrhadd
			append(new ArithmeticInstructions(EnumInstruction.rhadd, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Q.p());
			
			//vqadd
			append(new ArithmeticInstructions(EnumInstruction.qadd, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Q.p());
			
			//vqsub
			append(new ArithmeticInstructions(EnumInstruction.qsub, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Q.p());
*/
/*
			//LOGICAL2
			//vand register
			append(new LogicalInstruction(EnumInstruction.vand, EnumRegisterType.QUAD, false), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Q.p());
			
			//vand immediate
			append(new LogicalInstruction(EnumInstruction.vand, EnumRegisterType.QUAD, true), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), ImmVbic.p());
			
			//vbic immediate
			append(new LogicalInstruction(EnumInstruction.vbic, EnumRegisterType.QUAD, true), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), ImmVbic.p());
			
			//vbic register
			append(new LogicalInstruction(EnumInstruction.vbic, EnumRegisterType.QUAD, false), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Q.p());
			
			//veor register
			append(new LogicalInstruction(EnumInstruction.veor, EnumRegisterType.QUAD, false), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Q.p());
			
			//vbif register
			append(new LogicalInstruction(EnumInstruction.vbif, EnumRegisterType.QUAD, false), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Q.p());
			
			//vbit register
			append(new LogicalInstruction(EnumInstruction.vbit, EnumRegisterType.QUAD, false), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Q.p());
			
			//vbsl register
			append(new LogicalInstruction(EnumInstruction.vbsl, EnumRegisterType.QUAD, false), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Q.p());
			
			//vorr register
			append(new LogicalInstruction(EnumInstruction.vorr, EnumRegisterType.QUAD, false), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Q.p());
			
			//vorr immediate
			append(new LogicalInstruction(EnumInstruction.vorr, EnumRegisterType.QUAD, true), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), ImmVorr.p());
			
			//vorn register
			append(new LogicalInstruction(EnumInstruction.vorn, EnumRegisterType.QUAD, false), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Q.p());
*/
			//MOVE
			// special vmov
			append(new VmovInstruction(VmovInstruction.Mode.ARM_TO_D), Space.p(), D.p(), Comma.p(), RNotPCNotSP.p(), Comma.p(), RNotPCNotSP.p());
			append(new VmovInstruction(VmovInstruction.Mode.D_TO_ARM), Space.p(), RNotPCNotSP.p(), Comma.p(), RNotPCNotSP.p(), Comma.p(), D.p());
			append(new VmovInstruction(VmovInstruction.Mode.ARM_TO_DSUB), Space.p(), DSubReg.p(), Comma.p(), RNotPCNotSP.p());
			append(new VmovInstruction(VmovInstruction.Mode.ARM_TO_DSUB), DefType.p(EnumDataType._32), Space.p(), DSubReg.p(), Comma.p(), RNotPCNotSP.p());
			append(new VmovInstruction(VmovInstruction.Mode.DSUB_TO_ARM), Space.p(), RNotPCNotSP.p(), Comma.p(), DSubReg.p());
			append(new VmovInstruction(VmovInstruction.Mode.DSUB_TO_ARM), DefType.p(EnumDataType._32), Space.p(), RNotPCNotSP.p(), Comma.p(), DSubReg.p());

			//ordinary move
			//vmov neon imm
			append(new MoveInstruction(EnumInstruction.vmov, EnumRegisterType.QUAD, true), Space.p(), Q.p(), Comma.p(), ImmVmov.p());

			//vmov neon neon
			append(new MoveInstruction(EnumInstruction.vmov, EnumRegisterType.QUAD, false), Space.p(), Q.p(), Comma.p(), Q.p());
			
			//vmvn neon imm
			append(new MoveInstruction(EnumInstruction.vmvn, EnumRegisterType.QUAD, true), Space.p(), Q.p(), Comma.p(), ImmVmvn.p());

			//vmvn neon neon
			append(new MoveInstruction(EnumInstruction.vmvn, EnumRegisterType.QUAD, false), Space.p(), Q.p(), Comma.p(), Q.p());
			
			//vmovn neon neon
			append(new MoveInstruction(EnumInstruction.vmovn, EnumRegisterType.DOUBLE, false), Space.p(), D.p(), Comma.p(), Q.p());
			
			//vmovl neon neon
			append(new MoveInstruction(EnumInstruction.vmovl, EnumRegisterType.QUAD, false), Space.p(), Q.p(), Comma.p(), D.p());
			
			//vqmov{u}n neon neon
			append(new MoveInstruction(EnumInstruction.vqmovun, EnumRegisterType.DOUBLE, false), Space.p(), D.p(), Comma.p(), Q.p());
			append(new MoveInstruction(EnumInstruction.vqmovn, EnumRegisterType.DOUBLE, false), Space.p(), D.p(), Comma.p(), Q.p());
			
			//DUPLICATE
			// #vdup
			append(new VdupInstruction(false, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), DSubReg.p());
			append(new VdupInstruction(true, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), R.p());

			// VLD: #vld1 #vld2 #vld3 #vld4
			// #vld1: all
			append(new MemoryInstruction(EnumInstruction.vld1, MemoryInstruction.Mode.ALL, 1, 1, false), Space.p(), ListSubIndex.p(1, 1, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64 }));
			append(new MemoryInstruction(EnumInstruction.vld1, MemoryInstruction.Mode.ALL, 1, 1, false), Space.p(), ListSubIndex.p(1, 1, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vld1, MemoryInstruction.Mode.ALL, 1, 1, true), Space.p(), ListSubIndex.p(1, 1, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64 }), WriteBack.p());

			append(new MemoryInstruction(EnumInstruction.vld1, MemoryInstruction.Mode.ALL, 2, 1, false), Space.p(), ListSubIndex.p(2, 1, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128 }));
			append(new MemoryInstruction(EnumInstruction.vld1, MemoryInstruction.Mode.ALL, 2, 1, false), Space.p(), ListSubIndex.p(2, 1, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vld1, MemoryInstruction.Mode.ALL, 2, 1, true), Space.p(), ListSubIndex.p(2, 1, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128 }), WriteBack.p());

			append(new MemoryInstruction(EnumInstruction.vld1, MemoryInstruction.Mode.ALL, 3, 1, false), Space.p(), ListSubIndex.p(3, 1, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64 }));
			append(new MemoryInstruction(EnumInstruction.vld1, MemoryInstruction.Mode.ALL, 3, 1, false), Space.p(), ListSubIndex.p(3, 1, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vld1, MemoryInstruction.Mode.ALL, 3, 1, true), Space.p(), ListSubIndex.p(3, 1, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64 }), WriteBack.p());

			append(new MemoryInstruction(EnumInstruction.vld1, MemoryInstruction.Mode.ALL, 4, 1, false), Space.p(), ListSubIndex.p(4, 1, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128, 256 }));
			append(new MemoryInstruction(EnumInstruction.vld1, MemoryInstruction.Mode.ALL, 4, 1, false), Space.p(), ListSubIndex.p(4, 1, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128, 256 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vld1, MemoryInstruction.Mode.ALL, 4, 1, true), Space.p(), ListSubIndex.p(4, 1, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128, 256 }), WriteBack.p());

			// #vld1: One
			append(new MemoryInstruction(EnumInstruction.vld1, MemoryInstruction.Mode.ONE, 1, 1, false), Space.p(), ListSubIndex.p(1, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 16 }));
			append(new MemoryInstruction(EnumInstruction.vld1, MemoryInstruction.Mode.ONE, 1, 1, false), Space.p(), ListSubIndex.p(1, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 16 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vld1, MemoryInstruction.Mode.ONE, 1, 1, true), Space.p(), ListSubIndex.p(1, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 16 }), WriteBack.p());
			append(new MemoryInstruction(EnumInstruction.vld1, MemoryInstruction.Mode.ONE, 1, 1, false), Space.p(), ListSubIndex.p(1, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 32 }));
			append(new MemoryInstruction(EnumInstruction.vld1, MemoryInstruction.Mode.ONE, 1, 1, false), Space.p(), ListSubIndex.p(1, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 32 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vld1, MemoryInstruction.Mode.ONE, 1, 1, true), Space.p(), ListSubIndex.p(1, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 32 }), WriteBack.p());
			append(new MemoryInstruction(EnumInstruction.vld1, MemoryInstruction.Mode.ONE, 1, 1, false), Space.p(), ListSubIndex.p(1, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8 }));
			append(new MemoryInstruction(EnumInstruction.vld1, MemoryInstruction.Mode.ONE, 1, 1, false), Space.p(), ListSubIndex.p(1, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vld1, MemoryInstruction.Mode.ONE, 1, 1, true), Space.p(), ListSubIndex.p(1, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8 }), WriteBack.p());

			// #vld1: Repeat one
			append(new MemoryInstruction(EnumInstruction.vld1, MemoryInstruction.Mode.ONE_REPEAT, 1, 1, false), Space.p(), ListSubIndex.p(1, 1, true, false), Comma.p(), BaseAddress.p(new int[] { 8, 16 }));
			append(new MemoryInstruction(EnumInstruction.vld1, MemoryInstruction.Mode.ONE_REPEAT, 1, 1, false), Space.p(), ListSubIndex.p(1, 1, true, false), Comma.p(), BaseAddress.p(new int[] { 8, 16 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vld1, MemoryInstruction.Mode.ONE_REPEAT, 1, 1, true), Space.p(), ListSubIndex.p(1, 1, true, false), Comma.p(), BaseAddress.p(new int[] { 8, 16 }), WriteBack.p());
			append(new MemoryInstruction(EnumInstruction.vld1, MemoryInstruction.Mode.ONE_REPEAT, 1, 1, false), Space.p(), ListSubIndex.p(1, 1, true, false), Comma.p(), BaseAddress.p(new int[] { 8, 32 }));
			append(new MemoryInstruction(EnumInstruction.vld1, MemoryInstruction.Mode.ONE_REPEAT, 1, 1, false), Space.p(), ListSubIndex.p(1, 1, true, false), Comma.p(), BaseAddress.p(new int[] { 8, 32 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vld1, MemoryInstruction.Mode.ONE_REPEAT, 1, 1, true), Space.p(), ListSubIndex.p(1, 1, true, false), Comma.p(), BaseAddress.p(new int[] { 8, 32 }), WriteBack.p());
			append(new MemoryInstruction(EnumInstruction.vld1, MemoryInstruction.Mode.ONE_REPEAT, 1, 1, false), Space.p(), ListSubIndex.p(1, 1, true, false), Comma.p(), BaseAddress.p(new int[] { 8 }));
			append(new MemoryInstruction(EnumInstruction.vld1, MemoryInstruction.Mode.ONE_REPEAT, 1, 1, false), Space.p(), ListSubIndex.p(1, 1, true, false), Comma.p(), BaseAddress.p(new int[] { 8 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vld1, MemoryInstruction.Mode.ONE_REPEAT, 1, 1, true), Space.p(), ListSubIndex.p(1, 1, true, false), Comma.p(), BaseAddress.p(new int[] { 8 }), WriteBack.p());

			append(new MemoryInstruction(EnumInstruction.vld1, MemoryInstruction.Mode.ONE_REPEAT, 2, 1, false), Space.p(), ListSubIndex.p(2, 1, true, false), Comma.p(), BaseAddress.p(new int[] { 8, 16 }));
			append(new MemoryInstruction(EnumInstruction.vld1, MemoryInstruction.Mode.ONE_REPEAT, 2, 1, false), Space.p(), ListSubIndex.p(2, 1, true, false), Comma.p(), BaseAddress.p(new int[] { 8, 16 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vld1, MemoryInstruction.Mode.ONE_REPEAT, 2, 1, true), Space.p(), ListSubIndex.p(2, 1, true, false), Comma.p(), BaseAddress.p(new int[] { 8, 16 }), WriteBack.p());
			append(new MemoryInstruction(EnumInstruction.vld1, MemoryInstruction.Mode.ONE_REPEAT, 2, 1, false), Space.p(), ListSubIndex.p(2, 1, true, false), Comma.p(), BaseAddress.p(new int[] { 8, 32 }));
			append(new MemoryInstruction(EnumInstruction.vld1, MemoryInstruction.Mode.ONE_REPEAT, 2, 1, false), Space.p(), ListSubIndex.p(2, 1, true, false), Comma.p(), BaseAddress.p(new int[] { 8, 32 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vld1, MemoryInstruction.Mode.ONE_REPEAT, 2, 1, true), Space.p(), ListSubIndex.p(2, 1, true, false), Comma.p(), BaseAddress.p(new int[] { 8, 32 }), WriteBack.p());
			append(new MemoryInstruction(EnumInstruction.vld1, MemoryInstruction.Mode.ONE_REPEAT, 2, 1, false), Space.p(), ListSubIndex.p(2, 1, true, false), Comma.p(), BaseAddress.p(new int[] { 8 }));
			append(new MemoryInstruction(EnumInstruction.vld1, MemoryInstruction.Mode.ONE_REPEAT, 2, 1, false), Space.p(), ListSubIndex.p(2, 1, true, false), Comma.p(), BaseAddress.p(new int[] { 8 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vld1, MemoryInstruction.Mode.ONE_REPEAT, 2, 1, true), Space.p(), ListSubIndex.p(2, 1, true, false), Comma.p(), BaseAddress.p(new int[] { 8 }), WriteBack.p());

			// vld2: all
			append(new MemoryInstruction(EnumInstruction.vld2, MemoryInstruction.Mode.ALL, 2, 1, false), Space.p(), ListSubIndex.p(2, 1, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128 }));
			append(new MemoryInstruction(EnumInstruction.vld2, MemoryInstruction.Mode.ALL, 2, 1, false), Space.p(), ListSubIndex.p(2, 1, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vld2, MemoryInstruction.Mode.ALL, 2, 1, true), Space.p(), ListSubIndex.p(2, 1, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128 }), WriteBack.p());

			append(new MemoryInstruction(EnumInstruction.vld2, MemoryInstruction.Mode.ALL, 2, 2, false), Space.p(), ListSubIndex.p(2, 2, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128 }));
			append(new MemoryInstruction(EnumInstruction.vld2, MemoryInstruction.Mode.ALL, 2, 2, false), Space.p(), ListSubIndex.p(2, 2, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vld2, MemoryInstruction.Mode.ALL, 2, 2, true), Space.p(), ListSubIndex.p(2, 2, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128 }), WriteBack.p());

			append(new MemoryInstruction(EnumInstruction.vld2, MemoryInstruction.Mode.ALL, 4, 1, false), Space.p(), ListSubIndex.p(4, 1, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128, 256 }));
			append(new MemoryInstruction(EnumInstruction.vld2, MemoryInstruction.Mode.ALL, 4, 1, false), Space.p(), ListSubIndex.p(4, 1, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128, 256 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vld2, MemoryInstruction.Mode.ALL, 4, 1, true), Space.p(), ListSubIndex.p(4, 1, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128, 256 }), WriteBack.p());

			// vld2: one
			append(new MemoryInstruction(EnumInstruction.vld2, MemoryInstruction.Mode.ONE, 2, 1, false), Space.p(), ListSubIndex.p(2, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 16 }));
			append(new MemoryInstruction(EnumInstruction.vld2, MemoryInstruction.Mode.ONE, 2, 1, false), Space.p(), ListSubIndex.p(2, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 16 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vld2, MemoryInstruction.Mode.ONE, 2, 1, true), Space.p(), ListSubIndex.p(2, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 16 }), WriteBack.p());
			append(new MemoryInstruction(EnumInstruction.vld2, MemoryInstruction.Mode.ONE, 2, 1, false), Space.p(), ListSubIndex.p(2, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 32 }));
			append(new MemoryInstruction(EnumInstruction.vld2, MemoryInstruction.Mode.ONE, 2, 1, false), Space.p(), ListSubIndex.p(2, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 32 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vld2, MemoryInstruction.Mode.ONE, 2, 1, true), Space.p(), ListSubIndex.p(2, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 32 }), WriteBack.p());
			append(new MemoryInstruction(EnumInstruction.vld2, MemoryInstruction.Mode.ONE, 2, 1, false), Space.p(), ListSubIndex.p(2, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 64 }));
			append(new MemoryInstruction(EnumInstruction.vld2, MemoryInstruction.Mode.ONE, 2, 1, false), Space.p(), ListSubIndex.p(2, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 64 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vld2, MemoryInstruction.Mode.ONE, 2, 1, true), Space.p(), ListSubIndex.p(2, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 64 }), WriteBack.p());

			append(new MemoryInstruction(EnumInstruction.vld2, MemoryInstruction.Mode.ONE, 2, 2, false), Space.p(), ListSubIndex.p(2, 2, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 32 }));
			append(new MemoryInstruction(EnumInstruction.vld2, MemoryInstruction.Mode.ONE, 2, 2, false), Space.p(), ListSubIndex.p(2, 2, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 32 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vld2, MemoryInstruction.Mode.ONE, 2, 2, true), Space.p(), ListSubIndex.p(2, 2, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 32 }), WriteBack.p());
			append(new MemoryInstruction(EnumInstruction.vld2, MemoryInstruction.Mode.ONE, 2, 2, false), Space.p(), ListSubIndex.p(2, 2, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 64 }));
			append(new MemoryInstruction(EnumInstruction.vld2, MemoryInstruction.Mode.ONE, 2, 2, false), Space.p(), ListSubIndex.p(2, 2, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 64 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vld2, MemoryInstruction.Mode.ONE, 2, 2, true), Space.p(), ListSubIndex.p(2, 2, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 64 }), WriteBack.p());

			// vld2: repeat one
			append(new MemoryInstruction(EnumInstruction.vld2, MemoryInstruction.Mode.ONE_REPEAT, 2, 1, false), Space.p(), ListSubIndex.p(2, 1, true, false), Comma.p(), BaseAddress.p(new int[] { 8, 16 }));
			append(new MemoryInstruction(EnumInstruction.vld2, MemoryInstruction.Mode.ONE_REPEAT, 2, 1, false), Space.p(), ListSubIndex.p(2, 1, true, false), Comma.p(), BaseAddress.p(new int[] { 8, 16 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vld2, MemoryInstruction.Mode.ONE_REPEAT, 2, 1, true), Space.p(), ListSubIndex.p(2, 1, true, false), Comma.p(), BaseAddress.p(new int[] { 8, 16 }), WriteBack.p());
			append(new MemoryInstruction(EnumInstruction.vld2, MemoryInstruction.Mode.ONE_REPEAT, 2, 1, false), Space.p(), ListSubIndex.p(2, 1, true, false), Comma.p(), BaseAddress.p(new int[] { 8, 32 }));
			append(new MemoryInstruction(EnumInstruction.vld2, MemoryInstruction.Mode.ONE_REPEAT, 2, 1, false), Space.p(), ListSubIndex.p(2, 1, true, false), Comma.p(), BaseAddress.p(new int[] { 8, 32 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vld2, MemoryInstruction.Mode.ONE_REPEAT, 2, 1, true), Space.p(), ListSubIndex.p(2, 1, true, false), Comma.p(), BaseAddress.p(new int[] { 8, 32 }), WriteBack.p());
			append(new MemoryInstruction(EnumInstruction.vld2, MemoryInstruction.Mode.ONE_REPEAT, 2, 1, false), Space.p(), ListSubIndex.p(2, 1, true, false), Comma.p(), BaseAddress.p(new int[] { 8, 64 }));
			append(new MemoryInstruction(EnumInstruction.vld2, MemoryInstruction.Mode.ONE_REPEAT, 2, 1, false), Space.p(), ListSubIndex.p(2, 1, true, false), Comma.p(), BaseAddress.p(new int[] { 8, 64 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vld2, MemoryInstruction.Mode.ONE_REPEAT, 2, 1, true), Space.p(), ListSubIndex.p(2, 1, true, false), Comma.p(), BaseAddress.p(new int[] { 8, 64 }), WriteBack.p());

			append(new MemoryInstruction(EnumInstruction.vld2, MemoryInstruction.Mode.ONE_REPEAT, 2, 2, false), Space.p(), ListSubIndex.p(2, 2, true, false), Comma.p(), BaseAddress.p(new int[] { 8, 16 }));
			append(new MemoryInstruction(EnumInstruction.vld2, MemoryInstruction.Mode.ONE_REPEAT, 2, 2, false), Space.p(), ListSubIndex.p(2, 2, true, false), Comma.p(), BaseAddress.p(new int[] { 8, 16 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vld2, MemoryInstruction.Mode.ONE_REPEAT, 2, 2, true), Space.p(), ListSubIndex.p(2, 2, true, false), Comma.p(), BaseAddress.p(new int[] { 8, 16 }), WriteBack.p());
			append(new MemoryInstruction(EnumInstruction.vld2, MemoryInstruction.Mode.ONE_REPEAT, 2, 2, false), Space.p(), ListSubIndex.p(2, 2, true, false), Comma.p(), BaseAddress.p(new int[] { 8, 32 }));
			append(new MemoryInstruction(EnumInstruction.vld2, MemoryInstruction.Mode.ONE_REPEAT, 2, 2, false), Space.p(), ListSubIndex.p(2, 2, true, false), Comma.p(), BaseAddress.p(new int[] { 8, 32 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vld2, MemoryInstruction.Mode.ONE_REPEAT, 2, 2, true), Space.p(), ListSubIndex.p(2, 2, true, false), Comma.p(), BaseAddress.p(new int[] { 8, 32 }), WriteBack.p());
			append(new MemoryInstruction(EnumInstruction.vld2, MemoryInstruction.Mode.ONE_REPEAT, 2, 2, false), Space.p(), ListSubIndex.p(2, 2, true, false), Comma.p(), BaseAddress.p(new int[] { 8, 64 }));
			append(new MemoryInstruction(EnumInstruction.vld2, MemoryInstruction.Mode.ONE_REPEAT, 2, 2, false), Space.p(), ListSubIndex.p(2, 2, true, false), Comma.p(), BaseAddress.p(new int[] { 8, 64 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vld2, MemoryInstruction.Mode.ONE_REPEAT, 2, 2, true), Space.p(), ListSubIndex.p(2, 2, true, false), Comma.p(), BaseAddress.p(new int[] { 8, 64 }), WriteBack.p());

			// vld3: all
			append(new MemoryInstruction(EnumInstruction.vld3, MemoryInstruction.Mode.ALL, 3, 1, false), Space.p(), ListSubIndex.p(3, 1, false, false), Comma.p(), BaseAddress.p(new int[] { 8 }));
			append(new MemoryInstruction(EnumInstruction.vld3, MemoryInstruction.Mode.ALL, 3, 1, false), Space.p(), ListSubIndex.p(3, 1, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vld3, MemoryInstruction.Mode.ALL, 3, 1, true), Space.p(), ListSubIndex.p(3, 1, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64 }), WriteBack.p());
			append(new MemoryInstruction(EnumInstruction.vld3, MemoryInstruction.Mode.ALL, 3, 2, false), Space.p(), ListSubIndex.p(3, 2, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64 }));
			append(new MemoryInstruction(EnumInstruction.vld3, MemoryInstruction.Mode.ALL, 3, 2, false), Space.p(), ListSubIndex.p(3, 2, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vld3, MemoryInstruction.Mode.ALL, 3, 2, true), Space.p(), ListSubIndex.p(3, 2, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64 }), WriteBack.p());

			// vld3: one
			append(new MemoryInstruction(EnumInstruction.vld3, MemoryInstruction.Mode.ONE, 3, 1, false), Space.p(), ListSubIndex.p(3, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8 }));
			append(new MemoryInstruction(EnumInstruction.vld3, MemoryInstruction.Mode.ONE, 3, 1, false), Space.p(), ListSubIndex.p(3, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vld3, MemoryInstruction.Mode.ONE, 3, 1, true), Space.p(), ListSubIndex.p(3, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8 }), WriteBack.p());
			append(new MemoryInstruction(EnumInstruction.vld3, MemoryInstruction.Mode.ONE, 3, 2, false), Space.p(), ListSubIndex.p(3, 2, true, true), Comma.p(), BaseAddress.p(new int[] { 8 }));
			append(new MemoryInstruction(EnumInstruction.vld3, MemoryInstruction.Mode.ONE, 3, 2, false), Space.p(), ListSubIndex.p(3, 2, true, true), Comma.p(), BaseAddress.p(new int[] { 8 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vld3, MemoryInstruction.Mode.ONE, 3, 2, true), Space.p(), ListSubIndex.p(3, 2, true, true), Comma.p(), BaseAddress.p(new int[] { 8 }), WriteBack.p());
			append(new MemoryInstruction(EnumInstruction.vld3, MemoryInstruction.Mode.ONE, 3, 2, false), Space.p(), ListSubIndex.p(3, 2, true, true), Comma.p(), BaseAddress.p(new int[] { 8 }));
			append(new MemoryInstruction(EnumInstruction.vld3, MemoryInstruction.Mode.ONE, 3, 2, false), Space.p(), ListSubIndex.p(3, 2, true, true), Comma.p(), BaseAddress.p(new int[] { 8 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vld3, MemoryInstruction.Mode.ONE, 3, 2, true), Space.p(), ListSubIndex.p(3, 2, true, true), Comma.p(), BaseAddress.p(new int[] { 8 }), WriteBack.p());

			// vld3: one repeat
			append(new MemoryInstruction(EnumInstruction.vld3, MemoryInstruction.Mode.ONE_REPEAT, 3, 1, false), Space.p(), ListSubIndex.p(3, 1, true, false), Comma.p(), BaseAddress.p(new int[] { 8 }));
			append(new MemoryInstruction(EnumInstruction.vld3, MemoryInstruction.Mode.ONE_REPEAT, 3, 1, false), Space.p(), ListSubIndex.p(3, 1, true, false), Comma.p(), BaseAddress.p(new int[] { 8 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vld3, MemoryInstruction.Mode.ONE_REPEAT, 3, 1, true), Space.p(), ListSubIndex.p(3, 1, true, false), Comma.p(), BaseAddress.p(new int[] { 8 }), WriteBack.p());
			append(new MemoryInstruction(EnumInstruction.vld3, MemoryInstruction.Mode.ONE_REPEAT, 3, 2, false), Space.p(), ListSubIndex.p(3, 2, true, false), Comma.p(), BaseAddress.p(new int[] { 8 }));
			append(new MemoryInstruction(EnumInstruction.vld3, MemoryInstruction.Mode.ONE_REPEAT, 3, 2, false), Space.p(), ListSubIndex.p(3, 2, true, false), Comma.p(), BaseAddress.p(new int[] { 8 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vld3, MemoryInstruction.Mode.ONE_REPEAT, 3, 2, true), Space.p(), ListSubIndex.p(3, 2, true, false), Comma.p(), BaseAddress.p(new int[] { 8 }), WriteBack.p());

			// vld4: all
			append(new MemoryInstruction(EnumInstruction.vld4, MemoryInstruction.Mode.ALL, 4, 1, false), Space.p(), ListSubIndex.p(4, 1, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128, 256 }));
			append(new MemoryInstruction(EnumInstruction.vld4, MemoryInstruction.Mode.ALL, 4, 1, false), Space.p(), ListSubIndex.p(4, 1, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128, 256 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vld4, MemoryInstruction.Mode.ALL, 4, 1, true), Space.p(), ListSubIndex.p(4, 1, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128, 256 }), WriteBack.p());
			append(new MemoryInstruction(EnumInstruction.vld4, MemoryInstruction.Mode.ALL, 4, 2, false), Space.p(), ListSubIndex.p(4, 2, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128, 256 }));
			append(new MemoryInstruction(EnumInstruction.vld4, MemoryInstruction.Mode.ALL, 4, 2, false), Space.p(), ListSubIndex.p(4, 2, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128, 256 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vld4, MemoryInstruction.Mode.ALL, 4, 2, true), Space.p(), ListSubIndex.p(4, 2, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128, 256 }), WriteBack.p());

			// vld4: one
			append(new MemoryInstruction(EnumInstruction.vld4, MemoryInstruction.Mode.ONE, 4, 1, false), Space.p(), ListSubIndex.p(4, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 32 }));
			append(new MemoryInstruction(EnumInstruction.vld4, MemoryInstruction.Mode.ONE, 4, 1, false), Space.p(), ListSubIndex.p(4, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 32 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vld4, MemoryInstruction.Mode.ONE, 4, 1, true), Space.p(), ListSubIndex.p(4, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 32 }), WriteBack.p());
			append(new MemoryInstruction(EnumInstruction.vld4, MemoryInstruction.Mode.ONE, 4, 1, false), Space.p(), ListSubIndex.p(4, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 64 }));
			append(new MemoryInstruction(EnumInstruction.vld4, MemoryInstruction.Mode.ONE, 4, 1, false), Space.p(), ListSubIndex.p(4, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 64 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vld4, MemoryInstruction.Mode.ONE, 4, 1, true), Space.p(), ListSubIndex.p(4, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 64 }), WriteBack.p());
			append(new MemoryInstruction(EnumInstruction.vld4, MemoryInstruction.Mode.ONE, 4, 1, false), Space.p(), ListSubIndex.p(4, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128 }));
			append(new MemoryInstruction(EnumInstruction.vld4, MemoryInstruction.Mode.ONE, 4, 1, false), Space.p(), ListSubIndex.p(4, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vld4, MemoryInstruction.Mode.ONE, 4, 1, true), Space.p(), ListSubIndex.p(4, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128 }), WriteBack.p());

			append(new MemoryInstruction(EnumInstruction.vld4, MemoryInstruction.Mode.ONE, 4, 2, false), Space.p(), ListSubIndex.p(4, 2, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 64 }));
			append(new MemoryInstruction(EnumInstruction.vld4, MemoryInstruction.Mode.ONE, 4, 2, false), Space.p(), ListSubIndex.p(4, 2, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 64 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vld4, MemoryInstruction.Mode.ONE, 4, 2, true), Space.p(), ListSubIndex.p(4, 2, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 64 }), WriteBack.p());
			append(new MemoryInstruction(EnumInstruction.vld4, MemoryInstruction.Mode.ONE, 4, 2, false), Space.p(), ListSubIndex.p(4, 2, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128 }));
			append(new MemoryInstruction(EnumInstruction.vld4, MemoryInstruction.Mode.ONE, 4, 2, false), Space.p(), ListSubIndex.p(4, 2, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vld4, MemoryInstruction.Mode.ONE, 4, 2, true), Space.p(), ListSubIndex.p(4, 2, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128 }), WriteBack.p());

			// vld4: one repeat
			append(new MemoryInstruction(EnumInstruction.vld4, MemoryInstruction.Mode.ONE_REPEAT, 4, 1, false), Space.p(), ListSubIndex.p(4, 1, true, false), Comma.p(), BaseAddress.p(new int[] { 8, 32 }));
			append(new MemoryInstruction(EnumInstruction.vld4, MemoryInstruction.Mode.ONE_REPEAT, 4, 1, false), Space.p(), ListSubIndex.p(4, 1, true, false), Comma.p(), BaseAddress.p(new int[] { 8, 32 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vld4, MemoryInstruction.Mode.ONE_REPEAT, 4, 1, true), Space.p(), ListSubIndex.p(4, 1, true, false), Comma.p(), BaseAddress.p(new int[] { 8, 32 }), WriteBack.p());
			append(new MemoryInstruction(EnumInstruction.vld4, MemoryInstruction.Mode.ONE_REPEAT, 4, 1, false), Space.p(), ListSubIndex.p(4, 1, true, false), Comma.p(), BaseAddress.p(new int[] { 8, 64 }));
			append(new MemoryInstruction(EnumInstruction.vld4, MemoryInstruction.Mode.ONE_REPEAT, 4, 1, false), Space.p(), ListSubIndex.p(4, 1, true, false), Comma.p(), BaseAddress.p(new int[] { 8, 64 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vld4, MemoryInstruction.Mode.ONE_REPEAT, 4, 1, true), Space.p(), ListSubIndex.p(4, 1, true, false), Comma.p(), BaseAddress.p(new int[] { 8, 64 }), WriteBack.p());
			append(new MemoryInstruction(EnumInstruction.vld4, MemoryInstruction.Mode.ONE_REPEAT, 4, 1, false), Space.p(), ListSubIndex.p(4, 1, true, false), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128 }));
			append(new MemoryInstruction(EnumInstruction.vld4, MemoryInstruction.Mode.ONE_REPEAT, 4, 1, false), Space.p(), ListSubIndex.p(4, 1, true, false), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vld4, MemoryInstruction.Mode.ONE_REPEAT, 4, 1, true), Space.p(), ListSubIndex.p(4, 1, true, false), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128 }), WriteBack.p());

			append(new MemoryInstruction(EnumInstruction.vld4, MemoryInstruction.Mode.ONE_REPEAT, 4, 2, false), Space.p(), ListSubIndex.p(4, 2, true, false), Comma.p(), BaseAddress.p(new int[] { 8, 32 }));
			append(new MemoryInstruction(EnumInstruction.vld4, MemoryInstruction.Mode.ONE_REPEAT, 4, 2, false), Space.p(), ListSubIndex.p(4, 2, true, false), Comma.p(), BaseAddress.p(new int[] { 8, 32 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vld4, MemoryInstruction.Mode.ONE_REPEAT, 4, 2, true), Space.p(), ListSubIndex.p(4, 2, true, false), Comma.p(), BaseAddress.p(new int[] { 8, 32 }), WriteBack.p());
			append(new MemoryInstruction(EnumInstruction.vld4, MemoryInstruction.Mode.ONE_REPEAT, 4, 2, false), Space.p(), ListSubIndex.p(4, 2, true, false), Comma.p(), BaseAddress.p(new int[] { 8, 64 }));
			append(new MemoryInstruction(EnumInstruction.vld4, MemoryInstruction.Mode.ONE_REPEAT, 4, 2, false), Space.p(), ListSubIndex.p(4, 2, true, false), Comma.p(), BaseAddress.p(new int[] { 8, 64 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vld4, MemoryInstruction.Mode.ONE_REPEAT, 4, 2, true), Space.p(), ListSubIndex.p(4, 2, true, false), Comma.p(), BaseAddress.p(new int[] { 8, 64 }), WriteBack.p());
			append(new MemoryInstruction(EnumInstruction.vld4, MemoryInstruction.Mode.ONE_REPEAT, 4, 2, false), Space.p(), ListSubIndex.p(4, 2, true, false), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128 }));
			append(new MemoryInstruction(EnumInstruction.vld4, MemoryInstruction.Mode.ONE_REPEAT, 4, 2, false), Space.p(), ListSubIndex.p(4, 2, true, false), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vld4, MemoryInstruction.Mode.ONE_REPEAT, 4, 2, true), Space.p(), ListSubIndex.p(4, 2, true, false), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128 }), WriteBack.p());

			// VST: #vst1 #vst2 #vst3 #vst4
			// #vst1: ALL
			append(new MemoryInstruction(EnumInstruction.vst1, MemoryInstruction.Mode.ALL, 1, 1, false), Space.p(), ListSubIndex.p(1, 1, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64 }));
			append(new MemoryInstruction(EnumInstruction.vst1, MemoryInstruction.Mode.ALL, 1, 1, false), Space.p(), ListSubIndex.p(1, 1, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vst1, MemoryInstruction.Mode.ALL, 1, 1, true), Space.p(), ListSubIndex.p(1, 1, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64 }), WriteBack.p());
			append(new MemoryInstruction(EnumInstruction.vst1, MemoryInstruction.Mode.ALL, 2, 1, false), Space.p(), ListSubIndex.p(2, 1, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128 }));
			append(new MemoryInstruction(EnumInstruction.vst1, MemoryInstruction.Mode.ALL, 2, 1, false), Space.p(), ListSubIndex.p(2, 1, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vst1, MemoryInstruction.Mode.ALL, 2, 1, true), Space.p(), ListSubIndex.p(2, 1, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128 }), WriteBack.p());
			append(new MemoryInstruction(EnumInstruction.vst1, MemoryInstruction.Mode.ALL, 3, 1, false), Space.p(), ListSubIndex.p(3, 1, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64 }));
			append(new MemoryInstruction(EnumInstruction.vst1, MemoryInstruction.Mode.ALL, 3, 1, false), Space.p(), ListSubIndex.p(3, 1, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vst1, MemoryInstruction.Mode.ALL, 3, 1, true), Space.p(), ListSubIndex.p(3, 1, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64 }), WriteBack.p());
			append(new MemoryInstruction(EnumInstruction.vst1, MemoryInstruction.Mode.ALL, 4, 1, false), Space.p(), ListSubIndex.p(4, 1, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128, 256 }));
			append(new MemoryInstruction(EnumInstruction.vst1, MemoryInstruction.Mode.ALL, 4, 1, false), Space.p(), ListSubIndex.p(4, 1, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128, 256 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vst1, MemoryInstruction.Mode.ALL, 4, 1, true), Space.p(), ListSubIndex.p(4, 1, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128, 256 }), WriteBack.p());

			// vst1: ONE
			append(new MemoryInstruction(EnumInstruction.vst1, MemoryInstruction.Mode.ONE, 1, 1, false), Space.p(), ListSubIndex.p(1, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8 }));
			append(new MemoryInstruction(EnumInstruction.vst1, MemoryInstruction.Mode.ONE, 1, 1, false), Space.p(), ListSubIndex.p(1, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vst1, MemoryInstruction.Mode.ONE, 1, 1, true), Space.p(), ListSubIndex.p(1, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8 }), WriteBack.p());
			append(new MemoryInstruction(EnumInstruction.vst1, MemoryInstruction.Mode.ONE, 1, 1, false), Space.p(), ListSubIndex.p(1, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 16 }));
			append(new MemoryInstruction(EnumInstruction.vst1, MemoryInstruction.Mode.ONE, 1, 1, false), Space.p(), ListSubIndex.p(1, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 16 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vst1, MemoryInstruction.Mode.ONE, 1, 1, true), Space.p(), ListSubIndex.p(1, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 16 }), WriteBack.p());
			append(new MemoryInstruction(EnumInstruction.vst1, MemoryInstruction.Mode.ONE, 1, 1, false), Space.p(), ListSubIndex.p(1, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 32 }));
			append(new MemoryInstruction(EnumInstruction.vst1, MemoryInstruction.Mode.ONE, 1, 1, false), Space.p(), ListSubIndex.p(1, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 32 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vst1, MemoryInstruction.Mode.ONE, 1, 1, true), Space.p(), ListSubIndex.p(1, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 32 }), WriteBack.p());

			// vst2: ALL
			append(new MemoryInstruction(EnumInstruction.vst2, MemoryInstruction.Mode.ALL, 2, 1, false), Space.p(), ListSubIndex.p(2, 1, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128 }));
			append(new MemoryInstruction(EnumInstruction.vst2, MemoryInstruction.Mode.ALL, 2, 1, false), Space.p(), ListSubIndex.p(2, 1, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vst2, MemoryInstruction.Mode.ALL, 2, 1, true), Space.p(), ListSubIndex.p(2, 1, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128 }), WriteBack.p());
			append(new MemoryInstruction(EnumInstruction.vst2, MemoryInstruction.Mode.ALL, 2, 2, false), Space.p(), ListSubIndex.p(2, 2, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128 }));
			append(new MemoryInstruction(EnumInstruction.vst2, MemoryInstruction.Mode.ALL, 2, 2, false), Space.p(), ListSubIndex.p(2, 2, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vst2, MemoryInstruction.Mode.ALL, 2, 2, true), Space.p(), ListSubIndex.p(2, 2, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128 }), WriteBack.p());
			append(new MemoryInstruction(EnumInstruction.vst2, MemoryInstruction.Mode.ALL, 4, 1, false), Space.p(), ListSubIndex.p(4, 1, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128, 256 }));
			append(new MemoryInstruction(EnumInstruction.vst2, MemoryInstruction.Mode.ALL, 4, 1, false), Space.p(), ListSubIndex.p(4, 1, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128, 256 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vst2, MemoryInstruction.Mode.ALL, 4, 1, true), Space.p(), ListSubIndex.p(4, 1, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128, 256 }), WriteBack.p());

			// vst2: ONE
			append(new MemoryInstruction(EnumInstruction.vst2, MemoryInstruction.Mode.ONE, 2, 1, false), Space.p(), ListSubIndex.p(2, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 16 }));
			append(new MemoryInstruction(EnumInstruction.vst2, MemoryInstruction.Mode.ONE, 2, 1, false), Space.p(), ListSubIndex.p(2, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 16 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vst2, MemoryInstruction.Mode.ONE, 2, 1, true), Space.p(), ListSubIndex.p(2, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 16 }), WriteBack.p());
			append(new MemoryInstruction(EnumInstruction.vst2, MemoryInstruction.Mode.ONE, 2, 1, false), Space.p(), ListSubIndex.p(2, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 32 }));
			append(new MemoryInstruction(EnumInstruction.vst2, MemoryInstruction.Mode.ONE, 2, 1, false), Space.p(), ListSubIndex.p(2, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 32 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vst2, MemoryInstruction.Mode.ONE, 2, 1, true), Space.p(), ListSubIndex.p(2, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 32 }), WriteBack.p());
			append(new MemoryInstruction(EnumInstruction.vst2, MemoryInstruction.Mode.ONE, 2, 1, false), Space.p(), ListSubIndex.p(2, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 64 }));
			append(new MemoryInstruction(EnumInstruction.vst2, MemoryInstruction.Mode.ONE, 2, 1, false), Space.p(), ListSubIndex.p(2, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 64 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vst2, MemoryInstruction.Mode.ONE, 2, 1, true), Space.p(), ListSubIndex.p(2, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 64 }), WriteBack.p());

			append(new MemoryInstruction(EnumInstruction.vst2, MemoryInstruction.Mode.ONE, 2, 2, false), Space.p(), ListSubIndex.p(2, 2, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 32 }));
			append(new MemoryInstruction(EnumInstruction.vst2, MemoryInstruction.Mode.ONE, 2, 2, false), Space.p(), ListSubIndex.p(2, 2, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 32 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vst2, MemoryInstruction.Mode.ONE, 2, 2, true), Space.p(), ListSubIndex.p(2, 2, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 32 }), WriteBack.p());
			append(new MemoryInstruction(EnumInstruction.vst2, MemoryInstruction.Mode.ONE, 2, 2, false), Space.p(), ListSubIndex.p(2, 2, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 64 }));
			append(new MemoryInstruction(EnumInstruction.vst2, MemoryInstruction.Mode.ONE, 2, 2, false), Space.p(), ListSubIndex.p(2, 2, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 64 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vst2, MemoryInstruction.Mode.ONE, 2, 2, true), Space.p(), ListSubIndex.p(2, 2, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 64 }), WriteBack.p());

			// vst3: ALL
			append(new MemoryInstruction(EnumInstruction.vst3, MemoryInstruction.Mode.ALL, 3, 1, false), Space.p(), ListSubIndex.p(3, 1, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64 }));
			append(new MemoryInstruction(EnumInstruction.vst3, MemoryInstruction.Mode.ALL, 3, 1, false), Space.p(), ListSubIndex.p(3, 1, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vst3, MemoryInstruction.Mode.ALL, 3, 1, true), Space.p(), ListSubIndex.p(3, 1, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64 }), WriteBack.p());
			append(new MemoryInstruction(EnumInstruction.vst3, MemoryInstruction.Mode.ALL, 3, 2, false), Space.p(), ListSubIndex.p(3, 2, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64 }));
			append(new MemoryInstruction(EnumInstruction.vst3, MemoryInstruction.Mode.ALL, 3, 2, false), Space.p(), ListSubIndex.p(3, 2, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vst3, MemoryInstruction.Mode.ALL, 3, 2, true), Space.p(), ListSubIndex.p(3, 2, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64 }), WriteBack.p());

			// vst3: ONE
			append(new MemoryInstruction(EnumInstruction.vst3, MemoryInstruction.Mode.ONE, 3, 1, false), Space.p(), ListSubIndex.p(3, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8 }));
			append(new MemoryInstruction(EnumInstruction.vst3, MemoryInstruction.Mode.ONE, 3, 1, false), Space.p(), ListSubIndex.p(3, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vst3, MemoryInstruction.Mode.ONE, 3, 1, true), Space.p(), ListSubIndex.p(3, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8 }), WriteBack.p());
			append(new MemoryInstruction(EnumInstruction.vst3, MemoryInstruction.Mode.ONE, 3, 2, false), Space.p(), ListSubIndex.p(3, 2, true, true), Comma.p(), BaseAddress.p(new int[] { 8 }));
			append(new MemoryInstruction(EnumInstruction.vst3, MemoryInstruction.Mode.ONE, 3, 2, false), Space.p(), ListSubIndex.p(3, 2, true, true), Comma.p(), BaseAddress.p(new int[] { 8 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vst3, MemoryInstruction.Mode.ONE, 3, 2, true), Space.p(), ListSubIndex.p(3, 2, true, true), Comma.p(), BaseAddress.p(new int[] { 8 }), WriteBack.p());
			append(new MemoryInstruction(EnumInstruction.vst3, MemoryInstruction.Mode.ONE, 3, 2, false), Space.p(), ListSubIndex.p(3, 2, true, true), Comma.p(), BaseAddress.p(new int[] { 8 }));
			append(new MemoryInstruction(EnumInstruction.vst3, MemoryInstruction.Mode.ONE, 3, 2, false), Space.p(), ListSubIndex.p(3, 2, true, true), Comma.p(), BaseAddress.p(new int[] { 8 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vst3, MemoryInstruction.Mode.ONE, 3, 2, true), Space.p(), ListSubIndex.p(3, 2, true, true), Comma.p(), BaseAddress.p(new int[] { 8 }), WriteBack.p());

			// vst4: ALL
			append(new MemoryInstruction(EnumInstruction.vst4, MemoryInstruction.Mode.ALL, 4, 1, false), Space.p(), ListSubIndex.p(4, 1, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128, 256 }));
			append(new MemoryInstruction(EnumInstruction.vst4, MemoryInstruction.Mode.ALL, 4, 1, false), Space.p(), ListSubIndex.p(4, 1, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128, 256 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vst4, MemoryInstruction.Mode.ALL, 4, 1, true), Space.p(), ListSubIndex.p(4, 1, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128, 256 }), WriteBack.p());
			append(new MemoryInstruction(EnumInstruction.vst4, MemoryInstruction.Mode.ALL, 4, 2, false), Space.p(), ListSubIndex.p(4, 2, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128, 256 }));
			append(new MemoryInstruction(EnumInstruction.vst4, MemoryInstruction.Mode.ALL, 4, 2, false), Space.p(), ListSubIndex.p(4, 2, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128, 256 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vst4, MemoryInstruction.Mode.ALL, 4, 2, true), Space.p(), ListSubIndex.p(4, 2, false, false), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128, 256 }), WriteBack.p());

			// vst4: ONE
			append(new MemoryInstruction(EnumInstruction.vst4, MemoryInstruction.Mode.ONE, 4, 1, false), Space.p(), ListSubIndex.p(4, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 32 }));
			append(new MemoryInstruction(EnumInstruction.vst4, MemoryInstruction.Mode.ONE, 4, 1, false), Space.p(), ListSubIndex.p(4, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 32 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vst4, MemoryInstruction.Mode.ONE, 4, 1, true), Space.p(), ListSubIndex.p(4, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 32 }), WriteBack.p());
			append(new MemoryInstruction(EnumInstruction.vst4, MemoryInstruction.Mode.ONE, 4, 1, false), Space.p(), ListSubIndex.p(4, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 64 }));
			append(new MemoryInstruction(EnumInstruction.vst4, MemoryInstruction.Mode.ONE, 4, 1, false), Space.p(), ListSubIndex.p(4, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 64 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vst4, MemoryInstruction.Mode.ONE, 4, 1, true), Space.p(), ListSubIndex.p(4, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 64 }), WriteBack.p());
			append(new MemoryInstruction(EnumInstruction.vst4, MemoryInstruction.Mode.ONE, 4, 1, false), Space.p(), ListSubIndex.p(4, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128 }));
			append(new MemoryInstruction(EnumInstruction.vst4, MemoryInstruction.Mode.ONE, 4, 1, false), Space.p(), ListSubIndex.p(4, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vst4, MemoryInstruction.Mode.ONE, 4, 1, true), Space.p(), ListSubIndex.p(4, 1, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128 }), WriteBack.p());

			append(new MemoryInstruction(EnumInstruction.vst4, MemoryInstruction.Mode.ONE, 4, 2, false), Space.p(), ListSubIndex.p(4, 2, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 32 }));
			append(new MemoryInstruction(EnumInstruction.vst4, MemoryInstruction.Mode.ONE, 4, 2, false), Space.p(), ListSubIndex.p(4, 2, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 32 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vst4, MemoryInstruction.Mode.ONE, 4, 2, true), Space.p(), ListSubIndex.p(4, 2, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 32 }), WriteBack.p());
			append(new MemoryInstruction(EnumInstruction.vst4, MemoryInstruction.Mode.ONE, 4, 2, false), Space.p(), ListSubIndex.p(4, 2, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 64 }));
			append(new MemoryInstruction(EnumInstruction.vst4, MemoryInstruction.Mode.ONE, 4, 2, false), Space.p(), ListSubIndex.p(4, 2, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 64 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vst4, MemoryInstruction.Mode.ONE, 4, 2, true), Space.p(), ListSubIndex.p(4, 2, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 64 }), WriteBack.p());
			append(new MemoryInstruction(EnumInstruction.vst4, MemoryInstruction.Mode.ONE, 4, 2, false), Space.p(), ListSubIndex.p(4, 2, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128 }));
			append(new MemoryInstruction(EnumInstruction.vst4, MemoryInstruction.Mode.ONE, 4, 2, false), Space.p(), ListSubIndex.p(4, 2, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128 }), Comma.p(), ROffset.p());
			append(new MemoryInstruction(EnumInstruction.vst4, MemoryInstruction.Mode.ONE, 4, 2, true), Space.p(), ListSubIndex.p(4, 2, true, true), Comma.p(), BaseAddress.p(new int[] { 8, 64, 128 }), WriteBack.p());
			
			//SHIFT instructions
			//vqrshl
			append(new ShiftInstruction(EnumInstruction.vqrshl, EnumRegisterType.QUAD, false), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Q.p());
			
			//vshl
			append(new ShiftInstruction(EnumInstruction.vshl, EnumRegisterType.DOUBLE, true), Space.p(), D.p(), Comma.p(), D.p(), Comma.p(), Imm0toSizeMinus1.p());
			append(new ShiftInstruction(EnumInstruction.vshl, EnumRegisterType.QUAD, true), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Imm0toSizeMinus1.p());
			append(new ShiftInstruction(EnumInstruction.vshl, EnumRegisterType.DOUBLE, true), Space.p(), D.p(), Comma.p(), Imm0toSizeMinus1.p());
			append(new ShiftInstruction(EnumInstruction.vshl, EnumRegisterType.QUAD, true), Space.p(), Q.p(), Comma.p(), Imm0toSizeMinus1.p());
			
			append(new ShiftInstruction(EnumInstruction.vshl, EnumRegisterType.DOUBLE, false), Space.p(), D.p(), Comma.p(), D.p(), Comma.p(), D.p());
			append(new ShiftInstruction(EnumInstruction.vshl, EnumRegisterType.QUAD, false), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Q.p());
			append(new ShiftInstruction(EnumInstruction.vshl, EnumRegisterType.DOUBLE, false), Space.p(), D.p(), Comma.p(), D.p(), Comma.p(), D.p());
			append(new ShiftInstruction(EnumInstruction.vshl, EnumRegisterType.QUAD, false), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Q.p());
			append(new ShiftInstruction(EnumInstruction.vshl, EnumRegisterType.DOUBLE, false), Space.p(), D.p(), Comma.p(), D.p());
			append(new ShiftInstruction(EnumInstruction.vshl, EnumRegisterType.QUAD, false), Space.p(), Q.p(), Comma.p(), Q.p());
			append(new ShiftInstruction(EnumInstruction.vshl, EnumRegisterType.DOUBLE, false), Space.p(), D.p(), Comma.p(), D.p());
			append(new ShiftInstruction(EnumInstruction.vshl, EnumRegisterType.QUAD, false), Space.p(), Q.p(), Comma.p(), Q.p());
			
			//vshr
			append(new ShiftInstruction(EnumInstruction.vshr, EnumRegisterType.QUAD, true), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Imm1toSize.p());
			append(new ShiftInstruction(EnumInstruction.vshr, EnumRegisterType.QUAD, true), Space.p(), Q.p(), Comma.p(), Imm1toSize.p());
			
			append(new ShiftInstruction(EnumInstruction.vshr, EnumRegisterType.QUAD, true), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Imm1toSize.p());
			append(new ShiftInstruction(EnumInstruction.vshr, EnumRegisterType.QUAD, true), Space.p(), Q.p(), Comma.p(), Imm1toSize.p());
			
			//vsra
			append(new ShiftInstruction(EnumInstruction.vsra, EnumRegisterType.QUAD, true), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Imm1toSize.p());
			append(new ShiftInstruction(EnumInstruction.vsra, EnumRegisterType.QUAD, true), Space.p(), Q.p(), Comma.p(), Imm1toSize.p());
			
			append(new ShiftInstruction(EnumInstruction.vsra, EnumRegisterType.QUAD, true), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Imm1toSize.p());
			append(new ShiftInstruction(EnumInstruction.vsra, EnumRegisterType.QUAD, true), Space.p(), Q.p(), Comma.p(), Imm1toSize.p());
			
			//vrshl
			append(new ShiftInstruction(EnumInstruction.vrshl, EnumRegisterType.QUAD, false), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Q.p());
			append(new ShiftInstruction(EnumInstruction.vrshl, EnumRegisterType.QUAD, false), Space.p(), Q.p(), Comma.p(), Q.p());

			//vrshr
			append(new ShiftInstruction(EnumInstruction.vrshr, EnumRegisterType.QUAD, true), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Imm1toSize.p());
			append(new ShiftInstruction(EnumInstruction.vrshr, EnumRegisterType.QUAD, true), Space.p(), Q.p(), Comma.p(), Imm1toSize.p());
			
			//vrsra
			append(new ShiftInstruction(EnumInstruction.vrsra, EnumRegisterType.QUAD, true), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Imm1toSize.p());
			append(new ShiftInstruction(EnumInstruction.vrsra, EnumRegisterType.QUAD, true), Space.p(), Q.p(), Comma.p(), Imm1toSize.p());
			
			//vshll  //only VSHLL<c><q>.<type><size> <Qd>, <Dm>, #<imm>
			append(new ShiftInstruction(EnumInstruction.vshll, EnumRegisterType.DOUBLE, true), Space.p(), Q.p(), Comma.p(), D.p(), Comma.p(), Imm1toSize.p());
			append(new ShiftInstruction(EnumInstruction.vshll, EnumRegisterType.DOUBLE, true), Space.p(), Q.p(), Comma.p(), D.p(), Comma.p(), ImmIsSize.p());
			
			//vshrn  //only VSHRN<c><q>.I<size> <Dd>, <Qm>, #<imm>
			append(new ShiftInstruction(EnumInstruction.vshrn, EnumRegisterType.QUAD, true), Space.p(), D.p(), Comma.p(), Q.p(), Comma.p(), Imm1toHalfSize.p());
			
			//vrshrn  //VRSHRN<c><q>.I<size> <Dd>, <Qm>, #<imm>
			append(new ShiftInstruction(EnumInstruction.vrshrn, EnumRegisterType.QUAD, true), Space.p(), D.p(), Comma.p(), Q.p(), Comma.p(), Imm1toHalfSize.p());
			
			//vsli
			append(new ShiftInstruction(EnumInstruction.vsli, EnumRegisterType.QUAD, true), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Imm0toSizeMinus1.p());
			append(new ShiftInstruction(EnumInstruction.vsli, EnumRegisterType.QUAD, true), Space.p(), Q.p(), Comma.p(), Imm0toSizeMinus1.p());
			
			//vsri
			append(new ShiftInstruction(EnumInstruction.vsri, EnumRegisterType.QUAD, true), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Imm1toSize.p());
			append(new ShiftInstruction(EnumInstruction.vsri, EnumRegisterType.QUAD, true), Space.p(), Q.p(), Comma.p(), Imm1toSize.p());
			
			//VQSHL{u}
			append(new ShiftInstruction(EnumInstruction.vqshl, EnumRegisterType.QUAD, false), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Q.p());
			append(new ShiftInstruction(EnumInstruction.vqshl, EnumRegisterType.QUAD, true), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Imm0toSizeMinus1.p());
			append(new ShiftInstruction(EnumInstruction.vqshl, EnumRegisterType.QUAD, true), Space.p(), Q.p(), Comma.p(), Imm0toSizeMinus1.p());
			
			//vqrshr{U}n
			append(new ShiftInstruction(EnumInstruction.vqrshrn, EnumRegisterType.QUAD, true), Space.p(), D.p(), Comma.p(), Q.p(), Comma.p(), Imm1toHalfSize.p());
			append(new ShiftInstruction(EnumInstruction.vqrshrun, EnumRegisterType.QUAD, true), Space.p(), D.p(), Comma.p(), Q.p(), Comma.p(), Imm1toHalfSize.p());

			//vqshr{u}n
			append(new ShiftInstruction(EnumInstruction.vqshrn, EnumRegisterType.QUAD, true), Space.p(), D.p(), Comma.p(), Q.p(), Comma.p(), Imm1toHalfSize.p());
			append(new ShiftInstruction(EnumInstruction.vqshrun, EnumRegisterType.QUAD, true), Space.p(), D.p(), Comma.p(), Q.p(), Comma.p(), Imm1toHalfSize.p());
			
			//Comparison instructions
			//VACGE, VACGT, VACLE,VACLT
			append(new ComparisonInstruction(EnumInstruction.vacge, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Q.p());
			append(new ComparisonInstruction(EnumInstruction.vacge, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p());
			
			append(new ComparisonInstruction(EnumInstruction.vacgt, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Q.p());
			append(new ComparisonInstruction(EnumInstruction.vacgt, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p());
			
			append(new ComparisonInstruction(EnumInstruction.vacle, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Q.p());
			append(new ComparisonInstruction(EnumInstruction.vacle, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p());
			
			append(new ComparisonInstruction(EnumInstruction.vaclt, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Q.p());
			append(new ComparisonInstruction(EnumInstruction.vaclt, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p());
			
			//vceq reg
			append(new ComparisonInstruction(EnumInstruction.vceq, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Q.p());
			append(new ComparisonInstruction(EnumInstruction.vceq, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p());
			
			//vceq imm
			append(new ComparisonInstruction(EnumInstruction.vceq, EnumRegisterType.QUAD, true), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), ImmIsZero.p());
			append(new ComparisonInstruction(EnumInstruction.vceq, EnumRegisterType.QUAD, true), Space.p(), Q.p(), Comma.p(), ImmIsZero.p());
			
			//vcge reg
			append(new ComparisonInstruction(EnumInstruction.vcge, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Q.p());
			append(new ComparisonInstruction(EnumInstruction.vcge, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p());
			
			//vcge imm
			append(new ComparisonInstruction(EnumInstruction.vcge, EnumRegisterType.QUAD, true), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), ImmIsZero.p());
			append(new ComparisonInstruction(EnumInstruction.vcge, EnumRegisterType.QUAD, true), Space.p(), Q.p(), Comma.p(), ImmIsZero.p());
			
			//vcgt reg
			append(new ComparisonInstruction(EnumInstruction.vcgt, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Q.p());
			append(new ComparisonInstruction(EnumInstruction.vcgt, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p());
			
			//vcgt imm
			append(new ComparisonInstruction(EnumInstruction.vcgt, EnumRegisterType.QUAD, true), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), ImmIsZero.p());
			append(new ComparisonInstruction(EnumInstruction.vcgt, EnumRegisterType.QUAD, true), Space.p(), Q.p(), Comma.p(), ImmIsZero.p());
			
			//vcle imm
			append(new ComparisonInstruction(EnumInstruction.vcle, EnumRegisterType.QUAD, true), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), ImmIsZero.p());
			append(new ComparisonInstruction(EnumInstruction.vcle, EnumRegisterType.QUAD, true), Space.p(), Q.p(), Comma.p(), ImmIsZero.p());
			
			//vclt imm
			append(new ComparisonInstruction(EnumInstruction.vclt, EnumRegisterType.QUAD, true), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), ImmIsZero.p());
			append(new ComparisonInstruction(EnumInstruction.vclt, EnumRegisterType.QUAD, true), Space.p(), Q.p(), Comma.p(), ImmIsZero.p());
			
			//vtst reg
			append(new ComparisonInstruction(EnumInstruction.vtst, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Q.p());
			append(new ComparisonInstruction(EnumInstruction.vtst, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p());
		}
		
		private static void fillInstructions01(){

			// Multiply instructions
			//VMLA
			append(new MultiplyInstruction(EnumInstruction.vmla, EnumRegisterType.QUAD, false), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Q.p());
			append(new MultiplyInstruction(EnumInstruction.vmla, EnumRegisterType.QUAD, true), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), DsubRegForScalar.p());
			
			//VMLAL
			append(new MultiplyInstruction(EnumInstruction.vmlal, EnumRegisterType.QUAD, false), Space.p(), Q.p(), Comma.p(), D.p(), Comma.p(), D.p());
			append(new MultiplyInstruction(EnumInstruction.vmlal, EnumRegisterType.QUAD, true), Space.p(), Q.p(), Comma.p(), D.p(), Comma.p(), DsubRegForScalar.p());
			
			// VMLS
			append(new MultiplyInstruction(EnumInstruction.vmls, EnumRegisterType.QUAD, false), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Q.p());
			append(new MultiplyInstruction(EnumInstruction.vmls, EnumRegisterType.QUAD, true), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), DsubRegForScalar.p());
			
			//VMLSL
			append(new MultiplyInstruction(EnumInstruction.vmlsl, EnumRegisterType.QUAD, false), Space.p(), Q.p(), Comma.p(), D.p(), Comma.p(), D.p());
			append(new MultiplyInstruction(EnumInstruction.vmlsl, EnumRegisterType.QUAD, true), Space.p(), Q.p(), Comma.p(), D.p(), Comma.p(), DsubRegForScalar.p());
			
			// VMUL
			//quad
			append(new MultiplyInstruction(EnumInstruction.vmul, EnumRegisterType.QUAD, false), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Q.p());
			append(new MultiplyInstruction(EnumInstruction.vmul, EnumRegisterType.QUAD, true), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), DsubRegForScalar.p());
			
			//double
			append(new MultiplyInstruction(EnumInstruction.vmul, EnumRegisterType.QUAD, false), Space.p(), D.p(), Comma.p(), D.p(), Comma.p(), D.p());
			append(new MultiplyInstruction(EnumInstruction.vmul, EnumRegisterType.QUAD, true), Space.p(), D.p(), Comma.p(), D.p(), Comma.p(), DsubRegForScalar.p());
			
			// VMULL
			append(new MultiplyInstruction(EnumInstruction.vmull, EnumRegisterType.QUAD, false), Space.p(), Q.p(), Comma.p(), D.p(), Comma.p(), D.p());
			append(new MultiplyInstruction(EnumInstruction.vmull, EnumRegisterType.QUAD, true), Space.p(), Q.p(), Comma.p(), D.p(), Comma.p(), DsubRegForScalar.p());
			
			// VQDMLAL
			append(new MultiplyInstruction(EnumInstruction.vqdmlal, EnumRegisterType.QUAD, false), Space.p(), Q.p(), Comma.p(), D.p(), Comma.p(), D.p());
			append(new MultiplyInstruction(EnumInstruction.vqdmlal, EnumRegisterType.QUAD, true), Space.p(), Q.p(), Comma.p(), D.p(), Comma.p(), DsubRegForScalar.p());
			
			// VQDMLSL
			append(new MultiplyInstruction(EnumInstruction.vqdmlsl, EnumRegisterType.QUAD, false), Space.p(), Q.p(), Comma.p(), D.p(), Comma.p(), D.p());
			append(new MultiplyInstruction(EnumInstruction.vqdmlsl, EnumRegisterType.QUAD, true), Space.p(), Q.p(), Comma.p(), D.p(), Comma.p(), DsubRegForScalar.p());
			
			// VQDMULH
			//quad type
			append(new MultiplyInstruction(EnumInstruction.vqdmulh, EnumRegisterType.QUAD, false), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Q.p());
			append(new MultiplyInstruction(EnumInstruction.vqdmulh, EnumRegisterType.QUAD, true), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), DsubRegForScalar.p());
			
			//double
			append(new MultiplyInstruction(EnumInstruction.vqdmulh, EnumRegisterType.DOUBLE, false), Space.p(), D.p(), Comma.p(), D.p(), Comma.p(), D.p());
			append(new MultiplyInstruction(EnumInstruction.vqdmulh, EnumRegisterType.DOUBLE, true), Space.p(), D.p(), Comma.p(), D.p(), Comma.p(), DsubRegForScalar.p());
			
			// vqrdmulh
			//quad type
			append(new MultiplyInstruction(EnumInstruction.vqrdmulh, EnumRegisterType.QUAD, false), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Q.p());
			append(new MultiplyInstruction(EnumInstruction.vqrdmulh, EnumRegisterType.QUAD, true), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), DsubRegForScalar.p());
			
			//double
			append(new MultiplyInstruction(EnumInstruction.vqrdmulh, EnumRegisterType.DOUBLE, false), Space.p(), D.p(), Comma.p(), D.p(), Comma.p(), D.p());
			append(new MultiplyInstruction(EnumInstruction.vqrdmulh, EnumRegisterType.DOUBLE, true), Space.p(), D.p(), Comma.p(), D.p(), Comma.p(), DsubRegForScalar.p());
			
			
			// VQDMULL
			append(new MultiplyInstruction(EnumInstruction.vqdmull, EnumRegisterType.QUAD, false), Space.p(), Q.p(), Comma.p(), D.p(), Comma.p(), D.p());
			append(new MultiplyInstruction(EnumInstruction.vqdmull, EnumRegisterType.QUAD, true), Space.p(), Q.p(), Comma.p(), D.p(), Comma.p(), DsubRegForScalar.p());
			
			// ABSOLUTE AND NEGATE instructions
			//vaba			
			append(new AbsoluteAndNegateInstruction(EnumInstruction.vaba, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Q.p());
			
			//vabal
			append(new AbsoluteAndNegateInstruction(EnumInstruction.vabal, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), D.p(), Comma.p(), D.p());
			
			//vabd			
			append(new AbsoluteAndNegateInstruction(EnumInstruction.vabd, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Q.p());
			
			//vabdl
			append(new AbsoluteAndNegateInstruction(EnumInstruction.vabdl, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), D.p(), Comma.p(), D.p());
			
			//vabs			
			append(new AbsoluteAndNegateInstruction(EnumInstruction.vabs, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p());
			
			//vqabs
			append(new AbsoluteAndNegateInstruction(EnumInstruction.vqabs, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p());
			append(new AbsoluteAndNegateInstruction(EnumInstruction.vqabs, EnumRegisterType.DOUBLE), Space.p(), D.p(), Comma.p(), D.p());

			//vneg
			append(new AbsoluteAndNegateInstruction(EnumInstruction.vneg, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p());
			
			//vqneg
			append(new AbsoluteAndNegateInstruction(EnumInstruction.vqneg, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p());
			
			//CONVERSION INSTRUCTIONS
			//vcvt : fixed < - > single
			append(new ConversationInstruction(EnumInstruction.vcvt, EnumRegisterType.QUAD, true), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Imm1toSize.p());
			
			// vcvt: single < - > integer
			append(new ConversationInstruction(EnumInstruction.vcvt, EnumRegisterType.QUAD, false), Space.p(), Q.p(), Comma.p(), Q.p());
			
			//vcvt: half < - > single precision
			append(new ConversationInstruction(EnumInstruction.vcvt, EnumRegisterType.QUAD, false), Space.p(), Q.p(), Comma.p(), D.p());
			
			//COUNT INSTRUCTIONS
			//vcls
			append(new CountInstruction(EnumInstruction.vcls, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p());
			
			//vclz
			append(new CountInstruction(EnumInstruction.vclz, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p());
			
			//vcnt
			append(new CountInstruction(EnumInstruction.vcnt, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p());
			
			//ZIP INSTRUCTIONS
			//vzip
			append(new ZipInstruction(EnumInstruction.vzip, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p());
			
			//vuzip
			append(new ZipInstruction(EnumInstruction.vuzp, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p());
			
			//VECTOR TRANSPOSE
			//vtrn
			append(new VtrnInstruction(EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p());
			
			//TABLE INSTRUCTION
			//vtbl
			append(new TableInstruction(EnumInstruction.vtbl, EnumRegisterType.DOUBLE, 1), Space.p(), D.p(), Comma.p(), ListSubIndex.p(1, 1, false, false), Comma.p(), D.p());
			append(new TableInstruction(EnumInstruction.vtbl, EnumRegisterType.DOUBLE, 2), Space.p(), D.p(), Comma.p(), ListSubIndex.p(2, 1, false, false), Comma.p(), D.p());
			append(new TableInstruction(EnumInstruction.vtbl, EnumRegisterType.DOUBLE, 3), Space.p(), D.p(), Comma.p(), ListSubIndex.p(3, 1, false, false), Comma.p(), D.p());
			append(new TableInstruction(EnumInstruction.vtbl, EnumRegisterType.DOUBLE, 4), Space.p(), D.p(), Comma.p(), ListSubIndex.p(4, 1, false, false), Comma.p(), D.p());
			
			//vtbx
			append(new TableInstruction(EnumInstruction.vtbx, EnumRegisterType.DOUBLE, 1), Space.p(), D.p(), Comma.p(), ListSubIndex.p(1, 1, false, false), Comma.p(), D.p());
			append(new TableInstruction(EnumInstruction.vtbx, EnumRegisterType.DOUBLE, 2), Space.p(), D.p(), Comma.p(), ListSubIndex.p(2, 1, false, false), Comma.p(), D.p());
			append(new TableInstruction(EnumInstruction.vtbx, EnumRegisterType.DOUBLE, 3), Space.p(), D.p(), Comma.p(), ListSubIndex.p(3, 1, false, false), Comma.p(), D.p());
			append(new TableInstruction(EnumInstruction.vtbx, EnumRegisterType.DOUBLE, 4), Space.p(), D.p(), Comma.p(), ListSubIndex.p(4, 1, false, false), Comma.p(), D.p());
			
			//SWAP INSTRUCTION
			//vswp
			append(new VswpInstruction(EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p());
			
			//REVERSE INSTRUCTION
			//vrev32
			append(new ReverseInstruction(EnumInstruction.vrev16, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p());
			append(new ReverseInstruction(EnumInstruction.vrev32, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p());
			append(new ReverseInstruction(EnumInstruction.vrev64, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p());
			
			//MINIMUM AND MAXIMUM INSTRUCTION
			//vmin
			append(new MinimumAndMaximumInstruction(EnumInstruction.vmin, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Q.p());
			
			//vmax
			append(new MinimumAndMaximumInstruction(EnumInstruction.vmax, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Q.p());
			
			//vpmin
			append(new MinimumAndMaximumInstruction(EnumInstruction.vpmin, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Q.p());
			
			//vpmax
			// there is NO QUAD version!
			// TODO: really?
			append(new MinimumAndMaximumInstruction(EnumInstruction.vpmax, EnumRegisterType.DOUBLE), Space.p(), D.p(), Comma.p(), D.p(), Comma.p(), D.p());
			
			//RECIPROCAL ESTIMATE
			append(new ReciprocalSqrtReciprocalEstimate(EnumInstruction.vrecpe, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p());
			
			//vrsqrte
			append(new ReciprocalSqrtReciprocalEstimate(EnumInstruction.vrsqrte, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p());
			//FIXME: add testcases
			
			//RECIPROCAL (SQUARE ROOT) STEP
			//vrsqrts
			append(new ReciprocalSqrtReciprocalStep(EnumInstruction.vrsqrts, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Q.p());
			
			//vrecps
			append(new ReciprocalSqrtReciprocalStep(EnumInstruction.vrecps, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Q.p());
			
			//EXTRACT
			//vext
			append(new VextInstruction(EnumInstruction.vext, EnumRegisterType.QUAD), Space.p(), Q.p(), Comma.p(), Q.p(), Comma.p(), Q.p() , Comma.p(), ImmTimesDtDividedBy8IsMax15.p());
			
			//VMRS and VMSR
			append(new MoveFPSCAndRAPSR(EnumInstruction.vmrs), Space.p(), R.p(), Comma.p(), FPSCR.p());
			append(new MoveFPSCAndRAPSR(EnumInstruction.vmsr), Space.p(), FPSCR.p(), Comma.p(), R.p());
		}
		
		private static void append(Instruction create, Token... tokens) {
			EnumInstruction instruction = create.getInstructionName();
			List<InstructionForm> list = instructionList.get(instruction);
			if (list == null) {
				list = new ArrayList<InstructionForm>();
				instructionList.put(instruction, list);
			}

			Token[] formatsWithEnd = new Token[tokens.length + 1];
			for (int i = 0; i < tokens.length; i++) {
				formatsWithEnd[i] = tokens[i];
			}
			formatsWithEnd[tokens.length] = End.p();

			list.add(new InstructionForm(create, formatsWithEnd));
		}

		/***
		 * Returns a list of InstuctionForms for the given insturction name.
		 * 
		 * @param instruction
		 * @return List of instruction formats for the given instruction.
		 */
		public static List<InstructionForm> get(EnumInstruction instruction) {
			return instructionList.get(instruction);
		}
}
