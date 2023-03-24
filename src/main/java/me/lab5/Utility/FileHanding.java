package me.lab5.Utility;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import me.lab5.Data.LabWork;
import me.lab5.Exception.ScriptRecursionException;
import me.lab5.Manager.CollectionManager;
import me.lab5.Manager.CommandManager;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import me.lab5.xml.LabWorkDeserializer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.*;

public class FileHanding {


    public void setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    private Console console;
    private CollectionManager collectionManager;
    private CommandManager commandManager;
    private LabAsk labAsk;
    private String envVariable;
    private RunMode runMode;
    private List<String> nameScripts = new ArrayList<>();
    private List<Scanner> nameScanners = new ArrayList<>();
    private XmlMapper mapper;

    public FileHanding(CollectionManager collectionManager, LabAsk labAsk, String envVariable, RunMode runMode, Console console) {
        this.collectionManager = collectionManager;
        this.labAsk = labAsk;
        this.envVariable = envVariable;
        this.runMode = runMode;
        this.console = console;

        this.mapper = new XmlMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(LabWork.class, new LabWorkDeserializer());
        this.mapper.registerModule(module);
        this.mapper.registerModule(new JavaTimeModule());
        this.mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public void scriptReader(String scriptPath) {
        try {
            if (nameScripts.contains(scriptPath)) throw new ScriptRecursionException();
            nameScripts.add(scriptPath);
            nameScanners.add(labAsk.getScanner());
            if (!nameScripts.isEmpty()) runMode.setRunMode(RunModeEnum.SCRIPT);
        } catch (ScriptRecursionException e) {
            System.out.println("Повторный вызов скрипта " + scriptPath);
            labAsk.setScanner(nameScanners.get(0));
            nameScanners.clear();
            nameScripts.clear();
            runMode.setRunMode(RunModeEnum.CONSOLE);
            console.consoleReader();
        }
        try {
            String[] command;
            FileInputStream fileInputStream = new FileInputStream(Paths.get(System.getProperty("user.dir"), scriptPath).toString());

            Scanner scriptScanner = new Scanner(new InputStreamReader(fileInputStream, StandardCharsets.UTF_8));
            if (!scriptScanner.hasNext()) throw new NoSuchElementException();
            labAsk.setScanner(scriptScanner);
            do {
                command = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                command[1] = command[1].trim();
                commandManager.commandSelection(command);
            } while (scriptScanner.hasNextLine());
        } catch (NoSuchElementException e) {
            System.out.println("Скрипт пуст или отработал некорректно " + scriptPath);
        } catch (IOException e) {
            System.out.println("Файл не найден");
        } catch (IllegalArgumentException e) {
            System.out.println("Введен неправильный аргумент");
        } finally {
            if (nameScanners.size() > 0) {
                labAsk.setScanner(nameScanners.get(nameScanners.size() - 1));
                nameScanners.remove(nameScanners.size() - 1);
            }
            if (nameScripts.size() > 0) {
                nameScripts.remove(nameScripts.size() - 1);
            }
            runMode.setRunMode(RunModeEnum.CONSOLE);
        }

    }

    public void xmlFileReader() {
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(envVariable));
            int b;
            StringBuilder out = new StringBuilder();
            while ((b = reader.read()) != -1) {
                out.append(Character.toChars(b));
            }
            if (out.isEmpty()) throw new NoSuchElementException();
            deserialize(String.valueOf(out));
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
            creatFile();
        } catch (NullPointerException e) {
            System.out.println("Переменная окружения не задана");
            creatFile();
        } catch (NoSuchElementException e) {
            System.out.println("файл пуст");
        } catch (IOException e) {
            System.out.println("Ошибка с файлом");
        }
    }

    private void creatFile() {
        try {
            this.envVariable =Paths.get(System.getProperty("user.dir"), "LabWork.xml").toString();
            new File(envVariable).createNewFile();
            System.out.println("Файл создан по адресу " + envVariable);
        } catch (IOException e) {
            System.out.println("файл с таким путем создан");
        }
    }

    public void xmlFileWrite(CollectionManager collectionManager) {
        serialize(collectionManager);
    }

    private void serialize(CollectionManager collectionManager) {
        try {
            FileWriter fileWriter = new FileWriter(envVariable);
            mapper.writeValue(fileWriter, collectionManager.getLabWork());
        } catch (SecurityException e) {
            System.out.println("Не хватает прав доступа");
        } catch (StreamWriteException e) {
            System.out.println("исключение записи потока");
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (IOException e) {
            System.out.println("Ошибка добавления в xml файл");
        }
    }

    public void deserialize(String s) throws JsonProcessingException {
        LabWork[] labWorks = this.mapper.readValue(s, LabWork[].class);
        for (LabWork labWork : labWorks) {
            if (labWork != null) {
                collectionManager.addToCollection(labWork);
            }
        }
    }
}
