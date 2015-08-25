/*
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
import com.arm.nevada.client.shared.Out;
import com.arm.nevada.client.shared.SpecialBits;
import com.arm.nevada.client.utils.DataTypeTools;

/*
 add,
 addhn,
 addl,
 addw,
 hadd,
 hsub,
 padal,
 padd,
 paddl,
 raddhn,
 rhadd,
 rsubhn,
 qadd,
 qsub,
 sub,
 subhn,
 subl,
 subw,
 */
public class ArithmeticInstructions extends Instruction {

	private int destionationIndex;
	private int source1Index;
	private int source2Index;
	private EnumRegisterType destinationRegisterType;
	private EnumRegisterType source1RegisterType;
	private EnumRegisterType source2RegisterType;
	private EnumInstruction instruction;
	private EnumDataType dataType;

	private boolean addElseSub = false;
	private boolean narrowAndHighHalf = false;
	private boolean longing = false;
	private boolean wide = false;
	private boolean halving = false;
	private boolean pairwise = false;
	private boolean accumulate = false;
	private boolean rounding = false;
	private boolean saturating = false;

	private boolean floatType = false;
	private boolean signed = false;
	private boolean secondPart = false;
	
	private int source1Size;
	private int source2Size;
	private int destSize;
	
	public ArithmeticInstructions(EnumInstruction instruction){
		this.instruction = instruction;
		initializeFlags();
	}

