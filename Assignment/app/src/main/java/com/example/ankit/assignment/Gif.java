package com.example.ankit.assignment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.InputStream;

/**
 * Created by ankit on 8/11/2016.
 */
public class Gif extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InputStream input = getResources().openRawResource(R.raw.boat);
        setContentView(new view(this,input));

    }

    public class view extends View{
        InputStream input;
        private Movie mMovie;
        private long mMoviestart = 0;
        public view(Context context, InputStream inputstream) {
            super(context);
            this.input = inputstream;
            this.mMovie = Movie.decodeStream(input);
        }

        @Override
        protected void onDraw(Canvas canvas) {

            super.onDraw(canvas);
            canvas.drawColor(Color.TRANSPARENT);
            final long now = SystemClock.uptimeMillis();
            if (mMoviestart == 0) {
                mMoviestart = now;
            }
            if (mMovie != null) {
                int relTime = (int) ((now - mMoviestart) % mMovie.duration());
                mMovie.setTime(relTime);
                Log.d("time", String.valueOf(relTime));
                mMovie.draw(canvas, getWidth() - mMovie.width(), getHeight() - mMovie.height());
//                Log.d("Canvas", String.valueOf(mMovie));
                invalidate();
            }
//            invalidate();

        }

    }
}
