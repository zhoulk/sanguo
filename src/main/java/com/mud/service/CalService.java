package com.mud.service;

import com.mud.dao.HeroExtendDao;
import com.mud.dao.SkillExtendDao;
import com.mud.helper.NumberHelper;
import com.mud.mapper.Hero;
import com.mud.mapper.HeroExtend;
import com.mud.mapper.SkillExtend;
import com.mud.model.HeroModel;
import com.mud.model.SkillModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by leeesven on 2017/9/26.
 */
@Service
public class CalService {

    private Logger logger = Logger.getLogger(CalService.class.getName());

    @Autowired
    SkillExtendDao skillExtendDao;

    @Autowired
    HeroExtendDao heroExtendDao;

    public int calSkillPoint(HeroModel hero){
        // 增加研究进度
        HeroExtend heroExtend = heroExtendDao.getHeroExtendConvertSkillPoint(hero.getHeroId());
        int skillPoint = 0;
        if (heroExtend != null){
            skillPoint = Integer.parseInt(heroExtend.getVal());
        }
        return skillPoint;
    }

    /**
     *
     * @param skillId
     * @param hero
     * @return
     */
    public int calIntoPercent(String skillId, HeroModel hero){
        // 增加研究进度
        List<SkillExtend> skillExtends = skillExtendDao.getExtendOfSkillIntoCmd(skillId);
        int upPercent = 0;
        for (SkillExtend extend : skillExtends) {
            upPercent = calValue(extend.getVal(), hero);
            if (upPercent > 0){
                break;
            }
        }
        return upPercent;
    }

    /**
     * 脚本计算
     * @param script
     * @param model
     * @return
     */
    public int calValue(String script, Object model) {
        List<String> cmdList = Arrays.asList(script.split(" "));
        return calValue(cmdList, model);
    }

    /**
     * 计算脚本
     * @param cmds
     * @param model
     * @return
     */
    public int calValue(List<String> cmds, Object model){

        int index = 0;
        float result = 0;
        String op = "";
        while (index < cmds.size()){
            String cmd = cmds.get(index);

            if (cmd.equalsIgnoreCase("*")){
                op = cmd;
            }else if (cmd.equalsIgnoreCase("(")){
                index++;

                int offset = 1;
                int count = 1;
                while ((index + offset < cmds.size()) && count > 0){
                    if (cmds.get(index + offset).equalsIgnoreCase("(")){
                        count++;
                    }else if (cmds.get(index + offset).equalsIgnoreCase(")")){
                        count--;
                    }
                    offset ++;
                }

                int value = calValue(cmds.subList(index, index + offset-1), model);
                if (op.length() > 0){
                    if (op.equalsIgnoreCase("*")){
                        result *= value;
                    }
                }else{
                    result = value;
                }

                index += offset-1;

            }else if(isFunc(cmd)){
                index++;

                // Func 后面紧跟括号
                index++;

                int offset = 1;
                int count = 1;
                while ((index + offset < cmds.size()) && count > 0){
                    if (cmds.get(index + offset).equalsIgnoreCase("(")){
                        count++;
                    }else if (cmds.get(index + offset).equalsIgnoreCase(")")){
                        count--;
                    }
                    offset ++;
                }


                int value = getFuncValue(cmd, cmds.subList(index, index + offset-1), model);
                if (op.length() > 0){
                    if (op.equalsIgnoreCase("*")){
                        result *= value;
                    }
                }else{
                    result = value;
                }

                index += offset-1;
            }else if (isProp(cmd)){
                int value = getPropValue(cmd, model);
                if (op.length() > 0){
                    if (op.equalsIgnoreCase("*")){
                        result *= value;
                    }
                }else{
                    result = value;
                }
            }else if (NumberHelper.isRealNumber(cmd)){
                if (cmd.contains(".")){
                    float value = Float.parseFloat(cmd);
                    if (op.length() > 0){
                        if (op.equalsIgnoreCase("*")){
                            result *= value;
                        }
                    }else{
                        result = value;
                    }
                }else{
                    int value = Integer.parseInt(cmd);
                    if (op.length() > 0){
                        if (op.equalsIgnoreCase("*")){
                            result *= value;
                        }
                    }else{
                        result = value;
                    }
                }
            }

            index++;
        }


        return (int)result;
    }

    private final List<String> props = new ArrayList(){{
        add("soldierNum");
        add("exSoldierNum");
        add("intelligent");
        add("heroId");
        add("level");
    }};
    private boolean isProp(String cmd){
        return props.contains(cmd);
    }

