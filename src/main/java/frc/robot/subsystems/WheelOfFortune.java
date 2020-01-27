package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import frc.robot.config.ElectricalConstants;

public class WheelOfFortune {

    private Servo servo;

    public WheelOfFortune() {
        servo = new Servo(ElectricalConstants.ServoID);
    }

    public void cameraRotation(boolean pressed) {
        if (pressed){
            servo.setPosition(90);
        }else{
            servo.setPosition(0);
        }
    }
}