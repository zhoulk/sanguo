package com.mud.mapper;

/**
 * Created by leeesven on 2017/9/26.
 */
public class SkillExtend {

    // '战法编号'
    private String skillId;
    // '扩展属性'
    private String prop;
    // '值'
    private String val;

    public String getSkillId() {
        return skillId;
    }

    public String getProp() {
        return prop;
    }

    public String getVal() {
        return val;
    }

    public void setSkillId(String skillId) {
        this.skillId = skillId;
    }

    public void setProp(String prop) {
        this.prop = prop;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
