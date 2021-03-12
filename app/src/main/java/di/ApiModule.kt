/*
 * *
 *  * Created by Madhav Sapkota on 3/12/21 9:34 AM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 3/12/21 9:34 AM
 *
 */

package di

import dagger.Module
import dagger.Provides
import model.CountriesApi
import model.CountriesService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule  {
    private val BASE_URL = "https://raw.githubusercontent.com/"

 @Provides
    fun provideCountryApi(): CountriesApi {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(CountriesApi:: class.java)

    }
   @Provides
    fun provideCountries():CountriesService{
        return CountriesService()
    }

}