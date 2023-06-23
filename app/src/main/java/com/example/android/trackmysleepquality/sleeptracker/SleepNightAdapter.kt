package com.example.android.trackmysleepquality.sleeptracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.convertDurationToFormatted
import com.example.android.trackmysleepquality.convertNumericQualityToString
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.databinding.ListItemSleepNightBinding

class SleepNightDiffCallback : DiffUtil.ItemCallback<SleepNight>() {
    override fun areItemsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        return oldItem.nightId == newItem.nightId
    }

    override fun areContentsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        return oldItem == newItem
    }
}
//class SleepNightAdapter: RecyclerView.Adapter<SleepNightAdapter.ViewHolder>() {
//    var data =  listOf<SleepNight>()
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }
class SleepNightAdapter : ListAdapter<SleepNight, SleepNightAdapter.ViewHolder>(SleepNightDiffCallback()) {
//    class ViewHolder(itemView: ListItemSleepNightBinding) : RecyclerView.ViewHolder(itemView){
//        val sleepLength: TextView = itemView.findViewById(R.id.sleep_length)
//        val quality: TextView = itemView.findViewById(R.id.quality_string)
//        val qualityImage: ImageView = itemView.findViewById(R.id.quality_image)
//        fun bind(item: SleepNight) {
//            val res = itemView.context.resources
//            sleepLength.text = convertDurationToFormatted(
//                item.startTimeMilli, item.endTimeMilli, res)
//            quality.text = convertNumericQualityToString(
//                item.sleepQuality, res)
//            qualityImage.setImageResource(when (item.sleepQuality) {
    class ViewHolder private constructor(val binding: ListItemSleepNightBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(item: SleepNight) {
        val res = itemView.context.resources
        binding.sleepLength.text = convertDurationToFormatted(
            item.startTimeMilli, item.endTimeMilli, res
        )
        binding.qualityString.text = convertNumericQualityToString(
            item.sleepQuality, res
        )
        binding.qualityImage.setImageResource(
            when (item.sleepQuality) {
                0 -> R.drawable.ic_sleep_0
                1 -> R.drawable.ic_sleep_1
                2 -> R.drawable.ic_sleep_2
                3 -> R.drawable.ic_sleep_3
                4 -> R.drawable.ic_sleep_4
                5 -> R.drawable.ic_sleep_5
                else -> R.drawable.ic_sleep_active
            })
        }

        companion object {
            fun from(parent: ViewGroup): RecyclerView.ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
//                val view = layoutInflater
//                    .inflate(R.layout.list_item_sleep_night, parent, false)
                val binding =
                    ListItemSleepNightBinding.inflate(layoutInflater, parent, false)
//                return ViewHolder(view)
//                return RecyclerView.ViewHolder(binding)
                return ViewHolder(binding)
            }
        }
    }
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){*
//        val sleepLength: TextView = itemView.findViewById(R.id.sleep_length)
//        val quality: TextView = itemView.findViewById(R.id.quality_string)
//        val qualityImage: ImageView = itemView.findViewById(R.id.quality_image)
//    }
//    override fun onCreateViewHolder(
//        parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        parent: ViewGroup, viewType: Int): ViewHolder {
//        val layoutInflater = LayoutInflater.from(parent.context)
//        val view = layoutInflater.inflate(R.layout.list_item_sleep_night, parent, false)
//        return ViewHolder.from(parent)
//    }
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder.from(parent) as ViewHolder
    }

//    private fun from(view: View) = ViewHolder(view)

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
//        val layoutInflater = LayoutInflater.from(parent.context)
//        val view = layoutInflater.inflate(R.layout.text_item_view, parent, false) as TextView
//        return TextItemViewHolder(view)
//    }

//    override fun getItemCount() = data.size

//    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
//        val item = data[position]
//        holder.textView.text = item.sleepQuality.toString()
//        if (item.sleepQuality <= 1) {
//            holder.textView.setTextColor(Color.RED) // red
//        } else {
//            // reset
//            holder.textView.setTextColor(Color.BLACK) // black
//        }
//    }
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//    val item = bind(position)
//    val res = holder.itemView.context.resources
//        holder.sleepLength.text = convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, res)
//        holder.quality.text= convertNumericQualityToString(item.sleepQuality, res)
//        holder.qualityImage.setImageResource(when (item.sleepQuality) {
//            0 -> R.drawable.ic_sleep_0
//            1 -> R.drawable.ic_sleep_1
//            2 -> R.drawable.ic_sleep_2
//            3 -> R.drawable.ic_sleep_3
//            4 -> R.drawable.ic_sleep_4
//            5 -> R.drawable.ic_sleep_5
//            else -> R.drawable.ic_sleep_active
//        })
//    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val item = data[position]
        val item = getItem(position)
        holder.bind(item)
    }

    private fun bind(holder: ViewHolder, item: SleepNight) {
        val res = holder.itemView.context.resources
//        holder.sleepLength.text = convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, res)
//        holder.quality.text = convertNumericQualityToString(item.sleepQuality, res)
//        holder.qualityImage.setImageResource(when (item.sleepQuality) {
        holder.binding.sleepLength.text = convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, res)
        holder.binding.qualityString.text = convertNumericQualityToString(item.sleepQuality, res)
        holder.binding.qualityImage.setImageResource(when (item.sleepQuality) {
            0 -> R.drawable.ic_sleep_0
            1 -> R.drawable.ic_sleep_1
            2 -> R.drawable.ic_sleep_2
            3 -> R.drawable.ic_sleep_3
            4 -> R.drawable.ic_sleep_4
            5 -> R.drawable.ic_sleep_5
            else -> R.drawable.ic_sleep_active
        })
    }
}