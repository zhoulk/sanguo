package com.mud.service;

import com.mud.helper.NumberHelper;
import com.mud.mapper.UserReportDetail;
import com.mud.model.HeroModel;
import com.mud.model.SkillModel;
import com.mud.model.compare.ComparatorHeroModel;
import com.mud.model.define.SGMacro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by leeesven on 17/9/15.
 */

@Service
public class FightService {

    private Logger logger = Logger.getLogger(NpcService.class.getName());

    @Autowired
    CalService calService;

    /**
     * 战斗
     * @param playerHeros
     * @param otherHeros
     */
    public List<UserReportDetail> fight(ArrayList<HeroModel> playerHeros, ArrayList<HeroModel> otherHeros){
        logger.info("战斗开始...");

        List<UserReportDetail> reportList = new ArrayList<>();

        showHeroList("我方英雄:", playerHeros);
        showHeroList("对方英雄:", otherHeros);

        setRelation(playerHeros, true);
        setRelation(otherHeros, false);

        List<HeroModel> allHeros = new ArrayList<>();
        allHeros.addAll(playerHeros);
        allHeros.addAll(otherHeros);

        // 1.被动计  效果
        List<UserReportDetail> passiveReportList = executePassive(allHeros, playerHeros, otherHeros);
        reportList.addAll(passiveReportList);

        // 主动计
        for (int i=0; i<8; i++){
            List<UserReportDetail> roundReportList = new ArrayList<>();

            // 清理死掉的英雄
            allHeros = cleanDiedHeros(allHeros);
            // 回合开始
            roundReportList.addAll(roundStart(i+1));

            // 2. 按速度确定出手顺序
            Collections.sort(allHeros, new ComparatorHeroModel());
            Collections.reverse(allHeros);
            showHeroList("排序结果:", allHeros);

            roundReportList.addAll(executeBefore(allHeros, playerHeros, otherHeros));
            roundReportList.addAll(execute(allHeros, playerHeros, otherHeros));
            roundReportList.addAll(executeAfter(allHeros, playerHeros, otherHeros));

            // 4.1  判断是否结束(全部死亡)
            int result = judgeResult(playerHeros, otherHeros);
            if (result > 0){
                roundReportList.addAll(fightWin());
            }else if (result < 0){
                roundReportList.addAll(fightLost());
            }else {
                if (i == 7){
                    roundReportList.addAll(fightTie());
                }
            }

            showHeroList("当前武将详情:", allHeros);

            // 回合结束
            roundReportList.addAll(roundEnd(i+1));

            // 更新回合数到本回合记录
            for (UserReportDetail reportDetail : roundReportList) {
                reportDetail.setRound(i+1);
            }
            reportList.addAll(roundReportList);

            if (result != 0){
                break;
            }
        }

        // 战斗结束
        reportList.addAll(fightOver());

        return reportList;
    }

    private List<UserReportDetail> executePassive(List<HeroModel> allHeros, ArrayList<HeroModel> friendHeros, ArrayList<HeroModel> enermyHeros){

        List<UserReportDetail> passiveReportList = new ArrayList<>();

        // 1.1 确定顺序
        ArrayList<HeroModel> passivHeros = new ArrayList<>();
        for (HeroModel model: friendHeros) {
            if (model.getSkill().getSkillType() == SGMacro.SG_SKILL_TYPE_PASSIVE)
                passivHeros.add(model);
        }
        for (HeroModel model: enermyHeros) {
            if (model.getSkill().getSkillType() == SGMacro.SG_SKILL_TYPE_PASSIVE)
                passivHeros.add(model);
        }

        Collections.sort(passivHeros, new ComparatorHeroModel());
        Collections.reverse(passivHeros);
        logger.info("被动计排序结果:");
        for (HeroModel model : passivHeros) {
            logger.info(model.getHeroId() + ":" + model.getNickname() + " speed:" + model.getMoveSpeed());
        }

        // 1.2 执行效果
        logger.info("战法效果:---------");
        for (HeroModel model : passivHeros) {
            logger.info(model.getHeroId() + ":" + model.getNickname() + " desc:" + model.getSkill().getDesc() + " script:" + model.getSkill().getScript());

            if (model.isFriend()){
                passiveReportList = executeScript(model, model.getSkill(), friendHeros, enermyHeros);
            }else{
                passiveReportList = executeScript(model, model.getSkill(), enermyHeros, friendHeros);
            }
        }

        return passiveReportList;
    }

