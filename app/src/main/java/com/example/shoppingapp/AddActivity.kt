package com.example.shoppingapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.exam.productmodel
import com.example.shoppingapp.databinding.ActivityAddBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class AddActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddBinding
    lateinit var db: FirebaseFirestore
    lateinit var storageRef: StorageReference
    lateinit var dbref: DatabaseReference
    var imagecode = 2
    lateinit var uri: Uri
    private val TAG = "AddActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()
        dbref = FirebaseDatabase.getInstance().reference
        storageRef = FirebaseStorage.getInstance().reference

        binding.btnadd.setOnClickListener {
            var title = binding.edttitel.text.toString()
            var text = binding.edttext.text.toString()
            var price = binding.edtprice.text.toString()


            val ref = storageRef.child("images/${uri.lastPathSegment}.jpg")
            var uploadTask = ref.putFile(uri)

            val urlTask = uploadTask.continueWithTask { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        throw it
                    }
                }
                ref.downloadUrl
            }.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val downloadUri = task.result
                    var key = dbref.root.push().key!!

                    var data = productmodel(key, title, text, downloadUri.toString(), price)

                    db.collection("user").document().set(data).addOnCompleteListener {
                        if (task.isSuccessful) {
                            Toast.makeText(this, "123456", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "e", Toast.LENGTH_SHORT).show()
                        }
                    }

                    dbref.root.child("img").child(key).setValue(data).addOnCompleteListener {
                        binding.edttitel.text.clear()
                        binding.edttext.text.clear()
                        binding.edtprice.text.clear()
                        Log.e(TAG, "onCreate: 11111111111111111111 $")
                    }
                        .addOnFailureListener {
                            Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
                            Log.e(TAG, "onCreate: ================================= ${it.message}")
                        }
                } else {

                }
            }


        }
        binding.imguplodeimg.setOnClickListener {

            var intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"

            startActivityForResult(intent, imagecode)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            if (requestCode == imagecode) {
                uri = data?.data!!
                binding.imguplodeimg.setImageURI(uri)
            }
        }
    }
}