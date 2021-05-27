package com.example.bwlprojectlistsandmaps.DataService

import com.example.bwlprojectlistsandmaps.Model.CovidResponseDataClass
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {

    @GET
    suspend fun getCasosCovid(@Url url : String): Response<CovidResponseDataClass>

}