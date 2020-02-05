package frc.robot.commands;

import frc.robot.subsystems.Operator;
import frc.robot.subsystems.Shooter;

public class ShootBall {

    private Operator operator;
    private Shooter shooter;

    public ShootBall() {
        operator = new Operator();
        
        this.shooter = new Shooter();
    }

    public void execute() {
        if (operator.getShooterButton()) {
            shooter.shooterBoi(true);
        } else {
            shooter.shooterBoi(false);
        }
    }


}