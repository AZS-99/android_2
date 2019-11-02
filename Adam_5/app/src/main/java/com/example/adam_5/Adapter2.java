package com.example.adam_5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.ArrayList;

public class Adapter2 extends ArrayAdapter<Country> {
    Context context;
    int layoutId;
    public Adapter2(Context context, int layoutId, ArrayList<Country> objects) {
        super(context, layoutId, objects);
        this.context = context;
        this.layoutId = layoutId;
    }

    static class ViewHolder {
        TextView country, capital, letter;
        ImageView imageView;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String countryName = getItem(position).getCountry();
        String capital = getItem(position).getCapital();
        String imgURL = getItem(position).getImgURL();

        setupImageLoader();
        ViewHolder holder = new ViewHolder();

        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(layoutId, parent, false);


            holder.capital = (TextView) convertView.findViewById(R.id.capital);
            holder.country = (TextView) convertView.findViewById(R.id.country);
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            holder.letter = (TextView) convertView.findViewById(R.id.letter);
            convertView.setTag(holder);

        } else {
            holder = (Adapter2.ViewHolder) convertView.getTag();
        }

        ImageLoader imageLoader = ImageLoader.getInstance();
        holder.capital.setText(countryName);
        holder.country.setText(capital);
        holder.letter.setText(Character.toString(countryName.charAt(0)));
        imageLoader.displayImage(imgURL, holder.imageView);
        return convertView;
    }

    private void setupImageLoader(){
        // UNIVERSAL IMAGE LOADER SETUP
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc(true).cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context)
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache())
                .discCacheSize(100 * 1024 * 1024).build();

        ImageLoader.getInstance().init(config);
        // END - UNIVERSAL IMAGE LOADER SETUP
    }


}
