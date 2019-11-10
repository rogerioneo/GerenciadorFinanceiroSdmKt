package br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.controller

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.R
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.model.Conta
import kotlinx.android.synthetic.main.conta_celula.view.*

class ContaAdapter(private val contas: List<Conta>,
                   private val context: Context?): RecyclerView.Adapter<ContaAdapter.ContaViewHolder>(){

    private var clickListener: ItemClickListener? = null

    override fun onBindViewHolder(holder: ContaViewHolder, position: Int) {
        val conta = contas[position]
        holder.bindView(conta)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContaViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.conta_celula, parent, false)
        return ContaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contas.size
    }

    inner class ContaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        val descricao = itemView.descricao_conta
        val saldo = itemView.saldo

        fun bindView(conta: Conta) {
            descricao.text = conta.descricao
            if (conta.saldoFinal < 0)
                saldo.setTextColor(Color.RED)
            else saldo.setTextColor(Color.BLUE)
            saldo.text = String.format("%.2f",conta.saldoFinal)
        }

        override fun onClick(view: View) {
            clickListener?.onItemClick(adapterPosition)
        }
    }

    fun getContaList(): List<Conta> {
        return contas
    }

    fun setClickListener(itemClickListener: ItemClickListener) {
        this.clickListener = itemClickListener
    }

    interface ItemClickListener {
        fun onItemClick(position: Int)
    }
}

