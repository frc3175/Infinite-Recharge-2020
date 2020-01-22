package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.SerialPort.Port;
import frc.robot.config.ElectricalConstants;

//TODO: Write auton code
// Step 0.5 Shoot 3 Cells
// Step 1: turn 45 degrees
// Step 2: go straight for X encoder rotations
// Step 3: Straighten out (-45) degrees
// Step 4: go straight for X encoder rotations While intake motor is on
// Step 5: reverse all steps and shoot 5 cells
public class Auton {

    private WPI_TalonFX m_leftDriveBack, m_rightDriveBack, m_leftDriveFront, m_rightDriveFront;

    private Drive drive;
    private AHRS s_NavX;
    private int autonState;

    public Auton() {
        drive = new Drive();
        s_NavX = new AHRS(Port.kMXP);

        this.m_leftDriveBack = new WPI_TalonFX(ElectricalConstants.m_leftDriveBack);
        this.m_leftDriveFront = new WPI_TalonFX(ElectricalConstants.m_leftDriveFront);
        this.m_rightDriveBack = new WPI_TalonFX(ElectricalConstants.m_rightDriveBack);
        this.m_rightDriveFront = new WPI_TalonFX(ElectricalConstants.m_rightDriveFront);

        m_leftDriveBack.follow(m_leftDriveFront);
        m_rightDriveBack.follow(m_rightDriveFront);
        // runtime timer
        Timer runTime = new Timer();

    }

    public void periodic() {
        int averageDistance = (m_leftDriveBack.getSelectedSensorPosition() + m_rightDriveBack.getSelectedSensorPosition()) / 2;
        // forward 2
        if (averageDistance <= 2048 && autonState == 0) {
            drive.tankDrive(1, 1);
            autonState++;
            m_leftDriveBack.setSelectedSensorPosition(0);
            m_rightDriveBack.setSelectedSensorPosition(0);
            s_NavX.reset();
            if (autonState == 0 && s_NavX.getAngle() < -10) {
                drive.tankDrive(0.3, 0);
            } else if (autonState == 0 && s_NavX.getAngle() > 10) {
                drive.tankDrive(0, 0.3);
            }
        } 
        if (autonState == 1 && m_leftDriveBack.getSelectedSensorPosition() < 1024 || s_NavX.getAngle() < 90) {
            drive.tankDrive(0.5, 0);
            autonState++;
        } 
    }
}
