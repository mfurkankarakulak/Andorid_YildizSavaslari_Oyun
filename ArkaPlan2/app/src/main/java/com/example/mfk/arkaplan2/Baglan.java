package com.example.mfk.arkaplan2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by MfK on 11.12.2017.
 */

public class Baglan  extends Activity {

    String str;
    Kullanici kadi;

    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.baglan);

        final EditText kAdi = (EditText) findViewById(R.id.giris_adi);
        Button btnGiris = (Button) findViewById(R.id.btnGiris);

        btnGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str = kAdi.getText().toString();
                Intent intent = new Intent(Baglan.this,Oyun.class);
                intent.putExtra("kAdi", str);
                startActivity(intent);
                finish();

            }
        });
    }

}
