package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;

public class TrainSensorImpl implements TrainSensor {

	private TrainController controller;
	private TrainUser user;
	private int speedLimit = 5;

	public TrainSensorImpl(TrainController controller, TrainUser user) {
		this.controller = controller;
		this.user = user;
	}

	@Override
	public int getSpeedLimit() {
		return speedLimit;
	}

	@Override
	public void overrideSpeedLimit(int speedLimit) {		
		if (speedLimit < 0 || speedLimit > 500){
			user.setAlarmState(true);
			return;
		}
		
		int halfActualSpeedLimit = this.SpeedLimit;		
		if (speedLimit%2 == 0){
			halfActualSpeedLimit = this.SpeedLimit/2;
		} else {
			halfActualSpeedLimit = this.SpeedLimit/2 + 1;
		}

		if (speedLimit < halfActualSpeedLimit) {
			user.setAlarmState(true);
			return;
		}
	
		this.speedLimit = speedLimit;
		controller.setSpeedLimit(speedLimit);
		user.setAlarmState(false);
	}

}
