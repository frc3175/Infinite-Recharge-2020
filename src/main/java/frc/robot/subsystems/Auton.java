package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Timer;

// Step 0.5 Shoot 3 Cells
// Step 1: turn 45 degrees
// Step 2: go straight for X encoder rotations
// Step 3: Straighten out (-45) degrees
// Step 4: go straight for X encoder rotations While intake motor is on
// Step 5: reverse all steps and shoot 5 cells
public class Auton {

    private Shooter shooter;

    private AHRS s_NavX;
    private Timer timer;
    private int autonState = 0;

    public Auton() {

        Drive.m_leftDriveSlave.follow(Drive.m_leftDriveMaster);
        Drive.m_rightDriveSlave.follow(Drive.m_rightDriveMaster);

        // subsystems
        this.shooter = new Shooter();

        // runtime timer
        this.timer = new Timer();

    }

    // One wheel rotation = ~12,000 ticks

    public void turnRobot(int State, double val, double leftdrive, double rightdrive, boolean anglePositive) {
        if (autonState == State) {
            if (anglePositive) {
                // Do positive angle code
                if (s_NavX.getAngle() < val) {
                    Drive.autonTank(Drive.m_rightDriveMaster, Drive.m_leftDriveMaster, leftdrive, rightdrive);
                } else {
                    Drive.autonTank(Drive.m_rightDriveMaster, Drive.m_leftDriveMaster, 0, 0);
                    autonState++;
                }
            } else {
                // Do negative angle code
                if (s_NavX.getAngle() > val) {
                    Drive.autonTank(Drive.m_rightDriveMaster, Drive.m_leftDriveMaster, leftdrive, rightdrive);
                } else {
                    Drive.autonTank(Drive.m_rightDriveMaster, Drive.m_leftDriveMaster, 0, 0);
                    autonState++;
                }
            }
        }
    }

    public void shootBall(int State, double time) {
        if (autonState == State) {
            // Do code
            timer.start();
            // do code
            shooter.shooter(true);
            if (timer.hasPeriodPassed(time)) {
                timer.stop();
                timer.reset();
                autonState++;
            }
        }
    }

    public void drive(int State, int distance, double speed) {
        if (autonState == State) {
            // Do code
            if (Drive.m_leftDriveMaster.getSelectedSensorPosition() < distance
                    && Drive.m_rightDriveMaster.getSelectedSensorPosition() < distance) {
                Drive.autonTank(Drive.m_rightDriveMaster, Drive.m_leftDriveMaster, speed, speed);
            } else {
                Drive.autonTank(Drive.m_rightDriveMaster, Drive.m_leftDriveMaster, 0.0, 0);
                autonState++;
            }
        }
    }

    public void resetEncoders() {
        Drive.m_leftDriveMaster.setSelectedSensorPosition(0);
        Drive.m_rightDriveMaster.setSelectedSensorPosition(0);
    }

    public void execute() {
        shootBall(0, 2.5);
        // turn 45
        turnRobot(1, 45, 0.5, 0, true);
        drive(2, 36000, 1);
        // turn -45
        turnRobot(3, 0, 0, 0.5, false);
        drive(4, 36000, 1);
        resetEncoders();
        drive(5, -36000, -1);
        // do a 180 to aim
        turnRobot(6, 180, 0.8, 0, true);
        shootBall(7, 2.5);
    }
}