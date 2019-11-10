package br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.R
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.controller.ContaAdapter
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.model.Conta


class ContaFragment(val contas: List<Conta>) : Fragment() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_conta, container, false)
        recyclerView = rootView.findViewById(R.id.contas_list_recyclerview) as RecyclerView // Add this
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter =
            ContaAdapter(contas, context)
        recyclerView.adapter = adapter

        adapter.setClickListener(object : ContaAdapter.ItemClickListener {
            override fun onItemClick(position: Int) {
//                val conta = adapter.getContaList().get(position)
//                val i = Intent(rootView.context, CadastraConta::class.java)
//                i.putExtra("conta", conta)
//                startActivityForResult(i, 2)
            }
        })
        return rootView
    }


}
