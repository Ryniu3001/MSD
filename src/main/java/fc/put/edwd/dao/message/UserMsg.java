package fc.put.edwd.dao.message;

public class UserMsg {
    private static Integer idx = 0;

    private Integer id;
    private String fId;

    public UserMsg(String fId) {
        this.fId = fId;
        this.id = idx++;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId;
    }

    @Override
    public int hashCode() {
        return this.fId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        UserMsg msg = (UserMsg)obj;
        return this.fId.equals(msg.getfId());
    }
}
