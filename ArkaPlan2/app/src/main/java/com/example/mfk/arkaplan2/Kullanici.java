package com.example.mfk.arkaplan2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by MfK on 23.11.2017.
 */

public class Kullanici extends Activity {

    private VeriTabani erisim;
    public String kAdi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.kullanici);

        erisim = new VeriTabani(this);

        final EditText nickOrEmail = (EditText)findViewById(R.id.emailornick);
        final EditText sifreGiris = (EditText)findViewById(R.id.GirisSifre);

        Button giris;
        giris =(Button)findViewById(R.id.butonGiris);

        giris.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                boolean editTextControl = isEmpty(nickOrEmail,sifreGiris);
                //boolean kontrol  = erisim.GirisKontrol(nickOrEmail.getText().toString());

                try {
                    if(editTextControl){

                        String kontrol=erisim.GirisKontrol(nickOrEmail.getText().toString());
                        kAdi = nickOrEmail.getText().toString();

                        if(sifreGiris.getText().toString().equals(kontrol)){
                            Toast.makeText(Kullanici.this,"GİRİŞ BAŞARILI AFERİN",Toast.LENGTH_LONG).show();
                            Intent i = new Intent(Kullanici.this,OyunGiris.class);
                            startActivity(i);
                            finish();
                        }
                        else{
                            Toast.makeText(Kullanici.this,"Kullanıcı adı veya şifre hatalı",Toast.LENGTH_LONG).show();
                        }

                    }


                }
                finally {
                        erisim.close();
                }
            }
        });


    }

    private boolean isEmpty(EditText nickorEmail, EditText sifrGiris) {
        if (!(nickorEmail.getText().toString().trim().length() > 0)) {
            Toast.makeText(Kullanici.this, "LÜTFEN EMAİL YADA KULLANICI ADINIZI GİRİNİZ", Toast.LENGTH_LONG).show();
            return false;
        } else if (!(sifrGiris.getText().toString().trim().length() > 0)) {
            Toast.makeText(Kullanici.this, "LÜTFEN ŞİFRENİZİ GİRİNİZ", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }


}
