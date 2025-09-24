package com.example.myapplication

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.View
import android.widget.ImageButton

class FragmentBoton2 : Fragment(R.layout.fragment_boton2){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnCentro2 = view.findViewById<ImageButton>(R.id.btnCentro2)
        btnCentro2.setOnClickListener {

        }

    }
}