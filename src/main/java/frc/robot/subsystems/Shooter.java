package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.ElectricalConstants;
import frc.robot.config.RobotConfig;

public class Shooter {

    private TalonSRX m_shooterTop, m_shooterBottom;

    public Shooter() {
        this.m_shooterTop = new TalonSRX(ElectricalConstants.topMotorID);
        this.m_shooterBottom = new TalonSRX(ElectricalConstants.bottomMotorID);
    }
    //javier
    public void shooter(boolean pressed) {


        if (pressed) {
            m_shooterTop.set(ControlMode.PercentOutput, RobotConfig.shooterSpeed_TopMotor);
            m_shooterBottom.set(ControlMode.PercentOutput, -RobotConfig.shooterSpeed_BottomMotor);
        } else {

            m_shooterTop.set(ControlMode.PercentOutput, 0);
            m_shooterBottom.set(ControlMode.PercentOutput, 0);
        }

    }

}
