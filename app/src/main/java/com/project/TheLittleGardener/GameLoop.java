package com.project.TheLittleGardener;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class GameLoop extends Thread
{
    private static final double MAX_UPS = 60.0;
    private static final double UPS_PERIOD = 1E+3/MAX_UPS;
    private Game game;
    private Boolean isRunning = false;
    private SurfaceHolder surfaceHolder;
    private double averageUPS;
    private double averageFPS;

    public GameLoop(Game game, SurfaceHolder surfaceHolder)
    {
        this.game = game;
        this.surfaceHolder = surfaceHolder;
    }

    public double getAverageUPS()
    {
        return averageUPS;
    }

    public double getAverageFPS()
    {
        return averageFPS;
    }

    public void startLoop()
    {
        isRunning = true;
        start();
    }

    @Override
    public void run()
    {
        Log.i("game", "game has started");

        super.run();
        Canvas canvas = null;

        ///cycle count variables
        int updateCount = 0;
        int frameCount = 0;

        long startTime;
        long elapsedTime;
        long sleepTime;

        startTime = System.currentTimeMillis();
        averageUPS = getAverageUPS();
        averageFPS = getAverageFPS();


        while(isRunning)
        {
            try
            {
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder)
                {
                    game.update();
                    game.draw(canvas);
                    game.drawFPS(canvas);
                    game.drawUPS(canvas);
                    updateCount++;
                }
            }
            catch (IllegalArgumentException e)
            {
                e.printStackTrace();
            }
            finally
            {
                if (canvas != null)
                {
                    try
                    {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                        frameCount++;
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            //pause game loop to not exceed target UPS
            elapsedTime = System.currentTimeMillis() - startTime;
            sleepTime = (long) (updateCount*UPS_PERIOD - elapsedTime);
            if (sleepTime > 0)
            {
                try
                {
                    sleep(sleepTime);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }

            //skip frames to keep up with target UPS
            while (sleepTime < 0 && updateCount < MAX_UPS - 1)
            {
                game.update();
                updateCount++;
            }

            //average of UPS and FPS
            elapsedTime = System.currentTimeMillis() - startTime;
            if (elapsedTime >= 1000)
            {
                averageUPS = updateCount / (1E-3 * elapsedTime);
                averageFPS = updateCount / (1E-3 * elapsedTime);
                updateCount = 0;
                frameCount = 0;
                startTime = System.currentTimeMillis();
            }
        }
    }
}
