package com.bayes.hero.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.util.ReflectionTestUtils;

import com.bayes.hero.dto.AbilityDto;
import com.bayes.hero.entity.Ability;
import com.bayes.hero.repository.AbilityRepository;
import com.bayes.hero.repository.HeroRepository;
import com.bayes.hero.service.Heroservice;
import com.bayes.hero.service.impl.HeroserviceImpl;

public class HeroServiceImplTest {

	@Spy
	Heroservice classtoTest;
	
	@Mock
	public HeroRepository heroRepo;
	
	@Mock
	public AbilityRepository abilityRepo;
	
	@BeforeEach
	public void initialize() {
		MockitoAnnotations.initMocks(this);
		classtoTest = new HeroserviceImpl();
		ReflectionTestUtils.setField(classtoTest, "heroRepo", heroRepo);
		ReflectionTestUtils.setField(classtoTest, "abilityRepo", abilityRepo);
	}
	
	@Test
	public void shouldGetAbility() {
		Optional<Ability> abi = Optional.of(Ability.builder().abilityId((long) 1).name("name").is_ultimate(true).description("desc").build());
		when(abilityRepo.findByAbilityId((long) 1)).thenReturn(abi);
		AbilityDto result = classtoTest.getAbility((long) 1);
		assertNotNull(result);
		assertEquals(result.getName(), "name");
	}
}
