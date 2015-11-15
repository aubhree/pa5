package schedulingalgorithms;
import java.util.*;

import schedulingsimulation.SchedulingAlgorithm;
import schedulingsimulation.SchedulingMechanisms;
import schedulingsimulation.SimulatedProcess;

public class SchedulingAlgorithmPreemptiveShortestCPUBurstFirst implements SchedulingAlgorithm {

	ArrayList<SimulatedProcess> list = new ArrayList<SimulatedProcess>();
	SimulatedProcess tempProcess = null;
	
	public SchedulingAlgorithmPreemptiveShortestCPUBurstFirst() {

	}

	
	public void handleCPUBurstCompletionEvent(SimulatedProcess process) {

		if (SchedulingMechanisms.getRunningProcess() == null) {
			//if something is in the list, dispatch first one in the list.
			//for (int i = 0; i < list.size(); i++) {
				//System.out.println("Number = " + SchedulingMechanisms.getProcessName(list.get(i)));
			//}
   
			if (!(list.isEmpty())){
				
				SchedulingMechanisms.dispatchProcess(list.get(0), -1);
				list.remove(0);
				//System.out.println("does it come here again?");
				
			}
		
		}
		
		return;
	}

	public void handleExpiredTimeSliceEvent(SimulatedProcess process) {

		return;
	}

	public void handleProcessReadyEvent(SimulatedProcess process) {	

		if (SchedulingMechanisms.getRunningProcess() == null) {
			
			SchedulingMechanisms.dispatchProcess(process, -1);		
		
		}
		
		//checks if current process burst CPU duration is > new process burst CPU duration
		//if so, interrupt process, begin new process, and store old process to arraylist.
		else if ((SchedulingMechanisms.getProcessCPUBurstDuration(SchedulingMechanisms.getRunningProcess())) > (SchedulingMechanisms.getProcessCPUBurstDuration(process))){
			
			//list.add(SchedulingMechanisms.preemptRunningProcess());
			SchedulingMechanisms.preemptRunningProcess();
			SchedulingMechanisms.dispatchProcess(process, -1);	
			
		}
		
		else{
			//System.out.println("added to list");
			list.add(process);
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
			//NEED TO ADD SORT ALGORITHM HERE
			
		}
	}
}
