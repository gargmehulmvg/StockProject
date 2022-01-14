package com.example.stocksapp.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stocksapp.R
import com.example.stocksapp.interfaces.IAdapterItemClickListener
import com.example.stocksapp.models.responses.DataItem

class StockAdapter(private val mList: ArrayList<DataItem?>?, private val mListener: IAdapterItemClickListener): RecyclerView.Adapter<StockAdapter.StockViewHolder>() {

    inner class StockViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val headingTextView: TextView = itemView.findViewById(R.id.headingTextView)
        val quantityTextView: TextView = itemView.findViewById(R.id.quantityTextView)
        val ltpValueTextView: TextView = itemView.findViewById(R.id.ltpValueTextView)
        val pnlValueTextView: TextView = itemView.findViewById(R.id.pnlValueTextView)
        val container: View = itemView.findViewById(R.id.container)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        val view = StockViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.stock_item, parent, false)
        )
        view.container.setOnClickListener { mListener.onAdapterItemClickListener(view.adapterPosition) }
        return view
    }

    override fun getItemCount(): Int = mList?.size ?: 0

    override fun onBindViewHolder(
        holder: StockViewHolder,
        position: Int
    ) {
        holder.apply {
            val item = mList?.get(position)
            headingTextView.text = item?.companyName
            quantityTextView.text = item?.quantity?.toString()
            var message = "₹${item?.ltp}"
            ltpValueTextView.text = message
            message = "₹${item?.close}"
            pnlValueTextView.text = message
        }
    }

}