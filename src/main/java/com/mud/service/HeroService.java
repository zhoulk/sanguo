package com.mud.service;

import com.mud.dao.HeroDao;
import com.mud.dao.UserHeroDao;
import com.mud.mapper.Hero;
import com.mud.mapper.UserHero;
import com.mud.model.HeroModel;
import com.mud.model.define.SGMacro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

/**
 * Created by zlk on 2018/6/8.
 */

@Service
public class HeroService {

    private Logger logger = Logger.getLogger(HeroService.class.getName());

    @Autowired
    HeroDao heroDao;

    @Autowired
    UserHeroDao userHeroDao;

    @Autowired
    SequenceService sequenceService;

    /**
     * 随即一个武将
     * @param level
     * @return
     */
    public HeroModel randomAHero(int level){

        List<Hero> heros = CacheService.heros();
        if(heros.size() == 0){
            heros = heroDao.getAllHero();
            CacheService.cacheHeros(heros);
        }

        Random r = new Random(System.currentTimeMillis());

        List<Hero> oriHeros = new ArrayList<>();
        switch (level){
            case 1:
                // 随机一个三星
                oriHeros.addAll(CacheService.star3Heros());
                break;
            case 2:
                // 随机一个三星 四星
                oriHeros.addAll(CacheService.star3Heros());
                oriHeros.addAll(CacheService.star4Heros());
                break;
            case 3:
                // 随机一个四星 五星
                oriHeros.addAll(CacheService.star4Heros());
                oriHeros.addAll(CacheService.star5Heros());
                break;
            case 4:
                // 随机一个五星
                oriHeros.addAll(CacheService.star5Heros());
                break;
        }

        Hero destHero = null;
        if(oriHeros.size() > 0){
            int index = Math.round(r.nextFloat()*oriHeros.size() + 0.5f) - 1;
            destHero = oriHeros.get(index);
        }

        if(destHero != null){
            HeroModel heroModel = new HeroModel(destHero);
            return  heroModel;
        }
        return  null;
    }

    /**
     * 给用户增加一个武将
     * @param userId
     * @param heroId
     * @return
     */
    public boolean addUserHero(String userId, String heroId){
        Hero hero = CacheService.getHeroById(heroId);
        UserHero userHero = new UserHero();
        userHero.setUserId(userId);
        userHero.setHeroId(heroId);

        userHero.setIntelligence(hero.getIntelligence());
        userHero.setAtkDist(hero.getAtkDist());
        userHero.setAttack(hero.getAttack());
        userHero.setDefence(hero.getDefence());
        userHero.setMoveSpeed(hero.getMoveSpeed());
        userHero.setTowerAtk(hero.getTowerAtk());

        userHero.setUserHeroId(sequenceService.sequenceOfHero());
        userHero.setStatus(SGMacro.SG_HERO_USER_HERO_GET);
        userHeroDao.insertUserHero(userHero);

        return true;
    }
}
