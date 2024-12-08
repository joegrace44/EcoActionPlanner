/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecoactionapp;

import java.io.Serializable;

/**
 *
 * @author joegr
 */

// Serves as a parent class for specific types of eco actions, 
public abstract class EcoAction implements Serializable {

    // Description of the eco action
    private String description;

    // Constructor for initializing the EcoAction with a description.
    public EcoAction(String description) {
        this.description = description;
    }

    // Getter for the description of the eco action.
    public String getDescription() {
        return description;
    }

    // Setter for updating the description of the eco action.
    public void setDescription(String description) {
        this.description = description;
    }

    // Abstract method to calculate the environmental impact of the eco action.
    public abstract double calculateImpact();
}