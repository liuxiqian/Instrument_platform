package com.mengyunzhi.measurement.dataInit;

import com.mengyunzhi.measurement.EntityInitDataListener;
import com.mengyunzhi.measurement.entity.ApplyField;
import com.mengyunzhi.measurement.entity.ApplyType;
import com.mengyunzhi.measurement.repository.ApplyFieldRepository;
import com.mengyunzhi.measurement.repository.ApplyTypeRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by panjie on 17/10/12.
 * 申请字段初始化
 */
@Component
public class ApplyFieldDataInit  extends EntityInitDataListener {
    private final Logger logger = Logger.getLogger(ApplyFieldDataInit.class.getName());
    @Autowired private ApplyTypeDataInit applyTypeDataInit; // 申请类型
    @Autowired private ApplyTypeRepository applyTypeRepository;
    @Autowired private ApplyFieldRepository applyFieldRepository;   // 申请字段

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        List<ApplyField> applyFieldList = (List<ApplyField>) applyFieldRepository.findAll();
        if (applyFieldList.size() != 0) {
            return;
        }
        Integer weight = 0;
        ApplyType instrumentForcedApplyType = applyTypeRepository.findOneByName("区县器具用户新增器具");
        List<ApplyField> applyFieldSet = new ArrayList<>();

        logger.info("联络人");
        ApplyField applyField = new ApplyField();
        applyField.setApplyType(instrumentForcedApplyType);
        applyField.setName("name");
        applyField.setLabel("联络人");
        applyField.setDescription("用于与您取得必要的联系");
        applyField.setWeight(weight++);
        applyField.setType(ApplyField.TYPE_TEXT);
        applyField.setRequired(true);
        applyField.setWarnMessage("请输入联络人");
        applyFieldSet.add(applyField);

        logger.info("联络手机(主要)");
        ApplyField masterPhoneApplyField = new ApplyField();
        masterPhoneApplyField.setApplyType(instrumentForcedApplyType);
        masterPhoneApplyField.setName("contactNumber");
        masterPhoneApplyField.setLabel("联络手机(主要)");
        masterPhoneApplyField.setDescription("用于接收系统短信提醒（重要!）");
        masterPhoneApplyField.setWeight(weight++);
        masterPhoneApplyField.setType(ApplyField.TYPE_TEXT);
        masterPhoneApplyField.setRequired(true);
        masterPhoneApplyField.setWarnMessage("请输入主要联络手机");
        applyFieldSet.add(masterPhoneApplyField);

        logger.info("联络手机(备用)");
        ApplyField slavePhoneApplyField = new ApplyField();
        slavePhoneApplyField.setApplyType(instrumentForcedApplyType);
        slavePhoneApplyField.setName("slaveContactNumber");
        slavePhoneApplyField.setLabel("联络手机(备用)");
        slavePhoneApplyField.setDescription("当主要联络方式联系不上您时可能会使用");
        slavePhoneApplyField.setWeight(weight++);
        slavePhoneApplyField.setType(ApplyField.TYPE_TEXT);
        slavePhoneApplyField.setRequired(true);
        slavePhoneApplyField.setWarnMessage("请输入备用联络手机");
        applyFieldSet.add(slavePhoneApplyField);

        logger.info("增加 过期未检器具检定申请 表单信息");
        ApplyType timeoutCheckApplyType = applyTypeRepository.findOneByName("过期未检器具检定申请");
        ApplyField tContactNameApplyFile = new ApplyField();
        tContactNameApplyFile.setName("contactName");
        tContactNameApplyFile.setLabel("联系人");
        tContactNameApplyFile.setDescription("用于与您取得联系");
        tContactNameApplyFile.setWeight(weight++);
        tContactNameApplyFile.setType(ApplyField.TYPE_TEXT);
        tContactNameApplyFile.setRequired(true);
        tContactNameApplyFile.setWarnMessage("请输入联系人信息");
        tContactNameApplyFile.setApplyType(timeoutCheckApplyType);
        applyFieldSet.add(tContactNameApplyFile);

        ApplyField tReasonApplyFile = new ApplyField();
        tReasonApplyFile.setName("reason");
        tReasonApplyFile.setLabel("延期检定原因");
        tReasonApplyFile.setDescription("描述您延期检测的原因");
        tReasonApplyFile.setWeight(weight++);
        tReasonApplyFile.setType(ApplyField.TYPE_TEXTAREA);
        tReasonApplyFile.setRequired(false);
        tReasonApplyFile.setWarnMessage("请详细描述延期检测的原因");
        tReasonApplyFile.setApplyType(timeoutCheckApplyType);
        applyFieldSet.add(tReasonApplyFile);

        ApplyField tImagesApplyFile = new ApplyField();
        tImagesApplyFile.setName("images");
        tImagesApplyFile.setLabel("佐证图片");
        tImagesApplyFile.setDescription("佐证图片");
        tImagesApplyFile.setWeight(weight++);
        tImagesApplyFile.setType(ApplyField.TYPE_IMAGES);
        tImagesApplyFile.setRequired(false);
        tImagesApplyFile.setWarnMessage("如有必要，请您上传相关佐证图片");
        tImagesApplyFile.setApplyType(timeoutCheckApplyType);
        applyFieldSet.add(tImagesApplyFile);

        applyFieldRepository.save(applyFieldSet);
    }
    /**
     * 申请类型数据独立
     * @return
     */
    @Override
    public int getOrder() {
        // 基于申请类型
        return applyTypeDataInit.getOrder() + 10;
    }
}
