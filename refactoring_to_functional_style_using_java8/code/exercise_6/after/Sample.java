import java.util.*;
import java.util.stream.*;

class Sample {                                         
  public static boolean isPrime(int number) {
    return number > 1 &&
      IntStream.range(2, number)
               .noneMatch(i -> number % i == 0);
  } 
  
  public static double computeSumOfSqrtOfPrimes(int start, int count) {
    return Stream.iterate(start, e -> e + 1)
                 .filter(Sample::isPrime)
                 .mapToDouble(Math::sqrt)
                 .limit(count)
                 .sum();
  } 
  
  public static void main(String[] args) {
    System.out.println(computeSumOfSqrtOfPrimes(101, 51));
  }              
}