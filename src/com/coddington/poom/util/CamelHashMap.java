package com.coddington.poom.util;

import java.util.HashMap;

public class CamelHashMap extends HashMap<String, Object> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Object put(String key, Object value) {

		String newKey = key.toLowerCase();

		if (newKey.contains("_")) {

			int idx = newKey.indexOf("_");
			String before = newKey.substring(0, idx);
			String next = newKey.substring(idx + 1);
			String no1 = next.substring(0, 1);
			next = next.substring(1);
			next = no1.toUpperCase() + next;
			newKey = before + next;

		} // if end

		return super.put(newKey, value);
	}
}
