package com.mud.dao;

import com.mud.mapper.BattleExtend;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

/**
 * Created by leeesven on 2017/9/22.
 */
public interface BattleExtendDao {

    @Select("SELECT * FROM battle_extend WHERE battle_id = #{0} AND enable = 1")
    @Results({
            @Result(property = "battleId", column = "battle_id"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "createTime", column = "create_time")
    })
    public ArrayList<BattleExtend> getExtendOfBattle(int battleId);
}
