package org.usfirst.frc.team5546.robot.subsystems;

import org.usfirst.frc.team5546.robot.RobotMap;
import org.usfirst.frc.team5546.robot.commands.Suck;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
	
	VictorSP intakeMotor;
	Servo servo;
	
	public Intake() {
		intakeMotor = new VictorSP(RobotMap.INTAKE_MOTOR);
	    servo = new Servo(1);
	}

    public void initDefaultCommand() {
        setDefaultCommand(new Suck());
    }
    
    public void setSpeed(double speed) {
    	intakeMotor.set(speed);
    }
    
    public void setUp() {
    	servo.setAngle(180);
    }
    
    public void setDown() {
    	servo.setAngle(0);
    }
}

