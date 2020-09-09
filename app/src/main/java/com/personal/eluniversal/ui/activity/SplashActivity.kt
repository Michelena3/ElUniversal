
package com.personal.eluniversal.ui.activity

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.personal.eluniversal.R
import com.personal.eluniversal.databinding.ActivitySplashBinding
import com.personal.eluniversal.ui.viewModel.GeneralViewModel
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity(), AnkoLogger {
    private lateinit var binding: ActivitySplashBinding
    private lateinit var viewModel: GeneralViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_splash
        )
        viewModel = ViewModelProvider(this)[GeneralViewModel::class.java]
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    override fun onResume() {
        super.onResume()
        startActivity<HomeActivity>()
    }
}