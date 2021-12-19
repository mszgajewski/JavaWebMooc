package scoreservice;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScoreController {

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private GameRepository gameRepository;

    @GetMapping("/games/{name}/scores")
    public List<Score> getAllScores(@PathVariable String name){
        Game game = gameRepository.findByName(name);
        return scoreRepository.findByGame(game);
    }

    @GetMapping("/games/{name}/scores/{id}")
    public Score getScore(@PathVariable String name, @PathVariable Long id){
        Game game = gameRepository.findByName(name);
        return scoreRepository.findByGameAndId(game,id);
    }

    @PostMapping("/games/{name}/scores")
    public Score addScore(@PathVariable String name, @PathVariable Score score){
        Game game = gameRepository.findByName(name);
        score.setGame(game);
        return scoreRepository.save(score);
    }

    @DeleteMapping("/games/{name}/scores/{id}")
    public Score deleteScore(@PathVariable String name, @PathVariable Long id){
        Score score = getScore(name, id);
        scoreRepository.deleteById(id);
        return score;
    }
}
