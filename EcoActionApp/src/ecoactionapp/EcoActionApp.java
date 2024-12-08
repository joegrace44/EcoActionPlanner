/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ecoactionapp;

/**
 *
 * @author joegr
 */
public class EcoActionApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Run the GUI application
        java.awt.EventQueue.invokeLater(() -> {
            GUI app = new GUI();
            app.setVisible(true);
        });
    }
}
