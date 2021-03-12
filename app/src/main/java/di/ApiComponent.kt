/*
 * *
 *  * Created by Madhav Sapkota on 3/12/21 9:40 AM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 3/12/21 9:40 AM
 *
 */

package di

import dagger.Component
import model.CountriesService
import viewmodel.ListViewModel

@Component (modules = [ApiModule:: class])
interface ApiComponent {
     fun inject(service: CountriesService)
     fun inject(viewModel: ListViewModel)
}