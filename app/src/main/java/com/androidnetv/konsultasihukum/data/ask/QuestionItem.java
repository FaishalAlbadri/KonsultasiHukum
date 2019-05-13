package com.androidnetv.konsultasihukum.data.ask;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class QuestionItem{

	@SerializedName("user_name")
	private String userName;

	@SerializedName("ask")
	private String ask;

	@SerializedName("id_ask")
	private String idAsk;

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserName(){
		return userName;
	}

	public void setAsk(String ask){
		this.ask = ask;
	}

	public String getAsk(){
		return ask;
	}

	public void setIdAsk(String idAsk){
		this.idAsk = idAsk;
	}

	public String getIdAsk(){
		return idAsk;
	}

	@Override
 	public String toString(){
		return 
			"QuestionItem{" + 
			"user_name = '" + userName + '\'' + 
			",ask = '" + ask + '\'' + 
			",id_ask = '" + idAsk + '\'' + 
			"}";
		}
}