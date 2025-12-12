package com.example.mecha

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class VerNotasFragment : Fragment() {

    private lateinit var txtNotas: TextView
    private lateinit var db: NotasDBHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_ver_notas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        txtNotas = view.findViewById(R.id.txtNotas)
        db = NotasDBHelper(requireContext())

        val cursor = db.getNotas()
        val sb = StringBuilder()

        while (cursor.moveToNext()) {
            sb.append("Título: ")
                .append(cursor.getString(cursor.getColumnIndexOrThrow("titulo")))
                .append("\n")

            sb.append("Descripción: ")
                .append(cursor.getString(cursor.getColumnIndexOrThrow("descripcion")))
                .append("\n\n")
        }

        cursor.close()
        txtNotas.text = sb.toString()
    }
}