    private int getPropValue(String prop, Object model){
        int value = 0;
        if (prop.equalsIgnoreCase("soldierNum")){
            if (HeroModel.class.isInstance(model)){
                value = ((HeroModel)model).getSoldierNum();
            }
        }else if (prop.equalsIgnoreCase("exSoldierNum")){
            if (HeroModel.class.isInstance(model)) {
                value = ((HeroModel)model).getExSoldierNum();
            }
        }else if (prop.equalsIgnoreCase("intelligent")){
            if (HeroModel.class.isInstance(model)) {
                value = ((HeroModel)model).getIntelligence();
            }
        }else if (prop.equalsIgnoreCase("heroId")){
            if (HeroModel.class.isInstance(model)) {
                value = Integer.parseInt(((HeroModel)model).getHeroId());
            }
        }else if (prop.equalsIgnoreCase("level")) {
            if (SkillModel.class.isInstance(model)){
                value = ((SkillModel)model).getLevel();
            }
        }

        logger.info("getPropValue prop:" + prop + " value:" + value);
        return value;
    }

    private final List<String> funcs = new ArrayList(){{
        add("THIRD");
        add("RND");
        add("ABS");
        add("GT");
        add("EQ");
        add("AND");
        add("OR");
    }};
    private boolean isFunc(String cmd){
        return funcs.contains(cmd);
    }

    private int getFuncValue(String cmd, List<String> params, Object model){
        int result = 0;
        if (cmd.equalsIgnoreCase("ABS")){
            result = Math.abs(calValue(params, model));
        }else if(cmd.equalsIgnoreCase("RND")){
            result = (int)(Math.random() * Integer.parseInt(params.get(0)));
        }else if(cmd.equalsIgnoreCase("THIRD")){
            result = funcThird(params, model);
        }else if(cmd.equalsIgnoreCase("GT")){
            result = funcGT(params, model);
        }else if(cmd.equalsIgnoreCase("EQ")){
            result = funcEQ(params, model);
        }else if(cmd.equalsIgnoreCase("AND")){
            result = funcAND(params, model);
        }else if(cmd.equalsIgnoreCase("OR")){
            result = funcOR(params, model);
        }
        logger.info("getFuncValue func:" + cmd + " value:" + result);

        return result;
    }

    // AND ( EQ ( star , 4 ) , EQ ( type, 1 ) )
    private int funcAND(List<String> params, Object model){
        int result = 0;
        int index1 = indexOfSeparate(params, 0, ",");
        List<String> v1Part = params.subList(0, index1);
        List<String> v2Part = params.subList(index1, params.size());
        if (calValue(v1Part, model) > 0 &&
                calValue(v2Part, model) > 0) {
            result = 1;
        }
        return result;
    }

    // OR ( EQ ( star , 4 ) , EQ ( type, 1 ) )
    private int funcOR(List<String> params, Object model){
        int result = 0;
        int index1 = indexOfSeparate(params, 0, ",");
        List<String> v1Part = params.subList(0, index1);
        List<String> v2Part = params.subList(index1, params.size());
        if (calValue(v1Part, model) > 0 ||
                calValue(v2Part, model) > 0) {
            result = 1;
        }
        return result;
    }

    // GT ( RND ( 100 ) , 50 )
    private int funcGT(List<String> params, Object model) {
        int result = 0;
        int index1 = indexOfSeparate(params, 0, ",");
        List<String> v1Part = params.subList(0, index1);
        List<String> v2Part = params.subList(index1, params.size());
        if (calValue(v1Part, model) > calValue(v2Part, model)) {
            result = 1;
        }

        return result;
    }

    // EQ ( heroId , 501 )
    private int funcEQ(List<String> params, Object model) {
        int result = 0;
        int index1 = indexOfSeparate(params, 0, ",");
        List<String> v1Part = params.subList(0, index1);
        List<String> v2Part = params.subList(index1, params.size());
        if (calValue(v1Part, model) == calValue(v2Part, model)) {
            result = 1;
        }
        return result;
    }

    //  RND ( 100 ) > 50 , 1 , 0
    private int funcThird(List<String> params, Object model){
        int index1 = indexOfSeparate(params, 0, ",");
        int index2 = indexOfSeparate(params, index1, ",");

        List<String> cmdPart = params.subList(0, index1-1);
        List<String> v1Part = params.subList(index1, index2-1);
        List<String> v2Part = params.subList(index2, params.size());

        int result = 0;
        if (calValue(cmdPart, model) > 0){
            result = calValue(v1Part, model);
        }else{
            result = calValue(v2Part, model);
        }

        return result;
    }

    /**
     * 能够处理 括号 的 index
     * @param params
     * @param separate
     * @return
     */
    private int indexOfSeparate(List<String> params, int start, String separate){
        int index = start;
        int count = 0;
        while (index < params.size()){
            String str = params.get(index);
            if (str.equalsIgnoreCase("(")){
                count++;
            }else if (str.equalsIgnoreCase(")")){
                count--;
            }
            if (count == 0 && str.equalsIgnoreCase(separate)){
                index++;
                break;
            }
            index++;
        }
        return index;
    }
}
