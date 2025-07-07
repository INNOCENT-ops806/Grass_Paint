package com.example;

import javax.swing.SwingUtilities;

import com.formdev.flatlaf.FlatLightLaf;

public class App {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {

      @Override
      public void run() {
        try {
          FlatLightLaf.setup();
        } catch (Exception e) {
          System.err.println("Error: " + e.getMessage());
        }
        new MyFrame();
      }
    });
  }
}
