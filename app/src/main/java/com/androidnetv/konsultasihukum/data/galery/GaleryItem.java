package com.androidnetv.konsultasihukum.data.galery;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class GaleryItem{

	@SerializedName("galery_img")
	private String galeryImg;

	@SerializedName("galery_title")
	private String galeryTitle;

	@SerializedName("id_galery")
	private String idGalery;

	public void setGaleryImg(String galeryImg){
		this.galeryImg = galeryImg;
	}

	public String getGaleryImg(){
		return galeryImg;
	}

	public void setGaleryTitle(String galeryTitle){
		this.galeryTitle = galeryTitle;
	}

	public String getGaleryTitle(){
		return galeryTitle;
	}

	public void setIdGalery(String idGalery){
		this.idGalery = idGalery;
	}

	public String getIdGalery(){
		return idGalery;
	}

	@Override
 	public String toString(){
		return
        "GaleryItem{" +
            "galery_img = '" + galeryImg + '\'' +
            ",galery_title = '" + galeryTitle + '\'' +
			",id_galery = '" + idGalery + '\'' + 
			"}";
		}
}