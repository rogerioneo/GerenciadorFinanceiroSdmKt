package br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.provider.BaseColumns
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.model.Conta
import java.util.ArrayList


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


    fun listaContas(): ArrayList<Conta> {
        val database = dbhelper.readableDatabase
        val contas = ArrayList<Conta>()

        val cursor: Cursor

        cursor = database.query(
            ContaEntry.TABLE_NAME,
            null, null, null, null, null,
            ContaEntry.COLUMN_DESCRICAO
        )
        while (cursor.moveToNext()) {
            val conta = Conta(cursor.getInt(cursor.getColumnIndex(ContaEntry.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(ContaEntry.COLUMN_DESCRICAO)),
                cursor.getDouble(cursor.getColumnIndex(ContaEntry.COLUMN_SALDO_INICIAL)))
            contas.add(conta)
        }

        cursor.close()
        database.close()

        return contas
    }

    fun getConta(id: Int): Conta? {
        val database = dbhelper.readableDatabase
        var conta: Conta? = null

        val cursor: Cursor

        cursor = database.query(
            ContaEntry.TABLE_NAME,
            null,
            "where ("+ContaEntry.COLUMN_ID+"="+id+")",
            null, null, null,null
        )
        if (cursor.count > 0) {
            conta = Conta(cursor.getInt(cursor.getColumnIndex(ContaEntry.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(ContaEntry.COLUMN_DESCRICAO)),
                cursor.getDouble(cursor.getColumnIndex(ContaEntry.COLUMN_SALDO_INICIAL)))
        }

        cursor.close()
        database.close()

        return conta
    }

    fun incluir(conta: Conta):Int{
        val database = dbhelper.writableDatabase
        val values = ContentValues()
        values.put(ContaEntry.COLUMN_DESCRICAO, conta.descricao)
        values.put(ContaEntry.COLUMN_SALDO_INICIAL, conta.saldoInicial)
        values.put(ContaEntry.COLUMN_SALDO_FINAL, conta.saldoFinal)
        val id = database.insert(ContaEntry.TABLE_NAME, null, values)
        database.close()
        return id.toInt()
    }

    fun excluir(conta: Conta):Int{
        val database = dbhelper.writableDatabase
        val id = database.delete(ContaEntry.TABLE_NAME,
            ContaEntry.COLUMN_ID + "=" + conta.id, null)
        database.close()
        return id.toInt()
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

    fun alterarSaldoInicial(conta: Conta){
        val database = dbhelper.writableDatabase
        val values = ContentValues()
        values.put(ContaEntry.COLUMN_SALDO_INICIAL, conta.saldoInicial)
        database.update(ContaEntry.TABLE_NAME, values,
            "${ContaEntry.COLUMN_ID}=?",
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
}

