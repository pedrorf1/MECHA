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
    private lateinit var btnGuardar: Button
    private lateinit var btnEditar: Button
    private lateinit var btnBorrar: Button
    private lateinit var btnVer: Button
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

        edtTitulo = view.findViewById(R.id.edtTitulo)
        edtDescripcion = view.findViewById(R.id.edtDescripcion)
        btnGuardar = view.findViewById(R.id.btnGuardar)
        btnEditar = view.findViewById(R.id.btnEditar)
        btnBorrar = view.findViewById(R.id.btnBorrar)
        btnVer = view.findViewById(R.id.btnVer)
        db = NotasDBHelper(requireContext())

        btnGuardar.setOnClickListener {
            val t = edtTitulo.text.toString()
            val d = edtDescripcion.text.toString()

            if (t.isEmpty() || d.isEmpty()) {
                Toast.makeText(requireContext(), "Completa todo", Toast.LENGTH_SHORT).show()
            } else {
                val ok = db.insertNota(t, d)
                Toast.makeText(requireContext(),
                    if (ok) "Nota guardada" else "Error", Toast.LENGTH_SHORT).show()
            }
        }

        btnEditar.setOnClickListener {
            val ok = db.updateNota(
                edtTitulo.text.toString(),
                edtDescripcion.text.toString()
            )
            Toast.makeText(requireContext(),
                if (ok) "Nota actualizada" else "No existe", Toast.LENGTH_SHORT).show()
        }

        btnBorrar.setOnClickListener {
            val ok = db.deleteNota(edtTitulo.text.toString())
            Toast.makeText(requireContext(),
                if (ok) "Nota eliminada" else "No existe", Toast.LENGTH_SHORT).show()
        }

        btnVer.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_container, VerNotasFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}
