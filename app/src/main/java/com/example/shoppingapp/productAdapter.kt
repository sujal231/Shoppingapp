package com.example.exam

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.shoppingapp.databinding.ProductitemBinding

class productAdapter : Adapter<productAdapter.newsHolder>() {

    var productlist = ArrayList<productmodel>()
    lateinit var context: Context

    class newsHolder(itemView: ProductitemBinding) : ViewHolder(itemView.root) {
        var binding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): newsHolder {
        context = parent.context
        var binding = ProductitemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return newsHolder(binding)
    }

    override fun getItemCount(): Int {
        return productlist.size
    }

    override fun onBindViewHolder(holder: newsHolder, position: Int) {
        holder.binding.apply {
            productlist.get(position).apply {
                txttitle.text = title
                txttext.text = description
                txtprice.text = price
                Glide.with(context).load(img).into(imgpost)

            }
        }
    }

    fun update(newslist: ArrayList<productmodel>) {
        this.productlist = newslist
        notifyDataSetChanged()


    }

    fun read(newslist: ArrayList<productmodel>) {
        this.productlist = newslist
    }
}