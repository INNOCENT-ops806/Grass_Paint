package com.example;

import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Rectangle;

public class ScreenConfig {
  private int usableWidth;
  private int usableHeight;

  ScreenConfig() throws HeadlessException {

    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();// get the local GraphicsEnvironment
    Rectangle maxWindowsBounds = ge.getMaximumWindowBounds();
    usableWidth = maxWindowsBounds.width;// get usable screen width excluding taskbar
    usableHeight = maxWindowsBounds.height;// get usable screen height excluding taskbar

  }

  public int getScreenConfigHeight() {
    return this.usableHeight;
  }

  public int getScreenConfigWidth() {
    return this.usableWidth;
  }
}
