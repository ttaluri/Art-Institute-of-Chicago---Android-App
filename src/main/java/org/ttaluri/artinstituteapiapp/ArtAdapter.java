package org.ttaluri.artinstituteapiapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class ArtAdapter extends RecyclerView.Adapter<ArtAdapter.ArtViewHolder> {
    private List<Artwork> artworkList;

    public ArtAdapter(List<Artwork> artworkList) {
        this.artworkList = artworkList;
    }

    @NonNull
    @Override
    public ArtViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.artwork_item, parent, false);
        return new ArtViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtViewHolder holder, int position) {
        Artwork artwork = artworkList.get(position);

        holder.titleTextView.setText(artwork.getTitle());
        holder.artistTextView.setText(artwork.getArtist());
        holder.dateTextView.setText(artwork.getDateDisplay());

        Picasso.get().load(artwork.getThumbnailUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return artworkList.size();
    }

    public static class ArtViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public TextView artistTextView;
        public TextView dateTextView;
        public ImageView imageView;

        public ArtViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.artworkTitle);
            artistTextView = itemView.findViewById(R.id.artistNameTextView);
            dateTextView = itemView.findViewById(R.id.artworkDateTextView);
            imageView = itemView.findViewById(R.id.artworkImageView);
        }
    }
}
