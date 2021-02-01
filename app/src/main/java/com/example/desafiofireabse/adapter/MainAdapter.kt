package com.example.desafiofireabse.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiofireabse.R
import com.example.desafiofireabse.domain.Jogo
import kotlinx.android.synthetic.main.card_jogo.view.*

class MainAdapter (private var catalogo: ArrayList<Jogo>,
                   private var listener: OnItemClickListener):
                   RecyclerView.Adapter<MainAdapter.CardViewHolder>() {

    inner class CardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val nomeJogo: TextView = itemView.nomeJogo
        val anoJogo: TextView = itemView.anoJogo
        val imgJogo: ImageView = itemView.imgJogo

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            if(adapterPosition != RecyclerView.NO_POSITION){
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_jogo, parent, false)
        return CardViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        var jogoSelecionado = catalogo[position]

        holder.nomeJogo.text = jogoSelecionado.nome
        holder.anoJogo.text = jogoSelecionado.ano

    }

    override fun getItemCount() = catalogo.size

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

}