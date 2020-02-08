package frc.robot.util;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShuffleBoard {

    private double returnMatchTime() {
        return DriverStation.getInstance().getMatchTime();
    }

    private boolean returnMatchTimeStage1() {
        if(returnMatchTime() < 90) {
            return true;
        } else {
            return false;
        }
    }

    private boolean returnMatchTimeStage2() {
        if(returnMatchTime() < 60) {
            return true;
        } else {
            return false;
        }
    }

    private boolean returnMatchTimeStage3() {
        if(returnMatchTime() < 30) {
            return true;
        } else {
            return false;
        }
    }

    private boolean returnMatchTimeStage4() {
        if(returnMatchTime() < 17) {
            return true;
        } else {
            return false;
        }
    }

    public void shuffleBoardMatchTime() {
        if(returnMatchTimeStage1()) {
            SmartDashboard.putBoolean("90 Seconds", true);
        } else {
            SmartDashboard.putBoolean("90 Seconds", false);
        }
        if(returnMatchTimeStage2()) {
            SmartDashboard.putBoolean("60 Seconds", true);
        } else {
            SmartDashboard.putBoolean("60 Seconds", false);
        }
        if(returnMatchTimeStage3()) {
            SmartDashboard.putBoolean("30 Seconds", true);
        } else {
            SmartDashboard.putBoolean("30 Seconds", false);
        }
        if(returnMatchTimeStage4()) {
            SmartDashboard.putBoolean("15 Seconds", true);
        } else {
            SmartDashboard.putBoolean("15 Second", false);
        }
    }

}
