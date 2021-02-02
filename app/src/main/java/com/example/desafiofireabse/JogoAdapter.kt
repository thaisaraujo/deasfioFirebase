package com.example.desafiofireabse

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_jogo.view.*

class JogoAdapter (val catalogo: ArrayList<Jogo>,
                   val listener: OnItemClickListener): RecyclerView.Adapter<JogoAdapter.CardViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JogoAdapter.CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_jogo, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: JogoAdapter.CardViewHolder, position: Int) {
        var jogoSelecionado = catalogo[position]
        holder.nomeJogo.text = jogoSelecionado.nome
        holder.anoJogo.text = jogoSelecionado.ano
        if(jogoSelecionado.img != null){
            Picasso.get().load(jogoSelecionado.img).fit().into(holder.imgJogo)
        }
    }

    override fun getItemCount() = catalogo.size

    interface OnItemClickListener{
        fun jogoClick(position: Int, id: Int, nomeJogo:String, anoJogo: String)
    }

    inner class CardViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        val nomeJogo: TextView = view.nomeJogo
        val anoJogo: TextView = view.anoJogo
        val imgJogo: ImageView = view.imgJogo

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            var id = catalogo[position].id
            var anoJogo = catalogo[position].ano
            var nomeJogo = catalogo[position].nome

            if(RecyclerView.NO_POSITION != position)
                listener.jogoClick(position,id,nomeJogo,anoJogo)
        }

    }

}

