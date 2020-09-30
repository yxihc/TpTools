package com.taopao.rxareaselect;

import java.io.Serializable;

/**
 * @Author： 淘跑
 * @Date: 2018/5/29 15:21
 * @Use： 地址选择器数据模型
 */
public class AreaBean implements Serializable {

    /**
     * ids : 5846,5847,9909
     * level : 3
     * level4Useing : N
     * name : 呈贡区
     * names : 云南省,昆明市,呈贡区
     * notvalid :
     * pid : 5847
     * tid : 9909
     */

    private String ids;
    private String level;
    private String level4Useing;
    private String name;
    private String names;
    private String notvalid;
    private String pid;
    private int tid;

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevel4Useing() {
        return level4Useing;
    }

    public void setLevel4Useing(String level4Useing) {
        this.level4Useing = level4Useing;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getNotvalid() {
        return notvalid;
    }

    public void setNotvalid(String notvalid) {
        this.notvalid = notvalid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }



    private boolean check;

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

//    public int getTextColor() {
//        if (check) {
//            return ContextCompat.getColor(App.getContext(), R.color.colorPrimary);
//        }
//        return ContextCompat.getColor(App.getContext(), R.color.black);
//    }
}
