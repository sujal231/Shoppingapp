package com.example.shoppingapp

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.exam.productAdapter
import com.example.exam.productmodel
import com.example.shoppingapp.databinding.ActivityShowBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class ShowActivity : AppCompatActivity() {
    lateinit var binding: ActivityShowBinding
    lateinit var db: FirebaseFirestore
    lateinit var ref: DatabaseReference
    var list = ArrayList<productmodel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ref = FirebaseDatabase.getInstance().reference
        db = FirebaseFirestore.getInstance()
        list = ArrayList()
        list.clear()


//        db.collection("user").addSnapshotListener(object : EventListener<QuerySnapshot> {
//
//            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
//
//                if (error == null) {
//                    val data2: List<productmodel> = value!!.toObjects(productmodel::class.java)
//
//                    f()
//                    list.addAll(data2)
//
//
//                    var adapter = productAdapter()
//                    binding.rcv.layoutManager = LinearLayoutManager(this@ShowActivity)
//                    binding.rcv.adapter = adapter
//                    adapter.update(list)
//                    adapter.read(list)
//
//                }
//            }
//
//        })

        db.collection("user").addSnapshotListener { documents: QuerySnapshot?, error ->
            if (error != null) {
                val data2: List<productmodel> = documents!!.toObjects(productmodel::class.java)

//                f()
                list.addAll(data2)


                var adapter = productAdapter()
                binding.rcv.layoutManager = GridLayoutManager(this@ShowActivity,2)
                binding.rcv.adapter = adapter
                adapter.update(list)
                adapter.read(list)
            } else {
                Log.e(TAG, "onCreate: 12345678901234567890123")
                Log.e(TAG, "onCreate: $error")
            }
            if (documents != null) {
                for (document in documents) {
                    Log.e(TAG, "onCreate: $documents")
                }

                val data2: List<productmodel> = documents.toObjects(productmodel::class.java)

//                f()
                list.addAll(data2)


                var adapter = productAdapter()
                binding.rcv.layoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
                binding.rcv.adapter = adapter
                adapter.update(list)
                adapter.read(list)
            } else {
                Log.e(TAG, "onCreate: ++++++++++++123+++++++++++")
                Log.e(TAG, "onCreate: $error")
            }
        }

    }


}