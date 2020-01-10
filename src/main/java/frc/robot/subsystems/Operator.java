package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.lib.RobotMap;

public class Operator {

    @SuppressWarnings("all")
    private XboxController operator;

    public void initateOperator() {
        operator = new XboxController(RobotMap.operatorPort);
    }
    
}