package com.example.mecha

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class NotasActivity : AppCompatActivity() {

    private lateinit var etTitulo: EditText
    private lateinit var etContenido: EditText
    private lateinit var tvUltimaNota: TextView
    private lateinit var btnGuardar: Button
    private lateinit var db: NotasDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad_notas)

        etTitulo = findViewById(R.id.etTitulo)
        etContenido = findViewById(R.id.etContenido)
        tvUltimaNota = findViewById(R.id.tvUltimaNota)
        btnGuardar = findViewById(R.id.btnGuardar)

        db = NotasDBHelper(this)

        // Mostrar nota guardada
        tvUltimaNota.text = db.obtenerUltimaNota()

        btnGuardar.setOnClickListener {
            val titulo = etTitulo.text.toString()
            val contenido = etContenido.text.toString()

            if (titulo.isEmpty() || contenido.isEmpty()) {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val resultado = db.insertarNota(titulo, contenido)

            if (resultado > 0) {
                Toast.makeText(this, "Nota guardada", Toast.LENGTH_SHORT).show()
                tvUltimaNota.text = db.obtenerUltimaNota()
                etTitulo.setText("")
                etContenido.setText("")
            } else {
                Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
