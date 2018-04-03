package com.mengyunzhi.measurement.Service;

import java.io.File;
import java.io.IOException;

/**
 * Created by panjie on 17/12/16.
 */
public interface FontService {
    // 获取宋体的TTC文件
    File getSongTtc() throws IOException;
}
