package com.example.findnearbyplaces.presentation.place

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.findnearbyplaces.R
import com.example.findnearbyplaces.data.model.place.Location
import com.example.findnearbyplaces.databinding.PlaceActivityBinding
import com.example.findnearbyplaces.presentation.di.Injector
import com.example.findnearbyplaces.presentation.home.HomeActivity
import java.util.*
import javax.inject.Inject

class PlaceActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: PlaceViewModelFactory

    companion object {
        private var TAG = PlaceActivity::class.java.simpleName
    }

    private lateinit var binding: PlaceActivityBinding
    private lateinit var placeAdapter: PlaceAdapter
    private lateinit var placeViewModel: PlaceViewModel
    private lateinit var placeType: String
    private lateinit var location: Location

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.place_activity)

        (application as Injector).createPlaceSubComponent().inject(this)
        placeViewModel = ViewModelProvider(this, factory).get(PlaceViewModel::class.java)

        placeType = intent.extras?.getString(HomeActivity.KEY_TYPE)!!
        location = Location.parse(intent.extras?.getString(HomeActivity.KEY_LOCATION)!!)

        Log.d(TAG, "Type: $placeType - Location - $location")

        // Update status bar title
        supportActionBar?.title = placeType.toUpperCase(Locale.ROOT) + 'S'

        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.placeRecyclerView.layoutManager = LinearLayoutManager(this)
        placeAdapter = PlaceAdapter()
        binding.placeRecyclerView.adapter = placeAdapter

        binding.swipeRefresh.setOnRefreshListener {
            displayNearbyPlaces()
        }

        /**
         * Showing Swipe Refresh animation on activity create
         * As animation won't start on onCreate, post runnable is used
         */
        binding.swipeRefresh.post(Runnable {
            // Fetching data from server
            displayNearbyPlaces()
        })
    }

    private fun displayNearbyPlaces() {
        // Showing refresh animation before making http call
        binding.swipeRefresh.isRefreshing = true;

        val responseLiveData = placeViewModel.getNearByPlaces(placeType, location)
        responseLiveData.observe(this, {
            if (it != null) {
                placeAdapter.setList(it)
                placeAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(
                    applicationContext,
                    "No data available. Please check your network connection",
                    Toast.LENGTH_LONG
                ).show()
            }
            binding.swipeRefresh.isRefreshing = false;
        })
    }
}