package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShuffleBoard {

    // TODO: add ShuffleBoard
    // get Alliance
    // get MatchTime
    // get MatchNumber

    public void returnShuffleBoard() {
        //Alliance Color
        if (DriverStation.getInstance().getAlliance() == DriverStation.Alliance.Blue) {
            SmartDashboard.putString("Alliance", "Blue Alliance");
        } else {
            SmartDashboard.putString("Alliance", "Red Alliance");
        }
        
        //Match time - Match Number - Autonomous Mode - FMS Status
        SmartDashboard.putNumber("Match Time", DriverStation.getInstance().getMatchTime());
        SmartDashboard.putNumber("Match Number", DriverStation.getInstance().getMatchNumber());
        SmartDashboard.putBoolean("Autonomous Mode", DriverStation.getInstance().isAutonomous());
        SmartDashboard.putBoolean("FMS Status", DriverStation.getInstance().isFMSAttached());

        //DriverStation
        if (DriverStation.getInstance().getLocation() == 1) {
            SmartDashboard.putNumber("DriverStation", 1);
        } else if (DriverStation.getInstance().getLocation() == 2) {
            SmartDashboard.putNumber("DriverStation", 2);
        } else {
            SmartDashboard.putNumber("DriverStation", 3);
        }
    }
}
