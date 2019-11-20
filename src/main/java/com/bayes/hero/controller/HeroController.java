package com.bayes.hero.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bayes.hero.dto.AbilityDto;
import com.bayes.hero.dto.HeroDto;
import com.bayes.hero.entity.Ability;
import com.bayes.hero.entity.Hero;
import com.bayes.hero.service.Heroservice;

@RestController
public class HeroController {

	@Autowired
	public Heroservice heroService;

	@GetMapping(path = "/api/heros")
	public ResponseEntity<Page<Hero>> getAllHero(Pageable pageRequest) {
		Page<Hero> heros = heroService.getAllHero(pageRequest);

		return new ResponseEntity<Page<Hero>>(heros, HttpStatus.OK);
	}

	@GetMapping(path = "/api/heros/{hero_id}")
	public ResponseEntity<HeroDto> getHero(@PathVariable("hero_id") final Long Id) {
		HeroDto hero = heroService.getHero(Id);

		return new ResponseEntity<HeroDto>(hero, HttpStatus.OK);
	}

	@GetMapping(path = "/api/abilities")
	public ResponseEntity<Page<Ability>> getAllAbilities(Pageable pageRequest) {
		Page<Ability> abilities = heroService.getAllAbility(pageRequest);

		return new ResponseEntity<Page<Ability>>(abilities, HttpStatus.OK);
	}

	@GetMapping(path = "/api/abilities/{ability_id}")
	public ResponseEntity<AbilityDto> getAbility(@PathVariable("ability_id") final Long Id) {
		AbilityDto ability = heroService.getAbility(Id);
		return new ResponseEntity<AbilityDto>(ability, HttpStatus.OK);
	}

	@GetMapping(path = "/api/heros/{hero_id}/abilities")
	public ResponseEntity<List<Ability>> getHeroAbilities(@PathVariable("hero_id") final Long Id) {
		List<Ability> abilities = heroService.getHeroAbility(Id);
		return new ResponseEntity<List<Ability>>(abilities, HttpStatus.OK);
	}
}
