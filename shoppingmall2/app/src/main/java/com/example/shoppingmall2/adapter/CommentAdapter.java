package com.example.shoppingmall2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.shoppingmall2.R;
import com.example.shoppingmall2.adapter.base.ViewHolder;
import com.example.shoppingmall2.vo.Comment;
import com.example.shoppingmall2.vo._User;

import java.util.ArrayList;

import cn.bmob.v3.BmobQuery;


public class CommentAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Comment> comments;
    BmobQuery<_User> query = new BmobQuery<>();

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public CommentAdapter(Context context, ArrayList<Comment> comments){
        this.context = context;
        this.comments = comments;
    }
    @Override
    public int getCount() {
        return comments.size();
    }

    @Override
    public Object getItem(int position) {
        return comments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.comment_item,null);
        }
        final ImageView imageView_userIcon = ViewHolder.getView(convertView,R.id.imageView_userIcon,null);
        TextView textView11_username = ViewHolder.getView(convertView,R.id.textView11_username,null);
        TextView textView12_createdAt = ViewHolder.getView(convertView,R.id.textView12_createdAt,null);
        RatingBar ratingBar_star = ViewHolder.getView(convertView,R.id.ratingBar_star,null);
        TextView textView13_comment_content = ViewHolder.getView(convertView,R.id.textView13_comment_content,null);

        Comment comment = comments.get(position);
        textView11_username.setText(comment.getUser());
        textView12_createdAt.setText(comment.getCreatedAt());
        ratingBar_star.setRating(comment.getStar());
        textView13_comment_content.setText(comment.getContent());

        return convertView;
    }
}
