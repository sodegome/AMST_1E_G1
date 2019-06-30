package com.example.app_pingui_g1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {
    private String token = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent login = getIntent();
        this.token = (String)login.getExtras().get("token");
    }

    public void obtieneTemperatura(View v){
        Intent intent = new Intent(getBaseContext(), temperatura.class);
        intent.putExtra("token", token);
        startActivity(intent);

    }

    public void ObtieneRecorrido (View v){
        Intent intent = new Intent(getBaseContext(), recorrido.class);
        intent.putExtra("token", token);
        startActivity(intent);

    }

    public void salir(View v){
        this.finish();
        System.exit(0);

        MainActivity h = new MainActivity();
        h.finish();

    }



}
