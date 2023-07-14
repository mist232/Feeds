package com.example.feeds

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException

class FeedActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var requestQueue: RequestQueue? = null
    private var mList: MutableList<Item>? = null
    private var b: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.feed)

        b = findViewById<ImageButton>(R.id.rectangle_1)
        recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = LinearLayoutManager(this)

        requestQueue = VolleySingleton.getInstance(this).getRequestQueue()
        mList = ArrayList()
        fetchData()

        b?.setOnClickListener(View.OnClickListener {
            mList?.clear() // Clear the existing data in the list
            fetchData() // Fetch new data from the API

            // Create a new adapter with the updated data and set it to the RecyclerView
            val adapter = PostAdapter(this@FeedActivity, mList as ArrayList<Item>)
            recyclerView?.adapter = adapter
            adapter.notifyDataSetChanged()
        })
    }

    private fun fetchData() {
        val url = "https://api.unsplash.com/photos/random?count=10&client_id=6NFvgLYwgZfLcfqztgaJAjqOQbiJTJNMMwsCpxPb6rc"
        val requestQueue = Volley.newRequestQueue(this)
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                try {
                    for (i in 0 until 10) {
                        val jsonObject = response.getJSONObject(i)
                        val urlsObject = jsonObject.getJSONObject("urls")
                        val imageUrl = urlsObject.getString("regular")
                        val post = Item(imageUrl)
                        mList?.add(post)
                    }

                    // Create a new adapter with the updated data and set it to the RecyclerView
                    val adapter = mList?.let { PostAdapter(this@FeedActivity, it) }
                    recyclerView?.adapter = adapter
                    if (adapter != null) {
                        adapter.notifyDataSetChanged()
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        ) { error ->
            Toast.makeText(
                this@FeedActivity,
                "Error fetching data: " + error.message,
                Toast.LENGTH_SHORT
            ).show()
        }
        requestQueue.add(jsonArrayRequest)
    }
}
