import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

class Sample {            
  //Euclid's algorithm                         
  public static String getTriple(int m, int n) {   
    int a = m * m - n * n;
    int b = 2 * m * n;
    int c = m * m + n * n;
    
    return String.format("%d %d %d", a, b, c);
  } 
  
  public static List<String> computeTriples(int numberOfValues) {
    return Stream.iterate(2, e -> e + 1)      
                 .flatMap(m -> IntStream.range(1, m)
                                        .mapToObj(n -> getTriple(m, n)))
                 .limit(numberOfValues)
                 .collect(toList());
  }
  
  public static void main(String[] args) {
    //values of a, b, c where a**2 + b**2 == c**2.
    computeTriples(10).forEach(System.out::println); 
  }              
}