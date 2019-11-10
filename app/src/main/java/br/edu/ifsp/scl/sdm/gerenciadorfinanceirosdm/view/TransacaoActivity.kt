package br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.R
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.controller.TransacaoAdapter
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.data.TransacaoDAO
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.model.Transacao
import java.util.*

class TransacaoActivity : AppCompatActivity() {

    var transacoes: List<Transacao> = ArrayList<Transacao>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transacao)

        val dao = TransacaoDAO(this)
        val recyclerView = this.findViewById(R.id.transacoes_list_recyclerview) as RecyclerView
        val layout = LinearLayoutManager(this)
        recyclerView.layoutManager = layout
        transacoes = dao.listaTransacoes()
        val adapter = TransacaoAdapter(transacoes,this)
        recyclerView.adapter = adapter


        adapter.setClickListener(object : TransacaoAdapter.ItemClickListener {
            override fun onItemClick(position: Int) {
//                val Transacao = adapter.getTransacaoList().get(position)
//                val i = Intent(applicationContext, DetalheActivity::class.java)
//                i.putExtra("contato", contato)
//                startActivityForResult(i, 2)
            }
        })
    }
}
