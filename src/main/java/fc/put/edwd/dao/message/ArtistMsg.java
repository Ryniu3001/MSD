package fc.put.edwd.dao.message;

/**
 * Created by marcin on 22.10.16.
 */
public class ArtistMsg {

    private Integer id;
    private String name;

    public ArtistMsg(String name) {
        this.name = name;
    }

    public ArtistMsg(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
