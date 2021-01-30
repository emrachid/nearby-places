package com.example.findnearbyplaces.presentation.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.findnearbyplaces.R
import com.example.findnearbyplaces.data.model.place.Location
import com.example.findnearbyplaces.databinding.HomeActivityBinding
import com.example.findnearbyplaces.presentation.place.PlaceActivity
import com.example.findnearbyplaces.util.AlertDialog
import com.example.findnearbyplaces.util.PermissionControl
import com.google.android.gms.location.LocationServices

class HomeActivity : AppCompatActivity() {

    companion object {
        private var TAG = HomeActivity::class.java.simpleName

        const val KEY_LOCATION = "key_location"
        const val KEY_TYPE = "key_type"

        private const val BARS_TYPE = "bar"
        private const val CAFES_TYPE = "cafe"
        private const val RESTAURANT_TYPE = "restaurant"
    }

    private lateinit var binding: HomeActivityBinding
    private lateinit var location: Location

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.home_activity)

        binding.barsButton.setOnClickListener {
            showPlaces(BARS_TYPE)
        }
        binding.cafesButton.setOnClickListener {
            showPlaces(CAFES_TYPE)
        }
        binding.restaurantsButton.setOnClickListener {
            showPlaces(RESTAURANT_TYPE)
        }

        setButtonsState(false)
    }

    override fun onStart() {
        super.onStart()
        showLocation()
    }

    private fun setButtonsState(enable: Boolean) {
        binding.barsButton.isEnabled = enable
        binding.cafesButton.isEnabled = enable
        binding.restaurantsButton.isEnabled = enable
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        PermissionControl.onRequestPermissionsResult(this, requestCode, grantResults)
    }

    private fun showLocation() {
        if (!PermissionControl.checkPermissions(applicationContext)) {
            PermissionControl.requestPermissions(this)
        } else {
            getLastLocation()
        }
    }

    private fun showPlaces(type: String) {
        val intent = Intent(this, PlaceActivity::class.java)
        intent.putExtra(KEY_TYPE, type)
        intent.putExtra(KEY_LOCATION, location.toString())
        startActivity(intent)
    }

    /**
     * Provides a simple way of getting a device's location and is well suited for
     * applications that do not require a fine-grained location and that do not need location
     * updates. Gets the best and most recent location currently available, which may be null
     * in rare cases when a location is not available.
     *
     *
     * Note: this method should be called after location permission has been granted.
     *  The location permission is been handled by PermissionFragment
     */
    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationClient.lastLocation
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful && task.result != null) {
                    location = Location(task.result.latitude, task.result.longitude)
                    setButtonsState(true)
                    binding.locationTxt?.text = "Location: $location"
                } else {
                    Log.w(TAG, "getLastLocation:exception", task.exception)
                    AlertDialog.showSnackbar(this, getString(R.string.no_location_detected))
                }
            }
    }
}