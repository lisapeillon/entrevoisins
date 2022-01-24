package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NeighbourDetailActivity extends AppCompatActivity  {

    @BindView(R.id.activity_detail_imageview_avatar)
    public ImageView avatarImageView;
    @BindView(R.id.profile_informations_textview_name)
    public TextView secondNameTextView;
    @BindView(R.id.profile_informations_textview_location)
    public TextView locationTextView;
    @BindView(R.id.profile_informations_textview_phonenumber)
    public TextView phonenumberTextView;
    @BindView(R.id.profile_informations_textview_facebook)
    public TextView facebookTextView;
    @BindView(R.id.activity_detail_fab)
    public FloatingActionButton mFloatingActionButton;
    @BindView(R.id.profile_informations_textview_aproposdemoi)
    public TextView aproposTextView;
    @BindView(R.id.toolbar_layout)
    public CollapsingToolbarLayout mToolbarLayout;
    @BindView(R.id.activity_detail_toolbar)
    public Toolbar mToolbar;

    private Neighbour mNeighbour;
    private String neighbourName;
    private String avatarUrl;
    private String neighbourAdress;
    private String neighbourAbout;
    private String neighbourPhoneNumber;
    private boolean isFavorite;

    private NeighbourApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_detail);

        //Initialisation de l'API Service
        mApiService = DI.getNeighbourApiService();

        //Initialisation de ButterKnife
        ButterKnife.bind(this);

        //Attribution de la toolbar et affichage du bouton retour
        setSupportActionBar(mToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        //Récupération des informations du voisin à afficher
        mNeighbour = getIntent().getParcelableExtra("neighbour");
        getNeighbourInfo(mNeighbour);
        setNeighbourInfo();

        mFloatingActionButton.setOnClickListener(v -> setFavorite(mNeighbour));
    }

    private void getNeighbourInfo(Neighbour neighbour){
        neighbourName = neighbour.getName();
        avatarUrl = neighbour.getAvatarUrl();
        neighbourAdress = neighbour.getAddress().replace(';', 'à');
        neighbourAbout = neighbour.getAboutMe();
        neighbourPhoneNumber = neighbour.getPhoneNumber();
        isFavorite = neighbour.getFavorite();
    }

    private void setNeighbourInfo(){
        //Set avatar
        Glide.with(getApplicationContext()).load(avatarUrl).centerCrop().into(avatarImageView);
        //Set the textviews of the cardviews
        secondNameTextView.setText(neighbourName);
        locationTextView.setText(neighbourAdress);
        phonenumberTextView.setText(neighbourPhoneNumber);
        facebookTextView.setText("www.facebook.fr/" + neighbourName.toLowerCase());
        aproposTextView.setText(neighbourAbout);
        //Set toolbar title
        mToolbarLayout.setTitle(neighbourName);
        if(!isFavorite){
            mFloatingActionButton.setImageResource(R.drawable.ic_star_border);
        } else {
            mFloatingActionButton.setImageResource(R.drawable.ic_star);
        }
    }

    private void setFavorite(Neighbour neighbour){
        String toastMessage;
        if(!isFavorite){
            mApiService.addFavoriteNeighbour(neighbour);
            toastMessage = neighbourName + " a été ajouté à la liste des favoris.";
        } else {
            mApiService.removeFavoriteNeighbour(neighbour);
            toastMessage = neighbourName + " a été supprimé de la liste des favoris.";
        }
        checkFavorite(neighbour);
        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
    }

    private void checkFavorite(Neighbour neighbour){
        isFavorite = neighbour.getFavorite();
        if(!isFavorite){
            mFloatingActionButton.setImageResource(R.drawable.ic_star_border);
        } else {
            mFloatingActionButton.setImageResource(R.drawable.ic_star);
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }
}