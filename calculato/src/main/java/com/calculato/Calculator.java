package com.calculato;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;

public class Calculator {

	/* Remove The delimiter and return numbers */
	private String parseNumbers(String inputText) throws UnknownAmountOfNumbers {
		Pattern patterCheckIfDelimiterIsPresent = Pattern.compile("^//([^d])+\\\\n");
		Matcher delimiter = patterCheckIfDelimiterIsPresent.matcher(inputText);
		if(delimiter.find()) {
			return inputText.replace(delimiter.group(),"");
		}
		return inputText;
	}
	
	/* The delimiter cannot be a digit */
	private String parseDelimiter(String inputText) throws UnknownAmountOfNumbers {
		//this pattern will match all the begining, delimiter include
		Pattern patterCheckIfDelimiterIsPresent = Pattern.compile("^//([^d])+\\\\n");
		Matcher delimiter = patterCheckIfDelimiterIsPresent.matcher(inputText);
		if(delimiter.find()) {
			return delimiter.group().replace("\\n","").replace("//", "");
		}
		return ";";
	}

	/*
	 * This take a number as string and return number as type int and the number of negative number present and that number included */
	private Num parseStringToNum(String inputText ){
		int num = 0, tmp, countNegative = 0;
		String negatives = "";
		String [] numbersText = inputText.split("\\n");
		for(String numberText: numbersText) {
			tmp = Integer.parseInt(numberText);
			if(tmp < 0) {
				countNegative ++;
				negatives = negatives + tmp +" ";
			}
			num = num + tmp;
		}
		return new Num(num, countNegative, negatives.trim());	
	}
	
	public int add(String inputText) throws UnknownAmountOfNumbers , NegativesNotAllowed{
		if(inputText == null || inputText.trim().length() == 0) {
			return 0;
		}
		String delimeter = parseDelimiter(inputText);
		String numsText = parseNumbers(inputText);
		
		String [] numbers = numsText.split(delimeter);
		if(numbers.length != 2) {
			throw new UnknownAmountOfNumbers("Unknown amount of numbers");
		}
		
		Num num1 = parseStringToNum(numbers[0]);
		Num num2 = parseStringToNum(numbers[1]);
		
		if(num1.getOccurence() + num2.getOccurence()  > 1) {
			throw new NegativesNotAllowed("negatives not allowed "+num1.getNegatives() + " "+  num2.getNegatives());
		}
		if(num1.getOccurence() + num2.getOccurence() > 0) {
			throw new NegativesNotAllowed("negatives not allowed");
		}
		return num1.getNum() + num2.getNum();
	}
	

	@Data
	@AllArgsConstructor
	private class Num{
		private int num;
		private int occurence;	
		private String negatives;	
	}
	
}
