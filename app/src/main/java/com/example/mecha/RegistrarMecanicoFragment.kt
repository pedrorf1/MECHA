package com.example.mecha

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class RegistrarMecanicoFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.registrar_mecanico_fragment, container, false)

        val correo = v.findViewById<EditText>(R.id.correo)
        val contrasena = v.findViewById<EditText>(R.id.contrasena)
        val nombre = v.findViewById<EditText>(R.id.nombre)
        val apellido = v.findViewById<EditText>(R.id.apellido)
        val telefono = v.findViewById<EditText>(R.id.telefono)
        val ubicacion = v.findViewById<EditText>(R.id.ubicacion)
        val avale = v.findViewById<EditText>(R.id.avale)
        val carro = v.findViewById<CheckBox>(R.id.carro)
        val moto = v.findViewById<CheckBox>(R.id.moto)
        val btn = v.findViewById<Button>(R.id.registrarMecanico)

        btn.setOnClickListener {

            val especialidad =
                when {
                    carro.isChecked && moto.isChecked -> "Carro,Motocicleta"
                    carro.isChecked -> "Carro"
                    moto.isChecked -> "Motocicleta"
                    else -> "Carro"
                }

            val url = "http://10.0.2.2/registrar_mecanico.php"

            val request = object : StringRequest(Method.POST, url,
                { Toast.makeText(requireContext(), "Registro exitoso", Toast.LENGTH_LONG).show() },
                { Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show() }
            ) {
                override fun getParams(): MutableMap<String, String> {
                    val params = HashMap<String, String>()
                    params["Correo"] = correo.text.toString()
                    params["Contrasena"] = contrasena.text.toString()
                    params["Nombre"] = nombre.text.toString()
                    params["Apellido"] = apellido.text.toString()
                    params["Telefono"] = telefono.text.toString()
                    params["Ubicacion"] = ubicacion.text.toString()
                    params["Avale"] = avale.text.toString()
                    params["Especialidad"] = especialidad
                    return params
                }
            }

            Volley.newRequestQueue(requireContext()).add(request)
        }
        return v
    }
}
