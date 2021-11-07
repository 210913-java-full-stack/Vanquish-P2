package Application.services;
import Application.models.Artist;
import Application.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ArtistService {
    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public void save(Artist artist){
        artistRepository.save(artist);
    }

    public Artist getArtist(Integer id){
        return artistRepository.getById(id);
    }

    public Artist getArtistByName(String name){
        return artistRepository.findByName(name);
    }
}

//    public Artist getArtistByName(String name){
//        return artistRepository.findByName(name);
//    }

