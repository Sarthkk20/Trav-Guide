package com.project.travguide.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.travguide.data.model.OpenTripFeature
import com.project.travguide.databinding.ItemTouristSpotBinding

class TouristSpotAdapter(
    private var touristSpots: List<OpenTripFeature>
) : RecyclerView.Adapter<TouristSpotAdapter.TouristSpotViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newSpots: List<OpenTripFeature>) {
        touristSpots = newSpots
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TouristSpotViewHolder {
        val binding = ItemTouristSpotBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TouristSpotViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TouristSpotViewHolder, position: Int) {
        holder.bind(touristSpots[position])
    }

    override fun getItemCount(): Int = touristSpots.size

    inner class TouristSpotViewHolder(private val binding: ItemTouristSpotBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(spot: OpenTripFeature) {
            binding.spotName.text = spot.properties.name
            binding.spotDesc.text = spot.properties.wikipedia ?: "No description available"
        }
    }
}
