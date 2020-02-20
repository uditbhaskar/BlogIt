package com.example.blogit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostHolder> {

    private List<Item> items;
    private Context context;

    public PostAdapter(List<Item> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.post_item_view, parent, false);
        return new PostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        Item item = items.get(position);
        holder.postText.setText(item.getTitle());

        Document document = Jsoup.parse(item.getContent());
        holder.postDescription.setText(document.text());

        Elements elements= document.select("img");
        Glide.with(context).load(elements.get(0).attr("src")).into(holder.postImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("url", item.getUrl());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class PostHolder extends RecyclerView.ViewHolder {
        ImageView postImage;
        TextView postText;
        TextView postDescription;

        public PostHolder(@NonNull View itemView) {

            super(itemView);
            postImage = itemView.findViewById(R.id.post_image);
            postText = itemView.findViewById(R.id.post_text);
            postDescription = itemView.findViewById(R.id.post_description);

        }
    }
}
