package weatherservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Cacheable(cacheNames = "locations")
    public List<Location> getLocations() {
        return locationRepository.findAll();
    }
    @Cacheable(cacheNames = "locations")
    public Location getLocation(Long id){
        return locationRepository.getOne(id);
    }
}
