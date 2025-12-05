package com.example.mecha

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject
//juan
class RegistrarConductorFragment : Fragment(R.layout.registrar_conductor_fragment) {

    private lateinit var edtCorreo: EditText
    private lateinit var edtPassword: EditText
    private lateinit var edtNombre: EditText
    private lateinit var edtApellido: EditText
    private lateinit var edtTelefono: EditText
    private lateinit var edtNumVehiculos: EditText
    private lateinit var contenedorVehiculos: LinearLayout
    private lateinit var btnGenerarVehiculos: Button
    private lateinit var btnRegistrar: Button
    private lateinit var tvmsj: TextView
    private lateinit var btnCerrar: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.registrar_conductor_fragment, container, false)

        edtCorreo = view.findViewById(R.id.edtCorreo)
        edtPassword = view.findViewById(R.id.edtPassword)
        edtNombre = view.findViewById(R.id.edtNombre)
        edtApellido = view.findViewById(R.id.edtApellido)
        edtTelefono = view.findViewById(R.id.edtTelefono)
        edtNumVehiculos = view.findViewById(R.id.edtNumVehiculos)
        contenedorVehiculos = view.findViewById(R.id.contenedorVehiculos)
        btnGenerarVehiculos = view.findViewById(R.id.btnGenerarVehiculos)
        btnRegistrar = view.findViewById(R.id.btnRegistrar)
        tvmsj = view.findViewById(R.id.tvmsj)
        btnCerrar = view.findViewById(R.id.btncerrarregcond)

        btnGenerarVehiculos.setOnClickListener { generarVehiculos() }
        btnRegistrar.setOnClickListener { registrarConductor() }

        btnCerrar.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return view
    }

    private fun generarVehiculos() {
        contenedorVehiculos.removeAllViews()

        val num = edtNumVehiculos.text.toString().toIntOrNull()
        if (num == null || num <= 0) {
            tvmsj.text = "Ingresa un número válido"
            return
        }

        val inflater = LayoutInflater.from(requireContext())

        repeat(num) {
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
            tvmsj.text = "Completa todos los campos"
            return
        }

        val vehiculosList = mutableListOf<Map<String, String>>()

        for (i in 0 until contenedorVehiculos.childCount) {
            val v = contenedorVehiculos.getChildAt(i)

            val placa = v.findViewById<EditText>(R.id.edtPlaca).text.toString()
            val marca = v.findViewById<EditText>(R.id.edtMarca).text.toString()
            val modelo = v.findViewById<EditText>(R.id.edtModelo).text.toString()
            val anio = v.findViewById<EditText>(R.id.edtAnio).text.toString()
            val color = v.findViewById<EditText>(R.id.edtColor).text.toString()
            val tipo = v.findViewById<EditText>(R.id.edtTipo).text.toString()

            if (placa.isEmpty() || marca.isEmpty() || modelo.isEmpty() ||
                anio.isEmpty() || color.isEmpty() || tipo.isEmpty()
            ) {
                tvmsj.text = "Completa todos los datos de los vehículos"
                return
            }

            vehiculosList.add(
                mapOf(
                    "placa" to placa,
                    "marca" to marca,
                    "modelo" to modelo,
                    "anio" to anio,
                    "color" to color,
                    "tipo" to tipo
                )
            )
        }

        val json = JSONObject().apply {
            put("correo", edtCorreo.text.toString())
            put("password", edtPassword.text.toString())
            put("nombre", edtNombre.text.toString())
            put("apellido", edtApellido.text.toString())
            put("telefono", edtTelefono.text.toString())
            put("vehiculos", JSONArray(vehiculosList))
        }

        val url = "http://10.0.2.2/mecha/registrar_conductor.php"

        val request = JsonObjectRequest(
            Request.Method.POST,
            url,
            json,
            { response ->
                tvmsj.text = response.getString("message")
            },
            {
                tvmsj.text = "Error al conectar con el servidor"
            }
        )

        Volley.newRequestQueue(requireContext()).add(request)
    }
}