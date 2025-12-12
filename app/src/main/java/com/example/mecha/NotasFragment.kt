package com.example.mecha

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class NotasFragment : Fragment() {

    private lateinit var edtTitulo: EditText
    private lateinit var edtDescripcion: EditText
    private lateinit var db: NotasDBHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_notas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        edtTitulo = view.findViewById(R.id.edtTitulo)
        edtDescripcion = view.findViewById(R.id.edtDescripcion)
        db = NotasDBHelper(requireContext())

        view.findViewById<Button>(R.id.btnGuardar).setOnClickListener {
            val t = edtTitulo.text.toString()
            val d = edtDescripcion.text.toString()

            if (db.insertNota(t, d)) {
                Toast.makeText(context, "Nota guardada", Toast.LENGTH_SHORT).show()
                edtTitulo.text.clear()
                edtDescripcion.text.clear()
            }
        }

        view.findViewById<Button>(R.id.btnEditar).setOnClickListener {
            val t = edtTitulo.text.toString()
            val d = edtDescripcion.text.toString()

            if (db.updateNota(t, d)) {
                Toast.makeText(context, "Nota actualizada", Toast.LENGTH_SHORT).show()
            }
        }

        view.findViewById<Button>(R.id.btnBorrar).setOnClickListener {
            val t = edtTitulo.text.toString()

            if (db.deleteNota(t)) {
                Toast.makeText(context, "Nota eliminada", Toast.LENGTH_SHORT).show()
                edtTitulo.text.clear()
                edtDescripcion.text.clear()
            }
        }
    }
}
