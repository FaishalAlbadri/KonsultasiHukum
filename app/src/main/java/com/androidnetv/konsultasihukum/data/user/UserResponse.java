package com.androidnetv.konsultasihukum.data.user;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class UserResponse{

	@SerializedName("result")
	private boolean result;

	@SerializedName("msg")
	private String msg;

	@SerializedName("user")
	private List<UserItem> user;

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

	public void setUser(List<UserItem> user){
		this.user = user;
	}

	public List<UserItem> getUser(){
		return user;
	}

	@Override
 	public String toString(){
		return 
			"UserResponse{" + 
			"result = '" + result + '\'' + 
			",msg = '" + msg + '\'' + 
			",user = '" + user + '\'' + 
			"}";
		}
}