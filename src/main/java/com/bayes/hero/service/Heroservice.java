package com.bayes.hero.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bayes.hero.dto.AbilityDto;
import com.bayes.hero.dto.HeroDto;
import com.bayes.hero.entity.Ability;
import com.bayes.hero.entity.Hero;

@Service
public interface Heroservice {

	Page<Hero> getAllHero(Pageable pageRequest);

	HeroDto getHero(Long id);

	Page<Ability> getAllAbility(Pageable pageRequest);

	AbilityDto getAbility(Long id);

	List<Ability> getHeroAbility(Long id);

}
