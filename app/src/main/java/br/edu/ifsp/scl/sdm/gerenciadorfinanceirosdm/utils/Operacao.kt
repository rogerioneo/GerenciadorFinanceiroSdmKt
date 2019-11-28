package br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.utils

enum class Operacao(val text: String) {
    Nenhuma(""),
    Pagamento("Pagamento"),
    Recebimento("Recebimento"),
    Transferencia("Transferência")
}