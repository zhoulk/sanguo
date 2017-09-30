package com.mud.mapper;

/**
 * Created by leeesven on 2017/9/24.
 */
public class HeroExtend {

    // '英雄编号'
    private String heroId;
    // '扩展属性'
    private String prop;
    // '值'
    private String val;

    public String getHeroId() {
        return heroId;
    }

    public String getProp() {
        return prop;
    }

    public String getVal() {
        return val;
    }

    public void setHeroId(String heroId) {
        this.heroId = heroId;
    }

    public void setProp(String prop) {
        this.prop = prop;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
