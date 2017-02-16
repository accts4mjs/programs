public class HelloWorld {
   public static void main(String[] args) {
     String phrase = "the music made   it   hard      to        concentrate";
     String delims = "[ ]+";
     String[] tokens = phrase.split(delims);
     int length = args.length;
     System.out.println("# args = " + Integer.toString(length));
     System.out.println("Hello, World!!" + " " + tokens[1] + ", " + tokens[3]);
   }
}
