package com.example.myapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DetailActivity;
import com.example.myapplication.R;
import com.example.myapplication.models.CatBreed;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CatBreedsAdapter extends RecyclerView.Adapter<CatBreedsAdapter.CatBreedViewHolder> {
    
    private List<CatBreed> catBreeds;
    
    public CatBreedsAdapter() {
        this.catBreeds = new ArrayList<>();
    }
    
    public void setCatBreeds(List<CatBreed> catBreeds) {
        this.catBreeds = catBreeds;
        notifyDataSetChanged();
    }
    
    @NonNull
    @Override
    public CatBreedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cat_breed, parent, false);
        return new CatBreedViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull CatBreedViewHolder holder, int position) {
        CatBreed catBreed = catBreeds.get(position);
        holder.bind(catBreed);
    }
    
    @Override
    public int getItemCount() {
        return catBreeds.size();
    }
    
    class CatBreedViewHolder extends RecyclerView.ViewHolder {
        
        private TextView tvBreedName;
        private ImageView ivCatImage;
        private TextView tvOrigin;
        private TextView tvIntelligence;
        
        public CatBreedViewHolder(@NonNull View itemView) {
            super(itemView);
            tvBreedName = itemView.findViewById(R.id.tv_breed_name);
            ivCatImage = itemView.findViewById(R.id.iv_cat_image);
            tvOrigin = itemView.findViewById(R.id.tv_origin);
            tvIntelligence = itemView.findViewById(R.id.tv_intelligence);
            
            itemView.setOnClickListener(v -> {
                if (getBindingAdapterPosition() != RecyclerView.NO_POSITION) {
                    CatBreed catBreed = catBreeds.get(getBindingAdapterPosition());
                    
                    // Navegar a DetailActivity
                    android.content.Intent intent = new android.content.Intent(itemView.getContext(), DetailActivity.class);
                    intent.putExtra(DetailActivity.EXTRA_CAT_BREED, catBreed);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
        
        public void bind(CatBreed catBreed) {
            tvBreedName.setText(catBreed.getName());
            tvOrigin.setText(catBreed.getOrigin() != null ? catBreed.getOrigin() : "Unknown");
            
            // Formatear inteligencia
            String intelligence = "Intelligence: " + catBreed.getIntelligence() + "/5";
            tvIntelligence.setText(intelligence);
            
            // Alternar colores de fondo para variedad visual
            int position = getBindingAdapterPosition();
            int[] backgroundColors = {
                R.color.card_background_purple,
                R.color.card_background_blue, 
                R.color.card_background_green,
                R.color.card_background_orange
            };
            
            int colorIndex = position % backgroundColors.length;
            int backgroundColor = itemView.getContext().getColor(backgroundColors[colorIndex]);
            
            // Aplicar color de fondo a la CardView
            androidx.cardview.widget.CardView cardView = (androidx.cardview.widget.CardView) itemView;
            cardView.setCardBackgroundColor(backgroundColor);
            
            String referenceImageId = catBreed.getReferenceImageId();
            if (referenceImageId != null && !referenceImageId.isEmpty()) {
                // Construir URL de imagen usando reference_image_id
                String imageUrl = getImageUrl(referenceImageId);
                
                Picasso.get()
                        .load(imageUrl)
                        .placeholder(R.drawable.loading_placeholder)     // Fondo gris mientras carga
                        .error(R.mipmap.hairycat)          // Imagen si falla
                        .fit()                             // Ajustar al ImageView
                        .centerCrop()                      // Recortar centro
                        .into(ivCatImage);
                        
            } else {
                // Usar imagen por defecto si no hay reference_image_id
                ivCatImage.setImageResource(R.mipmap.hairycat);
            }
        }
    }

    private String getImageUrl(String referenceImageId) {
        if (referenceImageId == null || referenceImageId.isEmpty()) {
            return "";
        }
        return "https://cdn2.thecatapi.com/images/" + referenceImageId + ".jpg";
    }
} 