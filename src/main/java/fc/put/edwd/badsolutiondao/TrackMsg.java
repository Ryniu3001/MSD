package fc.put.edwd.badsolutiondao;

/**
 * Created by marcin on 26.10.16.
 */
public class TrackMsg {
    private Integer id;
    private String songId;
    private String artist;
    private String title;

    public TrackMsg(Integer id, String songId, String artist, String title) {
        this.id = id;
        this.songId = songId;
        this.artist = artist;
        this.title = title;
    }

    public TrackMsg(String songId, String artist, String title) {
        this.songId = songId;
        this.artist = artist;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrackMsg trackMsg = (TrackMsg) o;

        return songId.equals(trackMsg.songId);

    }

    @Override
    public int hashCode() {
        return songId.hashCode();
    }
}
