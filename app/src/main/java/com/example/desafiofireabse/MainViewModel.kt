package com.example.desafiofireabse

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.CollectionReference

class MainViewModel (val cr: CollectionReference): ViewModel() {

    val retornoJogos = MutableLiveData<ArrayList<Jogo>>()

    fun addJogo(){

        cr.get().addOnSuccessListener { documents ->
            var catalogo = arrayListOf<Jogo>()
            var cont=0;
            for(doc in documents){
                var item:Jogo = doc.toObject(Jogo::class.java)
                item.id = cont
                catalogo.add(item)
                cont +=1
            }
            retornoJogos.value = catalogo
        }
    }
}