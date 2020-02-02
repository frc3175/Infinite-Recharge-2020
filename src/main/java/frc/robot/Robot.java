/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Operator;
import frc.robot.util.LEDs;
import frc.robot.subsystems.Pneumatics;
import frc.robot.util.ShuffleBoard;
import frc.robot.config.ElectricalConstants;
import frc.robot.lib.KvLib;

/*
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {

  // public config

  // subsystems
  private Drive drive;
  private KvLib kvLib;
  private Operator operator;
  private LEDs leds;
  private Pneumatics pneumatics;
  private ShuffleBoard shuffleBoard;
  private Limelight limelight;



  // Drive controller
  private XboxController driver;

  @Override
  public void autonomousInit() {

  }

  @Override
  public void autonomousPeriodic() {
    /*
     * if (autonMode.equals("cross")) { if (runTime.get() < 2) {
     * driveTrain.arcadeDrive(0.75, 0); } } else if (autonMode.equals("switch")) {
     * if ((field.charAt(0) == 'L' && LOCATION == 0) || (field.charAt(0) == 'R' &&
     * LOCATION == 1)) { // 0 is left, 1 is right switchDump(); } else { if
     * (runTime.get() < 1.5) { driveTrain.arcadeDrive(0.75, 0); } } }
     */
  }

  @Override
  public void robotInit() {
    /* Subsystems */
    this.drive = new Drive();
    this.kvLib = new KvLib();
    this.operator = new Operator();
    //TODO: Turn on Pneumatics
    //this.pneumatics = new Pneumatics();
    this.leds = new LEDs();
    this.limelight = new Limelight();
    this.shuffleBoard = new ShuffleBoard();

    // resets
    drive.reset();
    drive.calibrate();

    /* Initiate Controllers */
    
    driver = new XboxController(ElectricalConstants.driverPort);

    /* Pneumatic intiation */
    //pneumatics.initializeCompressor(true);

  }

  // Run this to reset Pneumatic pistons
  @Override
  public void testPeriodic() {
    pneumatics.resetPneumatics(true);
  }

  @Override
  public void robotPeriodic() {
    LiveWindow.disableAllTelemetry();
    leds.putData();
    leds.rainbow();
    //limelight.limelightTargetControl(operator.getLimelightTrenchAlignButton(), operator.getLimelightLineAlignButton());
  }

  @Override
  public void teleopPeriodic() {

    // LEDs
    //TODO: Optimize this


    /* ShuffleBoard */
    shuffleBoard.shuffleBoardMatchTime();

    /* Operator Commands */
    operator.hopperSpin();
    operator.shooter();
    operator.elevator();
    operator.cameraServo();

    
    double linearSpeed = kvLib.driveDeadband(driver.getRawAxis(1));
    double curveSpeed = kvLib.driveDeadband(-driver.getRawAxis(4));
    drive.move(linearSpeed, curveSpeed, driver.getRawButton(6));

  }
}