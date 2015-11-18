package com.mbellagamba.csvsync;

public class ArrayUtil {

	public static double[] strings2doubles(String[] strings) {
		double[] doubles = new double[strings.length];
		for(int i = 0; i < strings.length; i++) {
			try {
				doubles[i] = Double.parseDouble(strings[i]);
			} catch(NumberFormatException e) {
				doubles[i] = 0;
			}
		}
		return doubles;
	}
	
	public static String[] removeKey(String[] strings) {
		int resultLength = strings.length - 1;
		String[] result = new String[resultLength];
		System.arraycopy(strings, 1, result, 0, resultLength);
		return result;
	}
}
