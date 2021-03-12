/*
 * *
 *  * Created by Madhav Sapkota on 3/12/21 12:11 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 3/12/21 12:11 PM
 *
 */

package com.example.countries

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.assertEquals
import model.CountriesService
import model.Country
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import viewmodel.ListViewModel
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

class LiveViewModelTest {
    @get:Rule
    var rule: InstantTaskExecutorRule()

     @Mock
     lateinit var countriesService: CountriesService

    @InjectMocks
    var listViewModel = ListViewModel()

    private var testSingle: Single<List<Country>>? = null

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
    }
   @Test
    fun getCountriesSuccess(){
        val country = Country("countryName","capital","url")
        val countriesList = arrayListOf(country)
       testSingle = Single.just(countriesList)
       `when`(countriesService.getCountries()).thenReturn(testSingle)
       listViewModel.refresh()
       Assert.assertEquals(1,listViewModel.countries.value?.size)
       Assert.assertEquals(false,listViewModel.countryLoadError)
       Assert.assertEquals(false,listViewModel.loading.value)









    }


     @Before
     fun setUpRxScheduler(){
         val immediate = object : Scheduler() {
             override fun scheduleDirect(run: Runnable?, delay: Long, unit: TimeUnit?): Disposable {
                 return super.scheduleDirect(run, 0, unit)
             }
             override fun createWorker(): Worker {
                 return ExecutorScheduler.ExecutorWorker(Executor {
                     it.run()
                 })
             }

         }
         RxJavaPlugins.setInitIoSchedulerHandler{ schedular ->immediate }
         RxJavaPlugins.setInitComputationSchedulerHandler{schedular -> immediate}
         RxJavaPlugins.setInitComputationSchedulerHandler{schedular -> immediate}
         RxJavaPlugins.setInitSingleSchedulerHandler{schedular ->immediate}
         RxAndroidPlugins.setInitMainThreadSchedulerHandler { schedular -> immediate}

     }


}