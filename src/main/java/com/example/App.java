package com.example;

import javax.swing.SwingUtilities;

//import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;

public class App {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      try {
        // FlatDarculaLaf.setup();
        FlatLightLaf.setup();
      } catch (Exception e) {
        System.err.println("Error: " + e.getMessage());
      }
      new MainWindow();
    });
  }
}

/*
 * TODO:-- make FlatLaf adapt to system's settings for switching between light
 * and dark theme
 */
