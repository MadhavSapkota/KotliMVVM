/*
 * *
 *  * Created by Madhav Sapkota on 3/11/21 2:29 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 3/11/21 2:29 PM
 *
 */

package model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CountriesService {
    private val BASE_URL = "https://raw.githubusercontent.com/"

    private val api: CountriesApi

    init {
        api = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(CountriesApi:: class.java)
    }

    fun getCountries() : Single<List<Country>>{
        return api.getCountries()
    }


}