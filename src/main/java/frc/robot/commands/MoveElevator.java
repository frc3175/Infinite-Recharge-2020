package frc.robot.commands;

import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Operator;

public class MoveElevator {

    private Operator operator;
    private Elevator elevator;

    public MoveElevator() {
        operator = new Operator();
        
        this.elevator = new Elevator();
    }

    public void execute() {
        if (operator.getElevatorButton()) {
            elevator.move(true);
        } else {
            elevator.move(false);
        }
    }


}