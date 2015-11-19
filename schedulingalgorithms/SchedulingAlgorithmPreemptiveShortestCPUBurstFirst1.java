package schedulingalgorithms;
import java.util.*;

import schedulingsimulation.SchedulingAlgorithm;
import schedulingsimulation.SchedulingMechanisms;
import schedulingsimulation.SimulatedProcess;

public class SchedulingAlgorithmPreemptiveShortestCPUBurstFirst implements SchedulingAlgorithm {

	ArrayList<SimulatedProcess> list = new ArrayList<SimulatedProcess>();
	ArrayList<Long> burstRemains = new ArrayList<Long>();
	SimulatedProcess tempProcess = null;
	long tempBurst;
	long maxBurst;
	long arrivalDate;
	long currDate;
	
	public SchedulingAlgorithmPreemptiveShortestCPUBurstFirst() {

	}

	
	public void handleCPUBurstCompletionEvent(SimulatedProcess process) {

		if (SchedulingMechanisms.getRunningProcess() == null) {

			// checks if something is in the list
			if (!(list.isEmpty())){
				
				SchedulingMechanisms.dispatchProcess(list.get(0), -1);
				list.remove(0);
				//burstRemains.remove(0);
				
			}
		
		}
		
		return;
	}

	public void handleExpiredTimeSliceEvent(SimulatedProcess process) {

		return;
	}

	public void handleProcessReadyEvent(SimulatedProcess process) {	

		if (SchedulingMechanisms.getRunningProcess() == null) {
			
			// checks if something is in the list
			if (!(list.isEmpty())){
				//addes new process to the list
				list.add(process);
				//sets max cpu burst duration to tempBurst
				//maxBurst = SchedulingMechanisms.getProcessCPUBurstDuration(process);
				//burstRemains.add(maxBurst);
				//burstRemains.add(getProcessArrivalDate(process));
				//bubble sort list shortest job first
				if (list.size() > 1){
				
					for(int i = 0; i < list.size(); i++){
 
						for(int j = 1; j < list.size()-i; j++){
					
							if(SchedulingMechanisms.getProcessCPUBurstDuration(list.get(j-1)) > SchedulingMechanisms.getProcessCPUBurstDuration(list.get(j))){
						
								tempProcess = list.get(j-1);
								list.set((j-1), list.get(j));
								list.set((j), tempProcess);
						
							}
					
						}
				
					}	
				
				
				}
				//for (SimulatedProcess s : list)
					//System.out.println(SchedulingMechanisms.getProcessName(s)+ ": " + SchedulingMechanisms.getProcessCPUBurstDuration(s));
				//System.out.println("HELLO 1");
				SchedulingMechanisms.dispatchProcess(list.get(0), -1);
				list.remove(0);
				//burstRemains(0);
				
			}
			//no process is running so start the current process that is ready
			else{
				
				SchedulingMechanisms.dispatchProcess(process, -1);
				//maxBurst = SchedulingMechanisms.getProcessCPUBurstDuration(process);
				//currDate = SchedulingMechanisms.getProcessArrivalDate(process);
			
			}
			
		
		}
		
		//checks if current process burst CPU duration is > new process burst CPU duration
		//if so, interrupt process, begin new process, and store old process to arraylist.
		else if (currentCPUBurst() > (SchedulingMechanisms.getProcessCPUBurstDuration(process))){
			
			//list.add(SchedulingMechanisms.preemptRunningProcess());
			SchedulingMechanisms.preemptRunningProcess();
			SchedulingMechanisms.dispatchProcess(process, -1);	
			
		}
		
		else{
			//add process to the list
			list.add(process);
			//bubble sort list shortest job first
			if (list.size() > 1){
				
				for(int i = 0; i < list.size(); i++){
 
					for(int j = 1; j < list.size()-i; j++){
					
						if(SchedulingMechanisms.getProcessCPUBurstDuration(list.get(j-1)) > SchedulingMechanisms.getProcessCPUBurstDuration(list.get(j))){
						
							tempProcess = list.get(j-1);
							list.set((j-1), list.get(j));
							list.set((j), tempProcess);
						
						}
					
					}
				
				}	
				
				
			}
			for (SimulatedProcess s : list)
				System.out.println(SchedulingMechanisms.getProcessName(s)+ ": " + SchedulingMechanisms.getProcessCPUBurstDuration(s));
			
		}
	}
	
	public long currentCPUBurst() {
		
		long arrivalDate = SchedulingMechanisms.getProcessArrivalDate(SchedulingMechanisms.getRunningProcess());
		long currentDate = SchedulingMechanisms.getDate();
		long currentCPUBurst = SchedulingMechanisms.getProcessCPUBurstDuration(SchedulingMechanisms.getRunningProcess())-(currentDate - arrivalDate);
		System.out.println("The current process arrival date is: " + arrivalDate);
		System.out.println("The current date is: " + currentDate);
		System.out.println("The returned CPU burst is: " + currentCPUBurst);
		return currentCPUBurst;
		
	}		
}
