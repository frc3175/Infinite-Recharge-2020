package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.config.ControllerMap;
import frc.robot.config.ElectricalConstants;

public class Operator {

    @SuppressWarnings("all")
    private XboxController operator;
    private Hopper hopper;
    private Shooter shooter;
    private Elevator elevator;

    public Operator() {
        this.operator = new XboxController(ElectricalConstants.operatorPort);

        // subsystems
        this.hopper = new Hopper();
        this.shooter = new Shooter();
        this.elevator = new Elevator();
    }

    private boolean getHopperPressed() {
        return operator.getRawButton(ControllerMap.HopperButton);
    }

    public void hopperSpin() {
        if (getHopperPressed()) {
            hopper.hopperSpin(true);
        } else {
            hopper.hopperSpin(false);
        }
    }

    private boolean getShooterButton() {
        return operator.getRawButton(ControllerMap.ShooterButton);
    }

    public void shooter() {
        if (getShooterButton()) {
            shooter.shooterBoi(true);
        } else {
            shooter.shooterBoi(false);
        }
    }

    private boolean getElevatorButton() {
        return operator.getRawButton(ControllerMap.ElevatorButton);
    }

    public void elevator() {
        if (getElevatorButton()) {
            elevator.move(true);
        } else {
            elevator.move(false);
        }
    }
    private boolean getPistonButton() {
        return operator.getRawButton(ControllerMap.PistonReleaseButton);
    }
    public void pistonRelease() {
        if(getPistonButton()) {
            elevator.pistonRelease(true);
        }
    }
}
