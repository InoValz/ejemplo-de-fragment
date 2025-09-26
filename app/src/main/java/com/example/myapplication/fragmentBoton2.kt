package com.example.myapplication

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.View
import android.widget.TextView

class FragmentBoton2 : Fragment(R.layout.fragment_boton2) {

    companion object {
        private const val ARG_NOMBRE = "arg_nombre"
        private const val ARG_CORREO = "arg_correo"

        fun newInstance(nombre: String, correo: String) = FragmentBoton2().apply {
            arguments = Bundle().apply {
                putString(ARG_NOMBRE, nombre)
                putString(ARG_CORREO, correo)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val txtNombre = view.findViewById<TextView>(R.id.txtNombre)
        val txtCorreo = view.findViewById<TextView>(R.id.txtCorreo)

        val nombre = arguments?.getString(ARG_NOMBRE).orEmpty()
        val correo = arguments?.getString(ARG_CORREO).orEmpty()

        txtNombre.text = nombre
        txtCorreo.text = correo
    }
}
