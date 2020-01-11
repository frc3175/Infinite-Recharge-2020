/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Operator;
import frc.robot.config.RobotMap;
import frc.robot.lib.KvLib;

/*
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {

  // public config

  // subclasses
  private Drive drive;
  private KvLib kvLib;
  private Operator operator;
  

  // Drive controller
  private XboxController driver;


  @Override
	public void autonomousInit() {

  }

  @Override
	public void autonomousPeriodic() {
	/*	if (autonMode.equals("cross")) {
			if (runTime.get() < 2) {
				driveTrain.arcadeDrive(0.75, 0);
			}
		} else if (autonMode.equals("switch")) {
			if ((field.charAt(0) == 'L' && LOCATION == 0) || (field.charAt(0) == 'R' && LOCATION == 1)) {
				// 0 is left, 1 is right
				switchDump();
			} else {
				if (runTime.get() < 1.5) {
					driveTrain.arcadeDrive(0.75, 0);
				}
			}
		} */ 
	}

  @Override
  public void robotInit() {
    /* Subsystems */
    drive = new Drive();
    kvLib = new KvLib();
    operator = new Operator();


    /* Initiate Controllers */
    driver = new XboxController(RobotMap.driverPort);

    /* Pneumatic intiation*/

  }

  @Override
  public void teleopPeriodic() {

    /*Operator controls
    */
    operator.returnCommands();

    /* left Y, right X, right shoulder

    */
    double linearSpeed = kvLib.driveDeadband(-driver.getRawAxis(1));
    double curveSpeed = kvLib.driveDeadband(-driver.getRawAxis(4));

    drive.move(linearSpeed, curveSpeed, driver.getRawButton(6));
  }
}