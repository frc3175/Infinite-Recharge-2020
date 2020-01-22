package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.config.ElectricalConstants;
import frc.robot.config.RobotConfig;
import frc.robot.lib.KvLib;


//TODO: Change TalonSRXs to FXs
public class Drive {

    // TalonFX Motors
    private WPI_TalonSRX m_leftDriveBack, m_rightDriveBack, m_leftDriveFront, m_rightDriveFront;
    private KvLib kvLib;

    SpeedController leftDriveFrController = new WPI_TalonSRX(ElectricalConstants.m_leftDriveFront);
    SpeedController leftDriveBackController = new WPI_TalonSRX(ElectricalConstants.m_leftDriveBack);
    SpeedController rightDriveFrController = new WPI_TalonSRX(ElectricalConstants.m_rightDriveFront);
    SpeedController rightDriveBackController = new WPI_TalonSRX(ElectricalConstants.m_rightDriveBack);

    private SpeedControllerGroup leftDrive = new SpeedControllerGroup(leftDriveFrController, leftDriveBackController);
    private SpeedControllerGroup rightDrive = new SpeedControllerGroup(rightDriveFrController, rightDriveBackController);

    private DifferentialDrive drive;

    // anthony stuff below
    private AHRS s_NavX;

    public Drive() {

        this.m_leftDriveBack = new WPI_TalonSRX(ElectricalConstants.m_leftDriveBack);
        this.m_leftDriveFront = new WPI_TalonSRX(ElectricalConstants.m_leftDriveFront);
        this.m_rightDriveBack = new WPI_TalonSRX(ElectricalConstants.m_rightDriveBack);
        this.m_rightDriveFront = new WPI_TalonSRX(ElectricalConstants.m_rightDriveFront);
        
        this.s_NavX = new AHRS(Port.kMXP);
        this.kvLib = new KvLib();


        // Current limiting
        
        //TODO: Current limiting does not work
        /*
        kvLib.setDriveTrainCurrentLimiting(m_leftDriveBack);
        kvLib.setDriveTrainCurrentLimiting(m_leftDriveFront);
        kvLib.setDriveTrainCurrentLimiting(m_rightDriveBack);
        kvLib.setDriveTrainCurrentLimiting(m_rightDriveFront);
        */
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
            drive.curvatureDrive(linearSpeed * RobotConfig.transmission, curveSpeed * RobotConfig.transmission, quickT);
        }
    }

    public void calibrate() {
        this.s_NavX.reset();
    }
    
    public void tankDrive(double leftSpeed, double rightSpeed) {
        drive.tankDrive(leftSpeed, rightSpeed);
    }
}