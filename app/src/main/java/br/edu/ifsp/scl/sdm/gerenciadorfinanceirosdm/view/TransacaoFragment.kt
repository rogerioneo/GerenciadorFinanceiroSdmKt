package br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.R
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.controller.TransacaoAdapter
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.data.TransacaoDAO
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.model.Classificacao
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.model.Conta
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.model.Transacao
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.utils.Operacao
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.utils.Status
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.utils.Tipo
import java.sql.Timestamp
import java.time.LocalDateTime
import java.util.*

class TransacaoFragment(val main: MainActivity, val conta: Conta) : Fragment() {

    var transacoes: List<Transacao> = ArrayList<Transacao>()

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_transacao, container, false)
        val dao = TransacaoDAO(rootView.context)
        val recyclerView = rootView.findViewById(R.id.transacoes_list_recyclerview) as RecyclerView
        val layout = LinearLayoutManager(rootView.context)
        recyclerView.layoutManager = layout
        val transacoes = dao.listaTransacoes()
        val adapter = TransacaoAdapter(transacoes,rootView.context)
        recyclerView.adapter = adapter


        adapter.setClickListener(object : TransacaoAdapter.ItemClickListener {
            override fun onItemClick(position: Int) {
//                val Transacao = adapter.getTransacaoList().get(position)
//                val i = Intent(applicationContext, DetalheActivity::class.java)
//                i.putExtra("contato", contato)
//                startActivityForResult(i, 2)
            }
        })


        return rootView
    }
}
