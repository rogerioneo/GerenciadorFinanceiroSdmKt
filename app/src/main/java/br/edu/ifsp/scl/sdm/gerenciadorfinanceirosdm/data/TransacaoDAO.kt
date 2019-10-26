package br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.data

import android.provider.BaseColumns

class TransacaoDAO {
    object TransacaoEntry : BaseColumns {
        const val TABLE_NAME = "transacao"
        const val COLUMN_TIPO = "tra_tipo"
        const val COLUMN_OPERACAO = "tra_operacao"
        const val COLUMN_CLASSIFICACAO = "tra_cla_id"
        const val COLUMN_DESCRICAO = "tra_descricao"
        const val COLUMN_DATA_HORA = "tra_data_hora"
        const val COLUMN_VALOR = "tra_valor"
        const val COLUMN_STATUS = "tra_status"
        const val COLUMN_ERRO = "tra_erro"
        const val COLUMN_CTA_REALIZA = "tra_cta_id_realiza"
        const val COLUMN_CTA_RECEBE = "tra_cta_id_recebe"
    }

    companion object {
        fun deleteTable() = "DROP TABLE IF EXISTS ${TransacaoEntry.TABLE_NAME}"
        fun creteTable() =
            "CREATE TABLE ${TransacaoEntry.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${TransacaoEntry.COLUMN_CTA_REALIZA} INTEGER NOT NULL, " +
                    "${TransacaoEntry.COLUMN_CTA_RECEBE} INTEGER, " +
                    "${TransacaoEntry.COLUMN_TIPO} VARCHAR(20)," +
                    "${TransacaoEntry.COLUMN_OPERACAO} VARCHAR(20)," +
                    "${TransacaoEntry.COLUMN_CLASSIFICACAO} INTEGER," +
                    "${TransacaoEntry.COLUMN_DESCRICAO} TEXT," +
                    "${TransacaoEntry.COLUMN_DATA_HORA} DATETIME," +
                    "${TransacaoEntry.COLUMN_VALOR} DOUBLE, " +
                    "${TransacaoEntry.COLUMN_STATUS} VARCHAR(20), " +
                    "${TransacaoEntry.COLUMN_ERRO} TEXT, " +
                    "FOREIGN KEY(${TransacaoEntry.COLUMN_CTA_REALIZA}) " +
                    "   REFERENCES ${ContaDAO.ContaEntry.TABLE_NAME}(${BaseColumns._ID}), " +
                    "FOREIGN KEY(${TransacaoEntry.COLUMN_CTA_RECEBE}) " +
                    "   REFERENCES ${ContaDAO.ContaEntry.TABLE_NAME}(${BaseColumns._ID}), " +
                    "FOREIGN KEY(${TransacaoEntry.COLUMN_CLASSIFICACAO}) " +
                    "   REFERENCES ${ClassificacaoDAO.ClassificacaoEntry.TABLE_NAME}(${BaseColumns._ID}))"
    }
}