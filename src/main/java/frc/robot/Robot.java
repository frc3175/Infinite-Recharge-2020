/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import frc.robot.subsystems.Auton;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Pneumatics;
import frc.robot.util.ShuffleBoard;
import frc.robot.commands.LimelightTargetControl;
import frc.robot.commands.MoveElevator;
import frc.robot.commands.MoveIntake;
import frc.robot.commands.ShootBall;
import frc.robot.commands.SpinHopper;
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
  private Pneumatics pneumatics;
  private ShuffleBoard shuffleBoard;
  private Limelight limelight;
  private Auton auton;

  //commands
  private ShootBall shootball = new ShootBall();
  private SpinHopper spinHopper = new SpinHopper();
  private MoveElevator moveElevator = new MoveElevator();
  private LimelightTargetControl limelightTargetControl = new LimelightTargetControl();
  private MoveIntake moveIntake = new MoveIntake();



  // Drive controller
  private XboxController driver;

  @Override
  public void robotInit() {
    /* Subsystems */
    this.drive = new Drive();
    this.pneumatics = new Pneumatics();

    //TODO:Enable Limelight
    this.limelight = new Limelight();
    this.shuffleBoard = new ShuffleBoard();

    //TODO:Enable Auton
    this.auton = new Auton();
    

    // resets
    drive.reset();
    drive.calibrate();

    /* Initiate Controllers */
    driver = new XboxController(ElectricalConstants.driverPort);

    /* Pneumatic intiation */
    pneumatics.pneumaticsInitialization();
    pneumatics.initializeCompressor(true);

    //limelight initialization
    limelight.initializeLimelight();

  }

  // Run this to reset Pneumatic pistons
  @Override
  public void testPeriodic() {
    pneumatics.resetPneumatics(true);
  }

  @Override
  public void autonomousInit() {
    drive.reset();
    drive.calibrate();
  }

  @Override
  public void autonomousPeriodic() {
    auton.execute();
  }

  @Override
  public void robotPeriodic() {
    LiveWindow.disableAllTelemetry();
    limelight.periodicNumbers();

  }

  @Override
  public void teleopPeriodic() {

    /* ShuffleBoard */
    shuffleBoard.shuffleBoardMatchTime();

    /* Operator Commands */
    spinHopper.execute();
    shootball.execute();
    moveElevator.execute();
    limelightTargetControl.execute();
    moveIntake.execute();
    
    
    double linearSpeed = KvLib.driveDeadband(driver.getRawAxis(1));
    double curveSpeed = KvLib.driveDeadband(-driver.getRawAxis(4));
    drive.move(linearSpeed, curveSpeed, driver.getRawButton(6));

  }
}