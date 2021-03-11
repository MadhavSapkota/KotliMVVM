/*
 * *
 *  * Created by Madhav Sapkota on 3/10/21 5:40 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 3/10/21 5:37 PM
 *
 */
package viewmodel


import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Schedulers.newThread
import model.CountriesService
import model.Country
import java.util.concurrent.ScheduledExecutorService

class ListViewModel: ViewModel() {
    private val countriesService = CountriesService()
    private val disposable = CompositeDisposable()


    val countries = MutableLiveData<List<Country>>()
    val countryLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()


    fun refresh(){
        fetchCountries()

    }

    private fun fetchCountries(){
        loading.value =true
        disposable.add(
                countriesService.getCountries()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableSingleObserver<List<Country>>(){

                            override fun onSuccess(value: List<Country>?) {
                                countries.value = value
                                countryLoadError.value = false
                                loading.value = false
                            }
                            override fun onError(e: Throwable?) {
                                countryLoadError.value = true
                                loading.value = false

                            }

                        })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}