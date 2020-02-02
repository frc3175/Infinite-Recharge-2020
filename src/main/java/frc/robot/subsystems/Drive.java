package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.config.ElectricalConstants;
import frc.robot.config.RobotConfig;
import frc.robot.lib.KvLib;

@SuppressWarnings("unused")
public class Drive {
    
    private KvLib kvLib;

    private final WPI_TalonFX m_leftDriveSlave = new WPI_TalonFX(ElectricalConstants.m_leftDriveSlave);
    private final WPI_TalonFX m_rightDriveSlave = new WPI_TalonFX(ElectricalConstants.m_rightDriveSlave);
    private final WPI_TalonFX m_leftDriveMaster = new WPI_TalonFX(ElectricalConstants.m_leftDriveMaster);
    private final WPI_TalonFX m_rightDriveMaster = new WPI_TalonFX(ElectricalConstants.m_rightDriveMaster);
    
  private SpeedControllerGroup LeftMotors = new SpeedControllerGroup(m_leftDriveSlave, m_leftDriveMaster);
  private SpeedControllerGroup RightMotors = new SpeedControllerGroup(m_rightDriveSlave, m_rightDriveMaster);


    private DifferentialDrive drive = new DifferentialDrive(LeftMotors, RightMotors);

    // anthony stuff below
    private AHRS s_NavX;

    public Drive() {
    

        // Changed port to SPI from serial
        this.s_NavX = new AHRS(edu.wpi.first.wpilibj.SPI.Port.kMXP);
        this.kvLib = new KvLib();

        // Current limiting

        // TODO: Current limiting does not work
        /*
         * kvLib.setDriveTrainCurrentLimiting(m_leftDriveBack);
         * kvLib.setDriveTrainCurrentLimiting(m_leftDriveFront);
         * kvLib.setDriveTrainCurrentLimiting(m_rightDriveBack);
         * kvLib.setDriveTrainCurrentLimiting(m_rightDriveFront);
         */
    }

    // @param resets the drive encoders
    public void reset() {
        m_leftDriveSlave.setSelectedSensorPosition(0);
        m_leftDriveMaster.setSelectedSensorPosition(0);
        m_rightDriveSlave.setSelectedSensorPosition(0);
        m_rightDriveMaster.setSelectedSensorPosition(0);
    }

    public void move(double linearSpeed, double curveSpeed, boolean quickT) {
        drive.curvatureDrive(linearSpeed, curveSpeed, quickT);
    }

    public void calibrate() {
        this.s_NavX.reset();
    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        drive.tankDrive(leftSpeed, rightSpeed);
    }

    /**
     * Drive using two TalonSRX and adjustment values for horizontal and distance
     * correction
     */
    public static void flyWithWires(WPI_TalonFX starboard, WPI_TalonFX port, double heading, double throttle) {
        starboard.set(ControlMode.Velocity, -1 * heading - throttle); // Set the starboard drivetrain motor to the
                                                                      // steering power requirement added to the base
                                                                      // speed
        port.set(ControlMode.Velocity, -1 * heading + throttle); // Does the same but on the other side
    }
}
