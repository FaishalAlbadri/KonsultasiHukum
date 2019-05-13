package com.androidnetv.konsultasihukum.data;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Response{

	@SerializedName("result")
	private boolean result;

	@SerializedName("msg")
	private String msg;

	public void setResult(boolean result){
		this.result = result;
	}

	public boolean isResult(){
		return result;
	}

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"result = '" + result + '\'' + 
			",msg = '" + msg + '\'' + 
			"}";
		}
}