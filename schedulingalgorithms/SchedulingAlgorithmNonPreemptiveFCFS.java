package schedulingalgorithms;

import schedulingsimulation.SchedulingAlgorithm;
import schedulingsimulation.SchedulingMechanisms;
import schedulingsimulation.SimulatedProcess;

public class SchedulingAlgorithmNonPreemptiveFCFS implements SchedulingAlgorithm {

	private SimulatedProcess[] processes = new SimulatedProcess[1000];
	private int count = 0; // process counter
	private int queue = 0; // queue run counter
		
	public SchedulingAlgorithmNonPreemptiveFCFS() {

	}

	
	public void handleCPUBurstCompletionEvent(SimulatedProcess process) {

		if (SchedulingMechanisms.getRunningProcess() == null) {
			if (queue < count){
				
				SchedulingMechanisms.dispatchProcess(processes[queue], -1);
				this.queue++;	
				
			}
		}
		return;
	}

	public void handleExpiredTimeSliceEvent(SimulatedProcess process) {

		return;
		
	}

	public void handleProcessReadyEvent(SimulatedProcess process) {	

		this.processes[count] = process;
		this.count++;
		
		if (SchedulingMechanisms.getRunningProcess() == null) {
			SchedulingMechanisms.dispatchProcess(processes[queue], -1);
			this.queue++;
		}
			
	}
}
