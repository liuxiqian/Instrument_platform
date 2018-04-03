package com.mengyunzhi.measurement.entity;

/**
 * Created by panjie on 17/6/27.
 * JsonView进行配置
 */
public class JsonViewConfig {
    public interface WithAll {}
    public interface WithOutParentDistrict  extends WithAll {}
    public interface WithOutParentCheckResult extends WithAll {}
}
