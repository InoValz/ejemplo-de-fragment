package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.text.Spanned
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Formulario : AppCompatActivity() {

    private lateinit var edtNombre: EditText
    private lateinit var edtCorreo: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnEnviar: Button
    private  lateinit var btnVolver: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_formulario)

        edtNombre = findViewById(R.id.edtNombre)
        edtCorreo = findViewById(R.id.edtCorreo)
        edtPassword = findViewById(R.id.edtPassword)
        btnEnviar = findViewById(R.id.btnEnviar)
        btnVolver = findViewById(R.id.btnVolver)

        // Filtro opcional: bloquea caracteres no permitidos en Nombre mientras escribe
        edtNombre.filters = arrayOf(LetrasYEspaciosFilter())

        btnEnviar.setOnClickListener {
            validarYEnviar()
        }

        btnVolver.setOnClickListener {
            finish() // vuelve sin datos
        }
    }

    private fun validarYEnviar() {
        val nombre = edtNombre.text.toString().trim()
        val correo = edtCorreo.text.toString().trim()
        val pass = edtPassword.text.toString()

        // Limpia errores previos
        edtNombre.error = null
        edtCorreo.error = null
        edtPassword.error = null

        // Valida en orden y enfoca el primero con error
        when {
            !esNombreValido(nombre) -> {
                edtNombre.error = "Solo letras y espacios. 2 a 40 caracteres."
                edtNombre.requestFocus()
            }
            !esCorreoValido(correo) -> {
                edtCorreo.error = "Formato email válido y dominio gmail/outlook/hotmail."
                edtCorreo.requestFocus()
            }
            !esPasswordValida(pass) -> {
                edtPassword.error = "Mínimo 6 caracteres y al menos un número."
                edtPassword.requestFocus()
            }
            else -> {
                // DEVOLVER DATOS A MainActivity
                Prefs.save(this, nombre, correo)              // ← guarda persistente
                val data = Intent().apply {
                    putExtra(MainActivity.EXTRA_NOMBRE, nombre)
                    putExtra(MainActivity.EXTRA_CORREO, correo)
                }
                setResult(RESULT_OK, data)
                finish()
            }
        }
    }

    // Letras con acentos y espacios. Largo 2–40.
    private fun esNombreValido(nombre: String): Boolean {
        val regex = Regex("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]{2,40}$")
        return regex.matches(nombre)
    }

    // Solo dominios gmail, outlook o hotmail. TLD comunes .com/.cl/.es
    private fun esCorreoValido(correo: String): Boolean {
        val regex = Regex("^[A-Za-z0-9._%+-]+@(?:gmail|outlook|hotmail)\\.(?:com|cl|es)$", RegexOption.IGNORE_CASE)
        return regex.matches(correo)
    }

    // Al menos 6 chars y contiene dígito
    private fun esPasswordValida(pass: String): Boolean {
        val regex = Regex("^(?=.*\\d).{6,}$")
        return regex.matches(pass)
    }

    // Filtro para permitir solo letras con acento y espacios en el EditText de nombre
    private class LetrasYEspaciosFilter : InputFilter {
        private val patron = Regex("[A-Za-zÁÉÍÓÚáéíóúÑñ ]+")
        override fun filter(
            source: CharSequence,
            start: Int,
            end: Int,
            dest: Spanned,
            dstart: Int,
            dend: Int
        ): CharSequence {
            val sub = source.subSequence(start, end)
            return if (sub.isEmpty() || patron.matches(sub)) sub else ""
        }
    }
}
