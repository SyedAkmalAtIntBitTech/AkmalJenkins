package com.intbittech.utility;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.intbittech.responsemappers.OperationStatus;
import com.intbittech.responsemappers.OperationStatusType;

public class ErrorHandlingUtil {

	public static OperationStatus requestErrorValidation(BindingResult bindingResult) {
		OperationStatus operationStatus = new OperationStatus();
		if (bindingResult.hasErrors()) {
			operationStatus.setStatusCode(OperationStatusType.RequestError);
			ArrayList<String> arrayList = new ArrayList<String>();
			List<ObjectError> errors = bindingResult.getAllErrors();
			for (Iterator<ObjectError> it = errors.iterator(); it.hasNext();) {
				ObjectError error = (ObjectError) it.next();
				arrayList.add(error.getDefaultMessage());
			}
			operationStatus.setMessages(arrayList);
		}
		return operationStatus;
	}

	public static OperationStatus requestErrorValidation(String message) {
		OperationStatus operationStatus = new OperationStatus();
		operationStatus.setStatusCode(OperationStatusType.RequestError);
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add(message);
		operationStatus.setMessages(arrayList);

		return operationStatus;
	}

	public static OperationStatus dataErrorValidation(ArrayList<String> messages) {
		OperationStatus operationStatus = new OperationStatus();
		if (messages != null && messages.size() > 0) {
			operationStatus.setStatusCode(OperationStatusType.DataError);
			operationStatus.setMessages(messages);
		}
		return operationStatus;
	}

	public static OperationStatus dataErrorValidation(String message) {
		if (StringUtility.isEmpty(message)) {
			message = "An unknown error has occured";
		}
		ArrayList<String> messages = new ArrayList<>();
		if (!StringUtility.isEmpty(message)) {
			messages.add(message);
		}
		return dataErrorValidation(messages);
	}

	public static OperationStatus dataNoErrorValidation(ArrayList<String> messages) {
		OperationStatus operationStatus = new OperationStatus();
		if (messages != null && messages.size() > 0) {
			operationStatus.setStatusCode(OperationStatusType.Success);
			operationStatus.setMessages(messages);
		}
		return operationStatus;
	}

	public static OperationStatus dataNoErrorValidation(String message) {
		ArrayList<String> messages = new ArrayList<>();
		if (!StringUtility.isEmpty(message)) {
			messages.add(message);
		}
		return dataNoErrorValidation(messages);
	}

}
