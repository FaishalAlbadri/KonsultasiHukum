package com.androidnetv.konsultasihukum.api;

import com.androidnetv.konsultasihukum.data.RegisterResponse;
import com.androidnetv.konsultasihukum.data.Response;
import com.androidnetv.konsultasihukum.data.answer.AnswerResponse;
import com.androidnetv.konsultasihukum.data.ask.AskResponse;
import com.androidnetv.konsultasihukum.data.blog.BlogResponse;
import com.androidnetv.konsultasihukum.data.galery.GaleryResponse;
import com.androidnetv.konsultasihukum.data.user.UserResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface APIInterface {

  @FormUrlEncoded
  @POST("user_login.php")
  Call<UserResponse> getLogin(
      @Field("user_name") String username,
      @Field("user_password") String password);

  @FormUrlEncoded
  @POST("add_question.php")
  Call<Response> addQuestion(
      @Field("user_name") String username,
      @Field("ask") String ask);

  @FormUrlEncoded
  @POST("add_answer.php")
  Call<Response> addAnswer(
      @Field("user_name") String username,
      @Field("answer") String answer,
      @Field("id_ask") String ask);


  @FormUrlEncoded
  @POST("forget_account.php")
  Call<UserResponse> getFoget(
      @Field("user_mail") String mail);

  @FormUrlEncoded
  @POST("getAnswer.php")
  Call<AnswerResponse> getAnswer(
      @Field("id_ask") String id);

  @FormUrlEncoded
  @POST("user_register.php")
  Call<RegisterResponse> getRegister(
      @Field("user_name") String username,
      @Field("user_fullname") String fullname,
      @Field("user_password") String password,
      @Field("user_mail") String mail,
      @Field("user_birthday") String birthday,
      @Field("user_gender") String gender);

  @GET
  Call<BlogResponse> getBlog(@Url String url);

  @GET
  Call<AskResponse> getAsk(@Url String url);

  @GET
  Call<GaleryResponse> getGalery(@Url String url);

}
