package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.config.ControllerMap;
import frc.robot.config.ElectricalConstants;

public class Operator {

    @SuppressWarnings("all")

    //Xbox controller
    private XboxController operator = new XboxController(ElectricalConstants.operatorPort);

    public boolean getHopperPressed() {
        return operator.getRawButton(ControllerMap.HopperButton);
    }

    public boolean getHopperPressedReversed(){
        return operator.getRawButton(ControllerMap.HopperSpinReverse);
    }
    public boolean getShooterButton() {
        return operator.getRawButton(ControllerMap.ShooterButton);
    }

    public boolean getElevatorButton() {
        return operator.getRawButton(ControllerMap.ElevatorButton);
    }
    
    public boolean getLimelightTrenchAlignButton() {
        return operator.getRawButton(ControllerMap.LimelightTrenchAlignButton);

    }
    public boolean getLimelightLineAlignButton() {
        return operator.getRawButton(ControllerMap.LimelightLineAlignButton);
    }

}
