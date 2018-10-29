/**
 * author: team OS Ninjas
 * class: Operaring Systems
 * This class simulates how a Fcfs algorithm works
 * It uses a queue implemented on a list to go through all the process in
 * queue to figure out the waitTimes
 */
import java.util.*;
public class Fcfs{
  LinkedList<Process>readyQueue = new LinkedList<Process>();
  int IOtime = 2;


  public Fcfs(Memory<Process> queue){
   readyQueue = queue.processQueue();


  }

  /** calculates the waiting times of each processes. returns the total waiting time **/
  public int findWaitingTime(){
    int wt = 0;

    for (Process process : readyQueue){
      // relevant variables
      int at = process.getArrivalTime();
      int bt = process.getBurstTime();
      int IOfreq = process.getIOtimeFreq();

      //except for the first process whose wait is 0, the wait for the others are total wait time so far
      //minus the arrival time of that process
      process.setWaitTime(wt - at);
      try{
        if (bt % IOfreq == 0)
          wt = wt + process.getBurstTime() + (bt/IOfreq - 1)*IOtime;
        else
          wt = wt + process.getBurstTime() + (bt/IOfreq)*IOtime;
      }
      catch(ArithmeticException e){
        System.out.println(IOfreq);
        wt = wt + process.getBurstTime();

      }
    }

    return wt;

  }
  public int findTurnAroundTime(){
    //for (int i = 0 ; i < n; i++){
      //tat[i] = bt[i-1] + wt[i-1];
    //}
    int tat = 0;
    for (Process process : readyQueue){
      // setting up turn around times
      process.setTurnATime(tat+process.getWaitTime());
    }
    return tat;
  }
  public float findAvgTime(){
    int n = readyQueue.size();
    return findWaitingTime()/n;
  }
  public String toString(){
    String waitTimes = "";
    findWaitingTime();
    for(Process process: readyQueue){
      waitTimes += Integer.toString(process.getWaitTime()) + " ";
    }
    //System.out.println(waitTimes);
    return waitTimes;
  }
  /**
  public static void main(String[] args){
    Fcfs test = new Fcfs();
    int [] processes = {1,2,3, 4};
    int [] burstTime = {21, 3, 6, 2};
    int [] waitTime = new int[burstTime.length];

    test.findWaitingTime(burstTime, burstTime.length, waitTime);
    System.out.println(test.findAvgTime(waitTime, burstTime.length));
    System.out.println(test.toString(waitTime));
  }
  **/
}
