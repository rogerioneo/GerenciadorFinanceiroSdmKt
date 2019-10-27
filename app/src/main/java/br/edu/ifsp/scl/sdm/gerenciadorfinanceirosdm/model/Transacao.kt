package br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.model

import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.utils.Operacao
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.utils.Status
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.utils.Tipo
import java.sql.Timestamp

class Transacao(
        val id: Int,
        val contaSacado: Conta,
        val contaRecebedor: Conta? = null,
        val tipo: Tipo,
        val operacao: Operacao,
        val classificacao: Classificacao,
        val descricao: String,
        var dataHora: Timestamp,
        val valor: Double,
        var status: Status,
        var erro: String)