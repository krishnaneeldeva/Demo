package com.example.ankit.assignment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by ankit on 8/16/2016.
 */
public class ImageAdapter extends BaseAdapter {
    private Bitmap[] covers;
    private Context context;
    private View view1;
    private boolean mshowingBack = false;
    public ImageAdapter(Context context, Bitmap[] covers) {
        this.context = context;
        this.covers = covers;
    }

    @Override
    public int getCount() {
        return covers.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolderItem viewHolderItem;
//        ImageView imageView;
//        if(view == null){
//            imageView = new ImageView(context);
//            imageView.setLayoutParams(new GridView.LayoutParams(285,285));
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageView.setPadding(8,8,8,8);
//        }else {
//            imageView = (ImageView) view;
//        }
//        imageView.setImageBitmap(covers[i]);
//        return imageView;
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("mhv","hjbgj" + view.findViewById(R.id.framelayout));
//            }
//        });

//        LayoutInflater inflater = (LayoutInflater) context
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        if(view == null){
//            grid = new View(context);
//            grid = inflater.inflate(R.layout.gridview_single, null);
//            TextView textView = (TextView) grid.findViewById(R.id.gridviewsingle_text);
//            textView.setText("Hint");
//            view1 = (View) grid.findViewById(R.id.gridviewsingle_view);
        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.gridview_single, null);
            viewHolderItem = new ViewHolderItem();
            viewHolderItem.imageView = (ImageView) view.findViewById(R.id.gridviewsingle_image);
            viewHolderItem.frameLayout = (FrameLayout) view.findViewById(R.id.gridviewsingle_framelayout);
            view.setTag(viewHolderItem);
        }else{
            viewHolderItem = (ViewHolderItem) view.getTag();
        }
        viewHolderItem.imageView.setImageBitmap(covers[i]);
//        final TextView tvd = (TextView) grid.findViewById(R.id.gridviewsingle_view) ;
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view1) {
//                Log.d("Text",tvd.getText().toString());
////                tvd.setVisibility(View.VISIBLE);
////                flipCard(view);
//            }
//        });
////        }
        return view;
    }

    static class ViewHolderItem{
        ImageView imageView;
        FrameLayout frameLayout;
    }
    private void flipCard(View tv) {
        if(mshowingBack){
            ((Activity) context).getFragmentManager().popBackStack();
            mshowingBack = false;
            return;
        }
        FrameLayout fm = (FrameLayout) tv.findViewById(R.id.gridviewsingle_framelayout);
        mshowingBack = true;
        Log.d("Me fm","" + fm.getId());
        ((Activity) context).getFragmentManager().beginTransaction().setCustomAnimations( R.animator.card_flip_right_in,
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
    public void changeFlip(Context context2){
        View grid = LayoutInflater.from(context2).inflate(R.layout.gridview_single,null);
        View view1 = (View) grid.findViewById(R.id.gridviewsingle_view);
        Log.d("Me","Reached");
        view1.setVisibility(View.VISIBLE);
    }
}
