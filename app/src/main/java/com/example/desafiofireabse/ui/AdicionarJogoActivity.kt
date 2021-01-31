package com.example.desafiofireabse.ui

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.desafiofireabse.R
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_adicionar_jogo.*
import kotlinx.android.synthetic.main.activity_jogo.*

class AdicionarJogoActivity : AppCompatActivity() {
    lateinit var alertDialog: AlertDialog
    lateinit var storageReference: StorageReference
    private val CODE_IMG = 1000 //para recuperar a imagem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_jogo)
        config()

        fbFoto.setOnClickListener {
            getRes()
        }

    }



    fun config(){
        alertDialog = SpotsDialog.Builder().setContext(this).build()
        storageReference = FirebaseStorage.getInstance().getReference("img")
    }

    //ira pegar um recurso
    fun getRes(){
        val intent = Intent()
        intent.type = "image/"  //tipo de arquivo que ira receber
        intent.action = Intent.ACTION_GET_CONTENT //retorno da imagem
        startActivityForResult(Intent.createChooser(intent, "Captura Imagem"), CODE_IMG)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //verificar imagem para envia para firebase
        if(requestCode == CODE_IMG){
            alertDialog.show()
            val uploadFile = storageReference.putFile(data!!.data!!)
            val task = uploadFile.continueWithTask{task ->
                if(task.isSuccessful){
                    Toast.makeText(this, "Imagem carregada com sucesso!", Toast.LENGTH_SHORT).show()
                }
                storageReference!!.downloadUrl
            }.addOnCompleteListener { task ->
                if(task.isSuccessful){
                    val downloadUri = task.result
                    val url = downloadUri!!.toString().substring(0, downloadUri.toString().indexOf("&token"))
                    Log.i("URL da Imagem", url)
                    alertDialog.dismiss()
                    Picasso.get().load(url).into(fbFoto)
                }
            }
        }
    }


}