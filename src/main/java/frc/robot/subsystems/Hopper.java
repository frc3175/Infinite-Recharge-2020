package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.config.ElectricalConstants;
import frc.robot.config.RobotConfig;;

public class Hopper {

    private TalonSRX m_hopper = new TalonSRX(ElectricalConstants.m_hopperID); 

    public void hopperSpin(boolean Reverse, boolean Forward) {

        if(Reverse) {
            m_hopper.set(ControlMode.PercentOutput, RobotConfig.hopperSpeedReverse);
        } else if(Forward) {
            m_hopper.set(ControlMode.PercentOutput, RobotConfig.hopperSpeed);
        } else{
            m_hopper.set(ControlMode.PercentOutput, 0);
        }
    }
}

