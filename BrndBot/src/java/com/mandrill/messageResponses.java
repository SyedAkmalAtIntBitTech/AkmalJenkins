package com.mandrill;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class messageResponses {
	
	private ArrayList<messageResponse> messageResponseList;

	public messageResponses(String theString) throws JSONException {
		JSONArray array = new JSONArray(theString);
		ArrayList<messageResponse> responseArray = new ArrayList<messageResponse>(array.length());
		for (int i = 0; i < array.length(); i++) {
			JSONObject object = array.getJSONObject(i);
			messageResponse messageResponse = new messageResponse(object);
			responseArray.add(messageResponse);
		}
		setMessageResponseList(responseArray);
	}

	/**
	 * @return the messageResponseList
	 */
	public ArrayList<messageResponse> getMessageResponseList() {
		return messageResponseList;
	}

	/**
	 * @param messageResponseList the messageResponseList to set
	 */
	public void setMessageResponseList(
			ArrayList<messageResponse> messageResponseList) {
		this.messageResponseList = messageResponseList;
	}

}
