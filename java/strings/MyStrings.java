import java.io.*;

public class MyStrings {
  public static final int NUM_FUNCS = 1;
  private int function_number;
  private String function_data;

  public MyStrings(int num, String data) {
    function_number = num;
    function_data = data;
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

    if (function_data.compareTo(reversed_string) == 0) {
      System.out.println("TRUE");
      return 0;
    } else {
      System.out.println("FALSE");
      return -1;
    }
  }
  public int runFunction() {
    switch (function_number) {
      case 1:
        return checkPalindrome();
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