	private void initializeFlags() {
		switch (instruction) {

		case add:
			addElseSub = true;
			validArgumentListTypes = EnumArgumentListType.threeSameVectorRegisterBHSD; 
			break;
		case fadd:
			addElseSub = true;
			floatType = true;
			validArgumentListTypes = EnumArgumentListType.threeSameVectorRegisterFloat;
			break;
		case addhn:
			addElseSub = true;
			narrowAndHighHalf = true;
			validArgumentListTypes = EnumArgumentListType.validArgumentsForHN;
			break;
		case addhn2:
			addElseSub = true;
			narrowAndHighHalf = true;
			secondPart = true;
			validArgumentListTypes = EnumArgumentListType.validArgumentsForHNSP;
			break;
		case uaddl:
			longing = true;
			addElseSub = true;
			validArgumentListTypes = EnumArgumentListType.validArgumentsForL;
			break;
		case uaddl2:
			longing = true;
			addElseSub = true;
			secondPart = true;
			validArgumentListTypes = EnumArgumentListType.validArgumentsForLSP;
			break;
		case saddl:
			longing = true;
			addElseSub = true;
			signed = true;
			validArgumentListTypes = EnumArgumentListType.validArgumentsForL;
			break;
		case saddl2:
			longing = true;
			addElseSub = true;
			signed = true;
			secondPart = true;
			validArgumentListTypes = EnumArgumentListType.validArgumentsForLSP;
			break;
		case uaddw:
			wide = true;
			addElseSub = true;
			validArgumentListTypes = EnumArgumentListType.validArgumentsForW;
			break;
		case uaddw2:
			wide = true;
			addElseSub = true;
			secondPart = true;
			validArgumentListTypes = EnumArgumentListType.validArgumentsForWSP;
			break;
		case saddw:
			wide = true;
			addElseSub = true;
			signed = true;
			validArgumentListTypes = EnumArgumentListType.validArgumentsForW;
			break;
		case saddw2:
			wide = true;
			addElseSub = true;
			signed = true;
			secondPart = true;
			validArgumentListTypes = EnumArgumentListType.validArgumentsForWSP;
			break;
		case uhadd:
			halving = true;
			addElseSub = true;
			validArgumentListTypes = EnumArgumentListType.threeSameVectorRegisterBHS; 
			break;
		case shadd:
			halving = true;
			addElseSub = true;
			signed = true;
			validArgumentListTypes = EnumArgumentListType.threeSameVectorRegisterBHS;
			break;
		case uhsub:
			halving = true;
			addElseSub = false;
			validArgumentListTypes = EnumArgumentListType.threeSameVectorRegisterBHS;
			break;
		case shsub:
			halving = true;
			addElseSub = false;
			signed = true;
			validArgumentListTypes = EnumArgumentListType.threeSameVectorRegisterBHS;
			break;
		case uadalp:
			pairwise = true;
			accumulate = true;
			longing = true;
			addElseSub = true;
			validArgumentListTypes = EnumArgumentListType.validArgumentsForLP;
			break;
		case sadalp:
			pairwise = true;
			accumulate = true;
			longing = true;
			addElseSub = true;
			signed = true;
			validArgumentListTypes = EnumArgumentListType.validArgumentsForLP;
			break;
		case addp:
			pairwise = true;
			addElseSub = true;
			validArgumentListTypes = EnumArgumentListType.threeSameVectorRegisterBHSD; 
			break;
		case faddp:
			pairwise = true;
			addElseSub = true;
			floatType = true;
			validArgumentListTypes = EnumArgumentListType.threeSameVectorRegisterFloat;
			break;
		case uaddlp:
			pairwise = true;
			longing = true;
			addElseSub = true;
			validArgumentListTypes = EnumArgumentListType.validArgumentsForLP;
			break;
		case saddlp:
			pairwise = true;
			longing = true;
			addElseSub = true;
			signed = true;
			validArgumentListTypes = EnumArgumentListType.validArgumentsForLP;
			break;
		case raddhn:
			rounding = true;
			narrowAndHighHalf = true;
			addElseSub = true;
			validArgumentListTypes = EnumArgumentListType.validArgumentsForHN;
			break;
		case raddhn2:
			rounding = true;
			narrowAndHighHalf = true;
			addElseSub = true;
			secondPart = true;
			validArgumentListTypes = EnumArgumentListType.validArgumentsForHNSP;
			break;
		case urhadd:
			rounding = true;
			halving = true;
			addElseSub = true;
			validArgumentListTypes = EnumArgumentListType.threeSameVectorRegisterBHS;
			break;
		case srhadd:
			rounding = true;
			halving = true;
			addElseSub = true;
			signed = true;
			validArgumentListTypes = EnumArgumentListType.threeSameVectorRegisterBHS;
			break;
		case rsubhn:
			rounding = true;
			narrowAndHighHalf = true;
			addElseSub = false;
			validArgumentListTypes = EnumArgumentListType.validArgumentsForHN;
			break;
		case rsubhn2:
			rounding = true;
			narrowAndHighHalf = true;
			addElseSub = false;
			secondPart = true;
			validArgumentListTypes = EnumArgumentListType.validArgumentsForHNSP;
			break;
		case uqadd:
			saturating = true;
			addElseSub = true;
			validArgumentListTypes = EnumArgumentListType.threeSameVectorRegisterBHSD; 
			break;
		case sqadd:
			saturating = true;
			addElseSub = true;
			signed = true;
			validArgumentListTypes = EnumArgumentListType.threeSameVectorRegisterBHSD; 
			break;
		case uqsub:
			saturating = true;
			addElseSub = false;
			validArgumentListTypes = EnumArgumentListType.threeSameVectorRegisterBHSD; 
			break;
		case sqsub:
			saturating = true;
			addElseSub = false;
			signed = true;
			validArgumentListTypes = EnumArgumentListType.threeSameVectorRegisterBHSD; 
			break;
		case sub:
			addElseSub = false;
			validArgumentListTypes = EnumArgumentListType.threeSameVectorRegisterBHSD; 
			break;
		case fsub:
			addElseSub = false;
			floatType = true;
			validArgumentListTypes = EnumArgumentListType.threeSameVectorRegisterFloat;
			break;
		case subhn:
			narrowAndHighHalf = true;
			addElseSub = false;
			validArgumentListTypes = EnumArgumentListType.validArgumentsForHN;
			break;
		case subhn2:
			narrowAndHighHalf = true;
			addElseSub = false;
			secondPart = true;
			validArgumentListTypes = EnumArgumentListType.validArgumentsForHNSP;
			break;
		case usubl:
			longing = true;
			addElseSub = false;
			validArgumentListTypes = EnumArgumentListType.validArgumentsForL;
			break;
		case usubl2:
			longing = true;
			addElseSub = false;
			secondPart = true;
			validArgumentListTypes = EnumArgumentListType.validArgumentsForLSP;
			break;
		case ssubl:
			longing = true;
			addElseSub = false;
			signed = true;
			validArgumentListTypes = EnumArgumentListType.validArgumentsForL;
			break;
		case ssubl2:
			longing = true;
			addElseSub = false;
			signed = true;
			secondPart = true;
			validArgumentListTypes = EnumArgumentListType.validArgumentsForLSP;
			break;
		case usubw:
			wide = true;
			addElseSub = false;
			validArgumentListTypes = EnumArgumentListType.validArgumentsForW;
			break;
		case usubw2:
			wide = true;
			addElseSub = false;
			secondPart = true;
			validArgumentListTypes = EnumArgumentListType.validArgumentsForWSP;
			break;
		case ssubw:
			wide = true;
			addElseSub = false;
			signed = true;
			validArgumentListTypes = EnumArgumentListType.validArgumentsForW;
			break;
		case ssubw2:
			wide = true;
			addElseSub = false;
			signed = true;
			secondPart = true;
			validArgumentListTypes = EnumArgumentListType.validArgumentsForWSP;
			break;
		default:
			assert false : "invalid instruction";
			break;
		}
	}

