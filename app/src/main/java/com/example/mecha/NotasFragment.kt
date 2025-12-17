package com.example.mecha

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
//
class NotasFragment : Fragment(R.layout.fragment_notas) {

    private lateinit var edtTitulo: EditText
    private lateinit var edtDescripcion: EditText
    private lateinit var db: NotasDBHelper
    private lateinit var problem: ImageButton
    private lateinit var btnHome: ImageButton


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        edtTitulo = view.findViewById(R.id.edtTitulo)
        edtDescripcion = view.findViewById(R.id.edtDescripcion)

        db = NotasDBHelper(requireContext())

        view.findViewById<Button>(R.id.btnGuardar).setOnClickListener {
            val t = edtTitulo.text.toString()
            val d = edtDescripcion.text.toString()

            if (t.isNotEmpty() && d.isNotEmpty()) {
                if (db.insertNota(t, d)) {
                    Toast.makeText(requireContext(), "Nota guardada con éxito", Toast.LENGTH_SHORT).show()
                    edtTitulo.text.clear()
                    edtDescripcion.text.clear()
                }
            } else {
                Toast.makeText(requireContext(), "Llena todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        view.findViewById<Button>(R.id.btnEditar).setOnClickListener {
            val t = edtTitulo.text.toString()
            val d = edtDescripcion.text.toString()

            if (db.updateNota(t, d)) {
                Toast.makeText(requireContext(), "Nota actualizada", Toast.LENGTH_SHORT).show()
            }
        }

        view.findViewById<Button>(R.id.btnBorrar).setOnClickListener {
            val t = edtTitulo.text.toString()

            if (db.deleteNota(t)) {
                Toast.makeText(requireContext(), "Nota eliminada", Toast.LENGTH_SHORT).show()
                edtTitulo.text.clear()
                edtDescripcion.text.clear()
            }
        }

        problem = view.findViewById(R.id.problem)
        problem.setOnClickListener {
            botonrepp()
        }

        btnHome = view.findViewById(R.id.btnHome)
        btnHome.setOnClickListener {
            home()
        }
    }

    private fun botonrepp(){
        val reportarProblemaFragment = ReportarProblemaFragment()
        parentFragmentManager.beginTransaction()
            .replace(R.id.FragmentsInside, reportarProblemaFragment)
            .addToBackStack(null)
            .commit()
    }

    private fun home(){
        val pinicialfragment = PinicialFragment()
        parentFragmentManager.beginTransaction()
            .replace(R.id.FragmentsInside, pinicialfragment)
            .addToBackStack(null)
            .commit()
    }
}