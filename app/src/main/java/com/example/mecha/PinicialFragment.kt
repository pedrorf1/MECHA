package com.example.mecha

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.fragment.app.Fragment

class PinicialFragment: Fragment(R.layout.pinicial_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnInicio = view.findViewById<ImageButton>(R.id.btnInicio)
        val btnNotas = view.findViewById<ImageButton>(R.id.btnNotas)
        val btnAyuda = view.findViewById<ImageButton>(R.id.btnAyuda)
        val btnInfoPerfil = view.findViewById<ImageButton>(R.id.btnInfoPerfil)
        val btnBuscarM = view.findViewById<ImageButton>(R.id.btnBuscarM)

        btnInicio.setOnClickListener {

        }



    }


}