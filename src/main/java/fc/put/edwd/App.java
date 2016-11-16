package fc.put.edwd;

import fc.put.edwd.dao.*;
import fc.put.edwd.dao.message.DateMsg;
import fc.put.edwd.dao.message.ListenMsg;
import fc.put.edwd.dao.message.SongMsg;
import fc.put.edwd.dao.message.TimeMsg;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class App {

    public static final String tracksFile = "unique_tracks.txt";
    public static final String sampleFile = "triplets_sample_20p.txt";

    public static void main(String[] args) throws IOException {
        TimeIt.code(() -> saveSongsAndArtistToDb());
        System.out.println("Songs and artist inserted to DB");
        TimeIt.code(() -> fillTimeDb());
        TimeIt.code(() -> saveDateUser());
        TimeIt.code(() -> saveListens());
        TimeIt.code(() -> BaseDAO.getInstance().createIndexes());
    }

    public static void saveSongsAndArtistToDb() {

        try (Stream<String> stream = Files.lines(Paths.get(tracksFile), StandardCharsets.ISO_8859_1)) {
            Set<String> artistNames = new HashSet<String>();
            Set<SongMsg> songs = new HashSet<>();
            stream.forEach(s -> {
                List<String> tokens = tokenize(s);
                String songId = tokens.get(1);
                String artist = tokens.get(2);
                String title = tokens.get(3);

                artistNames.add(artist);
                songs.add(new SongMsg(songId, artist, title));
            });
            ArtistDAO.getInstance().insert(artistNames);
            Map<String, Integer> artistMap = ArtistDAO.getInstance().get();
            songs.parallelStream().forEach(songMsg -> songMsg.setArtistId(artistMap.get(songMsg.getArtistName())));
            SongDAO.getInstance().insert(songs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void fillTimeDb(){
        List<TimeMsg> list = new ArrayList<>();
        IntStream.range(0, 24).forEach(h -> {
            IntStream.range(0, 60).forEach(m -> {
                list.add(new TimeMsg(h, m));
            });
        });
        TimeDAO.getInstance().insert(list);
    }

    public static void saveDateUser(){
        try (Stream<String> stream = Files.lines(Paths.get(sampleFile), StandardCharsets.ISO_8859_1)) {
            Set<String> users = new HashSet<>();
            Set<DateMsg> dates = new HashSet<>();
            stream.forEach(s -> {
                List<String> tokens = tokenize(s);
                users.add(tokens.get(0));
                dates.add(new DateMsg(new Long(tokens.get(2))));
            });
           UserDAO.getInstance().insert(users);
           DateDAO.getInstance().insert(dates);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveListens(){
        Map<DateMsg, Integer> datesMap = DateDAO.getInstance().get();
        Map<TimeMsg, Integer> timesMap = TimeDAO.getInstance().get();
        Map<String, SongMsg> songsMap = SongDAO.getInstance().get();
        Map<String, Integer> usersMap = UserDAO.getInstance().get();
        try (Stream<String> stream = Files.lines(Paths.get(sampleFile), StandardCharsets.ISO_8859_1)) {
            List<ListenMsg> listens = new ArrayList<>();
            stream.forEach(s -> {
                List<String> tokens = tokenize(s);
                Integer userId = usersMap.get(tokens.get(0));
                SongMsg songMsg = songsMap.get(tokens.get(1));
                Integer artistId = songMsg.getArtistId();
                Integer dateId = datesMap.get(new DateMsg(new Long(tokens.get(2))));
                Integer timeId = timesMap.get(new TimeMsg(new Long(tokens.get(2))));
                listens.add(new ListenMsg(songMsg.getId(), dateId, timeId, userId, artistId));

                if (listens.size() == 1000000) {
                    ListenDAO.getInstance().insert(listens);
                    listens.clear();
                }
            });
            ListenDAO.getInstance().insert(listens);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> tokenize(String s){
        List<String> result = new ArrayList<>();
        int idx = 0;
        for (int i = s.indexOf("<SEP>"); i != -1; i = s.indexOf("<SEP>", i+1)) {
            result.add(s.substring(idx, i));
            idx = i + 5;
        }
        result.add(s.substring(idx, s.length()));
        return result;
    }

}
