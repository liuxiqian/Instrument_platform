package com.mengyunzhi.measurement.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;

/**
 * Created by chuhang on 17-5-24.
 * 人员资质
 */
public class QualifierRepositoryTest  extends RepositoryTest{
    @Autowired
    private QualifierRepository qualifierRepository;
    static private Logger logger = Logger.getLogger(QualifierRepository.class.getName());

    @Test
    public void save() {
        qualifierRepository.findAll();
    }
}