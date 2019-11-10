package br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.controller

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.R
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.model.Transacao
import kotlinx.android.synthetic.main.transacao_celula.view.*

class TransacaoAdapter(private val transacoes: List<Transacao>,
                   private val context: Context?): RecyclerView.Adapter<TransacaoAdapter.TransacaoViewHolder>(){
    private var clickListener: ItemClickListener? = null

    override fun onBindViewHolder(holder: TransacaoViewHolder, position: Int) {
        val transacao = transacoes[position]
        holder.bindView(transacao)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransacaoViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.transacao_celula, parent, false)
        return TransacaoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return transacoes.size
    }

    inner class TransacaoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        val classificacao = itemView.classificacao
        val valor = itemView.valor

        fun bindView(transacao: Transacao) {
            classificacao.text = transacao.classificacao.nome
            if (transacao.valor < 0)
                valor.setTextColor(Color.RED)
            else valor.setTextColor(Color.BLUE)
            valor.text = String.format("%.2f",transacao.valor)
        }

        override fun onClick(view: View) {
         clickListener?.onItemClick(adapterPosition)
        }
    }

    fun getTransacaoList(): List<Transacao> {
        return transacoes
    }

    fun setClickListener(itemClickListener: ItemClickListener) {
        this.clickListener = itemClickListener
    }

    interface ItemClickListener {
        fun onItemClick(position: Int)
    }
}