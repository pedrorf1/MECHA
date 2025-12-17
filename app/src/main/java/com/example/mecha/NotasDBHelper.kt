package com.example.mecha

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class NotasDBHelper(context: Context) :
    SQLiteOpenHelper(context, "NotasDB", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE notas (" +
                    "titulo TEXT PRIMARY KEY," +
                    "descripcion TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS notas")
        onCreate(db)
    }

    fun insertNota(titulo: String, descripcion: String): Boolean {
        val db = writableDatabase
        val values = ContentValues()
        values.put("titulo", titulo)
        values.put("descripcion", descripcion)
        return db.insert("notas", null, values) != -1L
    }

    fun updateNota(titulo: String, descripcion: String): Boolean {
        val db = writableDatabase
        val values = ContentValues()
        values.put("descripcion", descripcion)
        return db.update("notas", values, "titulo=?", arrayOf(titulo)) > 0
    }

    fun deleteNota(titulo: String): Boolean {
        val db = writableDatabase
        return db.delete("notas", "titulo=?", arrayOf(titulo)) > 0
    }

    fun getAllNotas(): List<Nota> {
        val lista = mutableListOf<Nota>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM notas", null)

        if (cursor.moveToFirst()) {
            do {
                val titulo = cursor.getString(0)
                val descripcion = cursor.getString(1)
                lista.add(Nota(titulo, descripcion))
            } while (cursor.moveToNext())
        }

        cursor.close()
        return lista
    }
}