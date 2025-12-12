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
    ): View? {
        return inflater.inflate(R.layout.fragment_ver_notas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        txtNotas = view.findViewById(R.id.txtNotas)
        db = NotasDBHelper(requireContext())

        val c = db.getNotas()
        val sb = StringBuilder()

        while (c.moveToNext()) {
            sb.append("Título: ").append(c.getString(1)).append("\n")
            sb.append("Descripción: ").append(c.getString(2)).append("\n\n")
        }

        txtNotas.text = sb.toString()
    }
}
