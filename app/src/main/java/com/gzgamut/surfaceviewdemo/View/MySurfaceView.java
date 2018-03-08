package com.gzgamut.surfaceviewdemo.View;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * 2018/3/6
 * guanbp@gzgamut.com
 */
public class MySurfaceView  extends SurfaceView implements Runnable, SurfaceHolder.Callback {
    
    
    private SurfaceHolder surfaceHolder;
    //子线程标志位
    private boolean mIsDrawing;
    //
    private Canvas canvas;


    //构造方法和View的一样
    public MySurfaceView(Context context) {
        this(context,null);
    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    
    
    
    private void init(){
        //初始化surfaceHolder
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        new Thread(this).start();
    }


    
    @Override
    public void run(){
        while(mIsDrawing){
            canvas = surfaceHolder.lockCanvas();
            if(canvas!=null){
                try {
                    //使用获得的Canvas做具体的绘制
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }


    //添加回调中国的生命周期
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mIsDrawing = true;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mIsDrawing = false;
    }

}
