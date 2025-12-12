package com.example.mecha

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class NotasDBHelper(context: Context) :
    SQLiteOpenHelper(context, "notas.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE notas(id INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT, descripcion TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldV: Int, newV: Int) {
        db.execSQL("DROP TABLE IF EXISTS notas")
        onCreate(db)
    }

    fun insertNota(t: String, d: String): Boolean {
        val db = writableDatabase
        val cv = ContentValues()
        cv.put("titulo", t)
        cv.put("descripcion", d)
        return db.insert("notas", null, cv) != -1L
    }

    fun updateNota(t: String, d: String): Boolean {
        val db = writableDatabase
        val cv = ContentValues()
        cv.put("descripcion", d)
        return db.update("notas", cv, "titulo=?", arrayOf(t)) > 0
    }

    fun deleteNota(t: String): Boolean {
        val db = writableDatabase
        return db.delete("notas", "titulo=?", arrayOf(t)) > 0
    }

    fun getNotas(): Cursor {
        val db = readableDatabase
        return db.rawQuery("SELECT * FROM notas", null)
    }
}
