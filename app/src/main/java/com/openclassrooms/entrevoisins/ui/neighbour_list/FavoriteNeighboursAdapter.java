package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteNeighboursAdapter extends RecyclerView.Adapter<FavoriteNeighboursAdapter.ViewHolder> {

    private final List<Neighbour> favoriteNeighboursList;
    private Context mContext;

    /**
     * Constucteur de l'adapter
     */

    public FavoriteNeighboursAdapter(List<Neighbour> favoriteNeighboursList){
        this.favoriteNeighboursList = favoriteNeighboursList;
    }

    /**
     * View Holder
     */

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.item_favorite_list_avatar)
        ImageView mNeighbourAvatar;
        @BindView(R.id.item_favorite_list_name)
        TextView mNeighbourName;
        @BindView(R.id.item_favorite_list_button)
        ImageButton mFavoriteButton;

        public ViewHolder(View view){
            super(view);
            ButterKnife.bind(this, view);

            view.setOnClickListener(v -> {
                Neighbour neighbour = favoriteNeighboursList.get(getAdapterPosition());
                Intent intent = new Intent(mContext, NeighbourDetailActivity.class);
                intent.putExtra("neighbour", neighbour);
                mContext.startActivity(intent);
            });
        }

        void displayNeighbour(Neighbour neighbour){
            mNeighbourName.setText(neighbour.getName());
            Glide.with(mNeighbourAvatar.getContext())
                    .load(neighbour.getAvatarUrl())
                    .apply(RequestOptions.circleCropTransform())
                    .into(mNeighbourAvatar);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.favorite_neighbour_item_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Neighbour neighbour = favoriteNeighboursList.get(position);
        holder.displayNeighbour(neighbour);
    }

    @Override
    public int getItemCount(){
        return favoriteNeighboursList.size();
    }
}
