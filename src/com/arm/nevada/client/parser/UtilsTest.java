package com.arm.nevada.client.parser;

import static org.junit.Assert.*;

import org.junit.Test;

public class UtilsTest extends R {

	@Test
	public void test() {
		Arguments a = new Arguments();
		assertEquals(parse("r0", 0, a).getMessage(), null);
		assertEquals(parse("r10", 0, a).getMessage(), null);
		assertEquals(parse("r15", 0, a).getMessage(), null);
		assertEquals(parse("lr", 0, a).getMessage(), null);
		assertEquals(parse("pc", 0, a).getMessage(), null);
		assertEquals(parse("sp", 0, a).getMessage(), null);
		assertEquals(parse("x", 0, a).getMessage(), "Arm core register needed");
		assertEquals(parse("r16", 0, a).getMessage(), "Arm core register needed");
	}

}
