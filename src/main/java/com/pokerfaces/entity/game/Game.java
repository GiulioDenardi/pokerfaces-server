package com.pokerfaces.entity.game;

import java.math.BigInteger;
import java.security.SecureRandom;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractPersistable;

import com.pokerfaces.enums.game.GameStatus;

@Entity
public class Game extends AbstractPersistable<Long> {

	private static final long serialVersionUID = 4724251420727722730L;
	
	@NotEmpty
	private String gameKey;
	@NotNull
	private Long playerId;
	@NotNull
	private Integer maxPlayers;
	@Enumerated
	@NotNull
	private GameStatus status;

	public Game() {
		super();
	}

	public Game(String gameKey) {
		this.setGameKey(gameKey);
	}

	public static String generateGameKey() {
		return new BigInteger(255, new SecureRandom()).toString(32);
	}

	public String getGameKey() {
		return gameKey;
	}

	public void setGameKey(String gameKey) {
		this.gameKey = gameKey;
	}

	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}

	public Integer getMaxPlayers() {
		return maxPlayers;
	}

	public void setMaxPlayers(Integer maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	public static Game withKey(String key) {
		return new Game(key);
	}

	public GameStatus getStatus() {
		return status;
	}

	public void setStatus(GameStatus status) {
		this.status = status;
	}

}
