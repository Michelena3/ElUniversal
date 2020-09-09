package com.personal.eluniversal.base

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.personal.eluniversal.injection.component.DaggerViewModelInjector
import com.personal.eluniversal.injection.component.ViewModelInjector
import com.personal.eluniversal.injection.module.NetworkModule
import com.personal.eluniversal.ui.viewModel.GeneralViewModel

abstract class BaseViewModel: ViewModel() {

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    private val enabledElement: MutableLiveData<Boolean> = MutableLiveData()

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
        loadingVisibility.value = View.GONE
        errorMessage.value = null
        enabledElement.value = true
    }

    private fun inject(){
        when(this){
            is GeneralViewModel -> injector.inject(this)
        }
    }

    protected fun onRetrieveInfoStart() {
        loadingVisibility.value = View.VISIBLE
        enabledElement.value = false
        errorMessage.value = null
    }

    protected fun onRetrieveInfoFinish(){
        loadingVisibility.value = View.GONE
        enabledElement.value = true
    }

    protected fun onRetrieveInfoError(error: String?){
        errorMessage.value = error
    }
}