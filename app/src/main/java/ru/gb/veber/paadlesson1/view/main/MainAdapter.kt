package ru.gb.veber.paadlesson1.view.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.gb.veber.paadlesson1.databinding.ActivityMainRecyclerviewItemBinding
import ru.gb.veber.paadlesson1.model.DataModel

class MainAdapter(
    private var onListItemClickListener: OnListItemClickListener,
    private var data: List<DataModel>?=null,
) :
    RecyclerView.Adapter<MainAdapter.RecyclerItemViewHolder>() {

    fun setData(data: List<DataModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(ActivityMainRecyclerviewItemBinding.inflate(LayoutInflater.from(
            parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data!!.get(position))
    }

    override fun getItemCount(): Int {
        return data!!.size
    }

    inner class RecyclerItemViewHolder(private var binging: ActivityMainRecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binging.root) {

        fun bind(data: DataModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                binging.headerTextviewRecyclerItem.text = data.text
                binging.descriptionTextviewRecyclerItem.text =
                    data.meanings?.get(0)?.translation?.translation
                itemView.setOnClickListener { openInNewWindow(data) }
            }
        }
    }

    private fun openInNewWindow(listItemData: DataModel) {
        onListItemClickListener.onItemClick(listItemData)
    }

    interface OnListItemClickListener {
        fun onItemClick(data: DataModel)
    }
}
