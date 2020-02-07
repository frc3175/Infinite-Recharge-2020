package frc.robot.config;

public class RobotConfig {

    // Electronic transmission degrade
    public static final double transmission = 1;

    // drive deadband
    public static final double driveDeadband = 0.2;

    // Pnuematic directions
    // Elevator piston 0 == forward 1 == reverse
    public static final int elevatorDirection = 0;

    // Trench piston 0 == forward 1 == reverse
    public static final int trenchDirection = 0;

    // Shooter speed
    public static final double shooterSpeed_TopMotor = 0.4;
    public static final double shooterSpeed_BottomMotor = 1;

    // Hopper
    public static final double hopperSpeed = -1; 
    public static final double hopperSpeedReverse = 0.49;

    //Elevator Speed
    public static final double elevatorSpeed = 0.6;

    //intake Speeds
    public static final double intakeSpeed = 0.8;





}