package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.ToMessage;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created By chuhang on 17-10-17
 */
public class ToMessageRepositoryTest extends RepositoryTest {
    static private Logger logger = Logger.getLogger(ToMessageRepositoryTest.class.getName());

    @Autowired
    private ToMessageRepository toMessageRepository;

    @Test
    public void save() throws Exception {
        logger.info("新建一个实体");
        ToMessage toMessage = new ToMessage();
        toMessage.setContent("test");
        toMessageRepository.save(toMessage);

        logger.info("断言实体成功建立");
        assertThat(toMessageRepository.findOne(toMessage.getId())).isNotNull();
    }

}