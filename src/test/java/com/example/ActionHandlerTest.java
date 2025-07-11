package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.awt.event.ActionEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;

public class ActionHandlerTest {

  // Declare mock objects for the dependencies of ActionHandler
  private JFrame mockFrame;
  private JPanel mockContentPanel;
  private JLabel mockNewContentLabel;
  private ImageIcon mockImageIcon;

  private ActionHandler actionHandler;

  @BeforeEach
  void setUp() {
    // Create mock instances for each test
    mockFrame = mock(JFrame.class);
    mockContentPanel = mock(JPanel.class);
    mockNewContentLabel = mock(JLabel.class);
    mockImageIcon = mock(ImageIcon.class);

    // Initialize the ActionHandler with mock dependencies
    actionHandler = new ActionHandler(mockFrame, mockContentPanel, mockNewContentLabel);
    actionHandler.setImageIcon(mockImageIcon);
  }

  /*
   * @Test
   * void testHandleExitAction() {
   * // Why: To ensure that calling handleExitAction attempts to terminate the
   * // application.
   * 
   * try (MockedStatic<System> mockedSystem = mockStatic(System.class)) {
   * mockedSystem.when(() -> System.exit(anyInt())).thenThrow(new
   * SecurityException("System.exit called"));
   * 
   * // Create a fake ActionEvent for "Exit"
   * ActionEvent exitEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
   * "Exit");
   * 
   * // Call the method under test
   * try {
   * actionHandler.actionPerformed(exitEvent);
   * } catch (SecurityException e) {
   * assertEquals("System.exit called", e.getMessage());
   * }
   * 
   * // Verify that System.exit was called exactly once with argument 0
   * mockedSystem.verify(() -> System.exit(0), times(1));
   * }
   * }
   */ // --TODO:-find a way to test System.exit() as Mockito does not mock static
      // method

  @Test
  void testHandleOpenAction_ApproveOption() {
    // Why: To verify that when the user approves the file chooser,
    // the content panel is updated with the selected image.

    // Mock the JFileChooser constructor
    try (MockedConstruction<JFileChooser> mockedFileChooser = mockConstruction(JFileChooser.class,
        (mock, context) -> {
          // When showOpenDialog is called, return APPROVE_OPTION
          when(mock.showOpenDialog(any())).thenReturn(JFileChooser.APPROVE_OPTION);
          // When getSelectedFile is called, return a mock File
          when(mock.getSelectedFile()).thenReturn(mock(File.class));
        })) {

      // Mock JOptionPane to prevent dialogs during test
      try (MockedStatic<JOptionPane> mockedOptionPane = mockStatic(JOptionPane.class)) {

        // Create a fake ActionEvent for "Open"
        ActionEvent openEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Open");

        // Call the method under test
        actionHandler.actionPerformed(openEvent);

        // Verify interactions with the mocked contentPanel
        verify(mockContentPanel, times(1)).removeAll();
        verify(mockContentPanel, times(1)).add(any(JLabel.class));
        verify(mockContentPanel, times(1)).revalidate();
        verify(mockContentPanel, times(1)).repaint();

        // Verify that JOptionPane was NOT shown for an error or cancel
        mockedOptionPane.verifyNoInteractions();
      }
    }
  }

  @Test
  void testHandleOpenAction_CancelOption() {
    // Why: To verify that when the user cancels the file chooser,
    // a "Pressed cancel" message is shown via JOptionPane.

    try (MockedConstruction<JFileChooser> mockedFileChooser = mockConstruction(JFileChooser.class,
        (mock, context) -> {
          when(mock.showOpenDialog(any())).thenReturn(JFileChooser.CANCEL_OPTION);
        })) {

      try (MockedStatic<JOptionPane> mockedOptionPane = mockStatic(JOptionPane.class)) {
        ActionEvent openEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Open");
        actionHandler.actionPerformed(openEvent);

        // Verify that the "Pressed cancel" dialog was shown
        mockedOptionPane.verify(() -> JOptionPane.showConfirmDialog(
            isNull(), // parentComponent
            eq("Pressed cancel"), // message
            eq("Exiting open file"), // title
            eq(JOptionPane.OK_OPTION), // optionType
            eq(JOptionPane.INFORMATION_MESSAGE) // messageType
        ), times(1));

        // Verify no other interactions with contentPanel if canceled
        verify(mockContentPanel, never()).removeAll();
      }
    }
  }

  @Test
  void testHandleNewAction() {
    // Why: To verify that the placeholder for new file action is triggered.
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream oldOut = System.out;
    System.setOut(new PrintStream(baos));

    ActionEvent newEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "New");
    actionHandler.actionPerformed(newEvent);

    System.setOut(oldOut);
    String output = baos.toString();
    assertTrue(output.contains("New file action triggered."));

  }

}