	@Override
	public void bindArguments(Arguments arguments) {
		this.dataType = arguments.getType();
		
		//int x = arguments.getVectorRegisterType(0).getElementSizeInBytes();
		int x = arguments.getVectorRegisterType(0).getVectorSizeInBytes();
		if ( x == 8) {
			this.destinationRegisterType = EnumRegisterType.DOUBLE;
		} else if ( x == 16) {
			this.destinationRegisterType = EnumRegisterType.QUAD;
		}
		
		// TODO: remove the else case, explicit destination register is mandatory now!
		// but be careful: longing + pairwise instructions have only one source argument
		if (arguments.size() > 2) {
			this.destionationIndex = arguments.getRegisterIndex(0);
			this.source1Index = arguments.getRegisterIndex(1);
			this.source2Index = arguments.getRegisterIndex(2);
		} else {
			this.destionationIndex = arguments.getRegisterIndex(0);
			this.source1Index = arguments.getRegisterIndex(0);
			this.source2Index = arguments.getRegisterIndex(1);
		}

		//source2Size = dataType.getSizeInBits();
		// FIXME: sok esetben nem jó!
		//source2Size = arguments.getVectorRegisterType(1).getElementSizeInBits();

		// FIXME: dirty hack for <u|s>adalp and <u|s>addlp (there is only 1 argument)
		if (!(longing && pairwise)){
			source2Size = arguments.getVectorRegisterType(2).getElementSizeInBits();
		} else {
			source2Size = arguments.getVectorRegisterType(1).getElementSizeInBits();
		}
		
		if (narrowAndHighHalf) {
			source1RegisterType = EnumRegisterType.QUAD;
			source2RegisterType = EnumRegisterType.QUAD;
			source1Size = source2Size;
			destSize = source2Size / 2;
		} else if (wide) {
			source1RegisterType = EnumRegisterType.QUAD;
			
			if (secondPart) {
				source2RegisterType = EnumRegisterType.QUAD;
			} else {
				source2RegisterType = EnumRegisterType.DOUBLE; // FIXME: second part esetén QUAD, de csak a felso DOUBLE kell belole :-/
			}
			 
			source1Size = source2Size * 2;
			destSize = source2Size * 2;
		} else if (longing) {
			if (pairwise) {
				source1RegisterType = destinationRegisterType;
				source2RegisterType = destinationRegisterType;
				source1Size = source2Size;
			} else {
				source1RegisterType = EnumRegisterType.DOUBLE;
				source2RegisterType = EnumRegisterType.DOUBLE;
			}
			source1Size = source2Size;
			destSize = source2Size * 2;
		} else {
			source1RegisterType = destinationRegisterType;
			source2RegisterType = destinationRegisterType;
			source1Size = source2Size;
			destSize = source2Size;
		}
	}

