package org.usfirst.frc.team5546.robot.commands;

import org.usfirst.frc.team5546.robot.Robot;
import org.usfirst.frc.team5546.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Drive extends Command {
	
	private final double SLOW = 0.7;

    public Drive() {
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if((boolean) Robot.modeChooser.getSelected()){
    		Robot.driveTrain.driveTank(
        			Robot.oi.leftJoystick.getRawAxis(RobotMap.LEFT_DRIVE_AXIS) * SLOW,
        			Robot.oi.rightJoystick.getRawAxis(RobotMap.RIGHT_DRIVE_AXIS) * SLOW,
        			true);
    	} else {
    		Robot.driveTrain.driveTank(
        			Robot.oi.leftJoystick.getRawAxis(RobotMap.LEFT_DRIVE_AXIS),
        			Robot.oi.rightJoystick.getRawAxis(RobotMap.RIGHT_DRIVE_AXIS),
        			true);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
