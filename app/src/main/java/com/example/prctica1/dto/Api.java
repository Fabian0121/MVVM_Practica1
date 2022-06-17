package com.example.prctica1.dto;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
   @GET("fact")
   public Call<Cat> datos();

}
