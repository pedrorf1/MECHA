package com.example.mecha

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class NotasFragment : Fragment() {

    private lateinit var etTitulo: EditText
    private lateinit var etContenido: EditText
    private lateinit var tvUltimaNota: TextView
    private lateinit var btnGuardar: Button
    private lateinit var db: NotasDBHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etTitulo = view.findViewById(R.id.etTitulo)
        etContenido = view.findViewById(R.id.etContenido)
        tvUltimaNota = view.findViewById(R.id.tvUltimaNota)
        btnGuardar = view.findViewById(R.id.btnGuardar)

        db = NotasDBHelper(requireContext())

        tvUltimaNota.text = db.obtenerUltimaNota()

        btnGuardar.setOnClickListener {
            val titulo = etTitulo.text.toString()
            val contenido = etContenido.text.toString()

            if (titulo.isEmpty() || contenido.isEmpty()) {
                Toast.makeText(requireContext(), "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val resultado = db.insertarNota(titulo, contenido)

            if (resultado > 0) {
                Toast.makeText(requireContext(), "Nota guardada", Toast.LENGTH_SHORT).show()
                tvUltimaNota.text = db.obtenerUltimaNota()
                etTitulo.setText("")
                etContenido.setText("")
            } else {
                Toast.makeText(requireContext(), "Error al guardar", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
