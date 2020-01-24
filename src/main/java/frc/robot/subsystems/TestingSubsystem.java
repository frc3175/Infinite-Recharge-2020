package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Servo;
import frc.robot.config.ElectricalConstants;
import frc.robot.config.RobotConfig;

public class TestingSubsystem {

    private WPI_TalonSRX testingMotor1, testingMotor2, testingMotor3;
    private Servo servo;

    public TestingSubsystem() {
        this.testingMotor1 = new WPI_TalonSRX(ElectricalConstants.TestingMotor1);
        this.testingMotor2 = new WPI_TalonSRX(ElectricalConstants.TestingMotor2);
        this.testingMotor3 = new WPI_TalonSRX(ElectricalConstants.TestingMotor3);
        this.servo = new Servo(ElectricalConstants.ServoID);
        
    }

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


