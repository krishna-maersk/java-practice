import java.util.stream.*;

class Sample {    
  public static boolean isPrime(int number) {
    return number > 1 && 
      IntStream.range(2, number)
               .noneMatch(i -> number % i == 0);
  } 
  
  public static void main(String[] args) {
    for(int i = 1; i < 8; i++) {
      System.out.printf("isPrime(%d)? %b\n", i, isPrime(i));
    }
  }              
}
