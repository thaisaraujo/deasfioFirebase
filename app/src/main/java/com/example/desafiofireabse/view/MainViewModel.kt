package com.example.desafiofireabse.view

import androidx.lifecycle.ViewModel
import com.example.desafiofireabse.domain.Jogo
import com.google.firebase.firestore.CollectionReference

class MainViewModel (val collectionReference: CollectionReference): ViewModel() {

    val retornoJogos = ArrayList<Jogo>()

    fun addJogo(jogo: Jogo){
        collectionReference.document().set(jogo)
    }
}