package com.example.mecha

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class ReportarProblemaFragment : Fragment() {

    private lateinit var tvIdMecanico: TextView
    private lateinit var etDescripcion: EditText
    private lateinit var etIdMecanico: EditText
    private lateinit var etIdConductor: EditText
    private lateinit var etPlaca: EditText
    private lateinit var btnReportar: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_reportar_problema, container, false)

        // Referencias
        tvIdMecanico = view.findViewById(R.id.tvIdMecanico)
        etDescripcion = view.findViewById(R.id.etDescripcion)
        etIdMecanico = view.findViewById(R.id.etIdMecanico)
        etIdConductor = view.findViewById(R.id.etIdConductor)
        etPlaca = view.findViewById(R.id.etPlaca)
        btnReportar = view.findViewById(R.id.btnReportarProblema)

        // Si viene el ID del mecánico desde otra pantalla
        val idMecanicoRecibido = arguments?.getString("id_mecanico")
        if (idMecanicoRecibido != null) {
            tvIdMecanico.text = "ID Mecánico: $idMecanicoRecibido"
            etIdMecanico.setText(idMecanicoRecibido)
        }

        btnReportar.setOnClickListener {
            reportarProblema()
        }

        return view
    }

    private fun reportarProblema() {
        val descripcion = etDescripcion.text.toString()
        val idMecanico = etIdMecanico.text.toString()
        val idConductor = etIdConductor.text.toString()
        val placa = etPlaca.text.toString()

        if (descripcion.isEmpty() || idMecanico.isEmpty() ||
            idConductor.isEmpty() || placa.isEmpty()
        ) {
            Toast.makeText(requireContext(), "Completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        // SIMULACIÓN (sin backend)
        Toast.makeText(
            requireContext(),
            "Problema reportado correctamente",
            Toast.LENGTH_LONG
        ).show()
    }
}