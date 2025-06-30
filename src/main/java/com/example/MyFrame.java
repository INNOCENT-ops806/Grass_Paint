package com.example;

import com.example.ScreenConfig;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.DoubleToIntFunction;

import javax.swing.*;

public class MyFrame extends JFrame implements ActionListener {
  ScreenConfig screenC = new ScreenConfig();// to get the actual height and width of the screen
  JMenuBar menuBar;
  JMenu menu;
  JMenuItem menuItem;

  // MenuBar
  JMenu fileMenu;
  JMenu editMenu;
  JMenu viewMenu;

  // Menu for editMenu
  JMenuItem cutEMenu;
  JMenuItem pasteEMenu;

  // Menu items for fileMenu
  JMenuItem openFItem;
  JMenuItem newFItem;
  JMenuItem saveFItem;
  JMenuItem exitFItem;

  MyFrame() {
    // Instatiating components
    menuBar = new JMenuBar();
    menu = new JMenu();
    menuItem = new JMenuItem();

    // Menubar content
    fileMenu = new JMenu("File");
    editMenu = new JMenu("Edit");
    viewMenu = new JMenu("View");

    // Menus
    menuBar.add(fileMenu);
    menuBar.add(editMenu);
    menuBar.add(viewMenu);

    // Menu for editMenu
    cutEMenu = new JMenuItem("✂️ Cut");
    pasteEMenu = new JMenuItem("Paste");

    // add editMenuItems
    editMenu.add(cutEMenu);
    editMenu.add(pasteEMenu);

    // Menu for fileMenu
    openFItem = new JMenuItem("Open");
    newFItem = new JMenuItem("New");
    saveFItem = new JMenuItem("Save");
    exitFItem = new JMenuItem("❌ Exit");

    // add fileMenuItems
    fileMenu.add(openFItem);
    fileMenu.add(newFItem);
    fileMenu.add(saveFItem);
    fileMenu.add(exitFItem);
    // settings for fileMenu

    // settings for menuBar
    menuBar.setFont(new Font("Sans Serif", Font.PLAIN, 35));
    menuBar.setForeground(Color.BLACK);
    menuBar.setBackground(Color.GRAY);
    // adding components

    // set the frame
    this.setTitle("Grass Paint");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(screenC.getScreenConfigWidth(), screenC.getScreenConfigHeight());
    this.setLocationRelativeTo(null);// to centralise the frame when it is initially rendered
    this.setFont(new Font("Sans Serif", Font.PLAIN, 16));// this will set the font for frame & everything in it
    this.setJMenuBar(menuBar);
    this.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == exitFItem) {
      System.exit(0);
    }
  }
}
