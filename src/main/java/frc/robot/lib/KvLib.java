package frc.robot.lib;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import frc.robot.config.RobotMap;


import edu.wpi.first.wpilibj.DoubleSolenoid;

public class KvLib {

    /* @param creates public deadband */
    public static double deadband(double val, double threshold) {
        if (Math.abs(val) < threshold) {
            return 0;
        }
        return val;
    }

    public double driveDeadband(double val) {
        if (Math.abs(val) < RobotMap.driveDeadband) {
            return 0;
        }
        return val;
    }

    /* Setting motor power at various speeds */
    public void setAxisMotor(WPI_TalonSRX motor, double axis, double deadband, double power_multiplier) {
        /* This will be used for elevators and linear actuators */
        if (axis > deadband) {
            motor.set((-axis * power_multiplier));
        } else if (axis < -deadband) {
            motor.set(-(axis * power_multiplier));
        } else {
            motor.set(0.00);
        }
    }

    public void setMotorProperties(WPI_TalonSRX motor, boolean inverted, boolean voltageCompensation,
            double maxVoltage) {
        motor.setInverted(inverted);
        motor.enableVoltageCompensation(voltageCompensation);
        motor.configVoltageCompSaturation(maxVoltage);

    }

    public void setDriveTrainCurrentLimiting(WPI_TalonSRX motor) {
        /*
         * Current Limits the drivetrain to 35 with a peak of 50 Also contains a ramp of
         * 0.4 Seconds
         */
        
        motor.enableCurrentLimit(true);
        motor.configContinuousCurrentLimit(35);
        motor.configPeakCurrentDuration(250);
        motor.configPeakCurrentLimit(50);
        motor.configOpenloopRamp(.4);
    }

    public void setDriveTrainCurrentLimiting(TalonFX motor) {
        /*
         * Current Limits the drivetrain to 35 with a peak of 50 Also contains a ramp of
         * 0.4 Seconds
         */
        motor.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, 35, 50, 250));
        motor.configOpenloopRamp(.4);
    }

    public void simplePiston(boolean pressed, DoubleSolenoid piston) {
       /*Simple While pressed piston using a double solenoid */
        if (pressed) {
            piston.set(DoubleSolenoid.Value.kReverse);
        } else {
            piston.set(DoubleSolenoid.Value.kForward);
        }
    }
}