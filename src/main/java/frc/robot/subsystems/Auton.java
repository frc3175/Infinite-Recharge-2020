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

    private WPI_TalonFX m_leftDriveSlave, m_rightDriveSlave, m_leftDriveMaster, m_rightDriveMaster;

    private Drive drive;
    private AHRS s_NavX;
    private Shooter shooter;
    private Timer timer;
    private int autonState = 0;

    public Auton() {

        // subsystems
        this.drive = new Drive();
        this.shooter = new Shooter();
        this.s_NavX = new AHRS(Port.kMXP);

        this.m_leftDriveSlave = new WPI_TalonFX(ElectricalConstants.m_leftDriveSlave);
        this.m_leftDriveMaster = new WPI_TalonFX(ElectricalConstants.m_leftDriveMaster);
        this.m_rightDriveSlave = new WPI_TalonFX(ElectricalConstants.m_rightDriveSlave);
        this.m_rightDriveMaster = new WPI_TalonFX(ElectricalConstants.m_rightDriveMaster);

        m_leftDriveSlave.follow(m_leftDriveMaster);
        m_rightDriveSlave.follow(m_rightDriveMaster);
        // runtime timer
        timer = new Timer();

    }

    // One wheel rotation = ~12,000 ticks

    public void shootBalls() {
        if (autonState == 0) {
            timer.start();
            // do code
            shooter.shooterBoi(true);
            if (timer.hasPeriodPassed(2.5)) {
                timer.stop();
                timer.reset();
                autonState++;
            }
        }
    }

    public void turn45() {

        if (autonState == 1) {
            // Do code
            if (s_NavX.getAngle() < 45) {
                drive.tankDrive(0.3, 0);
            } else {
                drive.tankDrive(0, 0);
                m_rightDriveSlave.setSelectedSensorPosition(0);
                m_leftDriveSlave.setSelectedSensorPosition(0);
                autonState++;
            }
        }
    }

    public void goStraight() {

        if (autonState == 2) {
            // Do code
            if (m_leftDriveSlave.getSelectedSensorPosition() < 36000
                    && m_rightDriveSlave.getSelectedSensorPosition() < 36000) {
                drive.tankDrive(1, 1);
            } else {
                drive.tankDrive(0, 0);
                autonState++;
            }
        }
    }

    public void turn45Opposite() {

        if (autonState == 3) {
            // Do code
            if (s_NavX.getAngle() > 0) {
                drive.tankDrive(0, 0.3);
            } else {
                drive.tankDrive(0, 0);
                autonState++;
            }
        }
    }

    public void goStraight2() {

        if (autonState == 4) {
            // Do code
            if (m_leftDriveSlave.getSelectedSensorPosition() < 36000
                    && m_rightDriveSlave.getSelectedSensorPosition() < 36000) {
                drive.tankDrive(1, 1);
            } else {
                drive.tankDrive(0, 0);
                m_rightDriveSlave.setSelectedSensorPosition(0);
                m_leftDriveSlave.setSelectedSensorPosition(0);
                autonState++;
            }

        }

    }

    public void reverseBack() {

        if (autonState == 5) {
            // Do code
            if (m_rightDriveSlave.getSelectedSensorPosition() > -36000
                    && m_leftDriveSlave.getSelectedSensorPosition() > -36000) {
                drive.tankDrive(-1, -1);
            } else {
                drive.tankDrive(0, 0);
                autonState++;
            }
        }

    }

    public void turn180() {

        if (autonState == 6) {
            // Do code
            if (s_NavX.getAngle() < 180) {
                drive.tankDrive(0.8, 0);
            } else {
                drive.tankDrive(0, 0);
                autonState++;
            }

        }

    }

    public void shootBalls2() {

        if (autonState == 7) {
            // Do code
            timer.start();
            // do code
            shooter.shooterBoi(true);
            if (timer.hasPeriodPassed(2.5)) {
                timer.stop();
                timer.reset();
                autonState++;

            }

        }
    }
}