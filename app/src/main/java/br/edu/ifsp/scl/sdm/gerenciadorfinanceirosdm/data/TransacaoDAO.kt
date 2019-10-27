package br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.data

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.model.Transacao

class TransacaoDAO(context: Context) {

    private val dbhelper: SQLiteHelper = SQLiteHelper(context)

    object TransacaoEntry : BaseColumns {
        const val TABLE_NAME = "transacao"
        const val COLUMN_ID = BaseColumns._ID
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
                    "${TransacaoEntry.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
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

    fun incluir(transacao: Transacao){
        val database = dbhelper.writableDatabase
        val values = ContentValues()
        values.put(TransacaoEntry.COLUMN_CTA_REALIZA, transacao.contaSacado.id)
        if (transacao.contaRecebedor != null)
            values.put(TransacaoEntry.COLUMN_CTA_RECEBE, transacao.contaRecebedor.id)
        values.put(TransacaoEntry.COLUMN_TIPO, transacao.tipo.text)
        values.put(TransacaoEntry.COLUMN_OPERACAO, transacao.operacao.text)
        values.put(TransacaoEntry.COLUMN_CLASSIFICACAO, transacao.classificacao.id)
        values.put(TransacaoEntry.COLUMN_DESCRICAO, transacao.descricao)
        values.put(TransacaoEntry.COLUMN_DATA_HORA, transacao.dataHora.toString())
        values.put(TransacaoEntry.COLUMN_VALOR, transacao.valor)
        values.put(TransacaoEntry.COLUMN_STATUS, transacao.status.text)
        values.put(TransacaoEntry.COLUMN_ERRO, transacao.erro)
        database.insert(TransacaoEntry.TABLE_NAME, null, values)
        database.close()
    }

    fun atualizaStatus(transacao: Transacao){
        val database = dbhelper.writableDatabase
        val values = ContentValues()
        values.put(TransacaoEntry.COLUMN_DATA_HORA, transacao.dataHora.toString())
        values.put(TransacaoEntry.COLUMN_STATUS, transacao.status.text)
        values.put(TransacaoEntry.COLUMN_ERRO, transacao.erro)
        database.update(TransacaoEntry.TABLE_NAME,
                        values,
                        "${TransacaoEntry.COLUMN_ID}=?",
                        arrayOf(transacao.id.toString()))
        database.close()
    }
}