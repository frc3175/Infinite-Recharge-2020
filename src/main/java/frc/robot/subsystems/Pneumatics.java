package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.ElectricalConstants;
import frc.robot.config.RobotConfig;

public class Pneumatics {

    private DoubleSolenoid intakePiston;
    private Compressor compressor;


        public void pneumaticsInitialization() {
            compressor = new Compressor(0);
            
            //Pistons
            this.intakePiston = new DoubleSolenoid(ElectricalConstants.i_pistonSolenoid_F, ElectricalConstants.i_pistonSolenoid_R);
        }

    public void actuateIntakePiston(boolean pressed) {
        if(pressed) {
            intakePiston.set(Value.kReverse);
        } else {
            intakePiston.set(Value.kForward);
        }
    }

    public void resetPneumatics(boolean pressed) {
        if (pressed) {
            intakePiston.set(Value.kReverse);
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