package fc.put.edwd.dao.message;

public class SongMsg {

    private Integer id;
    private String songId;
    private Integer artistId;
    private String artistName;
    private String title;

    public SongMsg(String songId, String artistName, String title) {
        this.songId = songId;
        this.title = title;
        this.artistName = artistName;
    }

    public SongMsg(String songId, Integer artistId, String title) {
        this.songId = songId;
        this.artistId = artistId;
        this.title = title;
    }

    public SongMsg(Integer id, String songId, Integer artistId, String title) {
        this.id = id;
        this.songId = songId;
        this.artistId = artistId;
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

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SongMsg songMsg = (SongMsg) o;

        return songId.equals(songMsg.songId);
    }

    @Override
    public int hashCode() {
        return songId.hashCode();
    }
}
