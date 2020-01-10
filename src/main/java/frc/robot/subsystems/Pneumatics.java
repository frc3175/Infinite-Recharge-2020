package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.lib.RobotMap;

public class Pneumatics {

    private DoubleSolenoid e_piston, t_piston;

    
    @SuppressWarnings("all")
    public void elevatorActuater(boolean pressed) {
        e_piston = new DoubleSolenoid(RobotMap.e_pistonSolenoid_F, RobotMap.e_pistonSolenoid_R);

        if (pressed) {
            if (RobotMap.elevatorDirection == 0) {
                e_piston.set(Value.kReverse);
            } else {
                e_piston.set(Value.kForward);
            }
        }
    }

    @SuppressWarnings("all")
    public void trenchActuaterForward(boolean pressed) {
        t_piston = new DoubleSolenoid(RobotMap.t_pistonSolenoid_F, RobotMap.t_pistonSolenoid_R);
        if (pressed) {
            if (RobotMap.trenchDirection == 0) {
                t_piston.set(Value.kForward);
            } else {
                t_piston.set(Value.kReverse);
            }
        }

    }
    
    @SuppressWarnings("unused")
    public void trenchActuaterReverse(boolean pressed) {
        t_piston = new DoubleSolenoid(RobotMap.t_pistonSolenoid_F, RobotMap.t_pistonSolenoid_R);
        if (pressed) {
            if (RobotMap.trenchDirection == 1) {
                t_piston.set(Value.kReverse);
            } else {
                t_piston.set(Value.kForward);
            }
        }

    }

    public void resetPneumatics(boolean pressed) {
        t_piston = new DoubleSolenoid(RobotMap.t_pistonSolenoid_F, RobotMap.t_pistonSolenoid_R);
        e_piston = new DoubleSolenoid(RobotMap.e_pistonSolenoid_F, RobotMap.e_pistonSolenoid_R);
        if (pressed) {
            e_piston.set(Value.kReverse);
            t_piston.set(Value.kForward);
        }
    }

}