package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.config.RobotMap;

public class Elevator {

    private WPI_TalonSRX m_left, m_right;
    private Pneumatics pneumatics;

    public void move(boolean move) {
        this.m_left = new WPI_TalonSRX(RobotMap.elevatorleftID);
        this.m_right = new WPI_TalonSRX(RobotMap.elevatorRightID);
        if (move) {
            m_left.set(RobotMap.elevatorSpeed);
            m_right.set(RobotMap.elevatorSpeed);
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