package com.oskarijt.myapplication.utils

import android.os.AsyncTask
import android.util.Log
import com.oskarijt.myapplication.BirdDatabase
import com.oskarijt.myapplication.BirdModel

class PersistNewBirdAsyncTask (private val db: BirdDatabase)
    : AsyncTask<BirdModel, Unit, Unit>() {

    override fun doInBackground(vararg params: BirdModel?) {
        if(params[0] != null) {
            val birdie = params[0] as BirdModel
            Log.d("BirdSaving", "$birdie")
            db.newBirdDao().insert(birdie)
        }
    }

    override fun onPostExecute(result: Unit?) {
        super.onPostExecute(result)
        Log.d("newBird", "New bird saved !")
    }
}