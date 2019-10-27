package br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.model

class Conta (val id: Int,
             val descricao: String,
             val saldoInicial: Int,
             val saldoFinal: Int = saldoInicial)