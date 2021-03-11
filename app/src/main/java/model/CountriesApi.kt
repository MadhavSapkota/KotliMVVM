/*
 * *
 *  * Created by Madhav Sapkota on 3/11/21 2:13 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 3/11/21 2:13 PM
 *
 */

package model

import io.reactivex.Single
import retrofit2.http.GET

interface CountriesApi {
    @GET("DevTides/countries/master/countriesV2.json")
    fun  getCountries() : Single<List<Country>>


}