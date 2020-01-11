package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import frc.robot.config.RobotMap;

public class Elevator {

    private WPI_TalonSRX m_left, m_right;
    private Pneumatics pneumatics;
    private SpeedControllerGroup elevator = new SpeedControllerGroup(this.m_left, this.m_right);

    public void move(boolean move) {
        this.m_left = new WPI_TalonSRX(RobotMap.elevatorleftID);
        this.m_right = new WPI_TalonSRX(RobotMap.elevatorRightID);
        if (move) {
            elevator.set(RobotMap.elevatorSpeed);
        } else {
            elevator.set(0);
        }
    }
    public void pistonRelease(boolean piston) {
        pneumatics = new Pneumatics();
        if(piston) {
            pneumatics.elevatorActuater(true);
        }
    }
}