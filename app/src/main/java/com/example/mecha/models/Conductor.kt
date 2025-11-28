package com.example.mecha.models

data class Conductor (
    val correo: String,
    val password: String,
    val nombre: String,
    val apellido: String,
    val telefono: String,
    val vehiculos: List<Vehiculo>
)