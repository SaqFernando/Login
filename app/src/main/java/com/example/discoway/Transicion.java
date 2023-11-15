package com.example.discoway;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Transicion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_transicion);

        //Glide Comienza
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.casifinal, null));
        Glide.with(getBaseContext()).load(R.drawable.casifinal).into(imageView);

        //Agregar animaciones
        Animation animacion1 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_arriba);
        Animation animacion2 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_abajo);

        final TextView TOSTextView = findViewById(R.id.TOStextView);
        final ImageView bolaImageView = findViewById(R.id.bolaImageView);

        TOSTextView.setAnimation(animacion2);
        bolaImageView.setAnimation(animacion2);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Transicion.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View, String>(bolaImageView, "bolaImageViewTrans");
                pairs[1] = new Pair<View, String>(TOSTextView, "TOSTextViewTrans");

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Transicion.this, pairs);
                    startActivity(intent, options.toBundle());
                }else {
                    startActivity(intent);
                    finish();
                }
            }
        }, 4000);
    }
}