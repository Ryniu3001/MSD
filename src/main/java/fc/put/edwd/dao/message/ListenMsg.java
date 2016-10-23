package fc.put.edwd.dao.message;

/**
 * Created by marcin on 22.10.16.
 */
public class ListenMsg {
    private Integer id;
    private Integer songId;
    private Integer dateId;
    private Integer timeId;
    private Integer userId;
    private Integer artistId;

    public ListenMsg(Integer songId, Integer dateId, Integer timeId, Integer userId, Integer artistId) {
        this.songId = songId;
        this.dateId = dateId;
        this.timeId = timeId;
        this.userId = userId;
        this.artistId = artistId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
    }

    public Integer getDateId() {
        return dateId;
    }

    public void setDateId(Integer dateId) {
        this.dateId = dateId;
    }

    public Integer getTimeId() {
        return timeId;
    }

    public void setTimeId(Integer timeId) {
        this.timeId = timeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }
}
