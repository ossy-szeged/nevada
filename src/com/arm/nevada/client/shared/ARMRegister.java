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

package com.arm.nevada.client.shared;

public enum ARMRegister {
	R0(0),
	R1(1),
	R2(2),
	R3(3),
	R4(4),
	R5(5),
	R6(6),
	R7(7),
	R8(8),
	R9(9),
	R10(10),
	R11(11),
	R12(12),
	R13(13),
	R14(14),
	R15(15),
	R16(16),
	R17(17),
	R18(18),
	R19(19),
	R20(20),
	R21(21),
	R22(22),
	R23(23),
	R24(24),
	R25(25),
	R26(26),
	R27(27),
	R28(28),
	R29(29),
	R30(30),
	R31(31),
	/**
	 * Also known as WSP/SP (Stack Pointer) or WZR/XZR (Zero Register)
	 */
	R32(32);
	/**
	 * Known as PC (Program Counter)
	 */

	private final int index;

	private ARMRegister(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}
}
