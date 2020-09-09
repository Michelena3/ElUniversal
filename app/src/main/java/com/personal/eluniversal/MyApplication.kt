package com.personal.eluniversal

import android.app.Application
import com.google.firebase.messaging.FirebaseMessaging
import com.orhanobut.hawk.Hawk

@Suppress("unused")
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Hawk.init(this).build()
    }
}