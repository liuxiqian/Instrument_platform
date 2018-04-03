package com.mengyunzhi.measurement.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * Created by panjie on 17/12/16.
 */
@Service
public class FontServiceImpl implements FontService {
    static File songTtcFile;
    @Autowired FileService fileService; // 文件
    @Override
    public File getSongTtc() throws IOException {
        if (songTtcFile == null) {
            songTtcFile = fileService.getResourcesFileByFilename("fonts/simsun.ttc");
        }

        return songTtcFile;
    }
}