    private List<UserReportDetail> executeBefore(List<HeroModel> allHeros, ArrayList<HeroModel> friendHeros, ArrayList<HeroModel> enermyHeros){
        List<UserReportDetail> reportDetailList = new ArrayList<>();

        return reportDetailList;
    }

    private List<UserReportDetail> execute(List<HeroModel> allHeros, ArrayList<HeroModel> friendHeros, ArrayList<HeroModel> enermyHeros){
        List<UserReportDetail> reportDetailList = new ArrayList<>();
        for (HeroModel model : allHeros) {

            List<UserReportDetail> skillReportList = new ArrayList<>();

            if (isMidSkill(model.getSkill())){
                if (model.isFriend()){
                    skillReportList.addAll(executeScript(model, model.getSkill(), friendHeros, enermyHeros));
                }else{
                    skillReportList.addAll(executeScript(model, model.getSkill(), enermyHeros, friendHeros));
                }
            }

            if (isMidSkill(model.getExSkill1())){
                if (model.isFriend()){
                    skillReportList.addAll(executeScript(model, model.getExSkill1(), friendHeros, enermyHeros));
                }else{
                    skillReportList.addAll(executeScript(model, model.getExSkill1(), enermyHeros, friendHeros));
                }
            }

            if (isMidSkill(model.getExSkill2())){
                if (model.isFriend()){
                    skillReportList.addAll(executeScript(model, model.getExSkill2(), friendHeros, enermyHeros));
                }else{
                    skillReportList.addAll(executeScript(model, model.getExSkill2(), enermyHeros, friendHeros));
                }
            }

            if (skillReportList.size() == 0){
                List<UserReportDetail> attackReports;
                if (model.isFriend()){
                    attackReports = executeAttack(model, enermyHeros);
                }else{
                    attackReports = executeAttack(model, friendHeros);
                }
                reportDetailList.addAll(attackReports);
            }
        }
        return reportDetailList;
    }

    private List<UserReportDetail> executeAfter(List<HeroModel> allHeros, ArrayList<HeroModel> friendHeros, ArrayList<HeroModel> enermyHeros){
        List<UserReportDetail> reportDetailList = new ArrayList<>();
        for (HeroModel model : allHeros) {
            if (isAfterSkill(model.getSkill())){
                if (model.isFriend()){
                    reportDetailList.addAll(executeScript(model, model.getSkill(), friendHeros, enermyHeros));
                }else{
                    reportDetailList.addAll(executeScript(model, model.getSkill(), enermyHeros, friendHeros));
                }
            }

            if (isAfterSkill(model.getExSkill1())){
                if (model.isFriend()){
                    reportDetailList.addAll(executeScript(model, model.getExSkill1(), friendHeros, enermyHeros));
                }else{
                    reportDetailList.addAll(executeScript(model, model.getExSkill1(), enermyHeros, friendHeros));
                }
            }

            if (isAfterSkill(model.getExSkill2())){
                if (model.isFriend()){
                    reportDetailList.addAll(executeScript(model, model.getExSkill2(), friendHeros, enermyHeros));
                }else{
                    reportDetailList.addAll(executeScript(model, model.getExSkill2(), enermyHeros, friendHeros));
                }
            }
        }
        return reportDetailList;
    }

