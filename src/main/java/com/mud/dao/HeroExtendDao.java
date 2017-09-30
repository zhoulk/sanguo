package com.mud.dao;

import com.mud.mapper.HeroExtend;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * Created by leeesven on 2017/9/24.
 */
public interface HeroExtendDao {

    @Select("SELECT * FROM hero_extend WHERE hero_id = #{0} AND prop = 'levelUp'")
    @Results({
            @Result(property = "heroId", column = "hero_id"),
    })
    HeroExtend getHeroExtendLevelUp(String heroId);

    @Select("SELECT * FROM hero_extend WHERE hero_id = #{0} AND prop = 'convertSkillPoint'")
    @Results({
            @Result(property = "heroId", column = "hero_id"),
    })
    HeroExtend getHeroExtendConvertSkillPoint(String heroId);

    @Select("SELECT * FROM hero_extend WHERE hero_id = #{0} AND prop = 'intoNum'")
    @Results({
            @Result(property = "heroId", column = "hero_id"),
    })
    HeroExtend getHeroExtendIntoNum(String heroId);

    @Select("SELECT * FROM hero_extend WHERE val = #{0} AND prop = 'heroPiece'")
    @Results({
            @Result(property = "heroId", column = "hero_id"),
    })
    HeroExtend getHeroExtendHeroPiece(String pieceId);
}
