package com.example;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import utils.Constants;

public class MenuHandler {

  private JMenuBar menuBar;
  private ActionHandler actionHandler;

  private JMenu fileMenu, editMenu, shareMenu, helpMenu;
  private JMenuItem openFItem, newFItem, saveFItem, exitFItem;
  private JMenuItem cutEMenu, pasteEMenu;

  public MenuHandler(ActionHandler actionHandler) {
    this.actionHandler = actionHandler;

    // Instantiating components
    menuBar = new JMenuBar();
    menuBar.setBackground(Constants.light_blue);

    // Menubar content
    fileMenu = new JMenu("File");
    editMenu = new JMenu("Edit");
    shareMenu = new JMenu("Share");
    helpMenu = new JMenu("Help");

    // Add Menus to MenuBar
    menuBar.add(fileMenu);
    menuBar.add(editMenu);
    menuBar.add(shareMenu);
    menuBar.add(helpMenu);

    // Menu for editMenu
    cutEMenu = new JMenuItem("Cut");
    pasteEMenu = new JMenuItem("Paste");

    // Add editMenuItems
    editMenu.add(cutEMenu);
    editMenu.add(pasteEMenu);

    // Menu for fileMenu
    openFItem = new JMenuItem("Open");
    newFItem = new JMenuItem("New");
    saveFItem = new JMenuItem("Save");
    exitFItem = new JMenuItem("Exit");

    // Add fileMenuItems
    fileMenu.add(openFItem);
    fileMenu.add(newFItem);
    fileMenu.add(saveFItem);
    fileMenu.add(exitFItem);

    // Settings for fileMenuItems
    fileMenu.setFont(new Font(Constants.default_font_family, Constants.font_style, Constants.default_font_size));

    // Add ActionListener for fileMenu items
    // --WARNING:-- Attach the single ActionHandler instance to all menu items
    exitFItem.addActionListener(actionHandler);
    openFItem.addActionListener(actionHandler);
    newFItem.addActionListener(actionHandler);
    saveFItem.addActionListener(actionHandler);
    cutEMenu.addActionListener(actionHandler);
    pasteEMenu.addActionListener(actionHandler);

    // Settings for menuBar
    menuBar.setFont(new Font(Constants.default_font_family, Constants.font_style, Constants.default_font_size));
    menuBar.setForeground(Color.ORANGE);

    // Add the component to viewMenu
    helpMenu.setFont(new Font(Constants.default_font_family, Constants.font_style, Constants.default_font_size));

  }

  // Method to return the configured menu bar
  public JMenuBar getMenuBar() {
    return menuBar;
  }
}
