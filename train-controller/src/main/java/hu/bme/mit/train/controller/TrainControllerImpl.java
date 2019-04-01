package hu.bme.mit.train.controller;

import hu.bme.mit.train.interfaces.TrainController;
import java.util.Timer;
import java.util.TimerTask;

public class TrainControllerImpl implements TrainController {

	private int step = 0;
	private int referenceSpeed = 0;
	private int speedLimit = 0;

	private Timer timer;

	public TrainControllerImpl(){

		timer = new Timer();
		timer.schedule(new RemindTask(), 0, 500);
	}

	@Override
	public void followSpeed() {

		if (referenceSpeed < 0) {
			referenceSpeed = 0;
		} else {
				setJoystickPosition(step);
		    if(referenceSpeed+step > 0) {
	               referenceSpeed += step;
	           } else {
		        referenceSpeed = 0;
	           }
		}

		enforceSpeedLimit();
	}

	@Override
	public int getReferenceSpeed() {
		return referenceSpeed;
	}

	//Sets the maximum speed of the train.
	@Override
	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		enforceSpeedLimit();

	}

	private void enforceSpeedLimit() {
		if (referenceSpeed > speedLimit) {
			referenceSpeed = speedLimit;
		}
	}

	@Override
	public void setJoystickPosition(int joystickPosition) {
		this.step = joystickPosition;
	}

	class RemindTask extends TimerTask {
	        public void run() {
						followSpeed();
	        }
	}
}
