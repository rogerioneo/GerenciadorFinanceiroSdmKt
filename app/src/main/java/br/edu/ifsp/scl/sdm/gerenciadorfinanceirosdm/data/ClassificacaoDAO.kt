package br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.data

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.model.Classificacao

class ClassificacaoDAO(context: Context) {

    private val dbhelper: SQLiteHelper = SQLiteHelper(context)

    object ClassificacaoEntry : BaseColumns {
        const val TABLE_NAME = "classificacao"
        const val COLUMN_ID = BaseColumns._ID
        const val COLUMN_NOME = "cla_nome"
    }

    companion object {
        fun deleteTable() = "DROP TABLE IF EXISTS ${ClassificacaoEntry.TABLE_NAME}"
        fun creteTable() =
            "CREATE TABLE ${ClassificacaoEntry.TABLE_NAME} (" +
                    "${ClassificacaoEntry.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${ClassificacaoEntry.COLUMN_NOME} TEXT)"
    }

    fun incluir(classificacao: Classificacao){
        val database = dbhelper.writableDatabase
        val values = ContentValues()
        values.put(ClassificacaoEntry.COLUMN_NOME, classificacao.nome)
        database.insert(ClassificacaoEntry.TABLE_NAME, null, values)
        database.close()
    }

    fun atualizar(classificacao: Classificacao){
        val database = dbhelper.writableDatabase
        val values = ContentValues()
        values.put(ClassificacaoEntry.COLUMN_NOME, classificacao.nome)
        database.update(ClassificacaoEntry.TABLE_NAME, values,
            ClassificacaoEntry.COLUMN_ID + "=?",
            arrayOf(classificacao.id.toString()))
        database.close()
    }

    fun excluir(classificacao: Classificacao) {
        val database = dbhelper.writableDatabase
        database.delete(ClassificacaoEntry.TABLE_NAME,
            ClassificacaoEntry.COLUMN_ID + "=?",
            arrayOf(classificacao.id.toString()))
        database.close()
    }
}