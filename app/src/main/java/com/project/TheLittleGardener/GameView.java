package com.project.TheLittleGardener;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable
{
    private final int msecondsToFps = 17;
    private Thread thread;
    private boolean isPlaying;
    private GameBackground background;
    private int screenX, screenY;  //not used
    private Paint paint;
    private Player player;

    public GameView(Context context, int screenX, int screenY)
    {
        super(context);
        background = new GameBackground(screenX, screenY, getResources());
        this.screenX = screenX;
        this.screenY = screenY;
        this.paint = paint;
        this.player = new Player(screenX, screenY, getResources());
    }

    @Override
    public void run()
    {
        while (isPlaying)
        {
            update();
            draw();
            sleep();
        }
    }

    //update all actions in game
    private void update()
    {
        if (player.goUp)
        {
            player.y += 2;
            player.goUp = false;
        }
        if (player.goDown)
        {
            player.y -= 2;
            player.goDown = false;
        }
        if (player.y <0)
        {
            player.y =0;
        }
        if (player.y > screenY - player.height)
        {
            player.y = screenY - player.height;
        }
    }

    //draw on the Canvas
    private void draw()
    {
        if (getHolder().getSurface().isValid()) //trying if successfully initiated
        {
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background.background, background.x, background.y, paint);
            canvas.drawBitmap(player.getGardener(), player.x, player.y, paint);
            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    //makes game run smoothly
    //1s/17ms~60 so game runs at 60 fps
    private void sleep()
    {
        try
        {
            Thread.sleep(msecondsToFps);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public void resume()
    {
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    public void pause()
    {
        try
        {
            isPlaying = false;
            thread.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                player.goUp = true;
            case MotionEvent.ACTION_DOWN:
                player.goDown = true;
        }
        return true;
    }
}
