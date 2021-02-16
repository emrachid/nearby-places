package com.example.findnearbyplaces.presentation.place

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.findnearbyplaces.BuildConfig
import com.example.findnearbyplaces.R
import com.example.findnearbyplaces.data.model.Place
import com.example.findnearbyplaces.databinding.ListItemBinding

class PlaceAdapter: RecyclerView.Adapter<PlaceViewHolder>() {
    private val placeList = ArrayList<Place>()

    fun setList(places: List<Place>) {
        placeList.clear()
        placeList.addAll(places)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.list_item,
            parent,
            false)
        return PlaceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        holder.bind(placeList[position])
    }

    override fun getItemCount(): Int {
        return placeList.size
    }
}

class PlaceViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(place: Place) {
        binding.titleTextView.text = place.name
        binding.descriptionTextView.text = place.distance.toInt().toString() + " m"
        place.photos?.get(0)?.photo_reference?.let {
            val photoURL = "https://maps.googleapis.com/maps/api/place/photo?maxheight=200&photoreference=" +
                    it + "&key=${BuildConfig.GOOGLE_API_KEY}"
            Glide.with(binding.imageView.context).load(photoURL).into(binding.imageView)
        }
    }
}