package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.ElectricalConstants;
import frc.robot.config.RobotConfig;

@SuppressWarnings("unused")
public class Drive {

    public final static WPI_TalonFX m_leftDriveSlave = new WPI_TalonFX(ElectricalConstants.m_leftDriveSlave);
    public final static WPI_TalonFX m_rightDriveSlave = new WPI_TalonFX(ElectricalConstants.m_rightDriveSlave);
    public final static WPI_TalonFX m_leftDriveMaster = new WPI_TalonFX(ElectricalConstants.m_leftDriveMaster);
    public final static WPI_TalonFX m_rightDriveMaster = new WPI_TalonFX(ElectricalConstants.m_rightDriveMaster);

    private SpeedControllerGroup LeftMotors = new SpeedControllerGroup(m_leftDriveSlave, m_leftDriveMaster);
    private SpeedControllerGroup RightMotors = new SpeedControllerGroup(m_rightDriveSlave, m_rightDriveMaster);

    public DifferentialDrive drive = new DifferentialDrive(LeftMotors, RightMotors);

    // anthony stuff below
    private AHRS s_NavX = new AHRS(edu.wpi.first.wpilibj.SPI.Port.kMXP);;

    /**
     * resets all drive encoders
     */
    public void reset() {
        m_leftDriveSlave.setSelectedSensorPosition(0);
        m_leftDriveMaster.setSelectedSensorPosition(0);
        m_rightDriveSlave.setSelectedSensorPosition(0);
        m_rightDriveMaster.setSelectedSensorPosition(0);
    }

    /**
     * 
     * @param linearSpeed Foward and reverse linear direction
     * @param curveSpeed Curved speed
     * @param quickT Quick turn
     */
    public void move(double linearSpeed, double curveSpeed, boolean quickT) {
        drive.curvatureDrive(linearSpeed, curveSpeed, quickT);
    }

    /**
     * Calibrates NavX
     */
    public void calibrate() {
        this.s_NavX.reset();
    }

    /**
     * 
     * @param starboard right side talonFX
     * @param port left Side TalonFX
     * @param leftspeed leftspeed
     * @param rightspeed rightspeed
     */
    public static void autonTank(WPI_TalonFX starboard, WPI_TalonFX port, double leftspeed, double rightspeed) {
        starboard.set(ControlMode.PercentOutput, leftspeed);
        port.set(ControlMode.PercentOutput, rightspeed);
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
