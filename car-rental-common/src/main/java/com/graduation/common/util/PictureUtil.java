package com.graduation.common.util;

import com.graduation.common.constant.CategoryConstant;
import org.apache.commons.lang3.tuple.MutablePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption 图片处理工具
 * @Author fuxiaoxiang2
 * @Create 2019/1/9 18:05
 */
public class PictureUtil {
    private static final Logger logger = LoggerFactory.getLogger(PictureUtil.class);

    private PictureUtil() {

    }
    /**
     * @Author fuxiaoxiang2
     * @Description 上传类目时，存储图片
     * @Date 18:06 2019/1/9
     * @Param [files, categoryId]
     * @return org.apache.commons.lang3.tuple.MutablePair<java.lang.String,java.lang.String>
     **/
    public static MutablePair<String, String> parseAndSavePic(MultipartFile[] files, Long categoryId) {
        MutablePair<String, String> imgAddress = new MutablePair<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < files.length; ++i) {
            MultipartFile file = files[i];
            String prefix = categoryId + "(" + i + ")";
            File filePath = new File(CategoryConstant.IMG_HOME + prefix);
            if (!filePath.exists()) {
                try {
                    if (i == 0) {
                        imgAddress.setLeft(filePath.getName());
                    } else {
                        sb.append(filePath.getName());
                        if (i == files.length - 1) {
                            imgAddress.setRight(sb.toString());
                        } else {
                            sb.append("&");
                        }
                    }
                    file.transferTo(filePath);
                } catch (Exception e) {
                    logger.error("save file:{} error", categoryId);
                    e.printStackTrace();
                }
            }
        }
        return imgAddress;
    }
}
