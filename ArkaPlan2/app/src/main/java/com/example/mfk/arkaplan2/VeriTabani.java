package com.example.mfk.arkaplan2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MfK on 21.11.2017.
 */

public class VeriTabani extends SQLiteOpenHelper {

        private static final int DATABASE_VERSION = 2 ;
        private static final String DATABASE_NAME = "kullanicilar.db";
        public static final String KULLANICI_TABLOSU = "kullanici_bilgi";
        private final String KULLANICI_ID = "Kid";
        private final String KULLANICI_ISIM = "Kisim";
        private final String KULLANICI_SOYAD = "Ksoyad";
        private final String KULLANICI_MAIL = "Kemail";
        private final String KULLANICI_SIFRE = "Ksifre";
        private final String KULLANICI_NICK = "Knick";


        VeriTabani(Context context) {

            super(context, DATABASE_NAME, null, DATABASE_VERSION);

         }


         String TABLO ="CREATE TABLE "+ KULLANICI_TABLOSU+ "("
                 + KULLANICI_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                 + KULLANICI_ISIM + " TEXT, "
                 + KULLANICI_SOYAD + " TEXT, "
                 + KULLANICI_MAIL + " TEXT, "
                 + KULLANICI_SIFRE + " TEXT, "
                 + KULLANICI_NICK + " TEXT " + ")" ;

         @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(TABLO);


        }

        String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + KULLANICI_TABLOSU;
         @Override

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DROP_USER_TABLE);
            onCreate(db);

        }

        public long KayitEkle(String isim, String soyad, String kadi, String email, String sifre){

            SQLiteDatabase gv = this.getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put(KULLANICI_ISIM, isim);
            cv.put(KULLANICI_SOYAD, soyad);
            cv.put(KULLANICI_MAIL, email);
            cv.put(KULLANICI_SIFRE, sifre);
            cv.put(KULLANICI_NICK, kadi);

            long kontrol = gv.insert("kullanici_bilgi", null, cv);
            gv.close();

            return kontrol;
        }

       /* public boolean VeriTabanıKontrol(String isim,String soyad,String Email,String KullanıcıAdı,String Sifre){
            //Veri tabanında id yi bulup o idi den ismi elde ediyor
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor as = db.rawQuery("SELECT Kisim FROM KullanıcıDetay WHERE Kid = (SELECT Kid FROM KullanıcıDetay WHERE Kisim like '"+isim+"')", null);
            Cursor sS = db.rawQuery("SELECT Ksoyad FROM KullanıcıDetay WHERE Kid = (SELECT  Kid FROM KullanıcıDetay WHERE Ksoyad like '"+soyad+"')", null);
            Cursor eS = db.rawQuery("SELECT Kemail FROM KullanıcıDetay WHERE Kid = (SELECT  Kid FROM KullanıcıDetay WHERE Kemail like '"+Email+"')", null);
            Cursor nS = db.rawQuery("SELECT Knick FROM KullanıcıDetay WHERE Kid = (SELECT  Kid FROM KullanıcıDetay WHERE Knick like '"+KullanıcıAdı+"')", null);
            Cursor iS = db.rawQuery("SELECT Ksifre FROM KullanıcıDetay WHERE Kid = (SELECT  Kid FROM KullanıcıDetay WHERE Ksifre like '"+Sifre+"')", null);

            if(as != null && sS != null && eS != null && nS != null && iS != null ){
                return true;
            }
            else {
                return false;
            }
            return true;
        }*/
        public String GirisKontrol(String nick){
            SQLiteDatabase as = this.getReadableDatabase();


            //Cursor giris = as.rawQuery("SELECT Ksifre FROM KullanıcıDetay WHERE Ksifre like '"+sifre+"' ",null);
            Cursor cursor=as.query(KULLANICI_TABLOSU, null, KULLANICI_NICK+"=?", new String[]{nick}, null, null, null);

            if(cursor.getCount()<1)
            {
                cursor.close();
                return "Kayıt Bulunamadı";
            }

            cursor.moveToFirst();
            String password= cursor.getString(cursor.getColumnIndex(KULLANICI_SIFRE));
            cursor.close();

            return password;
            /*a = new Kullanici();

            String kontrola = email.getString(email.getColumnIndex("Kemail"));
            String kontrolb = giris.getString(giris.getColumnIndex("Ksifre"));

            Toast.makeText(a,"saasddadsadad",Toast.LENGTH_LONG).show();

            String a = giris.toString();
            
            if(email == null){
                return false;
            }
            else if (giris == null){
                return false;
            }



            return true;*/
        }


    }
