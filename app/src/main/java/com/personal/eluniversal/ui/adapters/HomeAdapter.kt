package com.personal.eluniversal.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.personal.eluniversal.R
import com.personal.eluniversal.base.BaseViewModel
import com.personal.eluniversal.databinding.HomeItemBinding
import com.personal.eluniversal.models.DataModel
import org.jetbrains.anko.AnkoLogger

class HomeAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>(), AnkoLogger{

    private lateinit var items: MutableList<Any>
    private lateinit var bindingItem: HomeItemBinding
    private lateinit var context: Context

    private var listenerClick: ((item: Any) -> Unit)? = null

    @Suppress("unused")
    fun setClickAction(listenerClick: (item: Any) -> Unit){
        this.listenerClick = listenerClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        bindingItem = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.home_item,
            parent,
            false
        )
        return HomeItemViewHolder(bindingItem)
    }

    override fun getItemCount(): Int {
        return if (::items.isInitialized) items.size else 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        when(holder) {
            is HomeItemViewHolder -> {
                holder.bind(items[position] as DataModel)
                bindingItem.root.setOnClickListener { listenerClick?.invoke(items[position]) }
                Glide.with(context)
                    .load((items[position] as DataModel).thumbnail)
                    .into(bindingItem.imagenNoticia)
            }
        }
    }

    fun setContext(context: Context){
        this.context = context
    }

    fun updateList(items: List<Any>){
        this.items = items.toMutableList()
        notifyDataSetChanged()
    }

    class HomeItemViewHolder(private val binding: HomeItemBinding) : RecyclerView.ViewHolder(binding.root){
        private val viewModel = HomeItemViewModel()
        fun bind(item: DataModel){
            binding.viewModel = viewModel
            viewModel.bind(item)
        }
    }
}

class HomeItemViewModel: BaseViewModel(), AnkoLogger {
    val tituloNoticia: MutableLiveData<String> = MutableLiveData()
    val tipoNoticia: MutableLiveData<String> = MutableLiveData()
    val descripcionNoticia: MutableLiveData<String> = MutableLiveData()
    val fechaNoticia: MutableLiveData<String> = MutableLiveData()
    val horaNoticia: MutableLiveData<String> = MutableLiveData()

    fun bind(item: DataModel){
        tituloNoticia.value = item.title
        tipoNoticia.value = item.main_section
        descripcionNoticia.value = item.body!!.removeRange(0,2)
        fechaNoticia.value = item.pupdate
        horaNoticia.value = item.pubtime!!.removeRange(5,8)
    }
}