package com.bayes.hero.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.bayes.hero.dto.AbilityDto;
import com.bayes.hero.dto.HeroDto;
import com.bayes.hero.entity.Ability;
import com.bayes.hero.entity.Hero;
import com.bayes.hero.repository.AbilityRepository;
import com.bayes.hero.repository.HeroRepository;
import com.bayes.hero.service.Heroservice;
import com.fasterxml.jackson.databind.JsonNode;

@Component
public class HeroserviceImpl implements Heroservice{
	

	@Autowired
	public HeroRepository heroRepo;
	
	@Autowired
	public AbilityRepository abilityRepo;
	
	@EventListener(ApplicationReadyEvent.class)
	public void ReadAllData() {
		System.out.println("start loading data");
		RestTemplate res =  new RestTemplate();
		ResponseEntity<JsonNode> result = res.getForEntity("https://overwatch-api.net/api/v1/hero/", JsonNode.class);
		String next_uri = "";
		while(!next_uri.equals("null")) {
			
			JsonNode data = result.getBody().get("data");
			if(data.isArray()) {
				for(int i=0; i<data.size();i++) {
					Hero hero = Hero.builder().build();
					JsonNode herodata = data.get(i);
					hero.setName(herodata.get("name").asText());
					hero.setHeroId(herodata.get("id").asLong());
					hero.setRealName(herodata.get("real_name").asText());
					hero.setArmour(herodata.get("armour").asInt());
					hero.setHealth(herodata.get("health").asInt());
					hero.setShield(herodata.get("shield").asInt());
					heroRepo.save(hero);
				}
			}
			next_uri = result.getBody().get("next").asText();
			if(!next_uri.equals("null")) {
			result = res.getForEntity(next_uri, JsonNode.class);}
		}
		
		
		ResponseEntity<JsonNode> abilityresult = res.getForEntity("https://overwatch-api.net/api/v1/ability/", JsonNode.class);
		String next_abilityuri = "";
		while(!next_abilityuri.equals("null")) {
			
			JsonNode data = abilityresult.getBody().get("data");
			if(data.isArray()) {
				for(int i=0; i<data.size();i++) {
					Ability ability = Ability.builder().build();
					JsonNode abilitydata = data.get(i);
					ability.setIs_ultimate(abilitydata.get("is_ultimate").asBoolean());
					ability.setAbilityId(abilitydata.get("id").asLong());
					ability.setDescription(abilitydata.get("description").asText());
					ability.setName(abilitydata.get("name").asText());
					JsonNode herodata = abilitydata.get("hero");
					ability.setHero(Hero.builder().heroId(herodata.get("id").asLong()).build());
					abilityRepo.save(ability);
				}
			}
			next_abilityuri = abilityresult.getBody().get("next").asText();
			if(!next_abilityuri.equals("null")) {
				RestTemplate rest =  new RestTemplate();
				String newuri = next_abilityuri.replace("http","https");
				abilityresult = rest.getForEntity(newuri, JsonNode.class);}
		}
		System.out.println("data loaded in db");
	}


	@Override
	public Page<Hero> getAllHero(Pageable pageRequest) {
		return heroRepo.findAll(pageRequest);
	}

	@Override
	public HeroDto getHero(Long id) {
		// TODO Auto-generated method stub
		Optional<Hero> hero = heroRepo.findByHeroId(id);
		Hero h = hero.orElseThrow(() ->new EntityNotFoundException("hero with id not found"));
		return HeroDto.builder().armour(h.getArmour()).health(h.getHealth()).id(h.getHeroId()).name(h.getName()).realName(h.getRealName()).shield(h.getShield()).build();
	}

	@Override
	public Page<Ability> getAllAbility(Pageable pageRequest) {
		// TODO Auto-generated method stub
		return abilityRepo.findAll(pageRequest);
	}

	@Override
	public AbilityDto getAbility(Long id) {
		// TODO Auto-generated method stub
		Optional<Ability> ability =  abilityRepo.findByAbilityId(id);
		Ability a = ability.orElseThrow(() ->new EntityNotFoundException("ability with id not found"));
		return AbilityDto.builder().description(a.getDescription()).id(a.getAbilityId()).is_ultimate(a.getIs_ultimate()).name(a.getName()).build();
	}

	@Override
	public List<Ability> getHeroAbility(Long id) {
		return abilityRepo.findAllByHeroHeroId(id);
	}

}
