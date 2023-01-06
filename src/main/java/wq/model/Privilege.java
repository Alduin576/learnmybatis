package wq.model;

/**
 * 权限类
 */
public class Privilege {
    //声明成员变量
    private Long privilegeId;//权限id
    private String privilegeName;//权限名称
    private String privilegeUrl;//权限url

    public Long getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Long privilegeId) {
        this.privilegeId = privilegeId;
    }

    public String getPrivilegeName() {
        return privilegeName;
    }

    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName;
    }

    public String getPrivilegeUrl() {
        return privilegeUrl;
    }

    public void setPrivilegeUrl(String privilegeUrl) {
        this.privilegeUrl = privilegeUrl;
    }

    @Override
    public String toString() {
        return "Privilege{" +
                "privilegeId=" + privilegeId +
                ", privilegeName=" + privilegeName +
                ", privilegeUrl=" + privilegeUrl +
                '}';
    }
}
