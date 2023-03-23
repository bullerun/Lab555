package me.lab5.Run;

import me.lab5.Utility.Console;
import me.lab5.Utility.FileHanding;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class RunMode {

    public RunMode(Console console, FileHanding fileHanding){
        this.console = console;
        this.fileHanding = fileHanding;
    }



    private RunModeEnum workingMode = RunModeEnum.NONE_MODE;
    private Console console;
    private FileHanding fileHanding;

    //Method for setting the operating mode based on the working mode
    public void operatingModeSetting() throws IOException {
        try {
            if (workingMode.equals(RunModeEnum.CONSOLE_MODE)) {
                console.consoleReader();
            } else if (workingMode.equals(RunModeEnum.FILE_MODE)) {
                fileHanding.operatingTypeSetting();
                setMode(RunModeEnum.CONSOLE_MODE);
            }
        }
        catch (ParserConfigurationException e){
            System.out.println("проблемы с xml файлом");
        }catch (SAXException e){
            System.out.println("Ошибка с xml " + e.getException());
        }
    }

    //Method for set mode
    public void setMode(RunModeEnum mode){
        workingMode = mode;
    }

    //Method for return workingMode
    public RunModeEnum getMode(){
        return workingMode;
    }

}
