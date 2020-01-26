package com.example.namabarang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.HashMap;

import server.configurl;

public class MainActivity extends AppCompatActivity {

    private RequestQueue mRequestQueue;
    private TextView txtdatas;
    private EditText edtkode,edtnama,edtharga,edtjenis;
    private Button btninsert,btnread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRequestQueue = Volley.newRequestQueue(this);
        txtdatas=(TextView) findViewById(R.id.txtdatas);
        edtkode=(EditText)findViewById((R.id.edtkode));
        edtnama=(EditText)findViewById((R.id.edtnama));
        edtharga=(EditText)findViewById((R.id.edtharga));
        edtjenis=(EditText)findViewById((R.id.edtjenis));
        btninsert=(Button)findViewById((R.id.btninsert));
        btnread=(Button)findViewById(R.id.btnread);

        btninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strkode=edtkode.getText().toString();
                String strnama=edtnama.getText().toString();
                String strharga=edtharga.getText().toString();
                String strjenis=edtjenis.getText().toString();
                if(strkode.isEmpty()){
                    Toast.makeText(getApplicationContext(),"npm sudah digunakan",
                            Toast.LENGTH_LONG).show();
                }else
                if(strnama.isEmpty()){
                    Toast.makeText(getApplicationContext(),"nama sudah digunakan",
                            Toast.LENGTH_LONG).show();
                }else
                if(strharga.isEmpty()){
                    Toast.makeText(getApplicationContext(),"prodi sudah digunakan",
                            Toast.LENGTH_LONG).show();
                }else
                if(strjenis.isEmpty()){
                    Toast.makeText(getApplicationContext(),"password sudah digunakan",
                            Toast.LENGTH_LONG).show();
                }else
                {
                    inputdata(strkode,strnama,strharga,strjenis);
                    Intent a=new Intent(MainActivity.this,MainActivity.class);
                    startActivity(a);
                    finish();
                    Toast.makeText(getApplicationContext(),"Berhasil Input Data",
                            Toast.LENGTH_LONG).show();
                }
            }
        });


        btnread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(MainActivity.this,main2.class);
                startActivity(next);
                finish();
            }
        });

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


    private  void inputdata(String kodebarang,String namabarang,String harga,String jen){

// Post params to be sent to the server
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("kodebarang", kodebarang);
        params.put("namabarang", namabarang);
        params.put("harga", harga);
        params.put("jenis", jen);


        JsonObjectRequest req = new JsonObjectRequest(configurl.inputdatabarang, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            VolleyLog.v("Response:%n %s", response.toString(4));
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
        mRequestQueue.add(req);
    }
}
