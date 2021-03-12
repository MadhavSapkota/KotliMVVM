/*
 * *
 *  * Created by Madhav Sapkota on 3/11/21 2:29 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 3/11/21 2:29 PM
 *
 */

package model


import di.DaggerApiComponent
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class CountriesService {
    @Inject
    lateinit var  api: CountriesApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getCountries() : Single<List<Country>>{
        return api.getCountries()
    }


}