	@Override
	public void execute(Machine machine) {
		NEONRegisterSet neonRS = machine.getNEONRegisterSet();
		long[] source1Parts = DataTypeTools.createPartListFromWordsLong(source1Size,
				neonRS.getRegisterValues(source1RegisterType, source1Index));
		long[] source2Parts = DataTypeTools.createPartListFromWordsLong(source2Size,
				neonRS.getRegisterValues(source2RegisterType, source2Index));
		long[] destParts = DataTypeTools.createPartListFromWordsLong(destSize,
				neonRS.getRegisterValues(destinationRegisterType, destionationIndex));

		if (secondPart) {
			if (wide){
				source2Parts = DataTypeTools.keepOnlyTheSecondPart(source2Parts);
			} else if (longing) {
				source1Parts = DataTypeTools.keepOnlyTheSecondPart(source1Parts);
				source2Parts = DataTypeTools.keepOnlyTheSecondPart(source2Parts);
			} else if (narrowAndHighHalf) {
				destParts = DataTypeTools.keepOnlyTheSecondPart(destParts);
			}
		}
		
		if (pairwise) {
			long[] s1p;
			long[] s2p;
			if (longing) {
				s1p = new long[source1Parts.length / 2];
				s2p = new long[source1Parts.length / 2];
				for (int i = 0; i < source1Parts.length / 2; i++) {
					s1p[i] = source2Parts[2 * i + 0];
					s2p[i] = source2Parts[2 * i + 1];
				}
			} else {
				s1p = new long[source1Parts.length];
				s2p = new long[source2Parts.length];
				int cntr = 0;
				for (int i = 0; i < source1Parts.length / 2; i++, cntr++) {
					s1p[cntr] = source1Parts[i * 2 + 0];
					s2p[cntr] = source1Parts[i * 2 + 1];
				}
				for (int i = 0; i < source2Parts.length / 2; i++, cntr++) {
					s1p[cntr] = source2Parts[i * 2 + 0];
					s2p[cntr] = source2Parts[i * 2 + 1];
				}
			}
			source1Parts = s1p;
			source2Parts = s2p;
		}

		assert source1Parts.length == source2Parts.length && source1Parts.length == destParts.length;

		if (floatType) {
			for (int i = 0; i < destParts.length; i++) {
				destParts[i] = calculateFloat(source1Parts[i], source2Parts[i], destParts[i]);
			}
		} else {
			for (int i = 0; i < destParts.length; i++) {
				destParts[i] = calculateInt(source1Parts[i], source2Parts[i], destParts[i], machine);
			}
		}
		
		//FIXME: if secondPart && narrowAndHighHalf - felso 64 bitet kell irni!
		int[] resultWords = DataTypeTools.createWordsFromOnePartPerLong(destSize, destParts);
		neonRS.setRegisterValues(destinationRegisterType, (narrowAndHighHalf && secondPart), destionationIndex, resultWords);
		machine.incrementPCBy4();
		if (narrowAndHighHalf && secondPart){
			machine.highlightNEONRegisterSecondPart(destinationRegisterType, destionationIndex);
		} else {
			highlightDestinationRegisters(machine);	
		}
	}

	private void highlightDestinationRegisters(Machine machine) {
		machine.highlightNEONRegister(destinationRegisterType, destionationIndex);
	}

	private long calculateInt(long s1, long s2, long dest, Machine machine) {
		// boolean addElseSub;
		// boolean narrow = false;
		// boolean highHalf = false;
		// boolean longing = false;
		// boolean wide = false;
		// boolean halving = false;
		// boolean pairwise = false;
		// boolean accumulate = false;
		// boolean rounding = false;
		// boolean saturating = false;

		long result = 0;
		
		if (signed) {
			s1 = DataTypeTools.extendToSingnedLong(s1, source1Size);
			s2 = DataTypeTools.extendToSingnedLong(s2, source2Size);
			dest = DataTypeTools.extendToSingnedLong(dest, destSize);
		}

		if (addElseSub) {
			result = s1 + s2;
		} else {
			result = s1 - s2;
		}

		if (rounding) {
			long rundingConst = 0;
			if (narrowAndHighHalf)
				rundingConst = 1l << (destSize - 1);
			else if (halving) {
				rundingConst = 1;
			} else
				assert false;
			result += rundingConst;
		}

		if (halving) {
			if (signed)
				result = result >> 1;
			else
				result = result >>> 1;
		}

		if (narrowAndHighHalf) {
			assert source1Size == source2Size;
			result = result >>> (source2Size / 2);
		} else if (wide || longing) {
			// nothing to do
		}

		if (accumulate) {
			// It's a mistake in the documentation A8.6.348 VPADAL
			// if (signed && result < 0) {
			// result = -result;
			// }
			result = dest + result;
		}

		if (saturating) {
			Out<Boolean> saturated = new Out<Boolean>();
			result = saturatingAddOrSubstract(s1, s2, destSize, saturated, addElseSub);
			if (saturated.getValue()) {
				int fpscr = machine.getSpecialRegisters().getFPSCR();
				fpscr = DataTypeTools.setBit(fpscr, true, SpecialBits.FPSCR_QC);
				machine.getSpecialRegisters().setFPSCR(fpscr, true);
			}
		}

		result = result & DataTypeTools.getBitmaskLong(destSize);
		return result;
	}

