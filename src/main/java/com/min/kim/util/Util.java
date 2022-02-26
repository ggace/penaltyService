package com.min.kim.util;

public class Util {
	public static boolean isEmpty(Object object) {
		if(object == null) {
			return true;
		}
		if(object instanceof String) {
			if(object == "") {
				return true;
			}
		}
		
		return false;
		
	}
	
}
