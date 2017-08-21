package com.mud.controller;

import com.mud.dao.HeroDao;
import com.mud.mapper.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Created by leeesven on 17/8/19.
 */

@RequestMapping("/api/hero")
@RestController
public class HeroController {

    @Autowired
    HeroDao heroDao;

    @GetMapping(value = "/all")
    public ArrayList<Hero> getAllHero(){
        return heroDao.getAllHero();
    }

    @GetMapping(value = "/{heroId}")
    public Hero getHeroById(@PathVariable String heroId){
        System.out.println("heroId = " + heroId);
        return heroDao.getHeroById(heroId);
    }
}
