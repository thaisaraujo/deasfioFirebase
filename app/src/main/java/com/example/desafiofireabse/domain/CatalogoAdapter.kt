package com.example.desafiofireabse.domain

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiofireabse.R
import kotlinx.android.synthetic.main.card_jogo.view.*

class CatalogoAdapter (private val catalogo: ArrayList<Jogo>,
                      private val listener: OnItemClickListener):
                      RecyclerView.Adapter<CatalogoAdapter.CardViewHolder>() {

   inner class CardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        //Dados do jogo
        val nomeView: TextView = itemView.nomeJogo
        val anoView: TextView = itemView.anoJogo
        val imgView: ImageView = itemView.imgJogo

        init {
            itemView.setOnClickListener(this)
        }

       override fun onClick(v: View?) {
           if(adapterPosition != RecyclerView.NO_POSITION){
               listener.onItemClick(adapterPosition)
           }
       }
   }

    //Posicao do card do restaurante
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val cardView = LayoutInflater.from(parent.context).inflate(R.layout.activity_main, parent, false)
        return  CardViewHolder(cardView)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        var cardSelecionado = catalogo[position]
        holder.nomeView.text = cardSelecionado.nome
        holder.anoView.text = cardSelecionado.ano
        holder.imgView.setImageResource(cardSelecionado.img)
    }

    override fun getItemCount() = catalogo.size


}


