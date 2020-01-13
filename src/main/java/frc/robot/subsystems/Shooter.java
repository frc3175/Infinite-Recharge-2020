package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.config.RobotMap;

public class Shooter {

    private TalonSRX m_shooterTop, m_shooterBottom;

    public void shooterBoi(boolean pressed) {

        m_shooterTop = new TalonSRX(RobotMap.topMotorID);
        m_shooterBottom = new TalonSRX(RobotMap.bottomMotorID);

        if (pressed) {
            m_shooterTop.set(ControlMode.PercentOutput, RobotMap.shooterSpeed_TopMotor);
            m_shooterBottom.set(ControlMode.PercentOutput, -RobotMap.shooterSpeed_BottomMotor);
        } else {

            m_shooterTop.set(ControlMode.PercentOutput, 0);
            m_shooterBottom.set(ControlMode.PercentOutput, 0);
        }

    }

}
