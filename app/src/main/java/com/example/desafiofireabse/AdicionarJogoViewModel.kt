package com.example.desafiofireabse

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.CollectionReference

class AdicionarJogoViewModel(val cr: CollectionReference): ViewModel() {
    val enviar = MutableLiveData<Boolean>()
    val imgJogo = MutableLiveData<String>()

    fun enviarJogo(jogo: Jogo){
        cr.document().set(jogo).addOnSuccessListener {
            enviar.value = true
        }.addOnCanceledListener {
            enviar.value = false
        }
    }

    fun savarURL(img: String?){
        imgJogo.value = img
    }

}