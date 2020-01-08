/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.subsystems.Drive;
import frc.robot.lib.RobotMap;
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

  // Drive controller
  private XboxController driver;

  @Override
  public void robotInit() {
    drive = new Drive();
    kvLib = new KvLib();
    driver = new XboxController(RobotMap.port);
  }

  @Override
  public void teleopPeriodic() {
    // left Y, right X, right shoulder

    double linearSpeed = kvLib.driveDeadband(-driver.getRawAxis(1));
    double curveSpeed = kvLib.driveDeadband(-driver.getRawAxis(4));

    drive.move(linearSpeed, curveSpeed, driver.getRawButton(6));
  }
}