package frc.robot.commands;

import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Operator;

public class MoveIntake {

    private Operator operator;
    private Intake intake;

    public MoveIntake() {
        operator = new Operator();
        
        this.intake = new Intake();
    }

    public void execute() {
        if (operator.getIntakeButton()) {
            intake.move(true);
        } else {
            intake.move(false);
        }
    }


}