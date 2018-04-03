package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.Accuracy;
import com.mengyunzhi.measurement.entity.InstrumentType;
import com.mengyunzhi.measurement.repository.AccuracyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by panjie on 17/6/28.
 * 精度
 */
@Service
public class AccuracyServiceImpl implements AccuracyService {
    @Autowired
    private AccuracyRepository accuracyRepository; // 精度

    @Override
    public Accuracy save(Accuracy accuracy) {
        return accuracyRepository.save(accuracy);
    }

    @Override
    public Accuracy getOneSavedAccuracy() {
        Accuracy accuracy = new Accuracy();
        accuracy.setValue("测试精度度" + CommonService.getRandomStringByLength(10));
        return accuracyRepository.save(accuracy);
    }

    @Override
    public void deleteById(Long id) {
        accuracyRepository.delete(id);
        return;
    }

    @Override
    public Iterable<Accuracy> getAllByInstrumentTypeId(Long instrumentTypeId) {
        InstrumentType instrumentType = new InstrumentType();
        instrumentType.setId(instrumentTypeId);
        return accuracyRepository.findAllByInstrumentType(instrumentType);
    }
}
