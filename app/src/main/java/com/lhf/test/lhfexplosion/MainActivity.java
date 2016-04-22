package com.lhf.test.lhfexplosion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.lhf.test.lhfexplosion.utils.Explosion;

public class MainActivity extends AppCompatActivity {

    private TextView mTv_hello_word;
    private ImageView iv;
    private ImageView iv2;
    private ImageView iv3;
    private Explosion mE2;
//    private Explosion mE1;
//    private Explosion mE3;
    private ViewGroup rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mTv_hello_word = (TextView) findViewById(R.id.tv_hello_word);
        iv = (ImageView) findViewById(R.id.iv);
        iv2 = (ImageView) findViewById(R.id.iv2);
        iv3 = (ImageView) findViewById(R.id.iv3);
        rootView = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
        mE2 = new Explosion(MainActivity.this);
        rootView.addView(mE2, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        mTv_hello_word.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mE2 != null) {
//                    rootView.removeView(mE2);
//                    rootView.removeView(mE1);
//                    rootView.removeView(mE3);
//                    rootView.invalidate();
                    iv.setAlpha(1f);
                    iv2.setAlpha(1f);
                    iv3.setAlpha(1f);
//                    mE2 = new Explosion(MainActivity.this, iv);
//                    mE1 = new Explosion(MainActivity.this, iv2);
//                    mE3 = new Explosion(MainActivity.this, iv3);
                    mE2.clear(iv,iv2,iv3);
                    rootView.invalidate();
                }

            }
        });

        iv.post(new Runnable() {
            @Override
            public void run() {


                mE2.init(iv);
            }
        });
        iv2.post(new Runnable() {
            @Override
            public void run() {
                mE2.init(iv2);
//                mE1 = new Explosion(MainActivity.this, iv2);
//                rootView.addView(mE1, new ViewGroup.LayoutParams(
//                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            }
        });
        iv3.post(new Runnable() {
            @Override
            public void run() {
                mE2.init(iv3);
//                mE3 = new Explosion(MainActivity.this, iv3);
//                rootView.addView(mE3, new ViewGroup.LayoutParams(
//                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            }
        });

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                rootView.removeView(mE2);
//                rootView.addView(mE2, new ViewGroup.LayoutParams(
//                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                mE2.start(iv.getId());
            }
        });

        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                rootView.removeView(mE1);
//                rootView.addView(mE1, new ViewGroup.LayoutParams(
//                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                mE2.start(iv2.getId());
            }
        });

        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                rootView.removeView(mE3);
//                rootView.addView(mE3, new ViewGroup.LayoutParams(
//                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                mE2.start(iv3.getId());
            }
        });

    }
}
