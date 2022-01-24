package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    /**
     * Create a neighbour
     * @param neighbour
     */
    void createNeighbour(Neighbour neighbour);

    /**
     * Add a neighbour to favorite
     * @param neighbour
     */
    void addFavoriteNeighbour(Neighbour neighbour);

    /**
     * Removes a neighbour from favorites
     * @param neighbour
     */
    void removeFavoriteNeighbour(Neighbour neighbour);

    /**
     * Get a list of favorite neighbours
     * @return {@link List}
     */
    List<Neighbour> getFavoriteNeighbours();
}
