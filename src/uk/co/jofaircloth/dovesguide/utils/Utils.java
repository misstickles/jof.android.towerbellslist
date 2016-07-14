package uk.co.jofaircloth.dovesguide.utils;

import java.util.HashMap;
import java.util.Map;

public class Utils {

	private static final Map<String, String> countryCodes = new HashMap<String, String>();
	static {
		countryCodes.put("GB", "GB");
		countryCodes.put("Canada", "Can");
		countryCodes.put("New Zealand", "NZ");
		countryCodes.put("GB", "GB");
		countryCodes.put("GB", "GB");
		countryCodes.put("GB", "GB");
		countryCodes.put("GB", "GB");
	}
	
	public static double roundToDps(double value, int decimalPlaces) {
		int i = (int)((value * Math.pow(10, decimalPlaces)));
		return (((double) i) / Math.pow(10, decimalPlaces));
	}
	
	public static boolean isNumeric(String s) {
		if (s.matches("(\\d*)")) {
			return true;
		} else {
			return false;
		}
	}
	
}
