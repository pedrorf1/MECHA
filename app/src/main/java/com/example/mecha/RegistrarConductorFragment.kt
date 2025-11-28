package com.example.mecha

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.mecha.models.Conductor
import com.example.mecha.models.Vehiculo

class RegistrarConductorFragment : Fragment() {

    private lateinit var edtCorreo: EditText
    private lateinit var edtPassword: EditText
    private lateinit var edtNombre: EditText
    private lateinit var edtApellido: EditText
    private lateinit var edtTelefono: EditText
    private lateinit var edtNumVehiculos: EditText
    private lateinit var contenedorVehiculos: LinearLayout
    private lateinit var btnGenerarVehiculos: Button
    private lateinit var btnRegistrar: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.registrar_conductor_fragment, container, false)

        // Inicializar vistas
        edtCorreo = view.findViewById(R.id.edtCorreo)
        edtPassword = view.findViewById(R.id.edtPassword)
        edtNombre = view.findViewById(R.id.edtNombre)
        edtApellido = view.findViewById(R.id.edtApellido)
        edtTelefono = view.findViewById(R.id.edtTelefono)
        edtNumVehiculos = view.findViewById(R.id.edtNumVehiculos)
        contenedorVehiculos = view.findViewById(R.id.contenedorVehiculos)
        btnGenerarVehiculos = view.findViewById(R.id.btnGenerarVehiculos)
        btnRegistrar = view.findViewById(R.id.btnRegistrar)

        btnGenerarVehiculos.setOnClickListener { generarVehiculos() }
        btnRegistrar.setOnClickListener { registrarConductor() }

        return view
    }

    private fun generarVehiculos() {
        contenedorVehiculos.removeAllViews()

        val numVehiculos = edtNumVehiculos.text.toString().toIntOrNull()
        if (numVehiculos == null || numVehiculos <= 0) {
            Toast.makeText(requireContext(), "Ingresa un número válido de vehículos", Toast.LENGTH_SHORT).show()
            return
        }

        val inflater = LayoutInflater.from(requireContext())

        repeat(numVehiculos) {
            val vista = inflater.inflate(R.layout.item_vehiculo, contenedorVehiculos, false)
            contenedorVehiculos.addView(vista)
        }
    }

    private fun registrarConductor() {

        if (edtCorreo.text.isEmpty() ||
            edtPassword.text.isEmpty() ||
            edtNombre.text.isEmpty() ||
            edtApellido.text.isEmpty() ||
            edtTelefono.text.isEmpty() ||
            edtNumVehiculos.text.isEmpty()
        ) {
            Toast.makeText(requireContext(), "Completa todos los campos del conductor", Toast.LENGTH_SHORT).show()
            return
        }

        val listaVehiculos = mutableListOf<Vehiculo>()

        for (i in 0 until contenedorVehiculos.childCount) {
            val vistaVehiculo = contenedorVehiculos.getChildAt(i)

            val placa = vistaVehiculo.findViewById<EditText>(R.id.edtPlaca).text.toString()
            val marca = vistaVehiculo.findViewById<EditText>(R.id.edtMarca).text.toString()
            val modelo = vistaVehiculo.findViewById<EditText>(R.id.edtModelo).text.toString()
            val anio = vistaVehiculo.findViewById<EditText>(R.id.edtAnio).text.toString()
            val color = vistaVehiculo.findViewById<EditText>(R.id.edtColor).text.toString()
            val tipo = vistaVehiculo.findViewById<EditText>(R.id.edtTipo).text.toString()

            if (placa.isEmpty() || marca.isEmpty() || modelo.isEmpty() ||
                anio.isEmpty() || color.isEmpty() || tipo.isEmpty()
            ) {
                Toast.makeText(requireContext(), "Completa todos los datos de cada vehículo", Toast.LENGTH_SHORT).show()
                return
            }

            listaVehiculos.add(
                Vehiculo(
                    placa = placa,
                    marca = marca,
                    modelo = modelo,
                    anio = anio,
                    color = color,
                    tipo = tipo
                )
            )
        }

        val conductor = Conductor(
            correo = edtCorreo.text.toString(),
            password = edtPassword.text.toString(),
            nombre = edtNombre.text.toString(),
            apellido = edtApellido.text.toString(),
            telefono = edtTelefono.text.toString(),
            vehiculos = listaVehiculos
        )

        Toast.makeText(requireContext(), "Conductor listo para guardar", Toast.LENGTH_LONG).show()
    }
}
