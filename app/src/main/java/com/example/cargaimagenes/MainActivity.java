package com.example.cargaimagenes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.VoiceInteractor;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerViewHeroes;
    RequestQueue requestQueue;
    List<Heroe> listaHeroes;
    private static final String URL="https://simplifiedcoding.net/demos/view-flipper/heroes.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaHeroes=new ArrayList();
        recyclerViewHeroes=findViewById(R.id.rVHerores);
        requestQueue= Volley.newRequestQueue(getBaseContext());
        jsonArrayRequest();

    }

    private void llenarLista(){
        recyclerViewHeroes.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getBaseContext(),2);
        recyclerViewHeroes.setLayoutManager(gridLayoutManager);
        RecyclerViewAdapter recyclerViewAdapter=new RecyclerViewAdapter(listaHeroes,getBaseContext());
        recyclerViewHeroes.setAdapter(recyclerViewAdapter);
    }
    private void stringRequest(){
        StringRequest stringRequest=new StringRequest(
                Request.Method.GET,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(stringRequest);
    }
    private void jsonArrayRequest(){
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("XD", "OK: "+response);
                        try {
                            JSONArray jsonArray=response.getJSONArray("heroes");
                            int size=jsonArray.length();
                            for (int i=0; i<size; i++){
                                JSONObject jsonObject=new JSONObject(jsonArray.get(i).toString());
                                Heroe heroe=new Heroe(jsonObject.getString("imageurl"),jsonObject.getString("name"));
                                listaHeroes.add(heroe);
                            }
                            llenarLista();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("XD", "onErrorResponse: "+error);
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);

    }
}