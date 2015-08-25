/*
 * Copyright (C) 2015 University of Szeged
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

public enum EnumVectorRegisterType {

	_1D(".1D", 64, 64),
	_2D(".2D", 128, 64),

	_2S(".2S", 64, 32),
	_4S(".4S", 128, 32),

	_4H(".4H", 64, 16),
	_8H(".8H", 128, 16),

	_8B(".8B", 64, 8),
	_16B(".16B", 128, 8);
	
	public static final EnumVectorRegisterType[] all64bitvectorsBHSD = { _1D, _2S, _4H, _8B };
	public static final EnumVectorRegisterType[] all64bitvectorsBHS = { _2S, _4H, _8B };
	public static final EnumVectorRegisterType[] all128bitvectorsBHSD = { _2D, _4S, _8H, _16B };
	public static final EnumVectorRegisterType[] all128bitvectorsBHS = { _4S, _8H, _16B };
	public static final EnumVectorRegisterType[] allVectorsBHS = { _4S, _8H, _16B, _2S, _4H, _8B };
	public static final EnumVectorRegisterType[] allVectorsBHSD = { _2D,_4S, _8H, _16B, _2S, _4H, _8B };
	
	private final String assemblyName;
	private final Integer sizeOfTheVector;
	private final Integer sizeOfTheElement;
	
	private EnumVectorRegisterType(
			String assemblyName,
			Integer sizeOfTheVector,
			Integer sizeOfTheElement) {
		this.assemblyName = assemblyName;
		this.sizeOfTheVector = sizeOfTheVector;
		this.sizeOfTheElement = sizeOfTheElement;
	}

	public String getAssemblyName() {
		return assemblyName;
	}
	
	public Integer getElementSizeInBits() {
		return sizeOfTheElement;
	}

	public Integer getElementSizeInBytes() {
		return sizeOfTheElement / 8;
	}

	public Integer getVectorSizeInBits() {
		return sizeOfTheVector;
	}

	public Integer getVectorSizeInBytes() {
		return sizeOfTheVector / 8;
	}
}
