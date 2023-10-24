package com.example.seance3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CategoryAdapter
    private lateinit var progressIndicator: CircularProgressIndicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recycler_view)
        progressIndicator = findViewById(R.id.progress_indicator)
        performNetworkCall()
    }

    private fun displayCategories(categories: List<Category>) {
        adapter = CategoryAdapter(this, categories)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.visibility = View.VISIBLE
        progressIndicator.visibility = View.GONE

    }

    private fun onNetworkCallError() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Erreur")
            .setMessage("Une erreur s'est produite.")
            .setPositiveButton("Réessayer", { _, _ ->
                performNetworkCall()
            })
            .show()
    }

    private fun performNetworkCall() {

        recyclerView.visibility = View.GONE
        progressIndicator.visibility = View.VISIBLE

        progressIndicator.isIndeterminate = true

        val client = OkHttpClient()
        //val url = URL("https://www.themealdb.com/api/json/v1/1/categories.php")
        val url = URL("https://choidshfoidshoix")
        val request = Request.Builder().url(url).build()
        client
            .newCall(request)
            .enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.i("OKHTTP", "OnFailure: ${e.localizedMessage}")

                runOnUiThread {
                    onNetworkCallError()
                }
            }
            override fun onResponse(call: Call, response: Response) {
                Log.i("OKHTTP", "OnSuccess")

                val gson = Gson()
                val categoriesResponse = gson.fromJson(response.body?.string(),
                             CategoriesResponse::class.java)

                Thread.sleep(3000)

                runOnUiThread {
                    displayCategories(categoriesResponse.categories)
                }

            }
        })

    }

}