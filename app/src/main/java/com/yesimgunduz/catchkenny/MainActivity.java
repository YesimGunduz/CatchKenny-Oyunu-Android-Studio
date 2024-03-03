package com.yesimgunduz.catchkenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView timeTextview;
    TextView skorTextview;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView imageView10;
    ImageView [] imageArray;
    Handler handler;
    Runnable runnable;
    int skor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       timeTextview=findViewById(R.id.timeTextview);
       skorTextview=findViewById(R.id.skorTextview);
       skor=0;
       imageView2=findViewById(R.id.imageView2);
       imageView3=findViewById(R.id.imageView3);
       imageView4=findViewById(R.id.imageView4);
       imageView5=findViewById(R.id.imageView5);
       imageView6=findViewById(R.id.imageView6);
       imageView7=findViewById(R.id.imageView7);
       imageView8=findViewById(R.id.imageView8);
       imageView9=findViewById(R.id.imageView9);
       imageView10=findViewById(R.id.imageView10);

       imageArray=new ImageView [] {imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9,imageView10};
      hideImage();

        new CountDownTimer(10000,1000) {
            public void onTick(long l) {
                timeTextview.setText("Time:"+ l/1000);
            }

            @Override
            public void onFinish() {
            timeTextview.setText("Time off");
            handler.removeCallbacks(runnable);
                for (ImageView image:imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart");
                alert.setMessage("Tekrar");
                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent=getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Game over", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.show();
            }
        }.start();
    }


    public void İnCreaseSkor (View view){
    skor++;
    skorTextview.setText("Skor:"+skor);
    }
public void hideImage(){
    handler=new Handler();
    runnable=new Runnable() {
    @Override
    public void run() {
        for (ImageView image:imageArray) {
            image.setVisibility(View.INVISIBLE);
        }
        Random random= new Random();
        int i = random.nextInt(9);
        imageArray[i].setVisibility(View.VISIBLE);
        handler.postDelayed(this,200);
    }
};handler.post(runnable);
}
    }
