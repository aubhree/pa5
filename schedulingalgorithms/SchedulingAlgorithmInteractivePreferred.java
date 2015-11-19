package schedulingalgorithms;

import java.util.*;
import schedulingsimulation.SchedulingAlgorithm;
import schedulingsimulation.SchedulingMechanisms;
import schedulingsimulation.SimulatedProcess;

public class SchedulingAlgorithmInteractivePreferred  implements SchedulingAlgorithm {
	
	//interactive = no time slice
	//non-interactive = time slice 10
	ArrayList<SimulatedProcess> inter = new ArrayList<SimulatedProcess>();
	ArrayList<SimulatedProcess> nonInter = new ArrayList<SimulatedProcess>();
	
	public SchedulingAlgorithmInteractivePreferred () {
		
	}

	
	public void handleCPUBurstCompletionEvent(SimulatedProcess process) {
		
		if (SchedulingMechanisms.getProcessName(process).contains("interactive")){
		
			if (!(inter.isEmpty())){
				
				SchedulingMechanisms.dispatchProcess(inter.get(0), -1);
				inter.remove(0);
				
			}
			
			else if (!(nonInter.isEmpty())){
				
				SchedulingMechanisms.dispatchProcess(nonInter.get(0), 10);
				nonInter.remove(0);
				
			}
		
		}
		
		/*else if (SchedulingMechanisms.getProcessName(process).contains("compute")){
				
			if (!(nonInter.isEmpty())){
				
				SchedulingMechanisms.dispatchProcess(nonInter.get(0), 10);
				nonInter.remove(0);
				
			}
			
		}*/
			
		return;
	}

	public void handleExpiredTimeSliceEvent(SimulatedProcess process) {

		if (!(inter.isEmpty())){
				
			SchedulingMechanisms.dispatchProcess(inter.get(0), -1);
			inter.remove(0);
				
		}
		
		else if (!(nonInter.isEmpty())){
				
			SchedulingMechanisms.dispatchProcess(nonInter.get(0), 10);
			nonInter.remove(0);
				
		}
			
		return;
		
	}

	public void handleProcessReadyEvent(SimulatedProcess process) {	
		
		if (SchedulingMechanisms.getProcessName(process).contains("interactive")){
			
			inter.add(process);
			
		}
		else if (SchedulingMechanisms.getProcessName(process).contains("compute")){
			
			nonInter.add(process);
//for (SimulatedProcess s : nonInter)
//System.out.println(SchedulingMechanisms.getProcessName(s)+ ": " + SchedulingMechanisms.getProcessCPUBurstDuration(s) + " | " + SchedulingMechanisms.getProcessArrivalDate(s));

			
		}
		
		if (SchedulingMechanisms.getRunningProcess() == null) {
			
			//checks if something is in the list
			if (!(inter.isEmpty())){
				
				SchedulingMechanisms.dispatchProcess(inter.get(0), -1);
				inter.remove(0);
				
			}
			else if (!(nonInter.isEmpty())){
				
				SchedulingMechanisms.dispatchProcess(nonInter.get(0), 10);
				nonInter.remove(0);
				
			}
			
		}
		
	}
}
