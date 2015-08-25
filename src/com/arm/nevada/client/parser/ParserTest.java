package com.arm.nevada.client.parser;

import static org.junit.Assert.*;

import org.junit.Test;

import com.arm.nevada.client.interpreter.ArithmeticInstructions;
import com.arm.nevada.client.interpreter.ErrorInstruction;
import com.arm.nevada.client.interpreter.Instruction;
import com.arm.nevada.client.interpreter.LogicalInstruction;
import com.arm.nevada.client.interpreter.machine.Machine;

public class ParserTest {

	@Test
	public void test() {
		/*
		String x = "add v0.16B , v1.16B, v2.16B";
		Instruction i = Parser.Parse(x);
		
		assertTrue(i instanceof ErrorInstruction);
		assertEquals(((ErrorInstruction)i).getBestParsedLength(), 11);
		assertEquals(((ErrorInstruction)i).getErrorMessage(), "Invalid vector type");
		*/

		//String x = "uaddw v0.8h, v1.8h, v2.8b";
/*
		String x = "uaddw2 v0.8h, v1.8h, v2.16b";
		Instruction i = Parser.Parse(x);
		assertTrue(i instanceof ArithmeticInstructions);
		Machine m = new Machine(null);
		
		i.execute(m);
*/

/*
		String x = "addhn2 v0.16b, v1.8h, v2.8b";
		Instruction i = Parser.Parse(x);
		assertTrue(i instanceof ArithmeticInstructions);
		Machine m = new Machine(null);
		
		i.execute(m);
*/

/*
		String x = "uadalp v0.4h, v1.8b";
		Instruction i = Parser.Parse(x);
		assertTrue(i instanceof ArithmeticInstructions);
		Machine m = new Machine(null);
		
		i.execute(m);
*/

		String x = "and v0.8b, v1.8b, v2.8b";
		Instruction i = Parser.Parse(x);
		assertTrue(i instanceof LogicalInstruction);
		Machine m = new Machine(null);
		
		i.execute(m);


		

	}

}
