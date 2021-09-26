class Sample {    
  public static boolean isPrime(int number) {
    boolean divisible = false;
    for(int i = 2; i < number; i++) {
      if(number % i == 0) {
        divisible = true;
        break;
      }
    }         
    
    return number > 1 && !divisible;
  } 
  
  public static void main(String[] args) {
    for(int i = 1; i < 8; i++) {
      System.out.printf("isPrime(%d)? %b\n", i, isPrime(i));
    }
  }              
}
