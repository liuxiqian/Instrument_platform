package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.*;
import org.hibernate.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.expression.AccessException;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by panjie on 17/7/14.
 * 强制检定器具申请审核
 */
public interface MandatoryInstrumentApplyService extends ApplyService {
    String FIELD_CONTACT_NAME = "contactName";     // 联络人字段
    String FIELD_CONTACT_NUMBER = "contactNumber";  // 联络人手机
    String FIELD_SLAVE_CONTACT_NUMBER = "slaveContactNumber";  // 次要联络人手机

    Map<String, Long> tokens = new HashMap<>();

    static MandatoryInstrumentApply getOneMandatoryInstrumentApply() {
        MandatoryInstrumentApply mandatoryInstrumentApply = new MandatoryInstrumentApply();
        mandatoryInstrumentApply.setName("强制检定器具申请测试");
        return mandatoryInstrumentApply;
    }

    static String generateTokenById(Long id) throws Exception {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String token = CommonService.md5(id.toString() + timestamp.toString());
        MandatoryInstrumentApplyService.tokens.put(token, id);
        return token;
    }


    MandatoryInstrumentApply getOneSavedMandatoryInstrumentApply();

    MandatoryInstrumentApply getOneUnsavedMandatoryInstrumentApply();

    static Long getIdByToken(String token) {
        Long id = MandatoryInstrumentApplyService.tokens.get(token);
        return id;
    }

    static void deleteToken(String token) {
        if (null != MandatoryInstrumentApplyService.tokens.get(token)) {
            MandatoryInstrumentApplyService.tokens.remove(token);
        }
    }

    Page<Apply> getPageOfCurrentDepartment(Pageable pageable);

    MandatoryInstrumentApply findById(Long id);

    MandatoryInstrumentApply save(MandatoryInstrumentApply mandatoryInstrumentApply);       // 保存申请

    // 更新
    MandatoryInstrumentApply update(MandatoryInstrumentApply mandatoryInstrumentApply) throws SecurityException, ObjectNotFoundException;

    MandatoryInstrumentApply updateById(Long id, MandatoryInstrumentApply mandatoryInstrumentApply) throws ObjectNotFoundException;

    // 添加强检器具
    void addMandatoryInstrumentOfMandatoryInstrumentApply(MandatoryInstrument mandatoryInstrument, MandatoryInstrumentApply mandatoryInstrumentApply) throws SecurityException;

    void subMandatoryInstrumentOfMandatoryInstrumentApply(MandatoryInstrument mandatoryInstrument, MandatoryInstrumentApply mandatoryInstrumentApply) throws SecurityException;

    void delete(MandatoryInstrumentApply mandatoryInstrumentApply);

    // 当前用户是否可编辑
    boolean isCanEditOfCurrentUser(MandatoryInstrumentApply mandatoryInstrumentApply);

    /**
     * 计算某个部门对某个强检器具申请上的所有强检器具是否具备检定能力
     *
     * @param mandatoryInstrumentApplyId 强检申请id
     * @param departmentId               部门id
     * @return 强检申请
     * @throws Exception
     */
    MandatoryInstrumentApply computeCheckAbilityByMandatoryInstrumentApplyIdAndDepartmentId(Long mandatoryInstrumentApplyId, Long departmentId) throws Exception;

    /**
     * 计算某个部门对某个强检器具申请上的所有强检器具是否具备检定能力
     *
     * @param mandatoryInstrumentApply 强检申请
     * @param department               部门
     * @return
     * @throws Exception
     */
    MandatoryInstrumentApply computeCheckAbilityByMandatoryInstrumentApplyAndDepartment(MandatoryInstrumentApply mandatoryInstrumentApply, Department department) throws Exception;


    /**
     * 通过申请ID生成无检定能力的pdf文档，并返回文档
     *
     * @param id
     * @return
     * @throws IOException panjie
     */
    File generateDoNotHaveCheckAbilityPdfReportByApplyId(Long id) throws IOException, AccessException;

    /**
     * 生成pdf备案报告
     * 指定了检定机构的，说明本区域有检定能力，不加盖电子印章
     *
     * @param id                申请ID
     * @param checkDepartmentId 指定的检定机构ID
     * @return panjie
     */
    File generatePdfReportByApplyIdAndCheckDepartmentId(Long id, Long checkDepartmentId) throws AccessException, IOException;

