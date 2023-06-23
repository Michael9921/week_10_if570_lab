package com.example.android.trackmysleepquality.sleeptracker
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.databinding.ListItemSleepNightBinding


class SleepNightDiffCallback : DiffUtil.ItemCallback<SleepNight>() {
    override fun areItemsTheSame(oldItem: SleepNight, newItem: SleepNight):
            Boolean {
        return oldItem.nightId == newItem.nightId
    }
    override fun areContentsTheSame(oldItem: SleepNight, newItem: SleepNight):
            Boolean {
        return oldItem == newItem
    }
}



//class SleepNightAdapter:ListAdapter<SleepNight, SleepNightAdapter.ViewHolder>(SleepNightDiffCallback()) {
    class SleepNightAdapter(val clickListener: SleepNightListener): ListAdapter<SleepNight, SleepNightAdapter.ViewHolder>(SleepNightDiffCallback()) {
        //    var data = listOf<SleepNight>()
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }
        class ViewHolder private constructor(val binding: ListItemSleepNightBinding) :
            RecyclerView.ViewHolder(binding.root){
//            fun bind(item: SleepNight) {
                fun bind(item: SleepNight, clickListener: SleepNightListener) {
                    binding.sleep = item
                    binding.executePendingBindings()
                    binding.clickListener = clickListener
                }

                companion object {
                    fun from(parent: ViewGroup): ViewHolder {
                        val layoutInflater = LayoutInflater.from(parent.context)
//                val view = layoutInflater.inflate(R.layout.list_item_sleep_night, parent, false)
                        val binding =
                            ListItemSleepNightBinding.inflate(layoutInflater, parent, false)
//                return ViewHolder(view)
                        return ViewHolder(binding)
                    }
                }
            }
            override fun onCreateViewHolder(
                parent: ViewGroup, viewType: Int): ViewHolder {
                return ViewHolder.from(parent)
            }
//    override fun getItemCount() = data.size

            override fun onBindViewHolder(holder: ViewHolder, position: Int) {
                val item = getItem(position)
//                holder.bind(item)
//        holder.bind(item)
                holder.bind(getItem(position)!!, clickListener)
            }

//    private fun bind(
//        position: Int,
//        holder: ViewHolder
//    ) {
//        val item = getItem(position)
//        val res = holder.itemView.context.resources
//        holder.binding.sleepLength.text = convertDurationToFormatted(
//            item.startTimeMilli,
//            item.endTimeMilli, res
//        )
//        holder.binding.qualityString.text = convertNumericQualityToString(item.sleepQuality, res)
//        holder.binding.qualityImage.setImageResource(
//            when (item.sleepQuality) {
//                0 -> R.drawable.ic_sleep_0
//                1 -> R.drawable.ic_sleep_1
//                2 -> R.drawable.ic_sleep_2
//                3 -> R.drawable.ic_sleep_3
//                4 -> R.drawable.ic_sleep_4
//                5 -> R.drawable.ic_sleep_5
//                else -> R.drawable.ic_sleep_active
//            }
//        )
//    }

        }

        class SleepNightListener(val clickListener: (sleepId: Long) -> Unit) {
            fun onClick(night: SleepNight) = clickListener(night.nightId)
        }
