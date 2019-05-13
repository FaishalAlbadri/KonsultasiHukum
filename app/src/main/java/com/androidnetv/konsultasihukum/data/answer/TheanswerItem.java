package com.androidnetv.konsultasihukum.data.answer;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class TheanswerItem{

	@SerializedName("id_answer")
	private String idAnswer;

	@SerializedName("answer")
	private String answer;

	@SerializedName("user_name")
	private String userName;

	@SerializedName("id_ask")
	private String idAsk;

	public void setIdAnswer(String idAnswer){
		this.idAnswer = idAnswer;
	}

	public String getIdAnswer(){
		return idAnswer;
	}

	public void setAnswer(String answer){
		this.answer = answer;
	}

	public String getAnswer(){
		return answer;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserName(){
		return userName;
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
			"TheanswerItem{" + 
			"id_answer = '" + idAnswer + '\'' + 
			",answer = '" + answer + '\'' + 
			",user_name = '" + userName + '\'' + 
			",id_ask = '" + idAsk + '\'' + 
			"}";
		}
}