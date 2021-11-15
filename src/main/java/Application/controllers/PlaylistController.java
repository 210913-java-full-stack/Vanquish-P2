package Application.controllers;

import Application.models.Playlist;
import Application.models.Track;
import Application.services.PlaylistService;
import Application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping(value = "/4TheMusic")
public class PlaylistController {
    private final PlaylistService playlistService;
    private final UserService userService;

    @Autowired
    public PlaylistController(PlaylistService playlistService, UserService userService){
        this.playlistService = playlistService;
        this.userService = userService;
    }

    @PostMapping(value = "/playlist", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Playlist savePlaylist_name(@RequestBody Playlist playlist){
        playlistService.savePlaylist(playlist);
        return playlistService.getPlaylist(playlistService.getMaxPlaylistId());
    }

    @GetMapping(value = "/playlist/{playlist_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Playlist getPlaylistById(@PathVariable("playlist_id") Integer id){
        Playlist respPlaylist = new Playlist();
        respPlaylist.setID(id);
        respPlaylist.setPlaylistName(playlistService.getPlaylistName(id));
        respPlaylist.setUser(userService.getByID(playlistService.getUserId(id)));
        System.out.println(respPlaylist.getUser().getUserInfo().getUsername());
        respPlaylist.setTrackList(playlistService.getTracksByPlaylist(id));
        System.out.println(respPlaylist.getTrackList().size());
        return respPlaylist;
    }

//    @GetMapping(value = "/playlist/user/{user_id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(value = HttpStatus.OK)
//    public List<Playlist> getPlaylistsByUser(@PathVariable ("user_id") Integer user_id){
//        List<Playlist> userLists = new LinkedList<>();
//        return playlistService.getPlaylistByUserId(user_id);
//    }

    @GetMapping(value = "/playlist/tracks/{playlist_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Track> getTracksFromPlaylist(@PathVariable ("playlist_id") Integer id){
        return playlistService.getTracksByPlaylist(id);
    }
}
