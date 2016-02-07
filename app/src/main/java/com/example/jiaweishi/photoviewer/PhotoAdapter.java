package com.example.jiaweishi.photoviewer;

import android.content.Context;
import android.graphics.Typeface;
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

    private static class ViewHolder{
        TextView userName;
        TextView caption;
        TextView timestamp;
        TextView likeCount;
        TextView lastCommentor;
        TextView lastComment;
        ImageView userIcon;
        ImageView photo;

    }


    public PhotoAdapter(Context context, ArrayList<Photo> photos){
        super(context, 0, photos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Photo photo = getItem(position);

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.userName = (TextView) convertView.findViewById(R.id.tv_userName);
            viewHolder.caption = (TextView) convertView.findViewById(R.id.tv_caption);
            viewHolder.timestamp = (TextView) convertView.findViewById(R.id.tv_timeStamp);
            viewHolder.likeCount = (TextView) convertView.findViewById(R.id.tv_likeCount);
            viewHolder.userIcon = (ImageView) convertView.findViewById(R.id.iv_userIcon);
            viewHolder.photo = (ImageView) convertView.findViewById(R.id.iv_photo);
            viewHolder.lastCommentor = (TextView) convertView.findViewById(R.id.tv_commenterID);
            viewHolder.lastComment = (TextView) convertView.findViewById(R.id.tv_comment);

            convertView.setTag(viewHolder);
        }
        else
            viewHolder = (ViewHolder) convertView.getTag();

        Typeface fontLarge = Typeface.createFromAsset(getContext().getAssets(), "font.otf");
        Typeface fontSmall = Typeface.createFromAsset(getContext().getAssets(), "font2.otf");

        viewHolder.userName.setText(photo.getUserName());
        viewHolder.userName.setTypeface(fontLarge);

        viewHolder.caption.setText(photo.getCaption());

        viewHolder.timestamp.setText(photo.getRelativeTimeStamp());
        viewHolder.timestamp.setTypeface(fontSmall);

        viewHolder.likeCount.setText(photo.getLikeCount() + " likes");
        viewHolder.likeCount.setTypeface(fontLarge);

        viewHolder.lastCommentor.setText(photo.getLastCommenter());
        viewHolder.lastCommentor.setTypeface(fontLarge);

        viewHolder.lastComment.setText(photo.getLastComment());
        viewHolder.lastComment.setTypeface(fontLarge);

        Picasso.with(getContext())
                .load(photo.getUserProfileImageUrl())
                .transform(new CircleTransform())
                .into(viewHolder.userIcon);

        Picasso.with(getContext())
                .load(photo.getPhotoUrl())
                .resize(320,0)
                .into(viewHolder.photo);

        return convertView;
    }
}
