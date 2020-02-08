package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Drive;
import frc.robot.LimelightConstants;
import frc.robot.util.LimelightAssist;
import frc.robot.util.Mathematics;

public class Limelight {

    private LimelightAssist limelight;

    // Drivemode between Limelight auto drive and manual
    public int driveMode = 0;

    public void initializeLimelight(){

        // Limelight assist provided by team 694
        limelight = new LimelightAssist();
    }

    public void periodicNumbers(){
        SmartDashboard.putNumber("LimelightX", x); // Horizontal error
        SmartDashboard.putNumber("LimelightY", y); // Vertical error
        SmartDashboard.putNumber("LimelightArea", area); // Area of limelight target
    }

    // Establish link to limelight
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");
    NetworkTableEntry tv = table.getEntry("tv");

    // Read values periodically
    double x = tx.getDouble(0.0); // Horizontal error
    double y = ty.getDouble(0.0); // Vertical error
    double area = ta.getDouble(0.0); // % area of vision target
    @SuppressWarnings("all")
    public void limelightTargetControl(boolean pressedTrench, boolean pressedLine) {

        // Vision tracking with two buttons on the operator stick
        if (pressedTrench) {
            driveMode = 1;
            table.getEntry("camMode").setNumber(0); // Tracking mode
            table.getEntry("ledMode").setNumber(3); // LEDs on
            table.getEntry("pipeline").setNumber(1); // Pipeline 1

            if (limelight.hasAnyTarget()) {
                double currentDistance = Mathematics.countDistance(y, LimelightConstants.heightDifference); // Distance from target
                double distanceDifference = Mathematics.calcPulses(LimelightConstants.TrenchDistance) - Mathematics.calcPulses(currentDistance); // Difference in distance (error)
                double distanceAdjust = distanceDifference / LimelightConstants.navigationTime; // Calculates a distance adjustment based on error
                double steeringAdjust = LimelightConstants.angularScaleUp * x; // Creates a side-to-side adjustment based on error
                Drive.flyWithWires(Drive.m_rightDriveMaster, Drive.m_leftDriveMaster, steeringAdjust, distanceAdjust * LimelightConstants.distanceScaleUp); // Drive using adjustment values

            } else if (pressedLine) {
                driveMode = 1;
                table.getEntry("camMode").setNumber(0); // Tracking mode
                table.getEntry("ledMode").setNumber(3); // LEDs on
                table.getEntry("pipeline").setNumber(2); // Pipeline 2

                if (limelight.hasAnyTarget()) {
                    double currentDistance = Mathematics.countDistance(y, LimelightConstants.heightDifference); // Distance from target
                    double distanceDifference = Mathematics.calcPulses(LimelightConstants.LineDistance) - Mathematics.calcPulses(currentDistance); // Difference in distance (error)
                    double distanceAdjust = distanceDifference / LimelightConstants.navigationTime; // Calculates a distance adjustment based on error
                    double steeringAdjust = LimelightConstants.angularScaleUp * x; // Creates a side-to-side adjustment based on error
                    Drive.flyWithWires(Drive.m_rightDriveMaster, Drive.m_leftDriveMaster, steeringAdjust, distanceAdjust * LimelightConstants.distanceScaleUp); // Drive using adjustment values
                }

            } else {
                table.getEntry("camMode").setNumber(1); // Camera mode
                table.getEntry("ledMode").setNumber(1); // LEDs off
                table.getEntry("pipeline").setNumber(3); // Pipeline 3
                LimelightConstants.filterValue = 1000; // Reset filter value
                driveMode = 0;
            }
        }
    }
}