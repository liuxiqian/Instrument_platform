package com.mengyunzhi.measurement.jsonView;

/**
 * Created by panjie on 17/7/31.
 * 工作
 */
public class WorkJsonView {
    // 路线1：工作 -> 工作流结点
    // 路线2：工作 -> 申请 -> 申请类型
    // 路线3：工作 -> 申请 -> 申请字段记录 -> 申请字段 -> 工作流结点-申请字段权限 -> 工作流结点
    public interface getById{}
    public interface getAllByApplyId{}
}
