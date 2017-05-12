package benevent.elsys.org.benevent;

public class Event {

    private Integer id;
    private String name;
    private String description;

    private String lat;
    private String lng;
    private String start;
    private String end;
    private Integer user_id;
    private String updated_at;
    private String created_at;

    public Event(String name, String start, String end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "Event{" +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", user_id=" + user_id +
                ", updated_at='" + updated_at + '\'' +
                ", created_at='" + created_at + '\'' +
                '}';
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
