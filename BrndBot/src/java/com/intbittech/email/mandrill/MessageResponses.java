package com.intbittech.email.mandrill;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MessageResponses {

    private ArrayList<MessageResponse> messageResponseList;

    public MessageResponses(String theString) throws JSONException {
        JSONArray array = new JSONArray(theString);
        ArrayList<MessageResponse> responseArray = new ArrayList<MessageResponse>(array.length());
        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            MessageResponse messageResponse = new MessageResponse(object);
            responseArray.add(messageResponse);
        }
        setMessageResponseList(responseArray);
    }

    /**
     * @return the messageResponseList
     */
    public ArrayList<MessageResponse> getMessageResponseList() {
        return messageResponseList;
    }

    /**
     * @param messageResponseList the messageResponseList to set
     */
    public void setMessageResponseList(
            ArrayList<MessageResponse> messageResponseList) {
        this.messageResponseList = messageResponseList;
    }

}
