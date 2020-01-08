package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.lib.KvLib;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import com.kauailabs.navx.frc.AHRS;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.lib.RobotMap;

public class Drive {

    // TalonFX Motors
    public WPI_TalonSRX m_leftDriveBack;
    private WPI_TalonSRX m_rightDriveBack;
    private WPI_TalonSRX m_leftDriveFront;
    private WPI_TalonSRX m_rightDriveFront;

    private SpeedControllerGroup leftDrive = new SpeedControllerGroup(m_leftDriveFront, m_leftDriveBack);
    private SpeedControllerGroup rightDrive = new SpeedControllerGroup(m_rightDriveFront, m_rightDriveBack);

    private DifferentialDrive drive;

    // anthony stuff below
    private AHRS s_NavX;
    // public state
    public int gearshift;

    public Drive() {

        // left Falcons
        m_leftDriveBack = new WPI_TalonSRX(RobotMap.m_leftDriveBack);
        m_leftDriveFront = new WPI_TalonSRX(RobotMap.m_leftDriveFront);
        // right Falcons
        m_rightDriveBack = new WPI_TalonSRX(RobotMap.m_rightDriveBack);
        m_rightDriveFront = new WPI_TalonSRX(RobotMap.m_rightDriveFront);

        // @param sets the encoders
        // m_leftDriveBack.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.CTRE_MagEncoder_Relative);
        // m_leftDriveFront.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.CTRE_MagEncoder_Relative);
        // m_rightDriveBack.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.CTRE_MagEncoder_Relative);
        // m_rightDriveFront.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.CTRE_MagEncoder_Relative);

        // Current limiting
        m_leftDriveBack.configContinuousCurrentLimit(35);
        m_leftDriveBack.configPeakCurrentDuration(250);
        m_leftDriveBack.configPeakCurrentLimit(50);
        m_leftDriveBack.configOpenloopRamp(.4);

        m_leftDriveFront.configContinuousCurrentLimit(35);
        m_leftDriveFront.configPeakCurrentDuration(250);
        m_leftDriveFront.configPeakCurrentLimit(50);
        m_leftDriveFront.configOpenloopRamp(.4);

        m_rightDriveBack.configContinuousCurrentLimit(35);
        m_rightDriveBack.configPeakCurrentDuration(250);
        m_rightDriveBack.configPeakCurrentLimit(50);
        m_rightDriveBack.configOpenloopRamp(.4);

        m_rightDriveFront.configContinuousCurrentLimit(35);
        m_rightDriveFront.configPeakCurrentDuration(250);
        m_rightDriveFront.configPeakCurrentLimit(50);
        m_rightDriveFront.configOpenloopRamp(.4);

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

        if (s_NavX.getVelocityX() > 1.72 || s_NavX.getVelocityY() > 1.72) {

            drive.curvatureDrive(linearSpeed, curveSpeed, quickT);

        } else {

            drive.curvatureDrive(linearSpeed * RobotMap.transmission, curveSpeed * RobotMap.transmission, quickT);
        }

    }

    public void calibrate() {
        this.s_NavX.reset();
    }
}