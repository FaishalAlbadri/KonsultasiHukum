package com.androidnetv.konsultasihukum.data.galery;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class GaleryResponse{

	@SerializedName("msg")
	private String msg;

	@SerializedName("galery")
	private List<GaleryItem> galery;

	@SerializedName("status")
	private String status;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setGalery(List<GaleryItem> galery){
		this.galery = galery;
	}

	public List<GaleryItem> getGalery(){
		return galery;
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
			"GaleryResponse{" + 
			"msg = '" + msg + '\'' + 
			",galery = '" + galery + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}