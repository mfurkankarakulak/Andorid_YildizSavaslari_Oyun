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


public class MainActivity extends Activity {

    private VeriTabani erisim;
   // private Kullanıcı gitme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*Intent intent = new Intent(MainActivity.this,OyunGiris.class);
        startActivity(intent);
        finish();*/

        erisim = new VeriTabani(this);

        // gitme = new Kullanıcı(this);
        final EditText Eisim = (EditText) findViewById(R.id.isim);
        final EditText Esoyad = (EditText) findViewById(R.id.soyad);
        final EditText Email = (EditText) findViewById(R.id.email);
        final EditText Esifre = (EditText) findViewById(R.id.sifre);
        final EditText Ekadi = (EditText) findViewById(R.id.kullanici);



        Button buton = (Button) findViewById(R.id.buton);
        buton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    VeriTabani db = new VeriTabani(getApplicationContext());
                    boolean editTextControl = isEmpty(Eisim,Esoyad,Email,Ekadi,Esifre);
                    if(editTextControl) {
                       long id = db.KayitEkle(Eisim.getText().toString(), Esoyad.getText().toString(), Ekadi.getText().toString(), Email.getText().toString(), Esifre.getText().toString());

                       //erisim.VeriTabanıKontrol(Eisim.getText().toString(), Esoyad.getText().toString(), Ekadı.getText().toString(),Email.getText().toString(), Esifre.getText().toString());
                        //setContentView(R.layout.kullanici_giris);

                        if (id == -1)
                        {
                            Toast.makeText(MainActivity.this, "HAY AKSİ! Kayıt Hatası Oluştu!!!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Kayıt işlemi başarılı...", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(MainActivity.this, Kullanici.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                    else
                        Toast.makeText(MainActivity.this,"BİLGİLERİ EKSİKSİZ GİRİNİZ",Toast.LENGTH_SHORT).show();

                    /* boolean kontrol = erisim.VeriTabanıKontrol(Eisim.getText().toString(), Esoyad.getText().toString(), Ekadı.getText().toString(), Email.getText().toString(), Esifre.getText().toString());
                    if(kontrol == false)
                    {
                        Toast.makeText(MainActivity.this,"VERİ TABANINA EKLEME BAŞARISIZ",Toast.LENGTH_LONG).show();
                    }
                    else{
                        //kullanıcı giriş ekranı açılacak
                    }*/


                }
                finally {
                    erisim.close();
                }
            }
        });
    }
    private boolean isEmpty(EditText Eisim, EditText Esoyad, EditText Email, EditText Enick, EditText Esifre) {
        if (!(Eisim.getText().toString().trim().length() > 0)) {
            Toast.makeText(MainActivity.this, "LÜTFEN İSMİNİZİ GİRİNİZ", Toast.LENGTH_LONG).show();
            return false;
        }
        else if (!(Esoyad.getText().toString().trim().length() > 0)) {
            Toast.makeText(MainActivity.this, "LÜTFEN SOYADINIZI GİRİNİZ", Toast.LENGTH_LONG).show();
            return false;
        }
        else if (!(Email.getText().toString().trim().length() > 0)) {
            Toast.makeText(MainActivity.this, "LÜTFEN EMAİLİNİZİ GİRİNİZ", Toast.LENGTH_LONG).show();
            return false;
        }
        else if (!(Enick.getText().toString().trim().length() > 0)) {
            Toast.makeText(MainActivity.this, "LÜTFEN KULLANICI ADINIZI GİRİNİZ", Toast.LENGTH_LONG).show();
            return false;
        }
        else if (!(Esifre.getText().toString().trim().length() > 0)) {
            Toast.makeText(MainActivity.this, "LÜTFEN ŞİFRENİZİ GİRİNİZ", Toast.LENGTH_LONG).show();
            return false;
        }



        return true;

    }





}


