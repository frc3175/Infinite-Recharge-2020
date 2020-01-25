package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
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
    public WPI_TalonSRX m_leftDriveSlave, m_rightDriveSlave, m_leftDriveMaster, m_rightDriveMaster;
    private KvLib kvLib;

    SpeedController leftDriveFrController = new WPI_TalonSRX(ElectricalConstants.m_leftDriveMaster);
    SpeedController leftDriveBackController = new WPI_TalonSRX(ElectricalConstants.m_leftDriveSlave);
    SpeedController rightDriveFrController = new WPI_TalonSRX(ElectricalConstants.m_rightDriveMaster);
    SpeedController rightDriveBackController = new WPI_TalonSRX(ElectricalConstants.m_rightDriveSlave);

    private SpeedControllerGroup leftDrive = new SpeedControllerGroup(leftDriveFrController, leftDriveBackController);
    private SpeedControllerGroup rightDrive = new SpeedControllerGroup(rightDriveFrController, rightDriveBackController);

    private DifferentialDrive drive;

    // anthony stuff below
    private AHRS s_NavX;

    public Drive() {

        this.m_leftDriveSlave = new WPI_TalonSRX(ElectricalConstants.m_leftDriveSlave);
        this.m_leftDriveMaster = new WPI_TalonSRX(ElectricalConstants.m_leftDriveMaster);
        this.m_rightDriveSlave = new WPI_TalonSRX(ElectricalConstants.m_rightDriveSlave);
        this.m_rightDriveMaster = new WPI_TalonSRX(ElectricalConstants.m_rightDriveMaster);

        m_leftDriveSlave.follow(m_leftDriveMaster);
        m_rightDriveSlave.follow(m_rightDriveMaster);

        this.s_NavX = new AHRS(Port.kMXP);
        this.kvLib = new KvLib();

        // Current limiting

        // TODO: Current limiting does not work
        /*
         * kvLib.setDriveTrainCurrentLimiting(m_leftDriveBack);
         * kvLib.setDriveTrainCurrentLimiting(m_leftDriveFront);
         * kvLib.setDriveTrainCurrentLimiting(m_rightDriveBack);
         * kvLib.setDriveTrainCurrentLimiting(m_rightDriveFront);
         */
        // Differential drive
        drive = new DifferentialDrive(leftDrive, rightDrive);
    }

    // @param resets the drive encoders
    public void reset() {
        m_leftDriveSlave.setSelectedSensorPosition(0);
        m_leftDriveMaster.setSelectedSensorPosition(0);
        m_rightDriveSlave.setSelectedSensorPosition(0);
        m_rightDriveMaster.setSelectedSensorPosition(0);
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

    /**
     * Drive using two TalonSRX and adjustment values for horizontal and distance
     * correction
     */
    public static void flyWithWires(WPI_TalonSRX starboard, WPI_TalonSRX port, double heading, double throttle) {
        starboard.set(ControlMode.Velocity, -1 * heading - throttle); // Set the starboard drivetrain motor to the steering power requirement added to the base speed
        port.set(ControlMode.Velocity, -1 * heading + throttle); // Does the same but on the other side
    }
}
