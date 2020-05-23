package com.forcetechsoft.financeplanner.model;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    @POST("users/login")
    @FormUrlEncoded
    Single<LoginStatus> logIn(@Field("username") String username,
                              @Field("password") String password);
}
