import java.io.*;

public class MyStrings {
  public static final int NUM_FUNCS = 2;
  private int function_number;
  private String function_data;
  private ErrorMsgs dbg;

  public MyStrings(int num, String data) {
    function_number = num;
    function_data = data;
    dbg = new ErrorMsgs();
  }
  public static void usage(int exit_value) {
    System.out.printf("usage: MyStrings <1-%d> <data_for_function>\n", NUM_FUNCS);
    System.exit(exit_value);
  }
  public int checkPalindrome() {
    String reversed_string = "";

    for (int i=function_data.length()-1; i>=0; i--) {
      reversed_string += function_data.substring(i,i+1);
    }
    dbg.printf(String.format("rvrs: '%s'\n", reversed_string));
    if (function_data.compareTo(reversed_string) == 0) {
      System.out.println("TRUE");
      return 0;
    } else {
      System.out.println("FALSE");
      return -1;
    }
  }
  public int fizBuzz() {
    int max = Integer.parseInt(function_data);

    dbg.printf(String.format("fb: '%02d'\n", max));
    if (max > 30) {
      max = 30;
    }
    for (int i=1; i<=max; i++) {
      dbg.printf(String.format("fb: i: %02d\n", i));
      if ((i % 5) == 0 && (i % 3) == 0) {
        System.out.printf("%02d: fizbuzz\n", i);
      } else if ((i % 3) == 0) {
        System.out.printf("%02d: fiz\n", i);
      } else if ((i % 5) == 0) {
        System.out.printf("%02d: buzz\n", i);
      }
    }
    return 0;
  }
  public int runFunction() {
    switch (function_number) {
      case 1:
        return checkPalindrome();
      case 2:
        return fizBuzz();
      default:
        System.out.print("ERROR: Unknown function # ");
        System.out.println(Integer.toString(function_number));
        usage(4);
    }
    return -1;
  }
  public static void main(String[] args) {
    MyStrings mystring = null;
    int func_num;

    if (args.length != 2) {
      System.out.printf("ERROR: wrong # of args.  Expected 2 got %d.\n", args.length);
      usage(2);
    }
    func_num = Integer.parseInt(args[0]);
    if (func_num < 1 || func_num > NUM_FUNCS) {
      System.out.printf("ERROR: '%s' is incorrect function num.\n", args[0]);
      usage(3);
    }

    mystring = new MyStrings(func_num, args[1]);
    System.exit(mystring.runFunction());
  }
}
