/*
 * *
 *  * Created by Madhav Sapkota on 3/10/21 5:39 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 3/10/21 3:25 PM
 *
 */



package model

import com.google.gson.annotations.SerializedName

data class Country(
       @SerializedName("name")
        val countryName: String?,

       @SerializedName("capital")
       val capital : String?,

       @SerializedName("flagPNG")
       val flag : String?
)


