package com.pokerfaces.controller.game;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pokerfaces.entity.game.Game;
import com.pokerfaces.entity.game.Player;
import com.pokerfaces.enums.game.GameStatus;
import com.pokerfaces.repository.game.GameRepository;
import com.pokerfaces.repository.game.PlayerRepository;

@RestController
@RequestMapping("/game")
public class GameController {
	
	@Autowired
	private GameRepository gameRepository;
	@Autowired
	private PlayerRepository playerRepository;
	
	@PostMapping("/{player}")
	public ResponseEntity<String> initGame (@PathVariable("player") Long playerId, @RequestParam("players") Integer maxPlayers) {
		Game game = new Game();                            
		
		game.setGameKey(Game.generateGameKey());
		game.setPlayerId(playerId);
		game.setMaxPlayers(maxPlayers);
		game.setStatus(GameStatus.OPEN);
		
		game = gameRepository.save(game);
		
		return ResponseEntity.ok(game.getGameKey());
	}
	
	@PutMapping("/{key}/{player}")
	public ResponseEntity<Void> joinGame (@PathVariable("key") String key, @PathVariable("player") Long playerId) {
		ResponseEntity<Void> entity = ResponseEntity.ok().build();
		
		Object[] entry = (Object[]) gameRepository.acceptingPlayers(key);
		Integer currentPlayers = Integer.valueOf(String.valueOf(entry[0]));
		Integer maxPlayers = Integer.valueOf(String.valueOf(entry[1]));
				
		if (currentPlayers < maxPlayers) {
			Game game = new Game();
			game.setGameKey(Game.generateGameKey());
			game.setPlayerId(playerId);
			game.setMaxPlayers(maxPlayers);
			game.setStatus(GameStatus.OPEN);
			
			game = gameRepository.save(game); 
			
			entity = ResponseEntity.accepted().build(); 
		} else {
			entity = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
		
		return entity;
	}

	@PutMapping("/rank/{gameKey}")
	public ResponseEntity<Void> rank (@RequestBody Map<Integer, Player> players, @PathVariable("gameKey") String gameKey) {
		for (Entry<Integer, Player> entry : players.entrySet()) {
			if (entry.getKey().equals(1L)) {
				entry.getValue().setVictories(entry.getValue().getVictories()+1);
			} else {
				entry.getValue().setDefeats(entry.getValue().getDefeats()+1);
			}
			
			playerRepository.save(entry.getValue());
		}
		
		List<Game> games = gameRepository.findAll(Example.of(Game.withKey(gameKey)));
		games.forEach(g -> g.setStatus(GameStatus.CLOSED));
		gameRepository.save(games);
		
		return ResponseEntity.ok().build();
	}
	
}