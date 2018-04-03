package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.ApplyField;
import com.mengyunzhi.measurement.repository.ApplyFieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplyFieldServiceImpl implements ApplyFieldService {
    @Autowired
    ApplyFieldRepository applyFieldRepository;
    @Autowired ApplyTypeService applyTypeService;


    @Override
    public ApplyField getOneApplyField() {
        ApplyField applyField = new ApplyField();
        applyField.setName(CommonService.getRandomStringByLength(20));
        applyField.setApplyType(applyTypeService.getOneSavedApplyType());
        return applyField;
    }

    @Override
    public ApplyField getOneSavedApplyField() {
        ApplyField applyField = this.getOneApplyField();
        applyFieldRepository.save(applyField);
        return applyField;
    }

    @Override
    public ApplyField findById(Long id) {
        return applyFieldRepository.findOne(id);
    }

    @Override
    public ApplyField save(ApplyField applyField){ return applyFieldRepository.save(applyField); }

    @Override
    public void delete(Long id)
    {
        applyFieldRepository.delete(id);
        return;
    }

    @Override
    public void update(Long id, ApplyField applyField){
        applyField.setId(id);
        applyFieldRepository.save(applyField);
        return;
    }

    @Override
    public List<ApplyField> getAll(){
        List<ApplyField> applyFields = (List<ApplyField>) applyFieldRepository.findAll();
        return applyFields;
    }

    @Override
    public List<ApplyField> getAllByApplyTypeId(Long applyTypeId){
        List<ApplyField> applyFields = (List<ApplyField>) applyFieldRepository.findAllByApplyTypeId(applyTypeId);
        return applyFields;
    }
}
