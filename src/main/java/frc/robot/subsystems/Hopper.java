package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.lib.RobotMap;

public class Hopper {

    private TalonSRX m_hopper; 

    public void hopper(boolean pressed) {

        m_hopper = new TalonSRX(RobotMap.m_hopperID); 

        if(pressed) {
            m_hopper.set(ControlMode.PercentOutput, RobotMap.hopperSpeed);
        } else{
            m_hopper.set(ControlMode.PercentOutput, 0);

        }

    }
}

