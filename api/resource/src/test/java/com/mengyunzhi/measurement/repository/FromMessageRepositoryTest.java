package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.FromMessage;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created By chuhang on 17-10-17
 * 发送消息 实体仓库测试
 */
public class FromMessageRepositoryTest extends RepositoryTest {
    static private Logger logger = Logger.getLogger(FromMessageRepositoryTest.class.getName());

    @Autowired
    private FromMessageRepository fromMessageRepository;

    @Test
    public void save() throws Exception {
        logger.info("新建一个实体");
        FromMessage fromMessage = new FromMessage();
        fromMessage.setContent("test");
        fromMessageRepository.save(fromMessage);

        logger.info("断言实体成功建立");
        assertThat(fromMessageRepository.findOne(fromMessage.getId())).isNotNull();
    }

}