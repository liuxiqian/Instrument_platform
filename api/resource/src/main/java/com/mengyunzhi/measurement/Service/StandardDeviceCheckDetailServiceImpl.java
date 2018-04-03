package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.StandardDevice;
import com.mengyunzhi.measurement.entity.StandardDeviceCheckDetail;
import com.mengyunzhi.measurement.repository.StandardDeviceCheckDetailRepository;

import org.hibernate.ObjectNotFoundException;
import com.mengyunzhi.measurement.repository.StandardDeviceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liming on 17-5-14.
 */
@Service
public class StandardDeviceCheckDetailServiceImpl implements StandardDeviceCheckDetailService {
    @Autowired
    private StandardDeviceCheckDetailRepository standardDeviceCheckDetailRepository;
    @Autowired
    protected StandardDeviceRepository standardDeviceRepository;
    @Autowired
    private StandardDeviceService standardDeviceService;
    @Override
    public StandardDeviceCheckDetail save(StandardDeviceCheckDetail standardDeviceCheckDetail) {
        //保存
        standardDeviceCheckDetailRepository.save(standardDeviceCheckDetail);

        // 同步修改标准器实体的最近检定记录
        standardDeviceService.updateLastCheckDetailByStandardDeviceCheckDetail(standardDeviceCheckDetail);

        //返回
        return standardDeviceCheckDetail;
    }

    @Override
    public List<StandardDeviceCheckDetail> getAllByStandardDevice(StandardDevice standardDevice) {
        List<StandardDeviceCheckDetail> list = new ArrayList<StandardDeviceCheckDetail>();
        //根据standardDevice获取所有的数据
        list = standardDeviceCheckDetailRepository.findAllByStandardDevice(standardDevice);
        //返回数据
        return list;
    }

    public StandardDeviceCheckDetail update(StandardDeviceCheckDetail standardDeviceCheckDetail,Long id) {
        if (null == standardDeviceCheckDetailRepository.findOne(id)) {
            throw new ObjectNotFoundException(404, "id为" + id.toString() + "的StandardDeviceCheckDetal实体未找到");
        }

        StandardDeviceCheckDetail oldStandardDeviceCheckDetail1 = standardDeviceCheckDetailRepository.findOne(id);
        oldStandardDeviceCheckDetail1.setAlertDate(standardDeviceCheckDetail.getAlertDate());
        oldStandardDeviceCheckDetail1.setCalibrationDepartment(standardDeviceCheckDetail.getCalibrationDepartment());
        oldStandardDeviceCheckDetail1.setCertificateNum(standardDeviceCheckDetail.getCertificateNum());
        oldStandardDeviceCheckDetail1.setCheckDate(standardDeviceCheckDetail.getCheckDate());
        oldStandardDeviceCheckDetail1.setCheckResult(standardDeviceCheckDetail.getCheckResult());
        oldStandardDeviceCheckDetail1.setCorrectValue(standardDeviceCheckDetail.getCorrectValue());
        oldStandardDeviceCheckDetail1.setCreateTime(standardDeviceCheckDetail.getCreateTime());
        oldStandardDeviceCheckDetail1.setCreateUser(standardDeviceCheckDetail.getCreateUser());
        oldStandardDeviceCheckDetail1.setInspectorQualifierCertificate(standardDeviceCheckDetail.getInspectorQualifierCertificate());
        oldStandardDeviceCheckDetail1.setStandardDevice(standardDeviceCheckDetail.getStandardDevice());
        oldStandardDeviceCheckDetail1.setUpdateTime(standardDeviceCheckDetail.getUpdateTime());
        oldStandardDeviceCheckDetail1.setValidityDate(standardDeviceCheckDetail.getValidityDate());
        oldStandardDeviceCheckDetail1.setVerifierQualifierCertificate(standardDeviceCheckDetail.getVerifierQualifierCertificate());
        return standardDeviceCheckDetailRepository.save(oldStandardDeviceCheckDetail1);
    }

    @Override
    public void delete(Long id) {
        StandardDeviceCheckDetail standardDeviceCheckDetail =standardDeviceCheckDetailRepository.findOne(id);
        if(standardDeviceCheckDetail==null){
            throw new ObjectNotFoundException(404, "要删除的实体不存在");
        }else {
            standardDeviceCheckDetailRepository.delete(id);
        }
    }


    @Override
    public Page<StandardDeviceCheckDetail> pageAllByStandardDeviceId(Long standardDeviceId, Pageable pageable) {
        Page<StandardDeviceCheckDetail> standardDeviceCheckDetails = standardDeviceCheckDetailRepository.findAllByStandardDevice(standardDeviceRepository.findOne(standardDeviceId), pageable);
        return standardDeviceCheckDetails;
    }

    @Override
    public StandardDeviceCheckDetail findOneById(Long id) {
        return standardDeviceCheckDetailRepository.findOne(id);
    }
}
