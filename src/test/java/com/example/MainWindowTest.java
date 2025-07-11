package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MainWindowTest {
  // The tests are for learning purposes and to make debugging way easier when
  // adding other features
  @Test
  void shouldNotReturnAnEmptyMenuHandler() { // this is to test whether the MenuHandler that is referenced is not null
    assertNotNull(MenuHandler.class);
  }

  @Test
  void shouldNotReturnAnEmptyActionHandler() {
    assertNotNull(ActionHandler.class);
  }
}
