package com.intbittech.responsemappers;

import java.util.HashMap;

public class ContainerResponse extends HashMap<String, BaseResponse> {

/**
* 
*/
private static final long serialVersionUID = 4692781098160660408L;

public ContainerResponse(BaseResponse data) {
this.put("d", data);
}
}