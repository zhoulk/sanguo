package com.mud.service;

import com.mud.dao.SequeneDao;
import com.mud.mapper.defines.DBMacro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by leeesven on 2017/9/21.
 */

@Service
public class SequenceService {

    @Autowired
    SequeneDao sequeneDao;

    public String sequenceOfReport(){
        return sequenceOfType(DBMacro.SEQ_NAME_REPORT);
    }

    public String sequenceOfUser(){
        return sequenceOfType(DBMacro.SEQ_NAME_USER);
    }

    public String sequenceOfHero(){
        return sequenceOfType(DBMacro.SEQ_NAME_HERO);
    }

    public String sequenceOfSkill(){
        return sequenceOfType(DBMacro.SEQ_NAME_SKILL);
    }

    private String sequenceOfType(String type){
        int seqNo = sequeneDao.nextVal(type);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String nowdate = sdf.format(new Date());
        String sequenceId = String.format("%s%04d", nowdate, seqNo);
        return sequenceId;
    }

}
