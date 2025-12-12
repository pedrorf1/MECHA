package com.example.mecha

import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class inappActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_inapp)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnInicio = findViewById<ImageButton>(R.id.btnInicio)
        val btnNotas = findViewById<ImageButton>(R.id.btnNotas)
        val btnBusscarM = findViewById<ImageButton>(R.id.btnBuscarM)

        btnInicio.setOnClickListener {
            val pinicialfragment = PinicialFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.FragmentsInside, pinicialfragment)
                .addToBackStack(null)
                .commit()
        }

        btnNotas.setOnClickListener {
            val notasfragment = NotasFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.FragmentsInside, notasfragment)
                .addToBackStack(null)
                .commit()
        }


    }
}