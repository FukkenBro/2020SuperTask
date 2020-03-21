package build.driver;

import build.data.Structure;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class SaveAndLoad {

    private static final int historySize = 512;

    public static LinkedList<String> history = new LinkedList<>();

    public static String serialize(Structure state) {
        JSONSerializer serializer = new JSONSerializer();
        return serializer.deepSerialize(state);
    }

    public static Structure deserialize(String saveFile) {
        Structure state = new JSONDeserializer<Structure>().deserialize(saveFile);
        return state;
    }

    public static void addAction(Structure state) {
        String serializedState = serialize(state);
        if (history.size() >= historySize) {
            history.removeFirst();
        }
        history.addLast(serializedState);
    }

    public static Structure getPrevState() {
        if (history.size() <= 1) {
            return deserialize(history.getLast());
        }
        int prevIndex = history.size() - 1;
        String prevState = history.get(prevIndex);
        history.removeLast();
        return deserialize(prevState);
    }

    public static void saveToFile(Structure state) throws IOException {
        JSONSerializer serializer = new JSONSerializer();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyy(HHmm)");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        BufferedWriter newSave = new BufferedWriter(new FileWriter("saves/save" + dtf.format(now) + ".txt"));
        try {
            newSave.write(serializer.deepSerialize(state));
        } catch (
                IOException e) {
            System.out.println("Exception in SaveToFile()");
        } finally {
            newSave.close();
        }
    }

    public static Structure loadFromFile() throws IOException {
        FileDialog dialog = new FileDialog((Frame) null, "Select Save File to Load");
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
        String file = dialog.getFile();
        System.out.println(file + " is loaded");
        String contents = new String(Files.readAllBytes(Paths.get("saves/" + file)));
        return deserialize(contents);
    }

}
