package com.example.jiaweishi.photoviewer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by jiaweishi on 2/6/16.
 */
public class PhotoAdapter extends ArrayAdapter<Photo> {

    public PhotoAdapter(Context context, ArrayList<Photo> photos){
        super(context, 0, photos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Photo photo = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);
        }

        TextView tv_userName = (TextView) convertView.findViewById(R.id.tv_userName);
        tv_userName.setText(photo.getUserName());

        TextView tv_caption = (TextView) convertView.findViewById(R.id.tv_caption);
        tv_caption.setText(photo.getCaption());

        TextView tv_timeStamp = (TextView) convertView.findViewById(R.id.tv_timeStamp);
        tv_timeStamp.setText(photo.getRelativeTimeStamp());

        TextView tv_likeCount = (TextView) convertView.findViewById(R.id.tv_likeCount);
        tv_likeCount.setText(photo.getLikeCount() + " likes");

        ImageView iv_userIcon = (ImageView) convertView.findViewById(R.id.iv_userIcon);
        Picasso.with(getContext()).load(photo.getUserProfileImageUrl()).into(iv_userIcon);

        ImageView iv_photo = (ImageView) convertView.findViewById(R.id.iv_photo);
        Picasso.with(getContext()).load(photo.getPhotoUrl()).into(iv_photo);

        return convertView;
    }
}
