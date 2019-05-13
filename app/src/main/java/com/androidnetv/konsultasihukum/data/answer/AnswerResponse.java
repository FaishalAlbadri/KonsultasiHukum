package com.androidnetv.konsultasihukum.data.answer;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class AnswerResponse{

	@SerializedName("result")
	private boolean result;

	@SerializedName("msg")
	private String msg;

	@SerializedName("theanswer")
	private List<TheanswerItem> theanswer;

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

	public void setTheanswer(List<TheanswerItem> theanswer){
		this.theanswer = theanswer;
	}

	public List<TheanswerItem> getTheanswer(){
		return theanswer;
	}

	@Override
 	public String toString(){
		return 
			"AnswerResponse{" + 
			"result = '" + result + '\'' + 
			",msg = '" + msg + '\'' + 
			",theanswer = '" + theanswer + '\'' + 
			"}";
		}
}