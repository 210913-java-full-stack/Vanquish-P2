package VanquishP2.Controllers;


import VanquishP2.Application.Beans.ModelServices.LocationService;
import VanquishP2.Application.Beans.Models.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/4TheMusic")
public class LocationController {
    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping(value = "/location/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Location getLocation(@PathVariable ("id") Integer id){
        return locationService.getLocationById(id);
    }

    @PostMapping(value = "/location", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Location saveLocation(@RequestBody Location location){
        locationService.saveLocation(location);
        return locationService.getLocationById(location.getLocationID());
    }

    @PutMapping(value = "/location", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Location updateLocation(@RequestBody Location location){
        Location updatedLocation = locationService.getLocationById(location.getLocationID());
        updatedLocation.setCity(location.getCity());
        updatedLocation.setState(location.getState());
        return locationService.saveLocation(updatedLocation);
    }

    @DeleteMapping(value = "/location/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteLocation(@PathVariable ("id") Integer id){
        if(id>0){
            locationService.deleteLocation(locationService.getLocationById(id));
        }else{
            locationService.deleteAllLocations();
        }
    }
}