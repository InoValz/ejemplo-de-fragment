package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NOMBRE = "extra_nombre"
        const val EXTRA_CORREO = "extra_correo"
    }

    private val formularioLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK && result.data != null) {
                val nombre = result.data!!.getStringExtra(EXTRA_NOMBRE).orEmpty()
                val correo = result.data!!.getStringExtra(EXTRA_CORREO).orEmpty()

                // Guarda persistente
                Prefs.save(this, nombre, correo)

                // Muestra en el fragment
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_view, FragmentBoton2.newInstance(nombre, correo))
                    .commit()
            }
        }

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
        val btnTres = findViewById<Button>(R.id.btnTres)

        btnUno.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, FragmentBoton1())
                .commit()
        }

        // Información: si hay datos guardados los muestra, si no abre el formulario
        btnDos.setOnClickListener {
            val nombre = Prefs.getNombre(this)
            val correo = Prefs.getCorreo(this)
            if (nombre.isNotEmpty() && correo.isNotEmpty()) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_view, FragmentBoton2.newInstance(nombre, correo))
                    .commit()
            } else {
                formularioLauncher.launch(Intent(this, Formulario::class.java))
            }
        }

        // Formulario: siempre abre para capturar datos nuevos
        btnTres.setOnClickListener {
            formularioLauncher.launch(Intent(this, Formulario::class.java))
        }
    }
}
