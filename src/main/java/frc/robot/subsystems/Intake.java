package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.ElectricalConstants;
import frc.robot.config.RobotConfig;
// import frc.robot.subsystems.Pneumatics;

public class Intake {

    private WPI_TalonSRX intake;
    //private Pneumatics pneumatics;

    public Intake() {
        //pneumatics = new Pneumatics();
        this.intake = new WPI_TalonSRX(ElectricalConstants.intake_ID);
    }

    public void move(boolean move) {
        if (move) {
            intake.set(RobotConfig.intakeSpeed);

        } else {
            intake.set(0);
        }
    }
}