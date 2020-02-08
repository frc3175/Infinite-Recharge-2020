/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//FRC 3175 Code

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import frc.robot.subsystems.Auton;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Operator;
import frc.robot.subsystems.Pneumatics;
import frc.robot.util.ShuffleBoard;
import frc.robot.ElectricalConstants;
import frc.robot.lib.KvLib;

public class Robot extends TimedRobot {

  // public config

  // subsystems
  private Drive drive;
  private Pneumatics pneumatics;
  private ShuffleBoard shuffleBoard;
  private Limelight limelight;
  private Auton auton;


  //Global Timers
  private Timer hopperTimer = new Timer();
  private Timer autonTimer = new Timer();


  //Necessary subsystems for Ian's Unconventional Code
  private Hopper mainHopper = new Hopper();
  private Operator mainOperator = new Operator();

  // Drive controller
  private XboxController driver;

  @Override
  public void robotInit() {
    /* Subsystems */
    this.drive = new Drive();
    this.pneumatics = new Pneumatics();
    this.limelight = new Limelight();
    this.shuffleBoard = new ShuffleBoard();
    this.auton = new Auton();

    //Timer starts
    hopperTimer.start();
    autonTimer.start();

    // resets
    drive.reset();
    drive.calibrate();

    /* Initiate Controllers */
    driver = new XboxController(ElectricalConstants.driverPort);

    /* Pneumatic intiation */
    pneumatics.pneumaticsInitialization();
    pneumatics.initializeCompressor(true);

    // limelight initialization
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
    mainOperator.doElevator();
    mainOperator.doIntake();
    mainOperator.doLimelightTargetControl();
    mainOperator.doShooter();
    mainOperator.doSpinHopper();

    //LED magic
    mainOperator.shooterOutput();
    mainOperator.detectTarget();

    /**
    * Ian's unconventional code THAT WORKS (take that Jessica) 
    * Spins hopper in forward direction for 1 second
    * Spins hopper in reverse direction for 3 seconds
     */
    if(mainOperator.getIntakeButton()){
      if (hopperTimer.get() < 1) {
        mainHopper.hopperDirection(0); 
      } else if (hopperTimer.get() > 1 && hopperTimer.get() < 3) {
        mainHopper.hopperDirection(1);
      } else if (hopperTimer.get() > 3) {
        hopperTimer.reset();
      }
  }

    double linearSpeed = KvLib.driveDeadband(driver.getRawAxis(1));
    double curveSpeed = KvLib.driveDeadband(-driver.getRawAxis(4));
    drive.move(linearSpeed, curveSpeed, driver.getRawButton(6));

  }
}