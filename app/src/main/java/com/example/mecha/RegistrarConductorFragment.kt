package com.example.mecha

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.mecha.models.Vehiculo
import com.google.gson.Gson

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

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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

        btnGenerarVehiculos.setOnClickListener { generarVehiculos() }
        btnRegistrar.setOnClickListener { registrarConductor() }

        return view
    }

    private fun generarVehiculos() {
        contenedorVehiculos.removeAllViews()
        val num = edtNumVehiculos.text.toString().toIntOrNull() ?: return
        val inflater = LayoutInflater.from(requireContext())

        repeat(num) {
            contenedorVehiculos.addView(
                inflater.inflate(R.layout.item_vehiculo, contenedorVehiculos, false)
            )
        }
    }

    private fun registrarConductor() {

        val url = "http://10.0.2.2/mecha/registrar_conductor.php"
        val listaVehiculos = mutableListOf<Vehiculo>()

        for (i in 0 until contenedorVehiculos.childCount) {
            val v = contenedorVehiculos.getChildAt(i)

            val placa = v.findViewById<EditText>(R.id.edtPlaca).text.toString()
            val marca = v.findViewById<EditText>(R.id.edtMarca).text.toString()
            val modelo = v.findViewById<EditText>(R.id.edtModelo).text.toString()
            val anio = v.findViewById<EditText>(R.id.edtAnio).text.toString().toInt()
            val color = v.findViewById<EditText>(R.id.edtColor).text.toString()
            val tipo = if (v.findViewById<CheckBox>(R.id.Carro).isChecked) "Carro" else "Motocicleta"

            listaVehiculos.add(Vehiculo(placa, marca, modelo, anio.toString(), color, tipo))
        }

        val request = object : StringRequest(
            Request.Method.POST, url,
            { response ->
                if (response.trim() == "ok")
                    Toast.makeText(requireContext(), "Registro exitoso", Toast.LENGTH_LONG).show()
                else
                    tvmsj.text = response
            },
            { tvmsj.text = "Error de conexión" }
        ) {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["Correo"] = edtCorreo.text.toString()
                params["Contrasena"] = edtPassword.text.toString()
                params["Nombre"] = edtNombre.text.toString()
                params["Apellido"] = edtApellido.text.toString()
                params["Telefono"] = edtTelefono.text.toString()
                params["NumVehiculos"] = listaVehiculos.size.toString()
                params["Vehiculos"] = Gson().toJson(listaVehiculos)
                return params
            }
        }

        Volley.newRequestQueue(requireContext()).add(request)
    }
}
