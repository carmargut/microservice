/**
 * 
 */
package com.carmargut.microservice.utils;

import java.util.Random;

/**
 * This creates the random parameters
 * 
 * @author carmargut
 *
 */
public class RandomStringGenerator {

	static final int IBAN_LENGTH = 34;
	static final int REFERENCE_LENGTH = 6;

	public static String createNewIBAN() {
		return randomString(IBAN_LENGTH);
	}

	public static String createNewReference() {
		return randomString(REFERENCE_LENGTH);
	}

	/**
	 * creates a random String
	 * 
	 * @param targetStringLength length of the String
	 * @return
	 */
	private static String randomString(int targetStringLength) {
		int leftLimit = 65; // letter 'A'
		int rightLimit = 90; // letter 'Z'
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		String generatedString = buffer.toString();
		return generatedString;
	}
}
