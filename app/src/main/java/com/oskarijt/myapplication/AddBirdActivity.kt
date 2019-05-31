package com.oskarijt.myapplication

import android.app.Activity
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.oskarijt.myapplication.utils.PersistNewBirdAsyncTask
import kotlinx.android.synthetic.main.activity_add_bird.*
import java.util.*

class AddBirdActivity : Activity() {

    // The bird to be saved is stored in this variable
    private var newBird: BirdModel? = null
    private var locationManager : LocationManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_bird)

        val spinner: Spinner = this.findViewById(R.id.spinnerRarity)

        // Create persistent LocationManager reference
        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?

        try {
            // Request location updates
            locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener);
        } catch(ex: SecurityException) {
            Log.d("myTag", "No location available")
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.bird_rarities,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter }

        btnSaveBird.setOnClickListener {
            saveBird()
        }

        btnCancel.setOnClickListener {
            cancel()
        }
    }

    private fun saveBird() {

        newBird = BirdModel(
            0,
            txtSpecies.text.toString(),
            txtNotes.text.toString(),
            spinnerRarity.selectedItem.toString(),
            txtLatitude.text.toString(),
            txtLongitude.text.toString(),
            Date().time)

        persistNewBird(newBird!!)
        finish()
    }

    // Persists the caught fish into database using AsyncTask run in a worker thread
    private fun persistNewBird(birdie: BirdModel) {
        val task = PersistNewBirdAsyncTask(BirdDatabase.get(this))
        task.execute(birdie)

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun cancel() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    //define the listener
    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            txtLatitude.text = location.latitude.toString()
            txtLongitude.text = location.longitude.toString()
        }
        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }
}