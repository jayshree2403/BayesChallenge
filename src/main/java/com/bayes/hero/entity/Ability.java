package com.bayes.hero.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ability implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true)
	private Long abilityId;
	private String name;
	@Column(length = 10000)
	private String description;
	private Boolean is_ultimate;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "heroId", nullable = false)
	@JsonIgnore
    private Hero hero;
}
