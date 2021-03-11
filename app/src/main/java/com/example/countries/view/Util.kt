/*
 * *
 *  * Created by Madhav Sapkota on 3/11/21 4:32 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 3/11/21 4:32 PM
 *
 */

package com.example.countries.view

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.countries.R
import model.Country
import java.security.AccessControlContext

fun getProgressDrawable(context: Context) :CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }

}

fun ImageView.loadImage(uri:String?,progressDrawable:CircularProgressDrawable){
    val options  = RequestOptions()
            .placeholder(progressDrawable)
            .error(R.mipmap.ic_launcher_round)
    Glide.with(this.context)
            .setDefaultRequestOptions(options)
            .load(uri)
            .into(this)
}