    private List<UserReportDetail> executeScript(HeroModel model, SkillModel skill, ArrayList<HeroModel> friendHeros, ArrayList<HeroModel> enermyHeros){
        List<UserReportDetail> reportDetailList = new ArrayList<>();

        String[] effects = model.getSkill().getScript().split(";");
        for (String effect : effects) {
            List<String> cmds = Arrays.asList(effect.split(" "));
            String cmd = "";
            int index = 0;
            if (cmds.size() > 0) {
                cmd = cmds.get(0);
                index ++;
            }
            if (cmd.equalsIgnoreCase("AFTER")
                    || cmd.equalsIgnoreCase("BEFORE")){

                cmd = cmds.get(1);
                index ++;
            }
            String prop = cmds.get(index);
            index ++;

            // 1.3 确定范围
            String[] atkDestArr = model.getSkill().getAtkDestType().split(",");
            int atkDest = -1;
            if (atkDestArr.length > 1){
                // 随机选择一个范围
                int atkDestIndex = (int) Math.round(Math.random()*(atkDestArr.length - 1));
                atkDest = Integer.parseInt(atkDestArr[atkDestIndex]);
            }else{
                // 固定范围
                if (atkDestArr.length == 1){
                    atkDest = Integer.parseInt(atkDestArr[0]);
                }
            }
            if (atkDest < 0){
                // 不产生效果
                logger.warning("战法:" + model.getSkill().getSkillName() + " 未产生效果!" + "  攻击距离为: " + atkDest);
                break;
            }

            HeroModel target = model;
            switch(atkDest) {
                case SGMacro.SG_SKILL_ATK_DEST_TYPE_SELF: {
                    target = model;
                    break;
                }
            }

            // 产生效果
            if (cmd.equalsIgnoreCase("ADD")){
                int value = calService.calValue(cmds.subList(index, cmds.size()), model);
                String desc = "";
                if (prop.equalsIgnoreCase("soldierNum")){
                    target.setExSoldierNum(target.getExSoldierNum() + value);
                    desc = model.getHeroId() + ":" + model.getNickname() + " 触发 【" + skill.getSkillName() + "】" + "恢复伤兵"+ value + "," +
                            " 剩余士兵 " + target.getSoldierNum() + "("+ target.getExSoldierNum() +")";
                }else if (prop.equalsIgnoreCase("attack")){
                    model.setExAttack(model.getExAttack() + value);
                    desc = "战法:" + model.getSkill().getSkillName() + " 产生效果!" +  model.getNickname() + "  攻击力变为: " + model.getAttack() + "(+" + model.getExAttack() + ")";
                }else if (prop.equalsIgnoreCase("speed")){
                    model.setExSpeed(model.getExSpeed() + value);
                    desc = "战法:" + model.getSkill().getSkillName() + " 产生效果!" +  model.getNickname() + "  速度变为: " + model.getMoveSpeed() + "(+" + model.getExSpeed() + ")";
                }else if (prop.equalsIgnoreCase("defence")){
                    model.setExDefence(model.getExDefence() + value);
                    desc = "战法:" + model.getSkill().getSkillName() + " 产生效果!" +  model.getNickname() + "  防御变为: " + model.getDefence() + "(+" + model.getExDefence() + ")";
                }else if (prop.equalsIgnoreCase("exAtkNumOneRound")){
                    model.setExAtkNumOneRound(model.getExAtkNumOneRound() + value);
                    desc = "战法:" + model.getSkill().getSkillName() + " 产生效果!" +  model.getNickname() + "  攻击次数变为: " + model.getAtkNumOneRound() + "(+" + model.getExAtkNumOneRound() + ")";
                }

                UserReportDetail reportDetail = new UserReportDetail();
                reportDetail.setFromHero(model.getHeroId());
                reportDetail.setToHero(target.getHeroId());
                reportDetail.setSkill(model.getSkill().getSkillId());
                reportDetail.setProp(prop);
                reportDetail.setVal(String.valueOf(value));
                reportDetail.setDescrib(desc);
                reportDetailList.add(reportDetail);

                logger.info(desc);
            }
        }

        return reportDetailList;
    }

