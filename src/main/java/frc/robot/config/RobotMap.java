package frc.robot.config;

public class RobotMap {

    // Left Motor IDs
    public static final int m_leftDriveBack = 10; // CAN
    public static final int m_leftDriveFront = 11; // CAN

    // right motor IDs
    public static final int m_rightDriveBack = 12; // CAN
    public static final int m_rightDriveFront = 13; // CAN

    // Shooter Talon Motor IDs

    // This is the shooter Talon Motor IDs
    public static final int topMotorID = 14; // CAN
    public static final int bottomMotorID = 15; // CAN

    //Hopper Talon IDs
    public static final int m_hopperID = 16; // CAN

    // Prototype TalonSRX IDs

    // Electronic transmission degrade
    public static final double transmission = 0.6;

    // Drive Controller port
    public static final int driverPort = 0;
    // Operator Controller Port
    public static final int operatorPort = 1;

    // drive deadband
    public static final double driveDeadband = 0.2;

    // Elevator Solenoid ports
    public static final int e_pistonSolenoid_F = 0;
    public static final int e_pistonSolenoid_R = 1;
    // Trench Solenoid ports
    public static final int t_pistonSolenoid_F = 2;
    public static final int t_pistonSolenoid_R = 3;

    // Pnuematic directions
    // Elevator piston 0 == forward 1 == reverse
    public static final int elevatorDirection = 0;

    // Trench piston 0 == forward 1 == reverse
    public static final int trenchDirection = 0;

    // Shooter speed
    public static final double shooterSpeed_TopMotor = 0.75;
    public static final double shooterSpeed_BottomMotor = 1;

    // Hopper
    public static final double hopperSpeed = 0.8; 

    // ElevatorIDs
    public static final int elevatorleftID = 17;
    public static final int elevatorRightID = 18; 

    //Elevator Speed
    public static final double elevatorSpeed = 0.6;
}