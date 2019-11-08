package br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.model

import java.io.Serializable

class Conta(
    var id: Int,
    var descricao: String,
    var saldoInicial: Double,
    var saldoFinal: Double = saldoInicial) :
    Serializable {
    fun somarAoSaldo(valor: Double) {
        this.saldoFinal += valor
    }
    fun subtrairDoSaldo(valor: Double){
        this.saldoFinal -= valor
    }
}