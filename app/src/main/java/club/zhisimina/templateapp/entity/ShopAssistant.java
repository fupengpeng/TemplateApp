package club.zhisimina.templateapp.entity;

import java.io.Serializable;

/**
 * 门店店员
 */
public class ShopAssistant implements Serializable {
    // 人员ID
    private String userid;
    // 人员平台id
    private String suid;
    // 人员姓名
    private String username;
    // 人员工号
    private String jobnumber;
    // 人员级别ID
    private String jobid;
    // 人员级别名称
    private String jobname;
    // 人员图像
    private String photourl;
    // 是否选择
    private boolean serve;

    /*---------- 预约用 ----------*/
    // 店员id
    private String id;
    // 店员姓名
    private String name;
    // 分类id
    private String postid;
    // 分类名
    private String postname;
    // 是否为可预约人  0：不是  1：是
    private String appoint;
    // 是否可预约
    private boolean beforehandappoint;

    // 性别
    private String gender;


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getSuid() {
        return suid;
    }

    public void setSuid(String suid) {
        this.suid = suid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJobnumber() {
        return jobnumber;
    }

    public void setJobnumber(String jobnumber) {
        this.jobnumber = jobnumber;
    }

    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
        this.jobid = jobid;
    }

    public String getJobname() {
        return jobname;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname;
    }

    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }

    public boolean isServe() {
        return serve;
    }

    public void setServe(boolean serve) {
        this.serve = serve;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getPostname() {
        return postname;
    }

    public void setPostname(String postname) {
        this.postname = postname;
    }

    public String getAppoint() {
        return appoint;
    }

    public void setAppoint(String appoint) {
        this.appoint = appoint;
    }

    public boolean isBeforehandappoint() {
        return beforehandappoint;
    }

    public void setBeforehandappoint(boolean beforehandappoint) {
        this.beforehandappoint = beforehandappoint;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