    /**
     * 是否可以进行pdf申请表导出
     *
     * @param mandatoryInstrumentApply 强检器具申请
     * @return
     */
    Boolean isCanExportPdfOfCurrentUser(MandatoryInstrumentApply mandatoryInstrumentApply);

    /**
     * 第二种统计方法：只统计出状态为通过但未指定检定部门的器具。
     * 分别取出强检申请中的强检器具，并对状态和检定部门进行判断。符合条件的，加入到生成word文档使用的map数据中
     *
     * @param map                      <String, Object>
     * @param mandatoryInstrumentApply 强检器具申请
     * @return
     */
    ArrayList<Object> getTableDataAndSetStatisticsDataWithAlgorithm2ByMandatoryInstruments(Map<String, Object> map, MandatoryInstrumentApply mandatoryInstrumentApply);

    /**
     * 第一种器具分类算法：分辊取出检定技术为：区县技术机构、市技术机构、省技术机构的器具列表。
     * 并计算出统计信息（三种类型的器具分别多少个，起始点为多少，结束点为多少)
     *
     * @param map
     * @param mandatoryInstrumentApply 强检申请
     * @return
     */
    ArrayList<Object> getTableDataAndSetStatisticsDataWithAlgorithm1ByMandatoryInstruments(Map<String, Object> map, MandatoryInstrumentApply mandatoryInstrumentApply);


    // 获取带来各级技术机构对强检器具是否拥有检定能力的强检申请
    MandatoryInstrumentApply getMandatoryInstrumentApplyWithTechnicalInstitutionsAbilityByMandatoryInstrumentApplyId(Long id);

    // 批量通过审核
    void batchPassByMandatoryInstrumentsAndMandatoryInstrumentApplyId(Collection<MandatoryInstrument> mandatoryInstruments, Long mandatoryInstrumentApplyId);

    // 批量退回
    void batchBackByMandatoryInstrumentsAndReasonAndApplyId(Collection<MandatoryInstrument> mandatoryInstruments, String reason, Long applyId);

    // 当申请办结时，触发的动作
    void handleWhenApplyDoneByApplyId(Long applyId) throws AccessException, IOException;

    /**
     * 将序号与其它的数据添加到要输出word的数据列表中
     *
     * @param index               索引
     * @param mandatoryInstrument 强检器具
     * @return
     */
    static String addTableColumnDataByIndexAndMandatoryInstrument(Integer index, MandatoryInstrument mandatoryInstrument) {
        return index.toString() + ";" +
                mandatoryInstrument.getName() + ";" +
                mandatoryInstrument.getSpecificationName() + ";" +
                mandatoryInstrument.getMinMeasureScale().getValue() + ";" +
                mandatoryInstrument.getAccuracy().getValue() + ";" +
                mandatoryInstrument.getGenerativeDepartment().getName() + ";" +
                mandatoryInstrument.getSerialNum() + ";" +
                mandatoryInstrument.getFixSite() + ";" +
                mandatoryInstrument.getInstrumentType().getInstrumentFirstLevelType().getName() + "-" +
                mandatoryInstrument.getInstrumentType().getName() + ";" +
                mandatoryInstrument.getPurpose().getName();
    }

    /**
     * 回复并办结申请
     *
     * @param applyId        申请ID
     * @param replayOpinions 回复意见
     * @Author panjie
     */
    void replyByIdAndReplyOpinions(Long applyId, String replayOpinions);
//
//    /**
//     * 将强检申请办结时，发送办结短信
//     * 先接接，再找出字段来发送
//     *
//     * @param mandatoryInstrumentApply
//     */
//    void setMessageWhenApplyDoneByMandatoryInstrumentApply(MandatoryInstrumentApply mandatoryInstrumentApply) throws IOException;

    // 对申请的强检器具进行分类,分为四类
    // 1：区县级技术机构检定的。
    // 2：市级技术机构检定的。
    // 3：未通过审核并退回的。
    // 4：通过审核需要要省级技术机构提出检定申请的。
//    Map<String, List<MandatoryInstrument>> classificationOfMandatoryInstrument(MandatoryInstrumentApply mandatoryInstrumentApply);

}
