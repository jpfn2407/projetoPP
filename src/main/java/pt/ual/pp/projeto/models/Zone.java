package pt.ual.pp.projeto.models;

import java.util.HashMap;

public class Zone extends Line {
    private final String zoneId;
    private int numberOfLines;
    private final HashMap<String, Line> lineHashMap = new HashMap<>();

    public Zone(String zoneId, int numberOfLines) {
        this.zoneId = zoneId;
        this.numberOfLines = numberOfLines;
    }

    public void setNumberOfLines(int numberOfLines) {
        this.numberOfLines = numberOfLines;
    }


    //TODO -> Nao sei como criar de facto as linhas, mas faz sentido ter uma classe Line que implementa Runnable, estas linhas sao depois guardadas num HashMap
    //TODO -> que tem zoneId como Key e um objecto Line como value. É assim possivel aceder a cada Line individual constituinte da Zone
    public void createLines(int numberOfLines) {
        for (int i = 0; i < numberOfLines; i++) {
            lineHashMap.put(String.valueOf(i), new Line());
        }
    }
}
