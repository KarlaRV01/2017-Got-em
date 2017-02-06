package org.frc.team2579.subsystems;

import org.frc.team2579.Robot;
import org.frc.team2579.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {
	public static enum BallStopState {
		IN, OUT
	};

	public static final double OUTER_INTAKE_LOAD_SPEED = 1.0;
	public static final double INNER_INTAKE_LOAD_SPEED = 1.0;
	public static final double OUTER_INTAKE_EJECT_SPEED = -0.4;
	public static final double INNER_INTAKE_EJECT_SPEED = -0.4;

	private static Solenoid ballStop;
	private CANTalon outerRoller, innerRoller;

	public Intake() {
		try {
			outerRoller = new CANTalon(
					RobotMap.INTAKE_OUTER_ROLLER_MOTOR_CAN_ID);
			innerRoller = new CANTalon(
					RobotMap.INTAKE_INNER_ROLLER_MOTOR_CAN_ID);

			outerRoller.enableBrakeMode(true);
			innerRoller.enableBrakeMode(true);

			ballStop = new Solenoid(RobotMap.SHOOTER_POSITION_PCM_ID);
		} catch (Exception e) {
			System.err.println("An error occurred in the Intake constructor");
		}
	}

	public void setSpeedOuter(double speed) {
		outerRoller.set(-speed);
	}

	public void setSpeedInner(double speed) {
		innerRoller.set(speed);
	}

	@Override
	protected void initDefaultCommand() {

	}

	public void updateStatus(Robot.OperationMode operationMode) {
		if (operationMode == Robot.OperationMode.TEST) {
		}
	}

	public static boolean getBallStop() {
		return ballStop.get();
	}

	public void setBallStop(BallStopState state) {
		if(state == BallStopState.IN) {
			ballStop.set(true);
		} else if(state == BallStopState.OUT) {
			ballStop.set(false);
		}
	}
}