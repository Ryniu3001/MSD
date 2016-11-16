package fc.put.edwd.beforetransdao;

import fc.put.edwd.TimeIt;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

import static fc.put.edwd.App.*;

/**
 * Created by marcin on 26.10.16.
 */
public class BadSolutionApp {
    public static void main(String[] args){
        TimeIt.code(() -> saveTracks());
        System.out.println("Tracks saved");
        TimeIt.code(() -> saveSamples());
        System.out.println("Listens saved");
    }


    private static void saveTracks() {

        try (Stream<String> stream = Files.lines(Paths.get(tracksFile), StandardCharsets.ISO_8859_1)) {
            Set<TrackMsg> songs = new HashSet<>();
            stream.forEach(s -> {
                List<String> tokens = tokenize(s);
                String songId = tokens.get(1);
                String artist = tokens.get(2);
                String title = tokens.get(3);
                songs.add(new TrackMsg(songId, artist, title));
            });
            TrackDAO.getInstance().insert(songs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveSamples() {
        Map<String, TrackMsg> tracksMap = TrackDAO.getInstance().get();
        try (Stream<String> stream = Files.lines(Paths.get(sampleFile), StandardCharsets.ISO_8859_1)) {
            List<SampleMsg> listens = new ArrayList<>();
            stream.forEach(s -> {
                List<String> tokens = tokenize(s);
                String user = tokens.get(0);
                String song = tokens.get(1);
                Long date = new Long(tokens.get(2));
                listens.add(new SampleMsg(user, tracksMap.get(song).getId(), date));

                if (listens.size() == 1000000) {
                    SampleDAO.getInstance().insert(listens);
                    listens.clear();
                }
            });
            SampleDAO.getInstance().insert(listens);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
