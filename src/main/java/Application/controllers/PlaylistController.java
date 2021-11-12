package Application.controllers;

import Application.models.Playlist;
import Application.services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/4TheMusic")
public class PlaylistController {
    private final PlaylistService playlistService;

    @Autowired
    public PlaylistController(PlaylistService playlistService){
        this.playlistService = playlistService;
    }

    @PostMapping(value = "/name", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Playlist savePlaylist_name(@RequestBody Playlist playlist){
        playlistService.savePlaylist(playlist);
        return playlistService.getPlaylist(playlist.getPlaylist_id());
    }

    @GetMapping(value = "/name/{playlist_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Playlist getPlaylistById(@PathVariable("playlist_id") Integer id){
        return playlistService.getPlaylist(id);
    }
}
