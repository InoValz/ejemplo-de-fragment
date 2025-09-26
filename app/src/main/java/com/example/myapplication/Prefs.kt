// Prefs.kt
package com.example.myapplication
import android.content.Context

object Prefs {
    private const val FILE = "app_prefs"
    private const val K_NOMBRE = "k_nombre"
    private const val K_CORREO = "k_correo"

    fun save(context: Context, nombre: String, correo: String) {
        context.getSharedPreferences(FILE, Context.MODE_PRIVATE)
            .edit()
            .putString(K_NOMBRE, nombre)
            .putString(K_CORREO, correo)
            .apply()
    }

    fun getNombre(context: Context) =
        context.getSharedPreferences(FILE, Context.MODE_PRIVATE).getString(K_NOMBRE, "") ?: ""

    fun getCorreo(context: Context) =
        context.getSharedPreferences(FILE, Context.MODE_PRIVATE).getString(K_CORREO, "") ?: ""
}
