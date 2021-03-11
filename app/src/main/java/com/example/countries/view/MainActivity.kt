/*
 * *
 *  * Created by Madhav Sapkota on 3/10/21 5:39 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 3/10/21 3:18 PM
 *
 */


package com.example.countries.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countries.R
import kotlinx.android.synthetic.main.activity_main.*
import model.Country
import viewmodel.ListViewModel

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: ViewModel
    private val countriesAdapter = CountryListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ListViewModel ::class.java)
        (viewModel as ListViewModel).refresh()

        countriesList.apply{
            layoutManager = LinearLayoutManager(context)
            adapter = countriesAdapter
        }
        observerViewModel()
    }

    private fun observerViewModel() {
        (viewModel as ListViewModel).countries.observe(this, Observer { countries: List<Country>? ->
            countries?.let {
                countriesList.visibility = View.VISIBLE
                countriesAdapter.updateCountries(it)
            }

        })
        (viewModel as ListViewModel).countryLoadError.observe(this, Observer { isError ->
            isError?.let { list_error.visibility = if(it) View.VISIBLE else View.GONE }
        })

        (viewModel as ListViewModel).loading.observe(this, Observer {isLoading ->
            isLoading?.let{
                loading_view.visibility = if(it) View.VISIBLE else View.GONE
                if(it) {
                    list_error.visibility = View.GONE
                    countriesList.visibility = View.GONE
                }

            }
        })

    }

}