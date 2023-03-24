package me.lab5.Utility;

public class RunMode {
    RunModeEnum runMode;
    public RunMode(){
        runMode= RunModeEnum.CONSOLE;
    }

    public RunModeEnum getRunMode() {
        return runMode;
    }

    public void setRunMode(RunModeEnum runMode) {
        this.runMode = runMode;
    }
}