    /**
     *
     * 武将站位 3 2 1 | 1 2 3
     * @param model
     * @param enermyHeros
     * @return
     */
    private List<UserReportDetail> executeAttack(HeroModel model, ArrayList<HeroModel> enermyHeros){
        List<UserReportDetail> reportDetailList = new ArrayList<>();

        int atkDist = model.getAtkDist();
        int position = model.getPosCol();
        int maxAtkDist = Math.abs(position - atkDist);
        if (maxAtkDist > 0){
            if (enermyHeros.size() > 0){
                HeroModel target = enermyHeros.get(0);
                int value = -1 * model.getAttack();
                target.setExSoldierNum(target.getExSoldierNum() + value);

                String desc = model.getHeroId() + ":" + model.getNickname() + " 对 " +
                        target.getNickname() + " 发动 【 普通攻击 】" + " 造成 " + model.getAttack() + " 点伤害! " +
                        target.getNickname() + " 剩余士兵 " + target.getSoldierNum() + "("+ target.getExSoldierNum() +")";

                UserReportDetail reportDetail = new UserReportDetail();
                reportDetail.setFromHero(model.getHeroId());
                reportDetail.setToHero(target.getHeroId());
                reportDetail.setSkill(model.getSkill().getSkillId());
                reportDetail.setProp("soldierNum");
                reportDetail.setVal(String.valueOf(value));
                reportDetail.setDescrib(desc);
                reportDetailList.add(reportDetail);

                logger.info(desc);
            }
        }

        return reportDetailList;
    }

    private boolean isAfterSkill(SkillModel skill){
        boolean isAfter = false;

        if (skill != null && skill.getScript() != null &&
                (skill.getSkillType() == SGMacro.SG_SKILL_TYPE_ACTIVE
                || skill.getSkillType() == SGMacro.SG_SKILL_TYPE_DIRECTOR)) {

            String[] effects = skill.getScript().split(";");
            for (String effect : effects) {
                String[] cmds = effect.split(" ");
                String cmd = "";
                if (cmds.length > 0) {
                    cmd = cmds[0];
                }
                if (cmd.equalsIgnoreCase("AFTER")) {
                    isAfter = true;
                }
            }
        }
        return isAfter;
    }

    private boolean isBEFORESkill(SkillModel skill){
        boolean isAfter = false;

        if (skill != null && skill.getScript() != null
                && skill.getSkillType() == SGMacro.SG_SKILL_TYPE_ACTIVE
                && skill.getSkillType() == SGMacro.SG_SKILL_TYPE_DIRECTOR) {

            String[] effects = skill.getScript().split(";");
            for (String effect : effects) {
                String[] cmds = effect.split(" ");
                String cmd = "";
                if (cmds.length > 0) {
                    cmd = cmds[0];
                }
                if (cmd.equalsIgnoreCase("BEFORE")) {
                    isAfter = true;
                }
            }
        }
        return isAfter;
    }

    private boolean isMidSkill(SkillModel skill){
        boolean isAfter = false;

        if (skill != null && skill.getScript() != null
                && skill.getSkillType() == SGMacro.SG_SKILL_TYPE_ACTIVE
                && skill.getSkillType() == SGMacro.SG_SKILL_TYPE_DIRECTOR) {

            String[] effects = skill.getScript().split(";");
            for (String effect : effects) {
                String[] cmds = effect.split(" ");
                String cmd = "";
                if (cmds.length > 0) {
                    cmd = cmds[0];
                }
                if (!cmd.equalsIgnoreCase("AFTER")
                        && !cmd.equalsIgnoreCase("BEFORE")) {
                    isAfter = true;
                }
            }
        }
        return isAfter;
    }

