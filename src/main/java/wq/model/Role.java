package wq.model;

import java.util.Date;
import java.util.List;

public class Role {
    public List<Privilege> getPrivilegeList() {
        return privilegeList;
    }

    public void setPrivilegeList(List<Privilege> privilegeList) {
        this.privilegeList = privilegeList;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", enabled=" + enabled +
                ", createBy=" + createBy +
                ", createTime=" + createTime +
                ", privilegeList=" + privilegeList +
                '}';
    }

    /**
     * 角色表对应的javaBean对象
     * 变量命名采用 驼峰命名法
     * 角色id roleId
     * 角色名称 roleName
     * 有效状态 enabled
     * 创建者  createBy
     * 创建时间 createTime
     *
     */
    private Long roleId;
    private String roleName;
    private int enabled;
    private Long createBy;
    private Date createTime;
    //角色对应的权限
    private List<Privilege> privilegeList;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
