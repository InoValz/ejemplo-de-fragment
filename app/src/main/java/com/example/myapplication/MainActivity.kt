package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, FragmentBoton1())
                .commit()
        }

        val btnUno = findViewById<Button>(R.id.btnUno)
        val btnDos = findViewById<Button>(R.id.btnDos)

        btnUno.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, FragmentBoton1())
                .commit()
        }

        btnDos.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, FragmentBoton2())
                .commit()
        }
    }
}