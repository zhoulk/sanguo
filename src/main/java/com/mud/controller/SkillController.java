package com.mud.controller;

import com.mud.dao.SkillDao;
import com.mud.mapper.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Created by leeesven on 17/8/20.
 */

@RequestMapping("/api/skill")
@RestController
public class SkillController {

    @Autowired
    SkillDao skillDao;

    @GetMapping(value = "/all")
    public ArrayList<Skill> getAllSkill(){
        return skillDao.getAllSkill();
    }

    @GetMapping(value = "/{skillId}")
    public Skill getSkillById(@PathVariable String skillId){
        return skillDao.getSkillById(skillId);
    }
}
