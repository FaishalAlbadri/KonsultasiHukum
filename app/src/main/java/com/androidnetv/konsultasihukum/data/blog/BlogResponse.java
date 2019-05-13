package com.androidnetv.konsultasihukum.data.blog;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class BlogResponse{

	@SerializedName("msg")
	private String msg;

	@SerializedName("blog")
	private List<BlogItem> blog;

	@SerializedName("status")
	private String status;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setBlog(List<BlogItem> blog){
		this.blog = blog;
	}

	public List<BlogItem> getBlog(){
		return blog;
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
			"BlogResponse{" + 
			"msg = '" + msg + '\'' + 
			",blog = '" + blog + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}