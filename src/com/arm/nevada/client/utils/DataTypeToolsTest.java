package com.arm.nevada.client.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class DataTypeToolsTest {

	@Test
	public void testkeepOnlyTheSecondPart() {
		long input[] = {1,2,3,4};
		long expected[] = {3,4};
		assertArrayEquals(expected, DataTypeTools.keepOnlyTheSecondPart(input));
		
		input = new long[]{1,2,3,4,5};
		expected = new long[]{3,4,5};
		assertArrayEquals(expected, DataTypeTools.keepOnlyTheSecondPart(input));
		
		input = new long[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
		expected = new long[]{9,10,11,12,13,14,15,16};
		assertArrayEquals(expected, DataTypeTools.keepOnlyTheSecondPart(input));
	}

}
