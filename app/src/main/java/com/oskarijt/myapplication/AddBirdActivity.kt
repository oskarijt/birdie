package com.oskarijt.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.oskarijt.myapplication.utils.PersistNewBirdAsyncTask
import kotlinx.android.synthetic.main.activity_add_bird.*
import java.util.*

class AddBirdActivity : Activity() {

    // The bird to be saved is stored in this variable
    private var newBird: BirdModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_bird)

        val spinner: Spinner = this.findViewById(R.id.spinnerRarity)

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
    }

    private fun saveBird() {

        newBird = BirdModel(
            0,
            txtSpecies.toString(),
            txtNotes.toString(),
            spinnerRarity.selectedItem.toString(),
            null,
            Date().time)

        persistNewBird(newBird!!)

    }

    // Persists the caught fish into database using AsyncTask run in a worker thread
    private fun persistNewBird(birdie: BirdModel) {
        val task = PersistNewBirdAsyncTask(BirdDatabase.get(this))
        task.execute(birdie)

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}