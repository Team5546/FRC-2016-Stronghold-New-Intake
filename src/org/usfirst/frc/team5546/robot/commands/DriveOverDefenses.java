package org.usfirst.frc.team5546.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveOverDefenses extends CommandGroup {
    
    public  DriveOverDefenses() {    	
    	addSequential(new DriveAuto(0.5, 0.5, 5));
    }
}
