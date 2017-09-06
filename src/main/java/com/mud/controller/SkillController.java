package com.mud.controller;

import com.mud.dao.SkillDao;
import com.mud.mapper.Skill;
import com.mud.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Created by leeesven on 17/8/20.
 */

@RequestMapping("/api/skill")
@RestController
public class SkillController {

    @Autowired
    private SkillDao skillDao;

    /**
     * 获取战法列表
     * @return  {@code ArrayList<Skill>}
     */
    @GetMapping(value = "/all")
    public ResponseModel getAllSkill(){
        ResponseModel responseModel = new ResponseModel();
        responseModel.setData(skillDao.getAllSkill());
        return responseModel;
    }

    /**
     * 获取指定战法信息
     * @param skillId   战法Id
     * @return {@code Skill}
     */
    @GetMapping(value = "/{skillId}")
    public ResponseModel getSkillById(@PathVariable String skillId){
        ResponseModel responseModel = new ResponseModel();
        responseModel.setData(skillDao.getSkillById(skillId));
        return responseModel;
    }

    /**
     * 分解战法
     * @param userSkillId   战法Id
     * @return  {@code ResponseModel}
     */
    @PostMapping(value = "/convert_to_point")
    public ResponseModel convertToPoint(@RequestParam String userSkillId){
        ResponseModel responseModel = new ResponseModel();

        //TODO

        return responseModel;
    }

    /**
     * 战法升级
     * @param userSkillId   战法Id
     * @return  {@code ResponseModel}
     */
    @PostMapping(value = "/level_up")
    public ResponseModel levelUp(@RequestParam String userSkillId){
        ResponseModel responseModel = new ResponseModel();

        //TODO

        return responseModel;
    }
}
