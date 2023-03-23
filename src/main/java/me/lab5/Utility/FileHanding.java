package me.lab5.Utility;
import me.lab5.Exception.ScriptRecursionException;
import me.lab5.Manager.CollectionManager;
import me.lab5.Manager.CommandManager;
import org.xml.sax.SAXException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.*;

public class FileHanding {


    public void setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public enum FileType {
        XML_FILE,
        SCRIPT
    }

    private FileType fileType;
    private String scriptPath;
    private CollectionManager collectionManager;
    private CommandManager commandManager;
    private LabAsk labAsk;
    private String envVariable;
    private List<String> nameScripts = new ArrayList<>();

    public FileHanding(CollectionManager collectionManager, LabAsk labAsk, String envVariable) {
        this.collectionManager = collectionManager;

        this.labAsk = labAsk;
        this.envVariable = envVariable;
    }

    public void scriptReader() throws IOException {
        try {
            if (nameScripts.contains(scriptPath)) throw new ScriptRecursionException();
            nameScripts.add(scriptPath);
            String[] command;
            FileInputStream fileInputStream = new FileInputStream(scriptPath);
            Scanner scriptScanner = new Scanner(new InputStreamReader(fileInputStream, "UTF-8"));
            if (!scriptScanner.hasNext()) throw new NoSuchElementException();
            Scanner tmpScanner = labAsk.getScanner();
            labAsk.setScanner(scriptScanner);
            do{
                command = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                command[1] = command[1].trim();
                commandManager.commandSelection(command);
            }
            while (scriptScanner.hasNextLine());
            labAsk.setScanner(tmpScanner);
            nameScripts.clear();
        } catch (UnsupportedEncodingException e) {
            System.out.println("Проблема со скриптом");
        } catch (NoSuchElementException e) {
            System.out.println("Скрипт пуст");
        } catch (ScriptRecursionException e) {
            System.out.println("Повторный вызов скрипта " + scriptPath);
        }
    }


    public void setScriptPath(String scriptPath) {
        this.scriptPath = scriptPath;
    }

    public void xmlFileReader() {
        try {
            ObjectMapper objectMapper = new XmlMapper();
            FileInputStream file = new FileInputStream("C:\\Users\\nikitosek\\IdeaProjects\\Lab555\\src\\LabWorks.xml");

            System.out.println("");

            InputStreamReader inputBytes = new InputStreamReader(file);
            char[] chars = new char[1024];
            inputBytes.read(chars, 0, chars.length);
            String b = new String(chars);
//            System.out.println(b);
            CollectionManager labWork = objectMapper.readValue(b, CollectionManager.class);
//            System.out.println("ffds");
//            System.out.println(labWork.getElementById(1));
//            System.out.println("fvygkbhlkj");
        }catch (IOException e){
            System.out.println("ошибка xml");
        }

    }

    public void operatingTypeSetting() throws IOException, ParserConfigurationException, SAXException {
        if (fileType.equals(FileType.XML_FILE)) {
            xmlFileReader();
        } else {
            scriptReader();
        }
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public FileType get() {
        return fileType;
    }

}
