package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.lib.ControllerMap;
import frc.robot.lib.RobotMap;

public class Operator {

    @SuppressWarnings("all")
    private XboxController operator;
    private Hopper hopper;
    private Shooter shooter;
    private Elevator elevator;

    @SuppressWarnings("all")
    public void returnCommands() {
        operator = new XboxController(RobotMap.operatorPort);

        //subsystems
        hopper = new Hopper();
        shooter = new Shooter();
        elevator = new Elevator();

        //Operator Commands
        if(operator.getRawButton(ControllerMap.HopperButton)) {
            hopper.hopperSpin(true);
        } else {
            hopper.hopperSpin(false);
        }
        if(operator.getRawButton(ControllerMap.ShooterButton)) {
            shooter.shooterBoi(true);
        } else {
            shooter.shooterBoi(false);
        }
        if(operator.getRawButton(ControllerMap.PistonReleaseButton)) {
            elevator.pistonRelease(true);
        }
        if(operator.getRawButton(ControllerMap.ElevatorButton)) {
            elevator.move(true);
        } else {
            elevator.move(false);
        }
    }
}