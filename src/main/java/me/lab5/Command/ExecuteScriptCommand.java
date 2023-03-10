package me.lab5.Command;

import me.lab5.Run.*;
import me.lab5.Utility.FileHanding;

public class ExecuteScriptCommand extends AbstractCommand {
    private FileHanding fileHanding;
    private RunMode runMode;

    public ExecuteScriptCommand(FileHanding fileHanding, RunMode runMode) {
        super("execute_script file_name", "считывает и исполняет скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        this.fileHanding = fileHanding;
        this.runMode = runMode;
    }

    @Override
    public boolean execute(String argument) {
        fileHanding.setPath(argument.trim());
        fileHanding.setFileType(FileHanding.FileType.SCRIPT);
        runMode.setMode(RunModeEnum.FILE_MODE);
        runMode.operatingModeSetting();
        return true;
    }
}