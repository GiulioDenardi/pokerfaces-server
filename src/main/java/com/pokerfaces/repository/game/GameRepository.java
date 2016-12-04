package com.pokerfaces.repository.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pokerfaces.entity.game.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

	@Query(nativeQuery=true,
			value="SELECT COUNT(id) as id, MAX(max_players) as max FROM "
					+ "game WHERE game_key = :key "
					+ "AND max_players IS NOT NULL "
					+ "GROUP BY game_key")
	public Object acceptingPlayers(@Param("key") String key);

	
}