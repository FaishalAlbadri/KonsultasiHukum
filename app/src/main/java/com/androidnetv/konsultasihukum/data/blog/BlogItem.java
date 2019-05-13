package com.androidnetv.konsultasihukum.data.blog;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class BlogItem{

	@SerializedName("blog_img")
	private String blogImg;

	@SerializedName("blog_news")
	private String blogNews;

	@SerializedName("blog_title")
	private String blogTitle;

	@SerializedName("id_blog")
	private String idBlog;

	@SerializedName("blog_upload")
	private String blogUpload;

	public void setBlogImg(String blogImg){
		this.blogImg = blogImg;
	}

	public String getBlogImg(){
		return blogImg;
	}

	public void setBlogNews(String blogNews){
		this.blogNews = blogNews;
	}

	public String getBlogNews(){
		return blogNews;
	}

	public void setBlogTitle(String blogTitle){
		this.blogTitle = blogTitle;
	}

	public String getBlogTitle(){
		return blogTitle;
	}

	public void setIdBlog(String idBlog){
		this.idBlog = idBlog;
	}

	public String getIdBlog(){
		return idBlog;
	}

	public void setBlogUpload(String blogUpload){
		this.blogUpload = blogUpload;
	}

	public String getBlogUpload(){
		return blogUpload;
	}

	@Override
 	public String toString(){
		return 
			"BlogItem{" + 
			"blog_img = '" + blogImg + '\'' + 
			",blog_news = '" + blogNews + '\'' + 
			",blog_title = '" + blogTitle + '\'' + 
			",id_blog = '" + idBlog + '\'' + 
			",blog_upload = '" + blogUpload + '\'' + 
			"}";
		}
}