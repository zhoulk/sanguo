package com.mud.controller;

import com.mud.model.ResponseModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by leeesven on 17/8/21.
 */

@RequestMapping("/api/fight")
@RestController
public class FightController {

    /**
     * PVE 战役
     * @param battleId  战役Id
     * @return  {@code BattleModel}
     */
    @PostMapping("/pve/battle")
    public ResponseModel fightBattle(@RequestParam Integer battleId){
        ResponseModel responseModel = new ResponseModel();

        //TODO

        return responseModel;
    }


}
