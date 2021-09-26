import java.util.function.Predicate;

class Applicant {
  public boolean isCredible() {
    return true;    
  }                            
  
  public int getCreditScore() {
    return 700;
  }
  
  public int getEmploymentYears() {
    return 10;
  }
  
  public boolean hasCriminalRecord() {
    return true;
  }
}                  

class Sample {          
  public static void evaluate(
    Applicant applicant, Predicate<Applicant> evaluator) {
      
    String result = 
      applicant.isCredible() && evaluator.test(applicant) ?
        "accepted" : "rejected";
        
    System.out.println("Result of evaluating applicant: " + result);
  } 
  
  public static void main(String[] args) {
    Applicant applicant = new Applicant();
                               
    Predicate<Applicant> creditCheck = 
      theApplicant -> theApplicant.getCreditScore() > 600;      
    Predicate<Applicant> employmentCheck =
      theApplicant -> theApplicant.getEmploymentYears() > 0;
    Predicate<Applicant> crimeCheck =
      theApplicant -> !theApplicant.hasCriminalRecord();
    
    evaluate(applicant, creditCheck);
    
    evaluate(applicant, creditCheck.and(employmentCheck));
    
    evaluate(applicant, crimeCheck.and(employmentCheck));
    
    evaluate(applicant, crimeCheck.and(creditCheck).and(employmentCheck));
  }              
}