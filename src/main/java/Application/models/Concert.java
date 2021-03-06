package Application.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "concerts")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, ignoreUnknown = true)
public class Concert implements Serializable {
    public Concert(Integer concert_id, String date, String name, Location location, List<Artist> concert_lineup) {
        this.concert_id = concert_id;
        this.date = date;
        this.name = name;
        this.location = location;
        this.concert_lineup = concert_lineup;
    }

    public Concert() {
        this.concert_lineup = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer concert_id;

    @Column(name = "concert_date")
    private String date;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToMany(mappedBy = "concerts")
    private List<Artist> concert_lineup;

    public Integer getConcert_id() {
        return concert_id;
    }

    public void setConcert_id(Integer concert_id) {
        this.concert_id = concert_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Artist> getConcert_lineup() {
        return concert_lineup;
    }

    public void setConcert_lineup(List<Artist> concert_lineup) {
        this.concert_lineup = concert_lineup;
    }

    @Override
    public String toString() {
        return "Concert{" +
                "concert_id=" + concert_id +
                ", date='" + date + '\'' +
                ", name='" + name + '\'' +
                ", location=" + location +
                ", concert_lineup=" + concert_lineup +
                '}';
    }
}