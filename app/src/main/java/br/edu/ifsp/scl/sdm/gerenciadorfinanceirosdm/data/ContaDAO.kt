package br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.data

import android.provider.BaseColumns
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.model.Conta

class ContaDAO(){

    object ContaEntry : BaseColumns {
        const val TABLE_NAME = "conta"
        const val COLUMN_DESCRICAO = "cta_descricao"
        const val COLUMN_SALDO_INICIAL = "cta_saldo_inicial"
        const val COLUMN_SALDO_FINAL = "cta_saldo_final"
    }

    companion object {
        fun deleteTable() = "DROP TABLE IF EXISTS ${ContaEntry.TABLE_NAME}"
        fun creteTable() =
            "CREATE TABLE ${ContaEntry.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${ContaEntry.COLUMN_DESCRICAO} TEXT," +
                    "${ContaEntry.COLUMN_SALDO_INICIAL} DOUBLE, " +
                    "${ContaEntry.COLUMN_SALDO_FINAL} DOUBLE)"
    }

}

