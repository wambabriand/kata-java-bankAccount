package com.calculato;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class CalculatorTest {

	
	private Calculator underTest;
	
	@BeforeEach
	public void init() {
		underTest = new Calculator();
	}

	@Test
	public void testFirstCase1() throws UnknownAmountOfNumbers, NegativesNotAllowed {
		int sum = underTest.add(null);
		assertThat(sum).isEqualTo(0);
		sum = underTest.add("");
		assertThat(sum).isEqualTo(0);
		sum = underTest.add(" ");
		assertThat(sum).isEqualTo(0);	
	}

	@Test
	public void testFirstCase2() throws UnknownAmountOfNumbers, NegativesNotAllowed {
		int sum = underTest.add("1;1");
		assertThat(sum).isEqualTo(2);
	}
	
	@Test
	public void testFirstCase() throws UnknownAmountOfNumbers, NegativesNotAllowed {
		int sum = underTest.add("//;\\n2;3");
		assertThat(sum).isEqualTo(5);
		sum = underTest.add("//-\\n1-2");
		assertThat(sum).isEqualTo(3);
		sum = underTest.add("//-\\n1\n1-2");
		assertThat(sum).isEqualTo(4);
		
		
		Exception exception;
		exception = Assertions.assertThrows(UnknownAmountOfNumbers.class, ()->{ underTest.add("1;1;2"); });
		assertThat(exception.getMessage()).isEqualTo("Unknown amount of numbers");
		
		exception = Assertions.assertThrows(NegativesNotAllowed.class, ()->{ underTest.add("-1;-1"); });
		System.out.println(exception.getMessage());
		assertThat(exception.getMessage()).isEqualTo("negatives not allowed -1 -1");
		
	}
	
}
