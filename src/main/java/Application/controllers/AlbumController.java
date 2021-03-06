package Application.controllers;

import Application.deezer.AlbumSearch;
import Application.models.Album;
import Application.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * AlbumController
 * Handles requests that involve the manipulating or retrieval of album data
 *
 * @date 11/3/2021
 * @author Andrew Peterson and Kollier Martin
 */

@RestController
@RequestMapping(value = "/4TheMusic")
public class AlbumController {
    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    /**
     * This functions returns all albums cached into DB
     * @return ResponseEntity with HttpStatus and/or content
     */
    @GetMapping("/album/all")
    public ResponseEntity<List<Album>> getAllAlbums() {
        List<Album> allAlbums = albumService.getAll();

        if (allAlbums == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else if (allAlbums.isEmpty())
            return new ResponseEntity<>(allAlbums, HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(allAlbums, HttpStatus.OK);
    }

    /**
     * Receives ID from request, then returns an Album object if they exist
     * @param id ID Integer to distinguish album
     * @return Album Object
     */
    @GetMapping("/album/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Album retrieve(@PathVariable int id) {
        return albumService.getByID(id);
    }

    /**
     * Receives name from request, then returns an Album object if they exist
     * @param name Name String to distinguish album
     * @return Album Object
     */
    @GetMapping("/album/{name}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Album retrieve(@PathVariable String name) {
        return albumService.getByTitle(name);
    }

    /**
     * Deletes an Album from the DB, if it exists
     * @param id Album ID
     * @return ResponseEntity depending on success or failure
     */
    @DeleteMapping("/album{/id}")
    public ResponseEntity<?> remove(@PathVariable int id) {
        Optional<Album> album = Optional.ofNullable(albumService.getByID(id));

        if (!album.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            albumService.delete(id);
            return new ResponseEntity<>(album.get(), HttpStatus.NO_CONTENT);
        }
    }

    /**
     * Deletes an Album from the DB, if it exists
     * @param title Album title
     * @return ResponseEntity depending on success or failure
     */
    @DeleteMapping("/album/{title}")
    public ResponseEntity<?> remove(@PathVariable String title) {
        Optional<Album> album = Optional.ofNullable(albumService.getByTitle(title));

        if (!album.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            albumService.delete(album);
            return new ResponseEntity<>(album.get(), HttpStatus.NO_CONTENT);
        }
    }

    /**
     * Search for albums by using a title
     * @param title Album title
     * @return Arrays of Album with the variable {title}
     */
    @GetMapping(value = "/album/search/{title}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Album[] searchForAlbumsByTitle(@PathVariable ("title") String title){
        List<Album> albumList = AlbumSearch.albumSearch(title,5);
        Album[] albums = new Album[5];
        for (int i=0;i<5;i++) {
            albums[i] = albumList.get(i);
        }
        return albums;
    }
}

