package com.example.natallia_radaman.recyclerviewgithub.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.natallia_radaman.recyclerviewgithub.R;

import com.example.natallia_radaman.recyclerviewgithub.domain.Pull;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;

public class PullAdapter extends RecyclerView.Adapter<PullAdapter.VHolder> {
    private List<Pull> data;
    private final OnItemClickListener listener;
    private Context context;
    private Picasso picasso;

    public PullAdapter(List<Pull> data, Context context, OnItemClickListener listener){
        this.data = data;
        this.listener = listener;
        this.context = context;
        picasso = Picasso.with(context);
        picasso.setIndicatorsEnabled(true);
    }

    @Override
    public VHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_of_pull, parent, false);
        return new VHolder(view);
    }

    @Override
    public void onBindViewHolder( VHolder holder, int position) {
        holder.bind(data.get(position), listener);
    }

    public static class VHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        TextView textViewData;
        TextView textViewBody;
        TextView textViewAuthor;
        ImageView imageViewAvatar;

        public VHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewBody = itemView.findViewById(R.id.text_view_body);
            textViewData = itemView.findViewById(R.id.text_view_data);
            textViewAuthor = itemView.findViewById(R.id.text_view_name);
            imageViewAvatar = itemView.findViewById(R.id.image_view_avatar);

        }

        public void bind(final Pull pull, final OnItemClickListener listener) {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            textViewTitle.setText(pull.title);
            textViewBody.setText(pull.body);
            textViewData.setText(format.format(pull.date));
            textViewAuthor.setText(pull.user.login);
            Picasso.with(itemView.getContext()).load(pull.user.avatar).into(imageViewAvatar);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(pull);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Pull pull);
    }
}
