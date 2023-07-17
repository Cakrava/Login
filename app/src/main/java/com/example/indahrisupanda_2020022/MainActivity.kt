package com.example.indahrisupanda_2020022

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.indahrisupanda_2020022.databinding.ActivityMainBinding
import com.example.indahrisupanda_2020022.ui.login.LoginActivity
import com.example.projek_login.util.pref

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.setupWithNavController(navController)
        navView.setOnNavigationItemSelectedListener { item ->
            if (item.itemId == R.id.navigation_dashboard) {
                val s = pref(this)
                if (s.getISlogin()) {
                    Log.d("TAG", "SUDAH LOGIN")
                    // Navigasi ke halaman navigation_akun
                    navController.navigate(R.id.navigation_dashboard)
                    true
                } else {
                    startActivity(Intent(this, LoginActivity::class.java))
                    Log.d("TAG", "Belum Login, pindah ke halaman login")
                    false
                }
            } else {
                Log.d("TAG", "onCreate yg Lain" + item.itemId)
                navController.navigate(item.itemId)
                true
            }
        }


        val button = findViewById<Button>(R.id.btn1)
        button.setOnClickListener {
            val sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
            val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

            val destinationActivity = if (isLoggedIn) {
                profilku::class.java
            } else {
                LoginActivity::class.java
            }

            val intent = Intent(this@MainActivity, destinationActivity)

            val loginStatusMessage = if (isLoggedIn) {
                "Pengguna sudah login!"
            } else {
                "Pengguna belum login!"
            }

            val navigationMessage = if (isLoggedIn) {
                "Menavigasikan ke halaman profil..."
            } else {
                "Menavigasikan ke halaman login..."
            }

            Toast.makeText(this@MainActivity, "Mengecek status login...", Toast.LENGTH_SHORT).show()
            Toast.makeText(this@MainActivity, loginStatusMessage, Toast.LENGTH_SHORT).show()
            Toast.makeText(this@MainActivity, navigationMessage, Toast.LENGTH_SHORT).show()

            startActivity(intent)
        }

        val btn = findViewById<Button>(R.id.btn2)
        btn.setOnClickListener {
            val intent = Intent(this, profilku::class.java)
            startActivity(intent)
        }


    }
}