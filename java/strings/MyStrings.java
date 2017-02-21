import java.io.*;
import java.util.Arrays;

public class MyStrings {
  public static final int NUM_FUNCS = 4;
  private int function_number;
  private String function_data;
  private ErrorMsgs dbg;

  public MyStrings(int num, String data) {
    function_number = num;
    function_data = data;
    dbg = new ErrorMsgs();
  }
  private int getRandomRange(int min, int max) {
    return ((int) (Math.random()*(max - min))) + min;
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
  public int missingNumberInArray() {
    // Search an array containing numbers 1-100 and find missing number
    int array_of_nums[] = {48, 65, 14, 52, 54, 3, 79, 92, 60, 11, 76, 86, 88, 24, 95, 37, 47, 25, 96, 74, 26, 12, 100, 1, 56, 31, 72, 89, 85, 53, 39, 46, 27, 59, 94, 70, 21, 19, 80, 20, 32, 90, 43, 42, 66, 10, 35, 67, 83, 81, 49, 44, 23, 57, 77, 91, 2, 69, 8, 16, 13, 63, 40, 58, 82, 64, 29, 93, 55, 36, 7, 45, 17, 51, 99, 71, 38, 22, 75, 28, 5, 9, 41, 6, 30, 4, 73, 62, 50, 61, 87, 98, 15, 34, 18, 68, 33, 84, 78, 97};
    // Randomly choose a number to remove from the set (just add 1 or sub 1 if 100)
    int position = getRandomRange(0, 100);

    for (int i=0; i<100; i++) {
      //dbg.printf(String.format("mnia: i: %d v: %d\n", i, array_of_nums[i]));
    }
    if (array_of_nums[position] < 100) {
      array_of_nums[position] = array_of_nums[position] + 1;
    } else {
      array_of_nums[position] = 99;
    }
    // Sort and scan the array to find the duplicate (which indicates missing number)
    Arrays.sort(array_of_nums);
    for (int i=0; i<100; i++) {
      // dbg.printf(String.format("mnia: i: %d v: %d\n", i, array_of_nums[i]));
    }
    for (int i=0; i<100; i++) {
      if (array_of_nums[i] != (i+1)) {
        System.out.printf("Missing Number is: %d\n", i+1);
      }
    }

    return 0;
  }
  public int insertAndRemoveDoubleList() {
    // create a double linked list with 3 elements

    return 0;
  }
  public int runFunction() {
    switch (function_number) {
      case 1:
        return checkPalindrome();
      case 2:
        return fizBuzz();
      case 3:
        return missingNumberInArray();
      case 4:
        return insertAndRemoveDoubleList();
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
