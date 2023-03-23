package me.lab5.Command;

import me.lab5.Run.*;
import me.lab5.Utility.FileHanding;

import java.io.IOException;

public class ExecuteScriptCommand extends AbstractCommand {
    private FileHanding fileHanding;

    public ExecuteScriptCommand(FileHanding fileHanding) {
        super("execute_script file_name", "считывает и исполняет скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        this.fileHanding = fileHanding;

    }

    @Override
    public boolean execute(String argument) {
        try {
            fileHanding.setScriptPath(argument.trim());
            fileHanding.scriptReader();
            return true;
        } catch (IOException e) {
            System.out.println("Проблемы с файлом");
        } catch (IllegalArgumentException e){
            System.out.println("Введен неправильный аргумент");
        }
        return false;
    }
}