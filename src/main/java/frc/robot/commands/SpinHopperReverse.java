package frc.robot.commands;

import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.Operator;

public class SpinHopperReverse {

    private Operator operator;
    private Hopper hopper;

    public SpinHopperReverse() {
        operator = new Operator();
        hopper = new Hopper();
    }

    public void execute() {
        if (operator.getHopperPressedReversed()) {
            hopper.hopperSpinReverse(true);
        } else {
            hopper.hopperSpinReverse(false);
        }
    }


}