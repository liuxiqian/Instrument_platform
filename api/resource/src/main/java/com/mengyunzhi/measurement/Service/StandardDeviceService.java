package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.StandardDevice;
import com.mengyunzhi.measurement.entity.StandardDeviceCheckDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by liming on 17-5-9.
 */
public interface StandardDeviceService {
    //保存
    StandardDevice save(StandardDevice standardDevice);
    //查找一个StandardDevice
    StandardDevice findOne(Long id);

    // 获取一个持久化的对象
    StandardDevice getOneSavedStandardDevice();

    //根据standardFileId获取相应的标准器
    List<StandardDevice> getAllByDeviceSetId(Long deviceSetId);
    //更新标准器
    StandardDevice update(Long id, StandardDevice standardDevice);
    //删除标准器
    void delete(Long id);
    //根据standardFileId获取相应的标准器分页
    Page<StandardDevice> pageAllByDeviceSetId(Long deviceSetId, Pageable pageable);

    void updateLastCheckDetailByStandardDeviceCheckDetail(StandardDeviceCheckDetail standardDeviceCheckDetail);

    // 获取当前部门的标准器预警
    Page<StandardDevice> pageAllWarnStandardDevice(Pageable pageable);
    // 获取当前部门的标准器报警
    Page<StandardDevice> pageAllAlarmStandardDevice(Pageable pageable);

    StandardDevice getOneUnSavedStandardDevice();

}
