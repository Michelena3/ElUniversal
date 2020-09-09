package com.personal.eluniversal.injection.network

import com.personal.eluniversal.models.DataModel
import io.reactivex.Observable
import retrofit2.http.GET

interface ElUniversalAPI {

    @GET("notes/eluniversal/mxm/json/espectaculos/0/10")
    fun getData() : Observable<List<DataModel>>

}