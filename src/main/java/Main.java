import java.io.IOException;
import java.util.Scanner;

public class Main {
  public static void main(String[] args){
    if (args.length != 2 || !args[0].equals("-E")) {
      System.out.println("Usage: ./your_program.sh -E <pattern>");
      System.exit(1);
    }

    String pattern = args[1];  
    Scanner scanner = new Scanner(System.in);
    String inputLine = scanner.nextLine();

    // You can use print statements as follows for debugging, they'll be visible when running tests.
    System.err.println("Logs from your program will appear here!");

    //Uncomment this block to pass the first stage
    
    if (matchPattern(inputLine, pattern)) {
        System.exit(0);
    } else {
        System.exit(1);
    }
  }

  public static boolean matchPattern(String inputLine, String pattern) {
    if (pattern.length() == 1) {
      return inputLine.contains(pattern);
    } else if (pattern.equals("\\d")){
      return inputLine.chars().anyMatch(Character::isDigit);
    }else if (pattern.equals("\\w")){

      char ch[]=inputLine.toCharArray();
      int len=ch.length;

      for(int index=0;index<len;index++){

        if( (ch[index]>='A' &&  ch[index]<='Z') ||
            (ch[index]>='a' &&  ch[index]<='z') || 
            (ch[index]>='0' &&  ch[index]<='9') ||
            ch[index]=='_'){
              return true;
        }
      }

    return false;
    }else if(pattern.startsWith("[") && pattern.endsWith("]") && pattern.length()>2 ){
      String subStr=pattern.substring(1,pattern.length()-1);
      if(subStr.startsWith("^")){
         return inputLine.chars().anyMatch(ch->subStr.indexOf(ch)<0);
      }
      return inputLine.chars().anyMatch(ch->subStr.subStr(1).indexOf(ch)>=0);
    }
    else {
      throw new RuntimeException("Unhandled pattern: " + pattern);
    }
  }
}
