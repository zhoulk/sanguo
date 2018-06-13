package com.mud.service;

import com.mud.mapper.Config;
import com.mud.mapper.Hero;

import java.util.*;

/**
 * Created by zlk on 2018/6/8.
 */
public class CacheService {

    private static List<Hero> _heros = new ArrayList<>();
    private static Map<String, Hero> _heroDic = new HashMap<>();
    private static List<Hero> _star3Heros = new ArrayList<>();
    private static List<Hero> _star4Heros = new ArrayList<>();
    private static List<Hero> _star5Heros = new ArrayList<>();

    private static Map<String, Config> _levelUpExpDic = new HashMap<>();

    public static List<Hero> heros(){
        return _heros;
    }

    public static Hero getHeroById(String heroId){
        if(!_heroDic.containsKey(heroId)){
            for (Hero h : _heros) {
                if(h.getHeroId().equalsIgnoreCase(heroId)){
                    _heroDic.put(heroId, h);
                    break;
                }
            }
        }
        return _heroDic.get(heroId);
    }

    public static void cacheHeros(List<Hero> heros){
        _heros.clear();
        _heros.addAll(heros);
    }

    public static long levelUpExp(int lv){
        Config config = _levelUpExpDic.get(lv+"");
        if(config == null){
            return 0;
        }
        return Long.parseLong(config.getValue());
    }

    public static void cacheConfig(List<Config> configs){
        for (Config c : configs) {
            if(c.getType().equalsIgnoreCase("exp_user_lv_up")){
                _levelUpExpDic.put(c.getKey(), c);
            }
        }
    }

    public static List<Hero> star3Heros(){
        if(_star3Heros.size() == 0){
            for (Hero h : _heros) {
                if(h.getStar() == 3){
                    _star3Heros.add(h);
                }
            }
        }
        return _star3Heros;
    }

    public static List<Hero> star4Heros(){
        if(_star4Heros.size() == 0){
            for (Hero h : _heros) {
                if(h.getStar() == 4){
                    _star4Heros.add(h);
                }
            }
        }
        return _star4Heros;
    }

    public static List<Hero> star5Heros(){
        if(_star5Heros.size() == 0){
            for (Hero h : _heros) {
                if(h.getStar() == 5){
                    _star5Heros.add(h);
                }
            }
        }
        return _star5Heros;
    }
}
