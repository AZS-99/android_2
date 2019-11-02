package com.example.adam_5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import org.w3c.dom.Text;

import java.util.ArrayList;


public class Adapter1 extends ArrayAdapter<Country> {
    Context context;
    int layoutId;
    public Adapter1(Context context, int layoutId, ArrayList<Country> items) {
        super(context, layoutId, items);
        this.context = context;
        this.layoutId = layoutId;
    }

    static class ViewHolder {
          TextView capital;
          TextView country;
          ImageView img;
    }


    @NonNull
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
            holder.img = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ImageLoader imageLoader = ImageLoader.getInstance();


        holder.capital.setText(countryName);
        holder.country.setText(capital);
        imageLoader.displayImage(imgURL, holder.img);


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
