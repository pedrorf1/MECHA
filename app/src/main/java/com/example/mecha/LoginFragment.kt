package com.example.mecha

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.mecha.CardFragment
import kotlin.toString

class LoginFragment : Fragment(R.layout.login_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnRegistrar = view.findViewById<Button>(R.id.btnRegistrar)
        val edtcorreo = view.findViewById<EditText>(R.id.edtCorreo)
        val edtpassword = view.findViewById<EditText>(R.id.edtPassword)
        val btningresar = view.findViewById<Button>(R.id.btnLogin)
        val tvdatos = view.findViewById<TextView>(R.id.tvDatos)

        btnRegistrar.setOnClickListener {
            val cardFragment = CardFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.contenedorFragments, cardFragment)
                .addToBackStack(null)
                .commit()
        }


        btningresar.setOnClickListener {
            val correo = edtcorreo.text.toString()
            val psw = edtpassword.text.toString()

            if (correo.isNotEmpty() && psw.isNotEmpty()) {

                val url = "http://10.0.2.2/mecha/validar.php"
                val stringRequest = object : StringRequest(
                    Method.POST, url,
                    { response ->
                        if (response.trim() == "success") {
                            tvdatos.text = "Success"
                        } else {
                            tvdatos.text = "Datos incorrectos"
                        }
                    },
                    { error ->
                        tvdatos.text = "Error de conexión"
                    }
                ) {
                    override fun getParams(): MutableMap<String, String> {
                        val params = HashMap<String, String>()
                        params["correo"] = correo
                        params["contrasena"] = psw
                        return params
                    }
                }

                // ✔ Agregar solicitud a la cola de Volley
                val queue = Volley.newRequestQueue(requireContext())
                queue.add(stringRequest)

            } else {
                tvdatos.text = "Llena todos los campos."
            }
        }


    }}
