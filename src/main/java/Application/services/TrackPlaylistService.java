package Application.services;

import Application.models.Playlist;
import Application.models.Track;
import Application.repositories.PlaylistRepository;
import Application.repositories.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TrackPlaylistService {
    private final TrackRepository trackRepository;
    private final PlaylistRepository playlistRepository;

    @Autowired
    public TrackPlaylistService(TrackRepository trackRepository, PlaylistRepository playlistRepository){
        this.trackRepository = trackRepository;
        this.playlistRepository = playlistRepository;
    }

    public void saveTrack(Track track){
        trackRepository.save(track);
    }

    public Track getTrack(Integer id){
        return trackRepository.getById(id);
    }

    public Playlist getPlaylist(Integer id)
    {
        return playlistRepository.getById(id);
    }

    public void savePlaylist(Playlist playlist)
    {
        playlistRepository.save(playlist);
    }
}