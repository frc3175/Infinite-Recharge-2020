package frc.robot.commands;

import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.Operator;

public class SpinHopperForward {

    private Operator operator;
    private Hopper hopper;

    public SpinHopperForward() {
        operator = new Operator();
        hopper = new Hopper();
    }

    public void execute() {
        if (operator.getHopperPressed()) {
            hopper.hopperSpin(true);
        } else {
            hopper.hopperSpin(false);
        }
    }


}