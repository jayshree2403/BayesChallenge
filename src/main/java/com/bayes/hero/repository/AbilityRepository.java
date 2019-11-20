package com.bayes.hero.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.bayes.hero.entity.Ability;

@Repository
public interface AbilityRepository extends PagingAndSortingRepository<Ability, Long>{

	Optional<Ability> findByAbilityId(Long id);

	//List<Ability> findAllByHeroId(Long id);

	List<Ability> findAllByHeroHeroId(Long id);

}
