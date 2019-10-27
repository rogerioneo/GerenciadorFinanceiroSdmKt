package br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.model

class Conta (val id: Int,
             var descricao: String,
             val saldoInicial: Double,
             var saldoFinal: Double = saldoInicial) {
    fun somarAoSaldo(valor: Double) {
        this.saldoFinal += valor
    }
    fun subtrairDoSaldo(valor: Double){
        this.saldoFinal -= valor
    }
}