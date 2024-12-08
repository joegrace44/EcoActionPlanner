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
public class EnergyAction extends EcoAction implements Serializable {

    private double energySaved; // (kWh)

    // Constructor for EnergyAction
    public EnergyAction(String actionDescription, double energySaved) {
        super(actionDescription); // Call the parent class constructor to set the description
        this.energySaved = energySaved; // Initialize the energy saved
    }

    // Override the calculateImpact method to return the energy saved
    @Override
    public double calculateImpact() {
        return energySaved; // The impact for energy actions is the energy saved
    }

    // Getter method for energySaved
    public double getEnergySaved() {
        return energySaved; // Return the amount of energy saved
    }

    // Setter method for energySaved
    public void setEnergySaved(double energySaved) {
        this.energySaved = energySaved; // Update the energy saved
    }
}
