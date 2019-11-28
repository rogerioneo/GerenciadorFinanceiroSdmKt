package br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.view

import android.content.Intent
import android.graphics.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.R
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.controller.ContaAdapter
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.data.ContaDAO
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.model.Conta
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.utils.Fragments
import java.util.*


class ContaFragment(val main: MainActivity, val contas: ArrayList<Conta>) : Fragment() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_conta, container, false)
        val contaDAO = ContaDAO(rootView.context)
        recyclerView = rootView.findViewById(R.id.contas_list_recyclerview) as RecyclerView // Add this
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter =
            ContaAdapter(contas, context)
        recyclerView.adapter = adapter

        adapter.setClickListener(object : ContaAdapter.ItemClickListener {
            override fun onItemClick(position: Int) {
                val conta = adapter.getContaList()[position]
                val intent = Intent(rootView.context, TransacaoFragment::class.java)
                intent.putExtra("conta", conta)
                main.updateFragment(TransacaoFragment(main, conta), Fragments.Transacao.tag)
            }
        })

        //Efeito deslizando o item para os lados, o resultado disso é apagar a conta
        val simpleCallback = object : ItemTouchHelper.SimpleCallback(
            0, //Para mover para cima ou para baixo        (ItemTouchHelper.UP | ItemTouchHelper.DOWN)
            ItemTouchHelper.RIGHT
        ) {    //Para mover para a esquerda ou direita     (ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT)
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val c = adapter.getContaList()[viewHolder.adapterPosition]
                contaDAO.excluir(c)
                val count = adapter.apagarConta(c)
                if (count == 0) {
                    main.atualizaView(contas)
                }
                Toast.makeText(
                    rootView.context, "Conta " + c.descricao + " excluída",
                    Toast.LENGTH_LONG
                ).show()
            }

            //Exibe uma imagem e muda a cor de fundo quando esta deslizando
            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean
            ) {
                val icon: Bitmap = BitmapFactory.decodeResource(resources, android.R.drawable.ic_delete)
                val p = Paint()

                val itemView = viewHolder.itemView
                val height = itemView.bottom.toFloat() - itemView.top.toFloat()
                val width = height / 3

                p.color =
                    ContextCompat.getColor(rootView.context, R.color.backgroundDelete)
                val background = RectF(
                    itemView.left.toFloat(),
                    itemView.top.toFloat(), dX, itemView.bottom.toFloat()
                )
                c.drawRect(background, p)
                val iconDest = RectF(
                    itemView.left.toFloat() + width, itemView.top.toFloat() + width,
                    itemView.left.toFloat() + 2 * width, itemView.bottom.toFloat() - width
                )
                c.drawBitmap(icon, null, iconDest, null)
                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }
        }
        val itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        return rootView
    }
}
