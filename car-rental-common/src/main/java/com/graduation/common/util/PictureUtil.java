package com.graduation.common.util;

import com.graduation.common.constant.CategoryConstant;
import com.graduation.common.constant.CommonConstant;
import org.apache.commons.lang3.tuple.MutablePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
     * @return org.apache.commons.lang3.tuple.MutablePair<java.lang.String,java.lang.String>
     * @Author fuxiaoxiang2
     * @Description 上传类目时，存储图片
     * @Date 18:06 2019/1/9
     * @Param [files, categoryId]
     **/
    public static MutablePair<String, String> parseAndSavePic(MultipartFile[] files, Long categoryId) {
        MutablePair<String, String> imgAddress = new MutablePair<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < files.length; ++i) {
            MultipartFile file = files[i];
            String prefix = categoryId + "(" + i + ")";
            File filePath = new File(CategoryConstant.CATEGORY_IMG_HOME + prefix + file.getOriginalFilename());
            if (!filePath.exists()) {
                try {
                    if (i == 0) {
                        imgAddress.setLeft(filePath.getAbsolutePath());
                    } else {
                        sb.append(filePath.getAbsolutePath());
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

    public static List<String> parseAndSaveBanner(MultipartFile[] files) {
        List<String> addressList = new ArrayList<>();
        for (int i = 0; i < files.length; ++i) {
            MultipartFile file = files[i];
            String prefix = "banner" + "(" + i + ")";
            File filePath = new File(CommonConstant.BANNER_IMG_HOME + prefix + file.getOriginalFilename());
            if (!filePath.exists()) {
                try {
                    file.transferTo(filePath);
                    addressList.add(filePath.getAbsolutePath());
                } catch (Exception e) {
                    logger.error("save bannerFile,error", e);
                    e.printStackTrace();
                }
            }
        }
        return addressList;
    }
}
