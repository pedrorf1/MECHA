package com.example.mecha

import android.annotation.SuppressLint
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
    private lateinit var btnHome: ImageButton
    private lateinit var btnNotas: ImageButton


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_reportar_problema, container, false)

        // Referencias
        etDescripcion = view.findViewById(R.id.etDescripcion)
        etPlaca = view.findViewById(R.id.etPlaca)
        btnReportar = view.findViewById(R.id.btnReportarProblema)


        btnReportar.setOnClickListener {
            reportarProblema()
            if (etDescripcion.text.toString().isNotEmpty() && etPlaca.text.toString().isNotEmpty()) {
                val catalogomecanicoFragment = CatalogoMecanicoFragment()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.FragmentsInside, catalogomecanicoFragment)
                    .addToBackStack(null)
                    .commit()
            }
            else{
                val msjadvert = view?.findViewById<TextView>(R.id.msjadvert)
                msjadvert?.text = "Completa todos los campos"
                Toast.makeText(requireContext(), "Completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        btnHome = view.findViewById(R.id.btnHome)
        btnHome.setOnClickListener {
            home()
        }

        btnNotas = view.findViewById(R.id.btnNotas)
        btnNotas.setOnClickListener {
            botonnotas()
        }

        return view

    }

    private fun reportarProblema() {

        val msjadvert = view?.findViewById<TextView>(R.id.msjadvert)
        val descripcion = etDescripcion.text.toString()
        val placa = etPlaca.text.toString()


        if (descripcion.isEmpty() || placa.isEmpty()
        ) {
            msjadvert?.text = "Completa todos los campos"
            Toast.makeText(requireContext(), "Completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }
    }

    private fun home(){
        val pinicialfragment = PinicialFragment()
        parentFragmentManager.beginTransaction()
            .replace(R.id.FragmentsInside, pinicialfragment)
            .addToBackStack(null)
            .commit()
    }

    private fun botonnotas(){
        val notasfragment = NotasFragment()
        parentFragmentManager.beginTransaction()
            .replace(R.id.FragmentsInside, notasfragment)
            .addToBackStack(null)
            .commit()
    }
}