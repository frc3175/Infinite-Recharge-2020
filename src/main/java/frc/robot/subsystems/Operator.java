package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.config.ControllerMap;
import frc.robot.util.LimelightAssist;
import frc.robot.util.LimelightAssist.LEDMode;
import frc.robot.ElectricalConstants;

public class Operator {

    @SuppressWarnings("all")

    // Xbox controller
    private XboxController operator = new XboxController(ElectricalConstants.operatorPort);

    // Subsystems
    private Hopper subHopper = new Hopper();
    private Intake subIntake = new Intake();
    private Elevator subElevator = new Elevator();
    private Shooter subShooter = new Shooter();
    private Limelight subLimelight = new Limelight();
    private Pneumatics subPneumatics = new Pneumatics();
    private LimelightAssist subLimelightAssist = new LimelightAssist();
    private DigitalOutput shooterOutput = new DigitalOutput(0);
    private DigitalOutput limelightTarget = new DigitalOutput(1);

    /**
     * 
     * @return forward hopper button is pressed
     */
    @SuppressWarnings("unused")
    private boolean getHopperPressed() {
        return operator.getRawButton(ControllerMap.HopperButton);
    }

    /**
     * 
     * @return reverse hopper button is pressed
     */
    @SuppressWarnings("unused")
    private boolean getHopperPressedReversed() {
        return operator.getRawButton(ControllerMap.HopperSpinReverse);
    }

    /**
     * 
     * @return shooter button is pressed
     */
    private boolean getShooterButton() {
        return operator.getRawButton(ControllerMap.ShooterButton);
    }

    /**
     * 
     * @return elevator button is pressed
     */
    private boolean getElevatorButton() {
        return operator.getRawButton(ControllerMap.ElevatorButton);
    }

    /**
     * 
     * @return Trench Auto align is pressed
     */
    private boolean getLimelightTrenchAlignButton() {
        return operator.getRawButton(ControllerMap.LimelightTrenchAlignButton);

    }

    /**
     * 
     * @return line auto align is pressed
     */
    private boolean getLimelightLineAlignButton() {
        return operator.getRawButton(ControllerMap.LimelightLineAlignButton);
    }

    /**
     * 
     * @return intake button is pressed
     */
    public boolean getIntakeButton() {
        return operator.getRawButton(ControllerMap.intakeButton);
    }

    private boolean getLimelightLEDOn() {
        return operator.getRawButton(ControllerMap.LimelightLEDOn);
    }

    private boolean getLimelightLEDOff() {
        return operator.getRawButton(ControllerMap.LimightLEDOff);
    }

    private boolean getPistonIntakeButton() {
        return operator.getRawButton(ControllerMap.IntakePiston);
    }

    /* Use commands */

    // Spins Hopper in both directions
    public void doSpinHopper() {
        subHopper.hopperSpin(operator.getRawButton(ControllerMap.HopperSpinReverse),
                operator.getRawButton(ControllerMap.HopperButton));
    }

    // Shoot ball
    public void doShooter() {
        if (getShooterButton()) {
            subShooter.shooter(true);
        } else {
            subShooter.shooter(false);
        }
    }

    // Elevator move
    public void doElevator() {
        if (getElevatorButton()) {
            subElevator.move(true);
        } else {
            subElevator.move(false);
        }
    }

    // Spin Intake
    public void doIntake() {
        if (getIntakeButton()) {
            subIntake.move(true);
        } else {
            subIntake.move(false);
        }
    }

    // Limelight TargetControl
    public void doLimelightTargetControl() {
        subLimelight.limelightTargetControl(getLimelightTrenchAlignButton(), getLimelightLineAlignButton());
    }

    // change LED's to larson cyclon (Only sending digital signal to arduino)
    public void shooterOutput() {
        if (getShooterButton()) {
            shooterOutput.set(true);
        } else {
            shooterOutput.set(false);
        }
    }

    // limelight has target output digital signal
    @SuppressWarnings("all")
    public void detectTarget() {
        if (getShooterButton() == false) {
            if (subLimelightAssist.hasValidTarget()) {
                limelightTarget.set(true);
            } else {
                limelightTarget.set(false);
            }
        }
    }

    // Change limelight light
    @SuppressWarnings("all")
    public void changeLimelightLEDs(){
        //force off
        if(getLimelightLEDOn()) {
            subLimelightAssist.setLEDMode(LEDMode.FORCE_ON);
        }  else if(getLimelightLEDOff()) {
            subLimelightAssist.setLEDMode(LEDMode.FORCE_OFF);
        }
    }

    public void doIntakePiston() {
        if(getPistonIntakeButton()) {
            subPneumatics.actuateIntakePiston(true);
        } else {
            subPneumatics.actuateIntakePiston(false);
        }
    }

}
