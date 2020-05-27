package com.forcetechsoft.financeplanner.model;

import io.reactivex.Single;
import retrofit2.http.*;

import java.util.List;

public interface ApiService {

    @POST("users/login")
    @FormUrlEncoded
    Single<LoginStatus> logIn(@Field("username") String username,
                              @Field("password") String password);

    @Headers({"token-expiration-ignore: true"})
    @GET("users/mydata")
    Single<List<UserData>> myData(@Header("x-access-token") String jwtToken);

}
