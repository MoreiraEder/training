// public class DevMedia {

//     public class ThreadB extends Thread {
    
//         int total;
//          @Override
//          public void run(){
//              synchronized(this){
//                  for(int i=0; i<200 ; i++){
//                      total += i;
//                  }
//                  notify();
//              }
//          }
//  }



//     public static void main (String args[]){
//      ThreadB b = new ThreadB();
//      b.start();

//      synchronized(b){
//          try{
//              System.out.println("Aguardando o b completar...");
//              b.wait();
//          }catch(InterruptedException e){
//              e.printStackTrace();
//          }

//          System.out.println("Total é igual a: " + b.total);
//      }
    
//     }
    
// }

