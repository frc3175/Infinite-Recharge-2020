package frc.robot.commands;

import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Operator;

public class LimelightTargetControl {

    private Operator operator;
    private Limelight limelight;

    public LimelightTargetControl() {
        operator = new Operator();
        this.limelight = new Limelight();
        
    }

    public void execute() {
        limelight.limelightTargetControl(operator.getLimelightTrenchAlignButton(), operator.getLimelightLineAlignButton());
    }
}