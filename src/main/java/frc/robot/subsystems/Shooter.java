package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.lib.RobotMap;

public class Shooter {



    private TalonSRX shooter0, shooter1;

    public void shooter(boolean pressed) {

        shooter0 = new TalonSRX(RobotMap.shooter0ID);
        shooter1 = new TalonSRX(RobotMap.shooter1ID);

        if(pressed) {
            shooter0.set(ControlMode.PercentOutput, RobotMap.shooterSpeed);
            shooter1.set(ControlMode.PercentOutput, -RobotMap.shooterSpeed);
        } else {
            shooter0.set(ControlMode.PercentOutput, 0);
            shooter1.set(ControlMode.PercentOutput, 0);
        }



    }
}