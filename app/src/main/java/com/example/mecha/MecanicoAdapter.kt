package com.example.mecha

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mecha.models.Mecanico

class MecanicoAdapter(private val lista: List<Mecanico>) :
    RecyclerView.Adapter<MecanicoAdapter.MecanicoViewHolder>() {

    inner class MecanicoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtNombre: TextView = view.findViewById(R.id.txtNombre)
        val txtTelefono: TextView = view.findViewById(R.id.txtTelefono)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MecanicoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_mecanico, parent, false)
        return MecanicoViewHolder(view)
    }

    override fun onBindViewHolder(holder: MecanicoViewHolder, position: Int) {
        val m = lista[position]
        holder.txtNombre.text = m.nombre
        holder.txtTelefono.text = "Tel: ${m.telefono}"
    }

    override fun getItemCount(): Int = lista.size
}
