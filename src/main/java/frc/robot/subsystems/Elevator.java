package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.config.ElectricalConstants;
import frc.robot.config.RobotConfig;
import frc.robot.util.Pneumatics;

public class Elevator {

    private WPI_TalonSRX m_left, m_right;
    private Pneumatics pneumatics;

    public Elevator() {
        this.m_left = new WPI_TalonSRX(ElectricalConstants.elevatorleftID);
        this.m_right = new WPI_TalonSRX(ElectricalConstants.elevatorRightID);
    }

    public void move(boolean move) {
        if (move) {
            m_left.set(RobotConfig.elevatorSpeed);
            m_right.set(RobotConfig.elevatorSpeed);
        } else {
            m_left.set(0);
            m_right.set(0);
        }
    }
    public void pistonRelease(boolean piston) {
        pneumatics = new Pneumatics();
        if(piston) {
            pneumatics.elevatorActuater(true);
        }
    }
}