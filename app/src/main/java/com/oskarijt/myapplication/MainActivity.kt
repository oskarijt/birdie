package com.oskarijt.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var birdListAdapter : BirdListAdapter
    private lateinit var birds : List<BirdModel>

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_recent -> {
                val sortedBirds = ArrayList(birds)
                sortedBirds.sortBy { it.spotted_at }
                birds = sortedBirds.reversed().toList()
                recyclerView.adapter = BirdListAdapter(birds)

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_alphabetical -> {
                val sortedBirds = ArrayList(birds)
                sortedBirds.sortBy { it.species }
                birds = sortedBirds.toList()
                recyclerView.adapter = BirdListAdapter(birds)

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_rarity -> {
                val sortedBirds = ArrayList(birds)
                sortedBirds.sortBy { it.rarity }
                birds = sortedBirds.reversed().toList()
                recyclerView.adapter = BirdListAdapter(birds)

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mybutton, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId

        if (id == R.id.btnAddBird) {
            openAddBird()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        val db = BirdDatabase.get(this)

        thread(start = true) {
            birds = db.newBirdDao().getAll()
            recyclerView = findViewById(R.id.listBird)
            linearLayoutManager = LinearLayoutManager(this)
            recyclerView.layoutManager = linearLayoutManager
            birdListAdapter = BirdListAdapter(birds.reversed())
            recyclerView.adapter = birdListAdapter

        }
    }

    private fun openAddBird() {
        val intent = Intent(this, AddBirdActivity::class.java)
        startActivity(intent)
    }
}
