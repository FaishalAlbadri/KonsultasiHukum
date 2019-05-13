package com.androidnetv.konsultasihukum.data.user;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class UserItem{

	@SerializedName("user_birthday")
	private String userBirthday;

	@SerializedName("user_mail")
	private String userMail;

	@SerializedName("user_password")
	private String userPassword;

	@SerializedName("user_name")
	private String userName;

	@SerializedName("user_gender")
	private String userGender;

	@SerializedName("id_user")
	private String idUser;

	@SerializedName("user_fullname")
	private String userFullname;

	public void setUserBirthday(String userBirthday){
		this.userBirthday = userBirthday;
	}

	public String getUserBirthday(){
		return userBirthday;
	}

	public void setUserMail(String userMail){
		this.userMail = userMail;
	}

	public String getUserMail(){
		return userMail;
	}

	public void setUserPassword(String userPassword){
		this.userPassword = userPassword;
	}

	public String getUserPassword(){
		return userPassword;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserName(){
		return userName;
	}

	public void setUserGender(String userGender){
		this.userGender = userGender;
	}

	public String getUserGender(){
		return userGender;
	}

	public void setIdUser(String idUser){
		this.idUser = idUser;
	}

	public String getIdUser(){
		return idUser;
	}

	public void setUserFullname(String userFullname){
		this.userFullname = userFullname;
	}

	public String getUserFullname(){
		return userFullname;
	}

	@Override
 	public String toString(){
		return 
			"UserItem{" + 
			"user_birthday = '" + userBirthday + '\'' + 
			",user_mail = '" + userMail + '\'' + 
			",user_password = '" + userPassword + '\'' + 
			",user_name = '" + userName + '\'' + 
			",user_gender = '" + userGender + '\'' + 
			",id_user = '" + idUser + '\'' + 
			",user_fullname = '" + userFullname + '\'' + 
			"}";
		}
}