package me.lab5.Utility;

import me.lab5.Data.Coordinates;
import me.lab5.Data.Difficulty;
import me.lab5.Data.Discipline;
import me.lab5.Data.LabWork;
import me.lab5.Exception.ScriptRecursionException;
import me.lab5.Manager.CollectionManager;
import me.lab5.Manager.CommandManager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.time.LocalDate;
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
    private Console console;
    private String path;
    private CollectionManager collectionManager;
    private CommandManager commandManager;
    private LabAsk labAsk;
    private String envVariable;
    private List<String> nameScripts = new ArrayList<String>();

    public FileHanding(CollectionManager collectionManager, Console console, LabAsk labAsk, String envVariable) {
        this.collectionManager = collectionManager;
        this.console = console;
        this.labAsk = labAsk;
        this.envVariable = envVariable;
    }

    public void scriptReader() throws IOException {
        try {
            if (nameScripts.contains(path)) throw new ScriptRecursionException();
            nameScripts.add(path);
            String[] command;
            FileInputStream fileInputStream = new FileInputStream(path);
            Scanner scriptScanner = new Scanner(new InputStreamReader(fileInputStream, "UTF-8"));
            if (!scriptScanner.hasNext()) throw new NoSuchElementException();
            Scanner tmpScanner = labAsk.getScanner();
            labAsk.setScanner(scriptScanner);
            while (scriptScanner.hasNextLine()) {
                command = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                command[1] = command[1].trim();
                commandManager.commandSelection(command);
            }
            labAsk.setScanner(tmpScanner);
        } catch (UnsupportedEncodingException e) {
            System.out.println("Проблема со скриптом");
        } catch (NoSuchElementException e) {
            System.out.println("Скрипт пуст");
        } catch (ScriptRecursionException e) {
            System.out.println("Повторный вызов скрипта " + path);
        }
    }


    public void setPath(String path) {
        this.path = path;
    }

    public void xmlFileReader() {

        Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
        String name; //Поле не может быть null, Строка не может быть пустой
        String str_coordinates;
        Coordinates coordinates; //Поле не может быть null
        String str_creationDate;
        long minimalPoint; //Значение поля должно быть больше 0
        String str_difficulty = "";
        Difficulty difficulty = null; //Поле может быть null
        String str_discipline;
        Discipline discipline; //Поле может быть null
        String[] args;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            //установить path 2

            File file = new File(envVariable);
            Document document = builder.parse(file);

            NodeList nodeList = document.getDocumentElement().getChildNodes();

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;

                    // Get the value of all sub-elements.

                    //id
                    id = Long.valueOf(elem.getElementsByTagName("id")
                            .item(0).getChildNodes().item(0).getNodeValue());

                    //name
                    name = elem.getElementsByTagName("name").item(0)
                            .getChildNodes().item(0).getNodeValue();

                    //coordinates
                    str_coordinates = elem.getElementsByTagName("coordinates").item(0).getChildNodes().item(0).getNodeValue();
                    args = (str_coordinates.trim()).split(", ", 2);
                    coordinates = new Coordinates(Float.parseFloat(args[0]), Long.parseLong(args[1]));

                    //creationDate
                    str_creationDate = elem.getElementsByTagName("creationDate")
                            .item(0).getChildNodes().item(0).getNodeValue();

                    //minimalPoint
                    minimalPoint = Long.parseLong(elem.getElementsByTagName("minimalPoint")
                            .item(0).getChildNodes().item(0).getNodeValue());

                    //difficulty
                    try {
                        str_difficulty = elem.getElementsByTagName("difficulty").item(0).getChildNodes().item(0).getNodeValue();
                    } catch (NullPointerException e) {
                        str_difficulty = null;
                    }

                    if (str_difficulty == null) {
                        difficulty = null;
                    } else if (str_difficulty.equals("EASY")) {
                        difficulty = Difficulty.EASY;
                    } else if (str_difficulty.equals("VERY_EASY")) {
                        difficulty = Difficulty.VERY_EASY;
                    } else if (str_difficulty.equals("VERY_HARD")) {
                        difficulty = Difficulty.VERY_HARD;
                    } else if (str_difficulty.equals("TERRIBLE")) {
                        difficulty = Difficulty.TERRIBLE;
                    } else {
                        // обработать ошибку
                        System.out.println("Неправильный ввод");
                    }

                    // discipline
                    try {
                        str_discipline = elem.getElementsByTagName("discipline")
                                .item(0).getChildNodes().item(0).getNodeValue();
                        args = (str_discipline.trim()).split(", ", 2);
                        discipline = new Discipline(args[0], Integer.parseInt(args[1]));
                    } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                        discipline = null;
                    }
                    //заполнение коллекции 1 лабораторной работой
                    collectionManager.addToCollection(new LabWork(id, name, coordinates, LocalDate.parse(str_creationDate), minimalPoint, difficulty, discipline));
                }
            }
        } catch (NullPointerException | IOException e) {
            System.out.println("Файл не обнаружен");
        } catch (ParserConfigurationException | SAXException e) {
            System.out.println("некорректный xml файл");
        }
    }

    public void writeToXML(NavigableSet<LabWork> labWorks) throws IOException {
        FileWriter fileWriter = new FileWriter(envVariable);
        fileWriter.append("<?xml version = \"1.0\"?>\n");
        fileWriter.append("<LabWorks>\n");
        for (LabWork lab : labWorks) {
            fileWriter.append("\t<LabWork>\n");
            fileWriter.append("\t\t<id>").append(String.valueOf(lab.getId())).append("</id>\n");
            fileWriter.append("\t\t<name>").append(lab.getName()).append("</name>\n");
            fileWriter.append("\t\t<coordinates>").append(lab.getCoordinates().toString()).append("</coordinates>\n");
            fileWriter.append("\t\t<creationDate>").append(lab.getCreationDate().toString()).append("</creationDate>\n");
            fileWriter.append("\t\t<minimalPoint>").append(String.valueOf(lab.getMinimalPoint())).append("</minimalPoint>\n");
            if (lab.getDifficulty() == null) {
                fileWriter.append("\t\t<difficulty>").append("</difficulty>\n");
            } else {
                fileWriter.append("\t\t<difficulty>").append(lab.getDifficulty().toString()).append("</difficulty>\n");
            }
            if (lab.getDiscipline() == null) {
                fileWriter.append("\t\t<discipline>").append("</discipline>\n");
            } else {
                fileWriter.append("\t\t<discipline>").append(lab.getDiscipline().toString()).append("</discipline>\n");
            }
            fileWriter.append("\t</LabWork>\n");
        }
        fileWriter.append("</LabWorks>\n");
        fileWriter.close();
    }


    public void operatingTypeSetting() throws IOException, ParserConfigurationException, SAXException {
        //возможно надо изменить на if
        if (fileType.equals(FileType.XML_FILE)) {
            xmlFileReader();
        } else {
            scriptReader();
        }
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    // return fileType
    public FileType get() {
        return fileType;
    }

}
