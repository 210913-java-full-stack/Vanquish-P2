package Application.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to declare the POJO, Artist
 * @date 10/29/2021
 * @author Kollier Martin
 */

@Table(name = "artists")
@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, ignoreUnknown = true)
public class Artist {
    public Artist() {
        tracks = new ArrayList<>();
        albums = new ArrayList<>();
        concerts = new ArrayList<>();
    }

    public Artist(String name) {
        this.name = name;
        tracks = new ArrayList<>();
        albums = new ArrayList<>();
        concerts = new ArrayList<>();
    }

    public Artist(String name, int ID, String picture) {
        this.name = name;
        this.ID = ID;
        this.image_url = picture;
        tracks = new ArrayList<>();
        albums = new ArrayList<>();
        concerts = new ArrayList<>();
    }

    @Id
    @Column(name = "ArtistID")
    private int ID;
    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }

    @Column(name = "Name")
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "picture")
    private String image_url;
    public String getImage_url() {
        return image_url;
    }
    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    @ManyToMany
    @JsonIgnore
    private List<Concert> concerts;
    public List<Concert> getConcerts() {
        return concerts;
    }
    public void setConcerts(List<Concert> concerts) {
        this.concerts = concerts;
    }

    @OneToMany(mappedBy = "artist")
    @JsonIgnore
    private List<Album> albums;
    public List<Album> getAlbums() {
        return albums;
    }
    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    @OneToMany
    @JsonIgnore
    private List<Track> tracks;
    public List<Track> getTracks() {
        return tracks;
    }
    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", image_url='" + image_url + '\'' +
                ", concerts=" + concerts +
                ", albums=" + albums +
                ", tracks=" + tracks +
                '}';
    }
}
