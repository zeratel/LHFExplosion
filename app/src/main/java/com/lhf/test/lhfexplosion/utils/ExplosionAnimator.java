package com.lhf.test.lhfexplosion.utils;

import android.animation.ValueAnimator;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

/**
 * com.lhf.test.lhfexplosion.utils
 * Created by zeratel3000
 * on 2016 04 16/4/20 15 43
 * description
 */
public class ExplosionAnimator extends ValueAnimator{

    public static final int DEFAULT_DURATION = 1500;

    public ArrayList<Particle> mParticles = new ArrayList<>();

    private View view;

    private boolean start = false;
    private boolean stop = false;

    public ExplosionAnimator() {
        setFloatValues(0.0f,1.0f);
        setDuration(DEFAULT_DURATION);
    }

    public ArrayList<Particle> getParticles() {
        return mParticles;
    }

    public void setParticles(ArrayList<Particle> particles) {
        mParticles = particles;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public void setStop(boolean b) {
        this.stop = b;
    }

    public boolean isStop() {
        return stop;
    }

    /**
     * com.lhf.test.lhfexplosion.utils
     * Created by zeratel3000
     * on 2016 04 16/4/20 15 26
     * description
     */
    public static class Particle {

        int x;
        int y;
        int r;

        int color;
        int alpha;
        private Random random = new Random();
        private Rect mBound;
        public static int contR = 17;

        public static Particle generateParticle(int color, Rect bound, Point point) {

    //        point = new Point(column, row); //x是列，y是行
            int row = point.y; //行是高
            int column = point.x; //列是宽

            Particle particle = new Particle();
            particle.mBound = bound;
            particle.color = color;
            particle.alpha = 1;

            particle.r = contR;
            particle.x = bound.left + contR * column;
            particle.y = bound.top + contR * row;

            return particle;
        }

        public void advance(float factor) {
    //        x = (int) (x + factor * random.nextInt(mBound.width()) * (random.nextFloat() - 0.5f));
    //        y = (int) (y + factor * random.nextInt(mBound.height() / 2));

    //        Log.i("LHF","mBound.width():"+mBound.width());

            //最后的是正负机制
            x = (int) (x + factor * random.nextInt(mBound.width())* (random.nextFloat() - 0.5f));
            y = (int) (y + factor * random.nextInt(mBound.height()/2));

    //        r = (int) (r - factor * random.nextInt(2));

    //        alpha = (int) ((1f - factor) * (1 + random.nextFloat()));
        }

    }


}
