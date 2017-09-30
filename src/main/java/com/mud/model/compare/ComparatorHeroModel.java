package com.mud.model.compare;

import com.mud.model.HeroModel;

import java.util.Comparator;

/**
 * Created by leeesven on 17/9/18.
 */
public class ComparatorHeroModel implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {

        HeroModel hero1 = (HeroModel) o1;
        HeroModel hero2 = (HeroModel) o2;

        if (hero1 != null && hero1.getMoveSpeed() != null
                && hero2 != null && hero2.getMoveSpeed() != null){
            return hero1.getMoveSpeed().compareTo(hero2.getMoveSpeed());
        }
        return 0;
    }
}
