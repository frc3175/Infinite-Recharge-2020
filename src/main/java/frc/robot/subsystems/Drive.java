package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.*;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import com.kauailabs.navx.frc.AHRS;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import frc.robot.lib.KvLib;
import frc.robot.config.RobotMap;

public class Drive {

    // TalonFX Motors
    private WPI_TalonFX m_leftDriveBack, m_rightDriveBack, m_leftDriveFront, m_rightDriveFront;
    private KvLib kvLib;

    SpeedController leftDriveFrController = new WPI_TalonFX(RobotMap.m_leftDriveFront);
    SpeedController leftDriveBackController = new WPI_TalonFX(RobotMap.m_leftDriveBack);
    SpeedController rightDriveFrController = new WPI_TalonFX(RobotMap.m_rightDriveFront);
    SpeedController rightDriveBackController = new WPI_TalonFX(RobotMap.m_rightDriveBack);
    
    private SpeedControllerGroup leftDrive = new SpeedControllerGroup(leftDriveFrController, leftDriveBackController);
    private SpeedControllerGroup rightDrive = new SpeedControllerGroup(rightDriveFrController, rightDriveBackController);

    private DifferentialDrive drive;

    // anthony stuff below
    private AHRS s_NavX;
    // public state
    public int gearshift;

    public Drive() {

        kvLib = new KvLib();

        // @param sets the encoders
        // m_leftDriveBack.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.CTRE_MagEncoder_Relative);
        // m_leftDriveFront.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.CTRE_MagEncoder_Relative);
        // m_rightDriveBack.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.CTRE_MagEncoder_Relative);
        // m_rightDriveFront.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.CTRE_MagEncoder_Relative);

        // Current limiting
        kvLib.setDriveTrainCurrentLimiting(m_leftDriveBack);
        kvLib.setDriveTrainCurrentLimiting(m_leftDriveFront);
        kvLib.setDriveTrainCurrentLimiting(m_rightDriveBack);
        kvLib.setDriveTrainCurrentLimiting(m_rightDriveFront);

        // Differential drive
        drive = new DifferentialDrive(leftDrive, rightDrive);
    }

    // @param resets the drive encoders
    public void reset() {
        m_leftDriveBack.setSelectedSensorPosition(0);
        m_leftDriveFront.setSelectedSensorPosition(0);
        m_rightDriveBack.setSelectedSensorPosition(0);
        m_rightDriveFront.setSelectedSensorPosition(0);
    }

    public void move(double linearSpeed, double curveSpeed, boolean quickT) {

        drive.curvatureDrive(linearSpeed, curveSpeed, quickT);

        /*  if (s_NavX.getVelocityX() > 1.72 || s_NavX.getVelocityY() > 1.72) {

            drive.curvatureDrive(linearSpeed, curveSpeed, quickT);

        } else {

            drive.curvatureDrive(linearSpeed * RobotMap.transmission, curveSpeed * RobotMap.transmission, quickT);
        } */

    }

    public void calibrate() {
        this.s_NavX.reset();
    }
}