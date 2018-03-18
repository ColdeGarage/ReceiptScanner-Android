package com.example.qq;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.view.View;

public class gifView extends View {
    private Movie mMovie;
    private long mMovieStart = 0;

    public gifView(Context context,int gifName) {
        super(context);
        mMovie = Movie.decodeStream(getResources().openRawResource(gifName));
    }

    @Override
    public void onDraw(Canvas canvas) {

        long now = android.os.SystemClock.uptimeMillis();

        if (mMovieStart == 0) {
            mMovieStart = now;
        }

        if (mMovie != null) {
            int dur = mMovie.duration();

            if (dur == 0) {
                dur = 1000;
            }

            int relTime = (int) ((now - mMovieStart) % dur);
            mMovie.setTime(relTime);
            mMovie.draw(canvas, 0, 0);

            invalidate();
        }
    }
}
