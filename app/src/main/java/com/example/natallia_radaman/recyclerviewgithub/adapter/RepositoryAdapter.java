package com.example.natallia_radaman.recyclerviewgithub.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.natallia_radaman.recyclerviewgithub.R;

import com.example.natallia_radaman.recyclerviewgithub.domain.Repository;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.VHolder>{

    List<Repository> data;
    private final OnItemClickListener listener;
    Context context;
    Picasso picasso;

    public RepositoryAdapter(List<Repository> data, Context context, OnItemClickListener listener) {
        this.data = data;
        this.listener = listener;
        this.context = context;
        picasso = Picasso.with(context);
        picasso.setIndicatorsEnabled(true);
    }

    @Override
    public VHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_of_repository, parent, false);
        return new VHolder(view);
    }

    @Override
    public void onBindViewHolder(VHolder holder, int position) {
        holder.bind(data.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class VHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewDescription;
        TextView textViewForks;
        TextView textViewStars;
        TextView textViewAuthor;
        ImageView imageViewAvatar;

        public VHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            textViewForks = itemView.findViewById(R.id.text_view_forks);
            textViewStars = itemView.findViewById(R.id.text_view_stars);
            imageViewAvatar = itemView.findViewById(R.id.image_view_avatar);
            textViewAuthor = itemView.findViewById(R.id.text_view_author);
        }

        public void bind(final Repository repository, final OnItemClickListener listener) {
            textViewName.setText(repository.name);
            textViewDescription.setText(repository.description);
            textViewForks.setText(String.valueOf(repository.forks));
            textViewStars.setText(String.valueOf(repository.stars));
            textViewAuthor.setText(repository.author.login);
            Picasso.with(itemView.getContext()).load(repository.author.avatar).into(imageViewAvatar);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(repository);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Repository repository);
    }
}
