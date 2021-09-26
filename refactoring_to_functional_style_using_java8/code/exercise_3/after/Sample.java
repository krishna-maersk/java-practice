import java.util.*;
import static java.util.stream.Collectors.*;

class Sample {    
  public static Map<Integer, List<String>> groupByScores(
    Map<String, Integer> scores) {
                                    
    return scores.keySet()
                 .stream()
                 .collect(groupingBy(scores::get));
  } 
  
  public static void main(String[] args) {
    Map<String, Integer> scores = new HashMap<>();
    
    scores.put("Jack", 12);
    scores.put("Jill", 15);
    scores.put("Tom", 11);
    scores.put("Darla", 15);
    scores.put("Nick", 15);
    scores.put("Nancy", 11);
    
    System.out.println(groupByScores(scores));
  }              
}
