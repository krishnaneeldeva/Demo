package com.example.ankit.assignment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by ankit on 8/16/2016.
 */
public class Gridview extends AppCompatActivity{
    private boolean mshowingBack = false;
    private DecodeLargeBitmaps decodeLargeBitmaps;
    private View view1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridview);
        final GridView gridView = (GridView) findViewById(R.id.gridview);
        final Bitmap[] covers = new Bitmap[]{
                decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic1,200,200),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic2,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic3,100,100)
                ,decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic4,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic5,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic6,100,100)
                ,decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic7,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic8,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic9,100,100)
                ,decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic10,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic11,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic12,100,100)
                ,decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic13,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic14,100,100),
                decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic1,200,200),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic2,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic3,100,100)
                ,decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic4,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic5,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic6,100,100)
                ,decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic7,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic8,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic9,100,100)
                ,decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic10,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic11,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic12,100,100)
                ,decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic13,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic14,100,100),
                decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic1,200,200),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic2,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic3,100,100)
                ,decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic4,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic5,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic6,100,100)
                ,decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic7,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic8,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic9,100,100)
                ,decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic10,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic11,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic12,100,100)
                ,decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic13,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic14,100,100),
                decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic1,200,200),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic2,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic3,100,100)
                ,decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic4,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic5,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic6,100,100)
                ,decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic7,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic8,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic9,100,100)
                ,decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic10,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic11,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic12,100,100)
                ,decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic13,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic14,100,100),
                decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic1,200,200),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic2,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic3,100,100)
                ,decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic4,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic5,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic6,100,100)
                ,decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic7,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic8,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic9,100,100)
                ,decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic10,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic11,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic12,100,100)
                ,decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic13,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic14,100,100),
                decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic1,200,200),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic2,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic3,100,100)
                ,decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic4,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic5,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic6,100,100)
                ,decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic7,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic8,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic9,100,100)
                ,decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic10,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic11,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic12,100,100)
                ,decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic13,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic14,100,100)};
        gridView.setAdapter(new ImageAdapter(this,covers));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(Gridview.this,"" + i,Toast.LENGTH_LONG).show();
                ImageAdapter image = new ImageAdapter(Gridview.this,covers);
                TextView tv = (TextView) view.findViewById(R.id.gridviewsingle_view);
                Log.d("Me",tv.getText().toString());
//                FrameLayout fm = (FrameLayout) view.findViewById(R.id.framelayout);
                Log.d("Me tva up","" + tv.getId());
                tv.setVisibility(View.VISIBLE);
//                tv.setVisibility(View.VISIBLE);
//                image.changeFlip(getBaseContext());
                flipCard(view);
            }
        });
    }
    private void flipCard(View tv) {
        if(mshowingBack){
            getFragmentManager().popBackStack();
            mshowingBack = false;
            return;
        }

        FrameLayout fm = ((ImageAdapter.ViewHolderItem) tv.getTag()).frameLayout;
        mshowingBack = true;
        Log.d("Me fm","" + fm.getId());
        getFragmentManager().beginTransaction().setCustomAnimations( R.animator.card_flip_right_in,
                R.animator.card_flip_right_out,
                R.animator.card_flip_left_in,
                R.animator.card_flip_left_out).replace(fm.getId(), new CardBackFragment()).addToBackStack(null).commit();
        return;
    };

    public static class CardBackFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.textview, container, false);
        }
    }


}
