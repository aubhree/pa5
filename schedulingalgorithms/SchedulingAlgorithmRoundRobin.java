package schedulingalgorithms;
import java.util.*;
import schedulingsimulation.SchedulingAlgorithm;
import schedulingsimulation.SchedulingMechanisms;
import schedulingsimulation.SimulatedProcess;

public class SchedulingAlgorithmRoundRobin implements SchedulingAlgorithm {
	
	ArrayList<SimulatedProcess> list = new ArrayList<SimulatedProcess>();
	
	public SchedulingAlgorithmRoundRobin() {

	}

	
	public void handleCPUBurstCompletionEvent(SimulatedProcess process) {
		
		if (SchedulingMechanisms.getRunningProcess() == null) {

			// checks if something is in the list
			if (!(list.isEmpty())){
				
				SchedulingMechanisms.dispatchProcess(list.get(0), 10);
				list.remove(0);
				
			}
		
		}
		return;
	}

	public void handleExpiredTimeSliceEvent(SimulatedProcess process) {

		if (!(list.isEmpty())){
				
			SchedulingMechanisms.dispatchProcess(list.get(0), 10);
			list.remove(0);
				
			}
	
		return;
	}

	public void handleProcessReadyEvent(SimulatedProcess process) {	

		if (SchedulingMechanisms.getRunningProcess() == null) {
			
			if (!(list.isEmpty())){
				//addes new process to the list
				list.add(process);
			}
			else{
				
				SchedulingMechanisms.dispatchProcess(process, 10);			
				
			}
			
			
		}
		else{
			
			list.add(process);
			
		}
	}
}
