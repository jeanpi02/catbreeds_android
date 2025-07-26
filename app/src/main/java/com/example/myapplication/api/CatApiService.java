package com.example.myapplication.api;

import com.example.myapplication.models.CatBreed;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CatApiService {
    
    // Endpoint para cargar todas las razas
    @GET("breeds")
    Call<List<CatBreed>> getBreeds(
        @Query("limit") int limit,
        @Query("page") int page
    );
} 