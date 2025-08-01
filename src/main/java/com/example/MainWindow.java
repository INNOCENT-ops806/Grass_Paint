package com.example;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File; // Not used directly here, but could be.

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import utils.Constants;
import utils.ScreenConfig;

public class MainWindow extends JFrame {
  ScreenConfig screenC = new ScreenConfig();

  // MenuHandler
  private JPanel contentPanel = new JPanel();
  private JSlider slider = new JSlider();
  // private JSlider Bslider = new JSlider();
  private JLabel newContentLabel;
  private JPanel sliderPanel = new JPanel();

  // Handlers
  private ActionHandler actionHandler;
  private MenuHandler menuHandler;

  public MainWindow() {
    super("Grass Paint");

    actionHandler = new ActionHandler(this, contentPanel, newContentLabel);

    menuHandler = new MenuHandler(actionHandler);
    this.setJMenuBar(menuHandler.getMenuBar());

    // --TODO:- add a gridLayout to separate different artifacts on the Bslider part
    // this.getContentPane().add(Bslider, BorderLayout.SOUTH);

    slider.setValue(0);
    slider.setForeground(Constants.default_color);
    slider.setOrientation(SwingConstants.VERTICAL);
    slider.setPreferredSize(new Dimension(((int) (screenC.getScreenConfigWidth() * 0.075)),
        ((int) (screenC.getScreenConfigWidth() * 0.40))));
    slider.setBackground(Constants.purple);
    Border border = BorderFactory.createEtchedBorder();
    contentPanel.setBorder(border);
    menuHandler.getMenuBar().setBorder(border);

    sliderPanel.setPreferredSize(new Dimension(((int) (screenC.getScreenConfigWidth() * 0.075)),
        ((int) (screenC.getScreenConfigWidth() * 0.30))));
    sliderPanel.setBackground(Color.LIGHT_GRAY);
    sliderPanel.add(slider);
    this.getContentPane().add(sliderPanel, BorderLayout.WEST);
    this.getContentPane().add(contentPanel, BorderLayout.CENTER); // Main image content goes here

    // Set up the JFrame properties
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(screenC.getScreenConfigWidth(), screenC.getScreenConfigHeight());
    this.setLocationRelativeTo(null); // Centralize the frame
    this.setFont(new Font(Constants.default_font_family, Constants.font_style, Constants.default_font_size));

    this.setVisible(true);
  }

}
