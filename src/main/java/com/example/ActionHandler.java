package com.example;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import utils.Constants;

public class ActionHandler implements ActionListener {

  private JFrame frame;
  private JPanel contentPanel;
  private JLabel newContentLabel;
  private ImageIcon imageIcon;

  public ActionHandler(JFrame frame, JPanel contentPanel, JLabel newContentLabel) {
    this.frame = frame;
    this.contentPanel = contentPanel;
    this.newContentLabel = newContentLabel;
  }

  public void setNewContentLabel(JLabel label) {
    this.newContentLabel = label;
  }

  public void setImageIcon(ImageIcon icon) {
    this.imageIcon = icon;
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    String command = e.getActionCommand();
    switch (command) {
      case "Exit":
        handleExitAction();
        break;
      case "Open":
        handleOpenAction();
        break;
      case "New":
        handleNewAction();
        break;
      /*
       * case "fullscreen [ ]": // Use the exact string from JMenuItem for now
       * handleFullScreenAction();
       * break;
       * case "Zoom in [+]":
       * handleZoomInAction();
       * break; // --TODO:-- handle zoom in & out by keyboard
       * action
       * case "Zoom out [-]":
       * handleZoomOutAction();
       * break;
       */
      case "Cut":
        handleCutAction();
        break;
      case "Paste":
        handlePasteAction();
        break;
      default:
        System.out.println("Unhandled action command: " + command);
        break;
    }
  }

  private void handleExitAction() {
    System.out.println("Exiting the application");
    System.exit(0);
  }

  private void handleNewAction() {
    // --TODO: -- add logic for creating a new image file
    // the new open file method should ask the user to enter the file name and the
    // extension if the user does not enter the extension the extension should
    // defalt to filename."png"
    // and the user should select the dir where they are saving their new file
    System.out.println("New file action triggered.");
  }

  /*
   * private void handleFullScreenAction() {
   * System.out.println("This button is working.... the fullscreen btn");
   * // --TODO: -- probably make the contentPanel fullScreen whilst the other
   * // components are hiden
   * }
   */

  /*
   * private void handleZoomInAction() {
   * System.out.println("Zoom in action triggered.");
   * }
   * // --TODO:-- add keyboard shorcut logic
   * private void handleZoomOutAction() {
   * System.out.println("Zoom out action triggered.");
   * }
   */

  private void handleCutAction() {
    System.out.println("Cut action triggered.");
  }

  private void handlePasteAction() {
    System.out.println("Paste action triggered.");
  }

  // Original openFileM logic, now part of ActionHandler
  private void handleOpenAction() {
    JFileChooser chooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png");
    chooser.setFileFilter(filter);
    chooser.setFont(new Font(Constants.default_font_family, Constants.font_style, Constants.default_font_size));
    int returnVal = chooser.showOpenDialog(null);

    switch (returnVal) {
      case JFileChooser.APPROVE_OPTION -> {
        File file = chooser.getSelectedFile();
        openFileMToFrame(file);
      }
      case JFileChooser.CANCEL_OPTION -> {
        JOptionPane.showConfirmDialog(null, "Pressed cancel", "Exiting open file", JOptionPane.OK_OPTION,
            JOptionPane.INFORMATION_MESSAGE);
      }
      default -> JOptionPane.showConfirmDialog(null, "An unexpected error occured", "Error", JOptionPane.OK_OPTION,
          JOptionPane.ERROR_MESSAGE);
    }
  }

  private void openFileMToFrame(File file) {
    contentPanel.removeAll();
    imageIcon = new ImageIcon(file.getAbsolutePath());
    JLabel tempLabel = new JLabel(imageIcon);
    setNewContentLabel(tempLabel);
    setImageIcon(imageIcon);
    contentPanel.add(newContentLabel);
    contentPanel.revalidate();
    contentPanel.repaint();
  }
}
