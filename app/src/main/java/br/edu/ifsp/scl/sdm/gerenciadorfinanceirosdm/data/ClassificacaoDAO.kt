package br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.data

import android.provider.BaseColumns

class ClassificacaoDAO() {
    object ClassificacaoEntry : BaseColumns {
        const val TABLE_NAME = "classificacao"
        const val COLUMN_NOME = "cla_nome"
    }

    companion object {
        fun deleteTable() = "DROP TABLE IF EXISTS ${ClassificacaoEntry.TABLE_NAME}"
        fun creteTable() =
            "CREATE TABLE ${ClassificacaoEntry.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${ClassificacaoEntry.COLUMN_NOME} TEXT)"
    }
}