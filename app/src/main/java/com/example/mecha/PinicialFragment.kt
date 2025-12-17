package com.example.mecha

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment

class PinicialFragment : Fragment(R.layout.pinicial_fragment), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var btnRePro: Button
    private lateinit var btnNotas: ImageButton
    private lateinit var problem: ImageButton



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        btnRePro = view.findViewById(R.id.btnRePro)
        btnRePro.setOnClickListener {
            boton()
        }
        btnNotas = view.findViewById(R.id.btnNotas)
        btnNotas.setOnClickListener {
            botonnotas()
        }
        problem = view.findViewById(R.id.problem)
        problem.setOnClickListener {
            botonrepp()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
            return
        }

        activarUbicacion()
    }

    private fun activarUbicacion() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            try {
                mMap.isMyLocationEnabled = true
                mMap.uiSettings.isMyLocationButtonEnabled = true
            } catch (e: SecurityException) {
                e.printStackTrace()
            }
        }
    }

    private fun boton(){
        val reportarProblemaFragment = ReportarProblemaFragment()
        parentFragmentManager.beginTransaction()
            .replace(R.id.FragmentsInside, reportarProblemaFragment)
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

    private fun botonrepp(){
        val reportarProblemaFragment = ReportarProblemaFragment()
        parentFragmentManager.beginTransaction()
            .replace(R.id.FragmentsInside, reportarProblemaFragment)
            .addToBackStack(null)
            .commit()
    }
}