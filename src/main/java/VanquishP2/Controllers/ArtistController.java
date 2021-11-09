package VanquishP2.Controllers;

import VanquishP2.Application.Beans.ModelServices.ArtistService;
import VanquishP2.Application.Beans.Models.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/4TheMusic")
public class ArtistController {

    private final ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @PostMapping(path = "/artist", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Artist saveArtist(@RequestBody Artist artist) {
        artistService.save(artist);
        return artistService.getArtist(artist.getID());
    }

    @GetMapping(path = "/artist/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Artist getArtistById(@PathVariable("id") Integer id) {
        return artistService.getArtist(id);
    }
}