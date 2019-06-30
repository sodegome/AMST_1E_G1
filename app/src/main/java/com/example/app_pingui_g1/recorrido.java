package com.example.app_pingui_g1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class recorrido extends AppCompatActivity {

    private RequestQueue mQueue;
    String token = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorrido);
        mQueue = Volley.newRequestQueue(this);

        Intent login = getIntent();
        this.token = (String)login.getExtras().get("token");
        obtRecorrido();
    }

    public void obtRecorrido() {

        Map<String, String> params = new HashMap();
        params.put("camion", "10");

        JSONObject parametros = new JSONObject(params);



        for ( int  i = 0; i < 10 ; i++){
            final TextView recorrido = (TextView) findViewById(R.id.txtOrigen1 +i);
            final TextView destino = (TextView) findViewById(R.id.txtDestino1 +i);
            String url_temp = "https://amstdb.herokuapp.com/db/recorrido/"+i;
            JsonObjectRequest request_temp = new JsonObjectRequest(
                    Request.Method.GET, url_temp, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            System.out.println(response);
                            try {
                                recorrido.setText(response.getString("origen") + " ");
                                destino.setText(response.getString("destino") + " ");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            }) {
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Authorization", "JWT " + token);
                    System.out.println(token);
                    return params;
                }
            };
            mQueue.add(request_temp);

        }
    }
}
