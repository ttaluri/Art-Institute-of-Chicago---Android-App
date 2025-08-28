package org.ttaluri.artinstituteapiapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class ArtworkAdapter extends RecyclerView.Adapter<ArtworkAdapter.ArtworkViewHolder> {
    private List<Artwork> artworkList;
    private Context context;
    private OnItemClickListener onItemClickListener;


    public ArtworkAdapter(List<Artwork> artworkList, Context context) {
        this.artworkList = artworkList;
        this.context = context;
    }


    public interface OnItemClickListener {
        void onItemClick(Artwork artwork);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public ArtworkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.artwork_item, parent, false);
        return new ArtworkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtworkViewHolder holder, int position) {
        Artwork artwork = artworkList.get(position);
        holder.titleTextView.setText(artwork.getTitle());


        Glide.with(context)
                .load(artwork.getThumbnailUrl())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.thumbnailImageView);

        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(artwork);
            }
        });
    }

    @Override
    public int getItemCount() {
        return artworkList.size();
    }

    public static class ArtworkViewHolder extends RecyclerView.ViewHolder {
        public ImageView thumbnailImageView;
        public TextView titleTextView;

        public ArtworkViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnailImageView = itemView.findViewById(R.id.artworkThumbnail);
            titleTextView = itemView.findViewById(R.id.artworkTitle);
        }
    }
}

