package com.example.namabarang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import server.configurl;

import android.content.Intent;

public class main2 extends AppCompatActivity {
    private RequestQueue mRequestQueue;
    private TextView txtdatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mRequestQueue = Volley.newRequestQueue(this);
        txtdatas=(TextView) findViewById(R.id.txtdatas);

        fetchJsonResponse();
    }

    private void fetchJsonResponse() {
        // Pass second argument as "null" for GET requests
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, configurl.getAllnamabarang, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String result = response.getString("data");
                            //Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                            //Log.v("ini data dari server",result.toString());

                            JSONArray res=new JSONArray(  result);
                            for(int i=0; i<res.length(); i++){
                                JSONObject jObj=res.getJSONObject(i);
                                txtdatas.append("Kode Barang =" +jObj.getString("kodebarang")+"\n"+
                                        "Nama Barang ="+jObj.getString("namabarang")+"\n"+
                                        "Harga Barang =" +jObj.getString("harga")+"\n"+
                                        "Jenis Barang =" +jObj.getString("jenis")+"\n\n");
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        });

        /* Add your Requests to the RequestQueue to execute */
        mRequestQueue.add(req);
    }

    @Override
    public void onBackPressed() {
        Intent back = new Intent(main2.this, MainActivity.class);
        startActivity(back);
        finish();

    }

}
