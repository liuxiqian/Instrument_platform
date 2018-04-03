package com.mengyunzhi.measurement.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.jsonView.NoneJsonView;
import com.mengyunzhi.measurement.jsonView.RoleJsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
/**
 * 角色实体
 */
@Entity
@ApiModel(value = "Role (角色)", description = "角色")
public class Role implements Serializable{
    final static public String NAME_GUAN_LI_YUAN = "管理员";
    final public static String NAME_QI_JU_YONG_HU = "器具用户";
    final public static String NAME_JI_SHU_JI_GOU = "技术机构";
    final public static String NAME_GUAN_LI_BU_MEN = "管理部门";
    final public static String NAME_XI_TONG_GUAN_LI_YUAN = "系统管理员";
    final public static String NAME_SHENG_CHAN_QI_YE = "生产企业";
    final public static String NAME_LING_DAO = "领导";
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ApiModelProperty("名称")
    @Column(unique = true)
    private String name;
    @ApiModelProperty("用户名拼音")
    private String pinyin;
    @ApiModelProperty("是否系统管理员")
    private boolean isAdmin = false;
    @ApiModelProperty("创建时间")
    private Long createTime;
    @ApiModelProperty("更新时间")
    private Long updateTime;

    @ApiModelProperty("该角色下的所有菜单")
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "menu_role",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_id")
    )
    @JsonView(NoneJsonView.class)
    private Set<Menu> menus = new HashSet<Menu>();

    @ApiModelProperty("对应的前台菜单")
    @ManyToMany
    @Lazy
    @JoinTable(
            name = "web_app_menu_role",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "web_app_menu_id")
    )
    @JsonView(RoleJsonView.GetAll.class)
    private Set<WebAppMenu> webAppMenus = new HashSet<WebAppMenu>();

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Role() {
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pinyin='" + pinyin + '\'' +
                ", isAdmin=" + isAdmin +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", menus=" + menus +
                ", webAppMenus=" + webAppMenus +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }

    public Set<WebAppMenu> getWebAppMenus() {
        return webAppMenus;
    }

    public void setWebAppMenus(Set<WebAppMenu> webAppMenus) {
        this.webAppMenus = webAppMenus;
    }

    public void addWebAppMenu(WebAppMenu webAppMenu) {
        this.webAppMenus.add(webAppMenu);
    }
}
