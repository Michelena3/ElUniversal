
package com.personal.eluniversal.ui.activity

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.orhanobut.hawk.Hawk
import com.personal.eluniversal.R
import com.personal.eluniversal.databinding.ActivityHomeBinding
import com.personal.eluniversal.models.DataModel
import com.personal.eluniversal.ui.viewModel.GeneralViewModel
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.startActivity

class HomeActivity : AppCompatActivity(), AnkoLogger {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: GeneralViewModel
    private var items: MutableList<Any> = mutableListOf()

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        viewModel = ViewModelProvider(this)[GeneralViewModel::class.java]
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        binding.viewModel = viewModel
        binding.rvHome.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        viewModel.homeAdapter.setContext(this)
        viewModel.doGetData()
        Hawk.init(this).build()

        viewModel.responseDataModel.observe(this, Observer {
            for (item in it ?: arrayListOf()){
                items.add(item)
            }
            viewModel.homeAdapter.updateList(items)
        })

        viewModel.homeAdapter.setClickAction {
            when (it) {
                is DataModel -> {
                    Hawk.put("idNoticia", it.id)
                    startActivity<NewsActivity>()
                }
            }
        }
    }
}