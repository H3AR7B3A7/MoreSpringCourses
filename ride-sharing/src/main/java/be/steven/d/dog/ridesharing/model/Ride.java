package be.steven.d.dog.ridesharing.model;

public class Ride {
    private Integer id;
    private String name;
    private int duration;

    public Integer getId() {
        return id;
    }

    public int getDuration() {
        return duration;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Ride{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                '}';
    }
}