	private long calculateFloat(long s1, long s2, long dest) {
		float f1 = DataTypeTools.intToFloat(DataTypeTools.integerFromLong(s1)[0]);
		float f2 = DataTypeTools.intToFloat(DataTypeTools.integerFromLong(s2)[0]);
		float result;

		if (addElseSub) {
			result = f1 + f2;
		} else {
			result = f1 - f2;
		}

		long longResult = DataTypeTools.LongFromIntegers(DataTypeTools.FloatToInt(result), 0);
		return longResult;
	}

	private long saturatingAddOrSubstract(long x, long y, int size, Out<Boolean> saturated, boolean addElseSub) {
		if (signed)
			if (addElseSub)
				return saturatingAddSigned(x, y, size, saturated);
			else
				return saturatingSubSigned(x, y, size, saturated);
		else if (addElseSub)
			return saturatingAddUnsigned(x, y, size, saturated);
		else
			return saturatingSubUnsigned(x, y, size, saturated);
	}

	private long saturatingAddSigned(long x, long y, int size, Out<Boolean> saturated) {
		saturated.setValue(false);
		long max = DataTypeTools.getBitmaskLong(size - 1);
		long min = ~max;
		if (x == 0 || y == 0 || (x > 0 ^ y > 0)) {
			// zero+N or one pos, another neg = no problems
			return x + y;
		} else if (x > 0) {
			// both pos, can only overflow
			saturated.setValue(max - x < y);
			return max - x < y ? max : x + y;
		} else {
			// both neg, can only underflow
			saturated.setValue(min - x > y);
			return min - x > y ? min : x + y;
		}
	}

	private long saturatingAddUnsigned(long x, long y, int size, Out<Boolean> saturated) {
		long result = x + y;
		result = DataTypeTools.getBitmaskLong(size) & result;
		if (DataTypeTools.unsignedGreaterEqualThan(result, x)) {
			// ok
			saturated.setValue(false);
			return (x + y);
		} else {
			// saturated
			saturated.setValue(true);
			return DataTypeTools.getBitmaskLong(size);
		}
	}

	private long saturatingSubSigned(long x, long y, int size, Out<Boolean> saturated) {
		long max = DataTypeTools.getBitmaskLong(size - 1);
		long min = ~max;
		saturated.setValue(false);
		if (y == min) {
			if (x < 0) {
				saturated.setValue(true);
				return min;
			} else
				return x - y;
		}
		y = -y;

		if (x == 0 || y == 0 || (x > 0 ^ y > 0)) {
			// zero+N or one pos, another neg = no problems
			return x + y;
		} else if (x > 0) {
			// both pos, can only overflow
			saturated.setValue(max - x < y);
			return max - x < y ? max : x + y;
		} else {
			// both neg, can only underflow
			saturated.setValue(min - x > y);
			return min - x > y ? min : x + y;
		}
	}

	private long saturatingSubUnsigned(long x, long y, int size, Out<Boolean> saturated) {
		saturated.setValue(false);
		if (DataTypeTools.unsignedGreaterThan(y, x)) {
			saturated.setValue(true);
			return 0;
		} else {
			return (x - y);
		}
	}

	@Override
	public EnumInstruction getInstructionName() {
		return instruction;
	}

	@Override
	public Instruction create() {
		return new ArithmeticInstructions(this.instruction);
	}

	@Override
	public EnumDataType getDataType() {
		return dataType;
	}
}
