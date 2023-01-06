package wq.model;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class User {
    //成员变量
    /**
     * 数据库字段
     * user_id 用户id
     * user_name 用户名
     * user_password 用户密码
     * user_info 用户简介
     * user_email 用户邮箱
     * head_img 用户头像
     * create_time 创建时间
     * birthday 生日
     * userRole  用户的角色
     * java对象采用 驼峰命名法
     */
    private Long userId;
    private String userName;
    private String userPassword;

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userInfo='" + userInfo + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", headImg=" + Arrays.toString(headImg) +
                ", createTime=" + createTime +
                ", userBirthday=" + userBirthday +
                ", roleList=" + roleList +
                ", role=" + role +
                '}';
    }

    private String userInfo;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    private String userEmail;
    private byte[] headImg;
    private Date createTime;
    private Date userBirthday;

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    private List<Role> roleList; //用户拥有的，角色用集合表示
    private Role role;  //角色属性，类型为Role类

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public byte[] getHeadImg() {
        return headImg;
    }

    public void setHeadImg(byte[] headImg) {
        this.headImg = headImg;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
