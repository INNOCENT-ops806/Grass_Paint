package com.example;

import com.formdev.flatlaf.FlatLightLaf;

public class App {
  public static void main(String[] args) {
    try {
      FlatLightLaf.setup();
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }
    new MyFrame();
  }
}
