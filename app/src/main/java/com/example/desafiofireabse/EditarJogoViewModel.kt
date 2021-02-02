package com.example.desafiofireabse

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.getField

class EditarJogoViewModel(val cr: CollectionReference): ViewModel() {
    var imgJogo = MutableLiveData<String?>()
    var resJogo = MutableLiveData<Jogo>()
    var catalogo = MutableLiveData<ArrayList<Jogo>>()

    fun editarJogo(nomeJogo: String, anoJogo: String, jogo: Jogo){

        cr.get().addOnSuccessListener { documents ->
            for (doc in documents){
                if(anoJogo == doc.getField("Ano de Lançamento") && nomeJogo == doc.getField("Nome Jogo")){
                    var getRefe = doc.reference.path
                    var splitGetRefe = getRefe.split("/")
                    var itemFolder = "/" + splitGetRefe[1]

                    cr.document(itemFolder).update(
                        "nome", jogo.nome,
                        "ano", jogo.ano,
                        "descrição", jogo.descricao)

                    if(jogo.img != null){
                        cr.document(itemFolder).update("jogoURL", jogo.img)
                    }
                }
            }

        }
    }

    fun salvarURL(img: String?){
        if(img != null){
            imgJogo.value = img
        }
    }

    fun getJogo(nomeJogo: String, anoJogo: String){
        cr.get().addOnSuccessListener { documents ->
            for(doc in documents){
                if(anoJogo == doc.getField("anoJogo") && nomeJogo == doc.getField("nomeJogo")){
                    var getRefe = doc.reference.path
                    var splitGetRefe = getRefe.split("/")
                    var itemFolder = "/" + splitGetRefe[1]
                    var d = cr.document(itemFolder).get().addOnSuccessListener {
                        resJogo.value = doc.toObject(Jogo::class.java)
                    }
                }
            }
        }
    }
}