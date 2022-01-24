package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Event fired when a user opens a Neighbour
 */

public class OpenNeighbourEvent {
    /**
     * Neighbour to open
     */
    public Neighbour neighbour;

    /**
     * Constructor.
     * @param neighbour
     */
    public OpenNeighbourEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
    }
}
