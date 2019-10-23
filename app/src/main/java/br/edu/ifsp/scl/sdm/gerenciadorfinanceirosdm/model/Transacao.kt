package br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.model

import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.utils.Operacao
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.utils.Status
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.utils.Tipo
import java.sql.Timestamp

class Transacao(
        tipo: Tipo,
        operacao: Operacao,
        classificacao: Classificacao,
        descricao: String,
        dataHora: Timestamp,
        valor: Double,
        status: Status,
        erro: String){

}