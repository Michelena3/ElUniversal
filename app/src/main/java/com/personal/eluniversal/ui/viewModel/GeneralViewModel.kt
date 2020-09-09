package com.personal.eluniversal.ui.viewModel

import androidx.lifecycle.MutableLiveData
import com.personal.eluniversal.base.BaseViewModel
import com.personal.eluniversal.injection.network.ElUniversalAPI
import com.personal.eluniversal.models.DataModel
import com.personal.eluniversal.ui.adapters.HomeAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.AnkoLogger
import javax.inject.Inject

class GeneralViewModel: BaseViewModel(), AnkoLogger {
    @Inject
    lateinit var elUniversalAPI: ElUniversalAPI
    private var subscription: Disposable? = null

    val responseDataModel: MutableLiveData<List<DataModel>> = MutableLiveData()
    val homeAdapter: HomeAdapter = HomeAdapter()

    fun doGetData(){
        subscription = elUniversalAPI.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {onRetrieveInfoStart() }
            .doOnTerminate { onRetrieveInfoFinish() }
            .subscribe(
                { r -> responseDataModel.value = r},
                { error -> onRetrieveInfoError(error.localizedMessage)}
            )
    }
}