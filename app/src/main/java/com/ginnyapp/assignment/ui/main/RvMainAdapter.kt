package com.ginnyapp.assignment.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ginnyapp.assignment.data.model.NumberModel
import com.ginnyapp.assignment.databinding.NumberItemBigBinding
import com.ginnyapp.assignment.databinding.NumberItemRegularBinding

class RvMainAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_REGULAR = 1
        const val VIEW_TYPE_BIG = 2
    }

    private val dataSet = ArrayList<DataTypeHolder>()

    private inner class ViewRegularViewHolder(private val itemBinding: NumberItemRegularBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        var textMsg = itemBinding.textNum

        fun bind(position: Int) {
            val rvModel = dataSet[position]
            textMsg.text = rvModel.textData
        }
    }

    private inner class ViewBigViewHolder(private val itemBinding: NumberItemBigBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        var textMsg = itemBinding.textNum

        fun bind(position: Int) {
            val rvModel = dataSet[position]
            textMsg.text = rvModel.textData
        }
    }

    fun setData(items: List<DataTypeHolder>) {
        this.dataSet.clear()
        this.dataSet.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_BIG) {
            val binding: NumberItemBigBinding =
                NumberItemBigBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ViewBigViewHolder(binding)
        }

        val binding: NumberItemRegularBinding =
            NumberItemRegularBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewRegularViewHolder(binding)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (dataSet[position].viewType === VIEW_TYPE_BIG) {
            (holder as ViewBigViewHolder).bind(position)
        } else {
            (holder as ViewRegularViewHolder).bind(position)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun getItemViewType(position: Int): Int {
        return dataSet[position].viewType
    }

}