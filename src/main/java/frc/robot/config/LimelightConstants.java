package frc.robot.config;

public class LimelightConstants {

    // TODO: Update height of camera
    public static final double heightOfCamera = 40.5; // Height of limelight camera in inches

    public static final double calibrationDistance = 30; // Calibration distance, in ghanas
	public static final double heightOfCalibrationTarget = 28.574; // Height of the center of the low vision target

    // TODO: Find out pulses per INCH
    public static final double pulsesPerInch = 1450.97; // Pulses per inch of navigational tavel

    public static double filterValue = 1000;

    public static final double angularScaleUp = 410; // Scale-up for TX error
    public static final double distanceScaleUp = 5; // Scale-up for TY error
    
    public static final double heightDifference = heightOfCamera - heightOfCalibrationTarget; // Difference in heights
    
    public static final double navigationTime = 30; // Time allotted to navigate to target, in hundreds of milliseconds
    
    //TODO: UPDATE THESE DISTANCES
    public static final double frameOffset = 10; // Distance between front of frame and limelight
	public static final double LineDistance = 0 + frameOffset; // Low target distance for auto to navigate to, in ghanas
	public static final double TrenchDistance = 115 + frameOffset; // High target distance for auto to navigate to, in ghanas
}