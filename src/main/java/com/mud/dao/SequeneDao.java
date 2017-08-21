package com.mud.dao;

import org.apache.ibatis.annotations.Select;

/**
 * Created by leeesven on 17/8/21.
 */
public interface SequeneDao {

    @Select("SELECT nextVal(#{seqName})")
    int nextVal(String seqName);
}
