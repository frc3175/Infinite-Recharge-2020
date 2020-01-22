package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.config.ElectricalConstants;
import frc.robot.config.RobotConfig;

public class Pneumatics {

    private DoubleSolenoid e_piston, t_piston;
    private Compressor compressor;


    public Pneumatics() {
        compressor = new Compressor(0);

        //Pistons
        this.e_piston = new DoubleSolenoid(ElectricalConstants.e_pistonSolenoid_F, ElectricalConstants.e_pistonSolenoid_R);
        this.t_piston = new DoubleSolenoid(ElectricalConstants.t_pistonSolenoid_F, ElectricalConstants.t_pistonSolenoid_R);
    }

    @SuppressWarnings("all")
    public void elevatorActuater(boolean pressed) {

        if (pressed) {
            if (RobotConfig.elevatorDirection == 0) {
                e_piston.set(Value.kReverse);
            } else {
                e_piston.set(Value.kForward);
            }
        }
    }

    @SuppressWarnings("all")
    public void trenchActuaterForward(boolean pressed) {
        if (pressed) {
            if (RobotConfig.trenchDirection == 0) {
                t_piston.set(Value.kForward);
            } else {
                t_piston.set(Value.kReverse);
            }
        }

    }

    @SuppressWarnings("unused")
    public void trenchActuaterReverse(boolean pressed) {
        if (pressed) {
            if (RobotConfig.trenchDirection == 1) {
                t_piston.set(Value.kReverse);
            } else {
                t_piston.set(Value.kForward);
            }
        }

    }

    public void resetPneumatics(boolean pressed) {
        if (pressed) {
            e_piston.set(Value.kReverse);
            t_piston.set(Value.kForward);
        }
    }

    public void initializeCompressor(boolean value) {

        if (value) {
            compressor.setClosedLoopControl(true);
        } else {
            compressor.setClosedLoopControl(false);
        }
    }

    public double getCompressorCurrent() {
        return compressor.getCompressorCurrent();
    }
}