    private List<UserReportDetail> roundStart(int round){
        List<UserReportDetail> reportList = new ArrayList<>();
        String desc = "第"+ round +"回合 开始:******************";
        logger.info(desc);
        UserReportDetail endReportDetail = new UserReportDetail();
        endReportDetail.setProp("start");
        endReportDetail.setDescrib(desc);
        reportList.add(endReportDetail);
        return reportList;
    }

    private List<UserReportDetail> roundEnd(int round){
        List<UserReportDetail> reportList = new ArrayList<>();
        String desc = "第"+ round +"回合 结束:******************";
        logger.info(desc);
        UserReportDetail endReportDetail = new UserReportDetail();
        endReportDetail.setProp("end");
        endReportDetail.setDescrib(desc);
        reportList.add(endReportDetail);
        return reportList;
    }

    private List<UserReportDetail> fightOver(){
        List<UserReportDetail> reportList = new ArrayList<>();
        String desc = "战斗结束...";
        logger.info(desc);
        UserReportDetail endReportDetail = new UserReportDetail();
        endReportDetail.setProp("over");
        endReportDetail.setDescrib(desc);
        endReportDetail.setRound(9);
        reportList.add(endReportDetail);
        return reportList;
    }

    private List<UserReportDetail> fightLost(){
        List<UserReportDetail> reportList = new ArrayList<>();
        String desc = "战斗失败！";
        logger.info(desc);
        UserReportDetail endReportDetail = new UserReportDetail();
        endReportDetail.setProp("lost");
        endReportDetail.setDescrib(desc);
        endReportDetail.setRound(10);
        reportList.add(endReportDetail);
        return reportList;
    }

    private List<UserReportDetail> fightWin(){
        List<UserReportDetail> reportList = new ArrayList<>();
        String desc = "战斗胜利！";
        logger.info(desc);
        UserReportDetail endReportDetail = new UserReportDetail();
        endReportDetail.setProp("win");
        endReportDetail.setDescrib(desc);
        endReportDetail.setRound(10);
        reportList.add(endReportDetail);
        return reportList;
    }

    /**
     * 平局
     */
    private List<UserReportDetail> fightTie(){
        List<UserReportDetail> reportList = new ArrayList<>();
        String desc = "战斗平局！";
        logger.info(desc);
        UserReportDetail endReportDetail = new UserReportDetail();
        endReportDetail.setProp("tie");
        endReportDetail.setDescrib(desc);
        endReportDetail.setRound(10);
        reportList.add(endReportDetail);
        return reportList;
    }

    private void showHeroList(String title, List<HeroModel> heros){
        logger.info(title);
        for (HeroModel model : heros) {
            logger.info(model.getHeroId() + ":" + model.getNickname() + " speed:" + model.getMoveSpeed() + " soldierNum:" + model.getSoldierNum() + "(" + model.getExSoldierNum() + ")");
        }
    }

    private void setRelation(List<HeroModel> heros, boolean isFriend){
        for (HeroModel model : heros) {
            model.setFriend(isFriend);
        }
    }

    private int judgeResult(List<HeroModel> playerHeros, List<HeroModel> otherHeros){
        int result = 0;
        boolean isFriendOver = true;
        boolean isEnermyOver = true;
        for ( HeroModel model : playerHeros) {
            if (model.getSoldierNum() > 0){
                isFriendOver = false;
            }
        }
        for (HeroModel model : otherHeros){
            if (model.getSoldierNum() > 0){
                isEnermyOver = false;
            }
        }

        if (isEnermyOver){
            result = 1;
        }else if (isFriendOver){
            result = -1;
        }

        return result;
    }

    private List<HeroModel> cleanDiedHeros(List<HeroModel> heros){
        List<HeroModel> lifeHeros = new ArrayList<>();
        for (HeroModel hero : heros) {
            if (hero.getSoldierNum() > 0){
                lifeHeros.add(hero);
            }
        }
        return lifeHeros;
    }
}
