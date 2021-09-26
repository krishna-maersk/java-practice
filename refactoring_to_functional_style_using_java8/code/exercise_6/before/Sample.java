import java.util.*;
import java.util.stream.*;

class Sample {                                         
  public static boolean isPrime(int number) {
    return number > 1 &&
      IntStream.range(2, number)
               .noneMatch(i -> number % i == 0);
  } 
  
  public static double computeSumOfSqrtOfPrimes(int start, int count) {
    int index = start;
    int computedCount = 0;
    double sum = 0;
    
    while(computedCount < count) {
      if(isPrime(index)) {
        sum += Math.sqrt(index);
        computedCount++;
      }
      index++;      
    }           
    
    return sum;
  } 
  
  
  public static void main(String[] args) {
    System.out.println(computeSumOfSqrtOfPrimes(101, 51));
  }              
}
