package com.mud.controller;

import com.mud.model.ResponseModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by leeesven on 17/8/24.
 */

@RequestMapping("/api/collect")
@RestController
public class CollectController {

    /**
     * 获取资源数据
     * @return  {@code ResponseModel}
     */
    @GetMapping(value = "/all")
    public ResponseModel getAllResource(){
        ResponseModel responseModel = new ResponseModel();

        //TODO

        return responseModel;
    }
}
