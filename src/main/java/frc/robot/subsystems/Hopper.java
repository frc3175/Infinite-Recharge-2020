package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.ElectricalConstants;
import frc.robot.config.RobotConfig;

public class Hopper {
    
    private TalonSRX m_hopper = new TalonSRX(ElectricalConstants.m_hopperID); 



    /**@param Reverse Reverse boolean for Hopper
     * @param Forward Forward Boolean for Hopper
     */
    public void hopperSpin(boolean Reverse, boolean Forward) {
        if(Reverse) {
            m_hopper.set(ControlMode.PercentOutput, RobotConfig.hopperSpeedReverse);
        } else if(Forward) {
            m_hopper.set(ControlMode.PercentOutput, RobotConfig.hopperSpeed);
        } else{
            m_hopper.set(ControlMode.PercentOutput, 0);
        }
    }
    /**
     * 
     * @param value 0 = forward 1 = reverse
     */
    public void hopperDirection(int value) {
        if (value == 0) {
            m_hopper.set(ControlMode.PercentOutput, RobotConfig.hopperSpeed);
        } else if (value == 1) {
            m_hopper.set(ControlMode.PercentOutput, RobotConfig.hopperSpeedReverse);
        }
    }
}
