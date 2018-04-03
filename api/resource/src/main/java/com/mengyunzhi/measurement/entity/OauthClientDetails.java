package com.mengyunzhi.measurement.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.jsonView.NoneJsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author panjie on 2017/12/27
 */
@Entity
@ApiModel(value = "oauth用户详情", description = "记录oauth的用户认证信息")
public class OauthClientDetails {

    @Id
    private String clientId;

    @ApiModelProperty("资源IDS")
    private String resourceIds;

    @ApiModelProperty("密钥")
    private String clientSecret;

    @ApiModelProperty("权限范围")
    private String scope;

    @ApiModelProperty("对应的用户")
    @OneToOne
    @JsonView(NoneJsonView.class)
    @NotNull
    private User user;

    @ApiModelProperty("认证类型")
    private String authorizedGrantTypes;

    @ApiModelProperty("认证地址")
    private String webServerRedirectUri;

    @ApiModelProperty("权限")
    private String authorities;

    @ApiModelProperty("accessToken有效期")
    private Integer accessTokenValidity;

    @ApiModelProperty("refreshToken有效期")
    private Integer refreshTokenValidity;

    @ApiModelProperty("附加信息")
    @Column(columnDefinition = "TEXT")
    private String additionalInformation;

    @ApiModelProperty("是否自动批准")
    private Boolean autoapprove;

    public OauthClientDetails() {
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    public String getWebServerRedirectUri() {
        return webServerRedirectUri;
    }

    public void setWebServerRedirectUri(String webServerRedirectUri) {
        this.webServerRedirectUri = webServerRedirectUri;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public Integer getAccessTokenValidity() {
        return accessTokenValidity;
    }

    public void setAccessTokenValidity(Integer accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }

    public Integer getRefreshTokenValidity() {
        return refreshTokenValidity;
    }

    public void setRefreshTokenValidity(Integer refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getAutoapprove() {
        return autoapprove;
    }

    public void setAutoapprove(Boolean autoapprove) {
        this.autoapprove = autoapprove;
    }
}
