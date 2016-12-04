package com.pokerfaces.entity.game;

import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Player extends AbstractPersistable<Long> {

	private static final long serialVersionUID = -8952964518936983928L;
	
	private String name;
	private Long totalMoney;
	private Long victories;
	private Long defeats;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Long totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Long getVictories() {
		return victories;
	}

	public void setVictories(Long victories) {
		this.victories = victories;
	}

	public Long getDefeats() {
		return defeats;
	}

	public void setDefeats(Long defeats) {
		this.defeats = defeats;
	}

}
