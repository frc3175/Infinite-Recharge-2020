package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.config.RobotConfig;

public class TestingSubsystem {

    private WPI_TalonSRX testingMotor1, testingMotor2, testingMotor3;

//Method for 1 motor
    public void singleMotor(boolean pressed) {

        if (pressed) {
            testingMotor1.set(RobotConfig.testingSpeed);
        } else {
            testingMotor1.set(0);
        }
    }

//Method for 2 motors
    public void twoMotors(boolean pressed) {

        if (pressed) {
            testingMotor2.set(RobotConfig.testingSpeed);
            testingMotor3.set(RobotConfig.testingSpeed);
        } else {
            testingMotor2.set(0);
            testingMotor3.set(0);
        }
    }
}


