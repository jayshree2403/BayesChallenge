package com.bayes.hero.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.bayes.hero.entity.Hero;

@Repository
public interface HeroRepository extends PagingAndSortingRepository<Hero, Long>{

	Optional<Hero> findByHeroId(Long id);

}
