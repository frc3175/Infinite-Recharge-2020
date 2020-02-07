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
                Drive.autonTank(Drive.m_rightDriveMaster, Drive.m_leftDriveMaster, 0.3, 0);
            } else {
                Drive.autonTank(Drive.m_rightDriveMaster, Drive.m_leftDriveMaster, 0.3, 0);
                Drive.m_leftDriveMaster.setSelectedSensorPosition(0);
                Drive.m_rightDriveMaster.setSelectedSensorPosition(0);
                autonState++;
            }
        }
    }

    public void goStraight() {

        if (autonState == 2) {
            // Do code
            if (Drive.m_leftDriveMaster.getSelectedSensorPosition() < 36000
                    && Drive.m_rightDriveMaster.getSelectedSensorPosition() < 36000) {
                Drive.autonTank(Drive.m_rightDriveMaster, Drive.m_leftDriveMaster, 1, 1);
            } else {
                Drive.autonTank(Drive.m_rightDriveMaster, Drive.m_leftDriveMaster, 0.0, 0);
                autonState++;
            }
        }
    }

    public void turn45Opposite() {

        if (autonState == 3) {
            // Do code
            if (s_NavX.getAngle() > 0) {
                Drive.autonTank(Drive.m_rightDriveMaster, Drive.m_leftDriveMaster, 0, 0.3);
            } else {
                Drive.autonTank(Drive.m_rightDriveMaster, Drive.m_leftDriveMaster, 0, 0);
                autonState++;
            }
        }
    }

    public void goStraight2() {

        if (autonState == 4) {
            // Do code
            if (Drive.m_leftDriveMaster.getSelectedSensorPosition() < 36000
                    && Drive.m_rightDriveMaster.getSelectedSensorPosition() < 36000) {
                Drive.autonTank(Drive.m_rightDriveMaster, Drive.m_leftDriveMaster, 1, 1);
            } else {
                Drive.autonTank(Drive.m_rightDriveMaster, Drive.m_leftDriveMaster, 0, 0);
                Drive.m_leftDriveMaster.setSelectedSensorPosition(0);
                Drive.m_rightDriveMaster.setSelectedSensorPosition(0);
                autonState++;
            }

        }

    }

    public void reverseBack() {

        if (autonState == 5) {
            // Do code
            if (Drive.m_rightDriveMaster.getSelectedSensorPosition() > -36000
                    && Drive.m_leftDriveMaster.getSelectedSensorPosition() > -36000) {
                Drive.autonTank(Drive.m_rightDriveMaster, Drive.m_leftDriveMaster, -1, -1);
            } else {
                Drive.autonTank(Drive.m_rightDriveMaster, Drive.m_leftDriveMaster, 0, 0);
                autonState++;
            }
        }

    }

    public void turn180() {

        if (autonState == 6) {
            // Do code
            if (s_NavX.getAngle() < 180) {
                Drive.autonTank(Drive.m_rightDriveMaster, Drive.m_leftDriveMaster, 0.8, 0);
            } else {
                Drive.autonTank(Drive.m_rightDriveMaster, Drive.m_leftDriveMaster, 0, 0);
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
    public void execute(){
        shootBalls();
        turn45();
        goStraight();
        turn45Opposite();
        goStraight2();
        reverseBack();
        turn180();
        shootBalls2();
    }
}