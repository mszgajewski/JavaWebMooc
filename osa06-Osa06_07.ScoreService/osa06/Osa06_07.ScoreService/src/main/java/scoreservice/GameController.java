package scoreservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @GetMapping("/games")
    public List<Game> getGames(){
        return gameRepository.findAll();
    }

    @GetMapping("/games/{name}")
    public Game getGame(@PathVariable String name){
        return gameRepository.findByName(name);
    }
    @PostMapping("/games")
    public Game postGame(@RequestParam Game game) {
        return gameRepository.save(game);
    }

    @DeleteMapping("/games/{name}")
    public Game deleteGame(@PathVariable String name){
        Game game = gameRepository.findByName(name);
        gameRepository.delete(game);
        return game;
    }
}
