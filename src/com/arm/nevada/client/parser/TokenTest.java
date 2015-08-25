package com.arm.nevada.client.parser;

import static org.junit.Assert.*;

import org.junit.Test;

public class TokenTest {

	@Test
	public void test() {
		Arguments a = new Arguments();
		assertEquals(D.p().parse("d0", 0, a).getMessage(), null);
		assertEquals(ListSubIndex.p(4,1,false,false).parse("{d0-d3}", 0, a).getMessage(), null);

		a = new Arguments();
		assertEquals(V64BHSD.p().parse("v13.1D", 0, a).getMessage(), null);
		assertEquals(V64BHSD.p().parse("v12.2S", 0, a).getMessage(), null);
		assertEquals(V64BHSD.p().parse("v11.4H", 0, a).getMessage(), null);
		assertEquals(V64BHSD.p().parse("v10.8B", 0, a).getMessage(), null);
		
		assertEquals(a.size(), 4);
		assertEquals(a.getRegisterIndex(0), 13);
		assertEquals(a.getVectorRegisterType(0), EnumVectorRegisterType._1D);
		
		assertEquals(a.getRegisterIndex(1), 12);
		assertEquals(a.getVectorRegisterType(1), EnumVectorRegisterType._2S);
		
		assertEquals(a.getRegisterIndex(2), 11);
		assertEquals(a.getVectorRegisterType(2), EnumVectorRegisterType._4H);
		
		assertEquals(a.getRegisterIndex(3), 10);
		assertEquals(a.getVectorRegisterType(3), EnumVectorRegisterType._8B);
		
		assertEquals(V64BHS.p().parse("v12.2S", 0, a).getMessage(), null);
		assertEquals(V64BHS.p().parse("v12.4H", 0, a).getMessage(), null);
		assertEquals(V64BHS.p().parse("v12.8B", 0, a).getMessage(), null);

		assertEquals(V128BHS.p().parse("v12.2D", 0, a).getMessage(), "Invalid vector type");
		assertEquals(V128BHS.p().parse("v12.4S", 0, a).getMessage(), null);
		assertEquals(V128BHS.p().parse("v12.8H", 0, a).getMessage(), null);
		assertEquals(V128BHS.p().parse("v12.16B", 0, a).getMessage(), null);

		assertEquals(V128BHSD.p().parse("v12.2D", 0, a).getMessage(), null);
		assertEquals(V128BHSD.p().parse("v12.4S", 0, a).getMessage(), null);
		assertEquals(V128BHSD.p().parse("v12.8H", 0, a).getMessage(), null);
		assertEquals(V128BHSD.p().parse("v12.16B", 0, a).getMessage(), null);

	}

}
