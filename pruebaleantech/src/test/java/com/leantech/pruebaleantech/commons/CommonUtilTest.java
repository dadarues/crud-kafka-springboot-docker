package com.leantech.pruebaleantech.commons;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.junit.jupiter.api.Test;

public class CommonUtilTest {

	@Test
	void generateStringTest() {
		String wordOne = "Hola";
		String wordTwo = "Lean";
		String wordThree = "Tech";
		String wordFour = "!";

		String concatString = CommonUtil.generateString(wordOne, LeanTechConstants.COMMON_STRING_SPACE, wordTwo,
				LeanTechConstants.COMMON_STRING_SPACE, wordThree, wordFour);

		assertAll(() -> {
			assertNotNull(concatString);
			assertTrue(concatString.contains(wordOne));
			assertTrue(concatString.contains(wordTwo));
			assertTrue(concatString.contains(wordThree));
			assertTrue(concatString.contains(wordFour));
			assertEquals("Hola Lean Tech!", concatString);
		});
	}
	
	@Test
	void getDaysOfDifferenceBetweenTwoDatesTest() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
	    Date firstDate = sdf.parse("10/29/2021");
	    Date secondDate = sdf.parse("10/31/2021");
	    
	    Long difference = CommonUtil.getDaysOfDifferenceBetweenTwoDates(firstDate, secondDate);

		assertAll(() -> {
			assertNotNull(difference);
			assertEquals(3L, difference);
		});
	}
}
