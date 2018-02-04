package com.example.mfk.arkaplan2;

import android.app.Activity;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.q42.android.scrollingimageview.ScrollingImageView;

/**
 * Created by MfK on 24.11.2017.
 */

public class Oyun extends Activity {
    RelativeLayout layout_joystick,boyut;
    JoyStick js;
    String kAdi;
    TextView karakter,atesresim,dusman;
    RelativeLayout olcek,atesEt;
    float X=0,Y=0;
    Button btnBaglan;
    public static Oyun _form;
    public static String gonderlicek,gelen;
    private Client mClient;
    public static String[] gelen_veri;
    TextView txt;



    public class connectTask extends AsyncTask<String,String,Client> {

        @Override
        protected Client doInBackground(String... message) {

            //we create a Client object and
            mClient = new Client(new Client.OnMessageReceived() {
                @Override
                //here the messageReceived method is implemented
                public void messageReceived(String message) {
                    publishProgress(message);

                }
            });
            mClient.run();

            return null;
        }


        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            gelen = values[0];
            receive(gelen);


        }
    }



    public void receive(String gelen1) {

        gelen_veri = gelen1.split(",");
        float x,y;

        x = olcek.getWidth() -Integer.valueOf(gelen_veri[0])-100;
        y = olcek.getHeight() - Integer.valueOf(gelen_veri[1])-100;
        if(X > olcek.getWidth()-80)
            X = olcek.getWidth()-80;
        if(Y > olcek.getHeight()-80)
            Y = olcek.getHeight()-80;
        if(X < 0)
            X = 0;
        if(Y < 0)
            Y = 0;
        dusman.setX(x);
        dusman.setY(y);



        if (Integer.valueOf(gelen_veri[2]) == 1)
        {
            float locationX = dusman.getX();
            float locationY = dusman.getY();
            int sinirY = olcek.getHeight();
            int sinirX = olcek.getWidth();

            TextView txt1 = new TextView(Oyun.this);

            txt1.setBackground( getResources().getDrawable(R.drawable.roketters));
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
            params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);

            txt1.setLayoutParams(params);
            atesEt.addView(txt1);

            Animation animation = new TranslateAnimation(locationX-sinirX/2+50, locationX-sinirX/2+50,locationY-sinirY/-100, sinirY);
            animation.setDuration(1000);
            txt1.startAnimation(animation);
            txt1.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        Bundle extras = getIntent().getExtras();
        kAdi = extras.getString("kAdi");
        _form = this;

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arka_plan);

        ScrollingImageView scrollingBackground = (ScrollingImageView)findViewById(R.id.scrolling_background);

        scrollingBackground.start();

        btnBaglan = (Button)findViewById(R.id.baglan);
        boyut = (RelativeLayout)findViewById(R.id.boyut);
        karakter = (TextView)findViewById(R.id.nesne);
        atesresim = (TextView)findViewById(R.id.ates) ;
        olcek = (RelativeLayout)findViewById(R.id.boyut);
        atesEt = (RelativeLayout) findViewById(R.id.atesText);
        dusman = (TextView) findViewById(R.id.nesneDusman);

        layout_joystick = (RelativeLayout)findViewById(R.id.layout_joystick);
        js = new JoyStick(getApplicationContext(), layout_joystick, R.drawable.image_button);
        js.setStickSize(150, 150);
        js.setLayoutSize(500, 500);
        js.setLayoutAlpha(150);
        js.setStickAlpha(100);
        js.setOffset(90);
        js.setMinimumDistance(50);

        new connectTask().execute("");

        btnBaglan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClient != null) {
                    mClient.sendMessage(kAdi);
                    btnBaglan.setVisibility(View.INVISIBLE);

                }
            }
        });


        layout_joystick.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                js.drawStick(arg1);

               if(arg1.getAction() == MotionEvent.ACTION_DOWN || arg1.getAction() == MotionEvent.ACTION_MOVE) {
                    X = karakter.getX();
                    Y = karakter.getY();
                    X +=js.getX()/10;
                    Y += js.getY()/10;

                    if(X > olcek.getWidth()-100)
                        X = olcek.getWidth()-100;
                    if(Y > olcek.getHeight()-100)
                        Y = olcek.getHeight()-100;
                    if(X < 0)
                        X = 0;
                    if(Y < 0)
                        Y = 0;

                    karakter.setX(X);
                    karakter.setY(Y);

                   gonderlicek = String.valueOf((int)karakter.getX())+ "," + String.valueOf((int)karakter.getY()) + "," + "0";
                   mClient.sendMessage(gonderlicek);

              }
              /*  else if(arg1.getAction() == MotionEvent.ACTION_UP) {
                    X +=js.getX();
                    Y += js.getY();
                    karakter.setX(X);
                    karakter.setY(Y);

                }*/







                return true;
            }
        });



        final Button ates = (Button)findViewById(R.id.atesTusu);
        ates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                float locationX = karakter.getX();
                float locationY = karakter.getY();
                final int sinirY = olcek.getHeight();
                int sinirX = olcek.getWidth();


                txt = new TextView(Oyun.this);

                txt.setBackground( getResources().getDrawable(R.drawable.roket));
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,view.getId());
                params.addRule(RelativeLayout.CENTER_IN_PARENT, view.getId());

                txt.setLayoutParams(params);
                boyut.addView(txt);


                Animation animation = new TranslateAnimation(locationX-sinirX/2+50,locationX-sinirX/2+50,locationY-sinirY+50,-sinirY);
                animation.setDuration(1000);
                txt.startAnimation(animation);
                txt.setVisibility(View.INVISIBLE);


               Rect BenimAtesim = new Rect();
                txt.getHitRect(BenimAtesim);

                Rect DüsmanKarakter = new Rect();
                dusman.getHitRect(DüsmanKarakter);

                if (Rect.intersects(BenimAtesim, DüsmanKarakter)) {
                    Toast.makeText(Oyun.this,"carptııııııııııııııııııııııı",Toast.LENGTH_SHORT).show();
                }







                gonderlicek = String.valueOf((int)karakter.getX())+ "," + String.valueOf((int)karakter.getY()) + "," + "1";
                mClient.sendMessage(gonderlicek);





            }
        });


    }


}
