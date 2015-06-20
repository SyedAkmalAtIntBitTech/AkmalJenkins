package com.mandrill;

import java.util.HashMap;

public class recipientMetadata {

	private String rcpt;
	private HashMap<String, String> values;
	/**
	 * @return the rcpt
	 */
	public String getRcpt() {
		return rcpt;
	}
	/**
	 * @param rcpt the rcpt to set
	 */
	public void setRcpt(String rcpt) {
		this.rcpt = rcpt;
	}
	/**
	 * @return the values
	 */
	public HashMap<String, String> getValues() {
		return values;
	}
	/**
	 * @param values the values to set
	 */
	public void setValues(HashMap<String, String> values) {
		this.values = values;
	}
}
