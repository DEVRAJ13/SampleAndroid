package com.example.xornor_pc.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.xornor_pc.myapplication.Base.AppConstants;
import com.example.xornor_pc.myapplication.adapter.ProductAdapter;
import com.example.xornor_pc.myapplication.model.Product;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    //the URL having the json data
    private static final String JSON_URL = AppConstants.baseUrl;

    CardView cardView;

    //a list to store all the products
    List<Product> productList;


    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        productList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
//        LinearLayoutManager manager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        loadNewsData();
    }






    private void loadNewsData(){
        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);

                            //we have the array named hero inside the object
                            //so here we are getting that json array
                            JSONArray newsArray = obj.getJSONArray("articles");


                            //now looping through all the elements of the json array
                            for (int i = 0; i < newsArray.length(); i++) {
                                //getting the json object of the particular index inside the array
                                JSONObject newsObject = newsArray.getJSONObject(i);
                                JSONObject sourceObject = newsObject.getJSONObject("source");

                                //creating a hero object and giving them the values from json object
                                Product news = new Product(
                                        sourceObject.getString("name"),
                                        newsObject.getString("author"),
                                        newsObject.getString("title"),
                                        newsObject.getString("description"),
                                        newsObject.getString("url"),
                                        newsObject.getString("urlToImage")
                                );

                                //adding some items to our list
                                productList.add(news);
                                Log.d("DATA", sourceObject.getString("name"));
                                Log.d("DATA", newsObject.getString("author"));
                                Log.d("DATA", newsObject.getString("title"));
                                Log.d("DATA", newsObject.getString("description"));
                                Log.d("DATA", newsObject.getString("url"));
                                Log.d("DATA", newsObject.getString("urlToImage"));
                                Log.d("DATA", newsObject.getString("publishedAt"));
                            }

                            //creating recyclerview adapter
                            ProductAdapter adapter = new ProductAdapter(HomeActivity.this, productList);

                            //setting adapter to recyclerview
                            recyclerView.setAdapter(adapter);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }
}
