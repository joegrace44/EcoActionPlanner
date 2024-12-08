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
public class TransportAction extends EcoAction implements Serializable {

    private String transportType; // The type of transport
    private double distanceTravelled; // The distance traveled in km
    private double co2Emission; // The CO2 emissions in kilos

    // Constructor for TransportAction
    public TransportAction(String transportType, double distanceTravelled) {
        super("Transport Type: " + transportType); // Call the parent class constructor with a description
        this.transportType = transportType; // Set the type of transport
        this.distanceTravelled = distanceTravelled; // Set the distance traveled
        this.co2Emission = calculateImpact(); // Calculate and store the CO2 emissions
    }

    @Override
    public double calculateImpact() {
        // Defines the emissions from each type of transport
        double carEmissionFactor = 0.21;
        double busEmissionFactor = 0.05;
        double bikeEmissionFactor = 0.0;
        double walkEmissionFactor = 0.0;

        // Calculate CO2 emissions based on the transport type and distance travelled
        if (transportType.equalsIgnoreCase("car")) {
            return distanceTravelled * carEmissionFactor;
        } else if (transportType.equalsIgnoreCase("bus")) {
            return distanceTravelled * busEmissionFactor;
        } else if (transportType.equalsIgnoreCase("bike")) {
            return distanceTravelled * bikeEmissionFactor;
        } else if (transportType.equalsIgnoreCase("walk")) {
            return distanceTravelled * walkEmissionFactor;
        } else {
            return 0.0;
        }
    }

    // Getter for transportType
    public String getTransportType() {
        return transportType;
    }

    // Getter for distanceTravelled
    public double getDistanceTravelled() {
        return distanceTravelled;
    }

    // Getter for CO2 emissions
    public double getCO2Emission() {
        return co2Emission;
    }

    // Setter for transportType
    public void setTransportType(String transportType) {
        this.transportType = transportType; // Update the transport type
        this.co2Emission = calculateImpact(); // Recalculate CO2 emissions based on the new type
    }

    // Setter for distanceTravelled
    public void setDistanceTravelled(double distanceTravelled) {
        this.distanceTravelled = distanceTravelled; // Update the distance traveled
        this.co2Emission = calculateImpact(); // Recalculate CO2 emissions based on the new distance
    }
}
