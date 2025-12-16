package com.example.mecha


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.mecha.models.Mecanico

class CatalogoMecanicoFragment :
    Fragment(R.layout.catalogo_mecanico) {

    private lateinit var recycler: RecyclerView
    private lateinit var adapter: MecanicoAdapter
    private val listaMecanicos = ArrayList<Mecanico>()

    private val url = "http://10.0.2.2/mecha/obtener_mecanicos.php"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler = view.findViewById(R.id.rvMecanicos)
        recycler.layoutManager = LinearLayoutManager(requireContext())
        adapter = MecanicoAdapter(listaMecanicos)
        recycler.adapter = adapter

        cargarMecanicos()
    }

    private fun cargarMecanicos() {

        val request = JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            { response ->

                listaMecanicos.clear()

                for (i in 0 until response.length()) {
                    val json = response.getJSONObject(i)

                    val m = Mecanico(
                        id = json.getInt("id"),
                        nombre = json.getString("nombre"),
                        telefono = json.getString("telefono")
                    )

                    listaMecanicos.add(m)
                }

                adapter.notifyDataSetChanged()
            },
            { error ->
                Toast.makeText(requireContext(),
                    "Error: ${error.message}", Toast.LENGTH_LONG).show()
            }
        )

        Volley.newRequestQueue(requireContext()).add(request)
    }
}
