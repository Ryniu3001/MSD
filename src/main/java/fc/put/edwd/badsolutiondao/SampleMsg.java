package fc.put.edwd.badsolutiondao;

/**
 * Created by marcin on 26.10.16.
 */
public class SampleMsg {
    private Integer id;
    private String userId;
    private Integer songId;
    private Long date;

    public SampleMsg(Integer id, String userId, Integer songId, Long date) {
        this.id = id;
        this.userId = userId;
        this.songId = songId;
        this.date = date;
    }

    public SampleMsg(String userId, Integer songId, Long date) {
        this.userId = userId;
        this.songId = songId;
        this.date = date;
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

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
