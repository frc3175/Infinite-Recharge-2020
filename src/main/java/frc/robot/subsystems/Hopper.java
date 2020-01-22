package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.config.ElectricalConstants;
import frc.robot.config.RobotConfig;;

public class Hopper {

    private TalonSRX m_hopper; 

    public Hopper() {
        this.m_hopper = new TalonSRX(ElectricalConstants.m_hopperID); 
    }

    public void hopperSpin(boolean pressed) {

        if(pressed) {
            m_hopper.set(ControlMode.PercentOutput, RobotConfig.hopperSpeed);
        } else{
            m_hopper.set(ControlMode.PercentOutput, 0);

        }

    }
}

