package com.example.mfk.arkaplan2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

/**
 * Created by MfK on 23.11.2017.
 */

public class OyunGiris extends Activity {
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.oyun_giris);

        Button buton = (Button) findViewById(R.id.oyunGiris);
        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OyunGiris.this,Oyun.class);
                startActivity(intent);
                finish();
            }
        });
      Button Cbuton = (Button)findViewById(R.id.cokluGiris);

        Cbuton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                Intent intent = new Intent(OyunGiris.this,Baglan.class);
                startActivity(intent);
                finish();

            }
        });
    }
}
