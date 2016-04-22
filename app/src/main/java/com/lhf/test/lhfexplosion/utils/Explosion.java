package com.lhf.test.lhfexplosion.utils;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * com.lhf.test.lhfexplosion.utils
 * Created by zeratel3000
 * on 2016 04 16/4/20 15 25
 * description
 */
public class Explosion extends View {

    private static final Canvas mCanvas = new Canvas();
    private Context context;
    //    private View view;
    private int mPartW_count;
    private int mPartH_count;
    Rect bound;
    //    private ExplosionAnimator mEa;
    Paint paint = new Paint();
    //    private boolean start = false;
    private ArrayList<ExplosionAnimator> mParticless = new ArrayList<>();

    public Explosion(Context context) {
        super(context);

        this.context = context;

    }

    public Explosion(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Explosion(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(View view) {
        ExplosionAnimator e = new ExplosionAnimator();
        e.setView(view);

        bound = new Rect();
        int[] location = new int[2];
        //getGlobalVisibleRect配合使用
        view.getGlobalVisibleRect(bound);

        bound.offset(0, -bound.height() + 60);//tv.margin

        int w = bound.width();
        int h = bound.height();

        mPartW_count = w / ExplosionAnimator.Particle.contR;
        mPartH_count = h / ExplosionAnimator.Particle.contR;

        getParticles(e);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mParticless.size() == 0) {
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
            return;
        }


        for (ExplosionAnimator particles : mParticless) {

            if (particles.isStart() && !particles.isStop()) {
                for (ExplosionAnimator.Particle p : particles.getParticles()) {

                    //这有点拧巴着 ExplosionAnimator只是提供一个0到1
                    p.advance((Float) particles.getAnimatedValue());

                    paint.setColor(p.color);
                    canvas.drawCircle(p.x, p.y, p.r, paint);


                    if (p.y > 3000) { //动画结束时停止
                        //设置停止标志，解决有一个是return就都停下的问题
                        particles.setStop(true);
                    }
                }
                invalidate();
            }
        }

    }

    //复制view
    private Bitmap createBitmapFromView(View view) {
        //和view同样大小的空白图
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        if (bitmap != null) {
            synchronized (mCanvas) {
                //画空白图 不理解
                mCanvas.setBitmap(bitmap);
                //把原图画到画布上
                view.draw(mCanvas);
                mCanvas.setBitmap(null); //清除引用
            }
        }
        return bitmap;
    }

    //获取颜色点
    public void getParticles(ExplosionAnimator e) {
        Point point = null;
        for (int row = 0; row < mPartH_count; row++) { //行
            for (int column = 0; column < mPartW_count; column++) { //列
                //取得当前粒子所在位置的颜色
                Bitmap temp = createBitmapFromView(e.getView());

                int color = temp.getPixel(column * ExplosionAnimator.Particle.contR, row * ExplosionAnimator.Particle.contR);

                point = new Point(column, row); //x是列，y是行

//                mParticles.add(ExplosionAnimator.Particle.generateParticle(color, bound, point));
                e.getParticles().add(ExplosionAnimator.Particle.generateParticle(color, bound, point));
            }
        }

        //一组点加入到动画集合
        mParticless.add(e);
//        Log.i("LHF", "mParticles:" + mParticles.size());
    }

    public void start(int id) {

        for (final ExplosionAnimator e : mParticless) {
            if (e.getView().getId() == id) {
                e.getView().setAlpha(0f);

                e.setStart(true);

                e.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        clearAnimation();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                e.start();

                invalidate();

            }
        }

    }

    public void clear(View... views) {

//        mParticles.clear();

        for (ExplosionAnimator e : mParticless) {
            e.getParticles().clear();
            e.setStart(false);
            e.setStop(false);
        }

        clearAnimation();
        invalidate();
        for (View v : views) {
            init(v);
        }

    }
}
