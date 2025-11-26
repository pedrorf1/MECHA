package com.example.mecha

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment

class RegistrarConductorFragment : Fragment(R.layout.fragment_registrar_conductor) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val correo = view.findViewById<EditText>(R.id.correo)
        val contrasena = view.findViewById<EditText>(R.id.contrasena)
        val nombre = view.findViewById<EditText>(R.id.nombre)
        val apellido = view.findViewById<EditText>(R.id.apellido)
        val telefono = view.findViewById<EditText>(R.id.telefono)
        val numVehiculos = view.findViewById<EditText>(R.id.numVehiculos)

        val placa = view.findViewById<EditText>(R.id.placa)
        val marca = view.findViewById<EditText>(R.id.marca)
        val modelo = view.findViewById<EditText>(R.id.modelo)
        val año = view.findViewById<EditText>(R.id.año)
        val color = view.findViewById<EditText>(R.id.color)
        val tipoAuto = view.findViewById<Spinner>(R.id.tipoAuto)

        val agregar = view.findViewById<Button>(R.id.agregarVehiculo)
        val registrar = view.findViewById<Button>(R.id.registrarConductor)

        val lista = ArrayList<String>()

        agregar.setOnClickListener {
            if (placa.text.isNotEmpty() && marca.text.isNotEmpty() &&
                modelo.text.isNotEmpty() && año.text.isNotEmpty() &&
                color.text.isNotEmpty()
            ) {
                lista.add(placa.text.toString())
                placa.text.clear(); marca.text.clear(); modelo.text.clear()
                año.text.clear(); color.text.clear()
            }
        }

        registrar.setOnClickListener {
            if (correo.text.isEmpty() || contrasena.text.isEmpty() || nombre.text.isEmpty() ||
                apellido.text.isEmpty() || telefono.text.isEmpty() || numVehiculos.text.isEmpty()
            ) return@setOnClickListener

            Toast.makeText(requireContext(), "Conductor registrado", Toast.LENGTH_SHORT).show()
        }
    }
}