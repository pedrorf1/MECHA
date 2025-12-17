package com.example.mecha

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class VerNotasFragment : Fragment(R.layout.fragment_ver_notas) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var db: NotasDBHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerNotas)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        db = NotasDBHelper(requireContext())

        val listaNotas = db.getAllNotas()
        recyclerView.adapter = NotasAdapter(listaNotas)
    }
}
