package com.personal.eluniversal.injection.component

import com.personal.eluniversal.injection.module.NetworkModule
import com.personal.eluniversal.ui.viewModel.GeneralViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface ViewModelInjector {

    fun inject(vm: GeneralViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector
        fun networkModule(networkModule: NetworkModule): Builder
    }
}