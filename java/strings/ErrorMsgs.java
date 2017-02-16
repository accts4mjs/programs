import java.io.*;

public class ErrorMsgs {
  public static final boolean DEBUG_ON = true;
  private String output ="";

  public ErrorMsgs() {
    if (DEBUG_ON) {
      output = String.format("DEBUG_ON\n");
      printf(output);
    }
  }
  public void printf(String fstr) {
    if (DEBUG_ON) {
      System.err.printf(fstr);
    }
  }
}
