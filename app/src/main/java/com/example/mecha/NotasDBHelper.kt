package com.example.mecha

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class NotasDBHelper(context: Context) :
    SQLiteOpenHelper(context, "notas.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE notas(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                titulo TEXT NOT NULL,
                contenido TEXT NOT NULL
            )
            """.trimIndent()
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS notas")
        onCreate(db)
    }

    fun insertarNota(titulo: String, contenido: String): Long {
        val db = writableDatabase
        val valores = ContentValues().apply {
            put("titulo", titulo)
            put("contenido", contenido)
        }
        return db.insert("notas", null, valores)
    }

    fun obtenerUltimaNota(): String {
        val db = readableDatabase
        val cursor = db.rawQuery(
            "SELECT titulo, contenido FROM notas ORDER BY id DESC LIMIT 1",
            null
        )

        return if (cursor.moveToFirst()) {
            val titulo = cursor.getString(0)
            val contenido = cursor.getString(1)
            cursor.close()
            "Título: $titulo\nContenido: $contenido"
        } else {
            cursor.close()
            "No hay notas guardadas"
        }
    }
}
