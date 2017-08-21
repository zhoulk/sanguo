package com.mud.dao;

import com.mud.mapper.Battle;
import com.mud.mapper.defines.BattleStatus;
import com.mud.mapper.defines.BattleStatusTypeHandler;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

/**
 * Created by leeesven on 17/8/20.
 */
public interface BattleDao {

    @Select("SELECT * FROM battle WHERE chapter_id = #{chapterId} order by battle_id")
    @Results({
            @Result(property = "battleId", column = "battle_id"),
            @Result(property = "chapterId", column = "chapter_id"),
            @Result(property = "battleTitle", column = "battle_title"),
            @Result(property = "battleDesc", column = "battle_desc"),
            @Result(property = "status", column = "status", javaType = BattleStatus.class, typeHandler = BattleStatusTypeHandler.class),
    })
    public ArrayList<Battle> getAllBattleByChapterId(Integer chapterId);

    @Select("SELECT * FROM battle WHERE battle_id = #{battleId}")
    @Results({
            @Result(property = "battleId", column = "battle_id"),
            @Result(property = "chapterId", column = "chapter_id"),
            @Result(property = "battleTitle", column = "battle_title"),
            @Result(property = "battleDesc", column = "battle_desc"),
            @Result(property = "status", column = "status", javaType = BattleStatus.class, typeHandler = BattleStatusTypeHandler.class),
    })
    public Battle getBattleById(Integer battleId);
}
