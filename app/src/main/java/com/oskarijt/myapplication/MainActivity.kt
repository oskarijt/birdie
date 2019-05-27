package com.oskarijt.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView



class MainActivity : AppCompatActivity() {


    private lateinit var recyclerView: RecyclerView
    private var birds : LiveData<List<BirdModel>> ? = null

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_recent -> {

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_alphabetical -> {

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_rarity -> {

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

        birds = db.newBirdDao().getAll()

        recyclerView.adapter = BirdListAdapter(birds)

    }


    private fun openAddBird() {
        val intent = Intent(this, AddBirdActivity::class.java)
        startActivity(intent)
    }


}
