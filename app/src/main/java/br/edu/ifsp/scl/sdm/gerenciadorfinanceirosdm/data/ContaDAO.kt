package br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.provider.BaseColumns
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.model.Conta


class ContaDAO(context: Context){

    private val dbhelper: SQLiteHelper = SQLiteHelper(context)

    object ContaEntry : BaseColumns {
        const val TABLE_NAME = "conta"
        const val COLUMN_ID = BaseColumns._ID
        const val COLUMN_DESCRICAO = "cta_descricao"
        const val COLUMN_SALDO_INICIAL = "cta_saldo_inicial"
        const val COLUMN_SALDO_FINAL = "cta_saldo_final"
    }

    companion object {
        fun deleteTable() = "DROP TABLE IF EXISTS ${ContaEntry.TABLE_NAME}"
        fun creteTable() =
            "CREATE TABLE ${ContaEntry.TABLE_NAME} (" +
                    "${ContaEntry.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${ContaEntry.COLUMN_DESCRICAO} TEXT," +
                    "${ContaEntry.COLUMN_SALDO_INICIAL} DOUBLE, " +
                    "${ContaEntry.COLUMN_SALDO_FINAL} DOUBLE)"
    }

    fun incluir(conta: Conta){
        val database = dbhelper.writableDatabase
        val values = ContentValues()
        values.put(ContaEntry.COLUMN_DESCRICAO, conta.descricao)
        values.put(ContaEntry.COLUMN_SALDO_INICIAL, conta.saldoInicial)
        values.put(ContaEntry.COLUMN_SALDO_FINAL, conta.saldoFinal)
        database.insert(ContaEntry.TABLE_NAME, null, values)
        database.close()
    }

    fun alterarDescricao(conta: Conta){
        val database = dbhelper.writableDatabase
        val values = ContentValues()
        values.put(ContaEntry.COLUMN_DESCRICAO, conta.descricao)
        database.update(ContaEntry.TABLE_NAME, values,
                ContaEntry.COLUMN_ID + "=?",
                arrayOf(conta.id.toString()))
        database.close()
    }

    fun alterarSaldo(conta: Conta){
        val database = dbhelper.writableDatabase
        val values = ContentValues()
        values.put(ContaEntry.COLUMN_SALDO_FINAL, conta.saldoFinal)
        database.update(ContaEntry.TABLE_NAME, values,
            "${ContaEntry.COLUMN_ID}=?",
            arrayOf(conta.id.toString()))
        database.close()
    }
/*
    fun listaContatos(): List<Conta> {
        val database = dbhelper.writableDatabase

       val contas: MutableList()

        val cursor: Cursor

        cursor = database.query(
            ContaEntry.TABLE_NAME,
            null, null, null, null, null,
            ContaEntry.COLUMN_DESCRICAO
        )

        while (cursor.moveToNext()) {
            val c = Conta()


            contas.add(c)
        }

        cursor.close()
        database.close()

        return contas
    }

 */
}

