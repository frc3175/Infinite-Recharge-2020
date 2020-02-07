package frc.robot.commands;

import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.Operator;

public class SpinHopper {

    private Operator operator;
    private Hopper hopper;

    public SpinHopper() {
        operator = new Operator();
        hopper = new Hopper();
    }

    public void execute() {
        hopper.hopperSpin(operator.getHopperPressed(), operator.getHopperPressedReversed());
    }
}