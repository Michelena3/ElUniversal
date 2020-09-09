package com.personal.eluniversal.ui.activity

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.orhanobut.hawk.Hawk
import com.personal.eluniversal.BuildConfig
import com.personal.eluniversal.R
import com.personal.eluniversal.databinding.ActivityNewsBinding
import com.personal.eluniversal.ui.viewModel.GeneralViewModel
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.custom.asyncResult
import org.jetbrains.anko.error
import org.jetbrains.anko.sdk27.coroutines.onClick

class NewsActivity : AppCompatActivity(), AnkoLogger {
    private lateinit var binding: ActivityNewsBinding
    private lateinit var viewModel: GeneralViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_news)
        viewModel = ViewModelProvider(this)[GeneralViewModel::class.java]
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        binding.viewModel = viewModel
        Hawk.init(this).build()

        viewModel.doGetData()

        viewModel.responseDataModel.observe(this, Observer {
            for (item in it ?: arrayListOf()){
                if (item.id == Hawk.get("idNoticia", -1)) {
                    binding.tituloNoticia.text = item.title
                    binding.summaryNoticia.text = item.summary
                    binding.fuente.text = item.site
                    binding.fechaNoticia.text = item.pupdate
                    binding.horaNoticia.text = item.pubtime!!.removeRange(5,8)
                    binding.descripcionNoticia.text = item.body
                    binding.nombreReporte.text = item.author
                    Glide.with(this)
                        .load(item.thumbnail)
                        .into(binding.imagenNoticia)
                    binding.share.onClick {
                        val shareIntent = Intent(Intent.ACTION_SEND)
                        shareIntent.setType("text/plain")
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "El Universal")
                        val shareMessage = item.link + BuildConfig.APPLICATION_ID + "\n\n"
                        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                        startActivity(Intent.createChooser(shareIntent, "Escoje tu red social"))
                    }
                }
            }
        })
    }
}