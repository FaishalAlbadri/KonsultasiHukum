package com.androidnetv.konsultasihukum.data.ask;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class AskResponse{

	@SerializedName("msg")
	private String msg;

	@SerializedName("question")
	private List<QuestionItem> question;

	@SerializedName("status")
	private String status;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setQuestion(List<QuestionItem> question){
		this.question = question;
	}

	public List<QuestionItem> getQuestion(){
		return question;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"AskResponse{" + 
			"msg = '" + msg + '\'' + 
			",question = '" + question + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}