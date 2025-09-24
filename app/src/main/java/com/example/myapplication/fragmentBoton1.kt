package com.example.myapplication

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.View
import android.widget.ImageButton

class FragmentBoton1 : Fragment(R.layout.fragment_boton1){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnCentro = view.findViewById<ImageButton>(R.id.btnCentro)
        btnCentro.setOnClickListener {

        }

    }
}