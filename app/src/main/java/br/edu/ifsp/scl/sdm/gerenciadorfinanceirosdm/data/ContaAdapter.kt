package br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.R
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.model.Conta
import kotlinx.android.synthetic.main.conta_celula.view.*

class ContaAdapter(private val contas: List<Conta>,
                   private val context: Context?): RecyclerView.Adapter<ContaAdapter.ViewHolder>(){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val conta = contas[position]
        holder.let {
            it.descricao.text = conta.descricao
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.conta_celula, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contas.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val descricao = itemView.descricao_conta
        fun bindView(conta: Conta) {
            descricao.text = conta.descricao
        }
    }
}
