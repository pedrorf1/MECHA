package com.example.mecha

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.fragment.app.Fragment

class menufragment: Fragment(R.layout.menu_bar) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnHome = view.findViewById<ImageButton>(R.id.btnHome)
        val btnNotas = view.findViewById<ImageButton>(R.id.btnNotas)
        val btnBuscarM = view.findViewById<ImageButton>(R.id.btnBuscarM)


        btnHome.setOnClickListener {
            val pinicialfragment = PinicialFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.FragmentsInside, pinicialfragment)
                .addToBackStack(null)
                .commit()
        }

        btnNotas.setOnClickListener {
            val notasfragment = NotasFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.FragmentsInside, notasfragment)
                .addToBackStack(null)
                .commit()
        }

    }
}