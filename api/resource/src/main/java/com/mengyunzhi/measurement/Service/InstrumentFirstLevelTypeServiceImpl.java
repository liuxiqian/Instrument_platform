package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.Discipline;
import com.mengyunzhi.measurement.entity.InstrumentFirstLevelType;
import com.mengyunzhi.measurement.repository.InstrumentFirstLevelTypeRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by panjie on 17/7/26.
 * 一级器具类别
 */
@Service
public class InstrumentFirstLevelTypeServiceImpl implements InstrumentFirstLevelTypeService {
    private Logger logger = Logger.getLogger(InstrumentFirstLevelTypeServiceImpl.class.getName());

    @Autowired
    DisciplineService disciplineService; // 学科类别
    @Autowired
    InstrumentFirstLevelTypeRepository instrumentFirstLevelTypeRepository;  // 一级器具类别

    @Override
    public List<InstrumentFirstLevelType> getAllByDisciplineId(Long disciplineId) {
        return instrumentFirstLevelTypeRepository.findAllByDisciplineId(disciplineId);
    }

    @Override
    public InstrumentFirstLevelType save(InstrumentFirstLevelType instrumentFirstLevelType) {
        return instrumentFirstLevelTypeRepository.save(instrumentFirstLevelType);
    }

    @Override
    public void delete(Long id) {
        instrumentFirstLevelTypeRepository.delete(id);
        return;
    }

    // 更新：只更新 名称、拼音 两个字段
    @Override
    public void update(Long id, InstrumentFirstLevelType instrumentFirstLevelType) throws ObjectNotFoundException {
        logger.info("检查所要更新的实体是否存在");
        InstrumentFirstLevelType instrumentFirstLevelType1 = instrumentFirstLevelTypeRepository.findOne(id);
        if (instrumentFirstLevelType1 == null) {
            throw new ObjectNotFoundException(404, "所需要查找的实体不存在");
        }
        instrumentFirstLevelType1.setName(instrumentFirstLevelType.getName());
        instrumentFirstLevelType1.setPinyin(instrumentFirstLevelType.getPinyin());
        instrumentFirstLevelTypeRepository.save(instrumentFirstLevelType1);
        return;
    }

    @Override
    public InstrumentFirstLevelType get(Long id) {
        return instrumentFirstLevelTypeRepository.findOne(id);
    }


    @Override
    public InstrumentFirstLevelType getOneSavedInstrumentFirstLevelType() {
        Discipline discipline = disciplineService.getOneSavedDiscipline();
        InstrumentFirstLevelType instrumentFirstLevelType = new InstrumentFirstLevelType();
        instrumentFirstLevelType.setDiscipline(discipline);
        instrumentFirstLevelTypeRepository.save(instrumentFirstLevelType);
        return instrumentFirstLevelType;
    }
}
