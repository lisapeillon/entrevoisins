package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private final List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    /**
     * Favorites
     * @param neighbour
     */
    @Override
    public void addFavoriteNeighbour(Neighbour neighbour) {
        Neighbour n = neighbours.get(neighbours.indexOf(neighbour));
        n.setFavorite(true);
        neighbour.setFavorite(true);
    }


    @Override
    public void removeFavoriteNeighbour(Neighbour neighbour) {
        Neighbour n = neighbours.get(neighbours.indexOf(neighbour));
        n.setFavorite(false);
        neighbour.setFavorite(false);
    }

    @Override
    public List<Neighbour> getFavoriteNeighbours() {
        ArrayList<Neighbour> favoriteNeighboursList = new ArrayList<>();
        for(Neighbour i : neighbours){
            if(i.getFavorite()){
                favoriteNeighboursList.add(i);
            }
        }
        return favoriteNeighboursList;
    }


}
