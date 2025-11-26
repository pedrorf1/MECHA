package com.example.mecha

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment

class RegistrarMecanicoFragment : Fragment(R.layout.fragment_registrar_mecanico) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val correo = view.findViewById<EditText>(R.id.correo)
        val contrasena = view.findViewById<EditText>(R.id.contrasena)
        val nombre = view.findViewById<EditText>(R.id.nombre)
        val apellido = view.findViewById<EditText>(R.id.apellido)
        val telefono = view.findViewById<EditText>(R.id.telefono)
        val ubicacion = view.findViewById<EditText>(R.id.ubicacion)
        val avale = view.findViewById<EditText>(R.id.avale)

        val carro = view.findViewById<CheckBox>(R.id.carro)
        val moto = view.findViewById<CheckBox>(R.id.moto)

        val registrar = view.findViewById<Button>(R.id.registrarMecanico)

        registrar.setOnClickListener {
            if (correo.text.isEmpty() || contrasena.text.isEmpty() || nombre.text.isEmpty() ||
                apellido.text.isEmpty() || telefono.text.isEmpty() ||
                ubicacion.text.isEmpty() || avale.text.isEmpty()
            ) return@setOnClickListener

            if (!carro.isChecked && !moto.isChecked) return@setOnClickListener

            Toast.makeText(requireContext(), "Mecánico registrado", Toast.LENGTH_SHORT).show()
        }
    }
}