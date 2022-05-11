package com.example.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.navigation.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
            }
            R.id.search -> {
                Snackbar.make(binding.root, "Click the Search Menu Item", Snackbar.LENGTH_LONG).show()
            }
            R.id.refresh -> {
                Snackbar.make(binding.root, "Click the Refresh Menu Item", Snackbar.LENGTH_LONG).show()
            }
            R.id.save -> {
                Snackbar.make(binding.root, "Click the Save Menu Item", Snackbar.LENGTH_LONG).show()
            }
            R.id.delete -> {
                Snackbar.make(binding.root, "Click the Delete Menu Item", Snackbar.LENGTH_LONG).show()
            }
        }
        return true
    }
}