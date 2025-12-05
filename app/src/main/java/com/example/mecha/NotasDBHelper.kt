package com.example.mecha

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class NotasDBHelper(context: Context) :
    SQLiteOpenHelper(context, "NotasDB", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            "CREATE TABLE notas (" +
                    "idNota INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "titulo TEXT, " +
                    "contenido TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS notas")
        onCreate(db)
    }

    fun insertarNota(titulo: String, contenido: String): Long {
        val db = writableDatabase
        val values = ContentValues()

        values.put("titulo", titulo)
        values.put("contenido", contenido)

        return db.insert("notas", null, values)
    }

    fun obtenerUltimaNota(): String {
        val db = readableDatabase
        val cursor = db.rawQuery(
            "SELECT titulo, contenido FROM notas ORDER BY idNota DESC LIMIT 1",
            null
        )

        return if (cursor.moveToFirst()) {
            val titulo = cursor.getString(0)
            val contenido = cursor.getString(1)
            cursor.close()
            "📌 Última Nota:\n\nTítulo: $titulo\n\nContenido:\n$contenido"
        } else {
            cursor.close()
            "Aún no hay notas guardadas."
        }
    }
}
