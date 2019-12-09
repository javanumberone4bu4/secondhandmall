package com.rimi.secondhandtradingmall.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * @author shangzf
 * @date 2019/10/30 11:38
 */
public class
FileUtils {
    /**
     * 文件存放的地址
     */
    public static final String FILE_PATH = "/download/image/";

    /**
     * 上传文件,返回文件的地址
     *
     * @param file
     * @param request
     * @return
     */
    public static String uploadFile(MultipartFile file, HttpServletRequest request) {
        if (file != null) {
            String realPath = getRealPath(request);
            createFileDirectory(realPath);
            String fileName = getNewFileName(file);
            try {
                // 保存
                file.transferTo(new File(realPath + File.separator + fileName));
                return FILE_PATH + fileName;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String getNewFileName(MultipartFile file) {
        // 重新命名
        String fileName = System.currentTimeMillis() + "";
        // 获取文件的后缀名
        String suffix = getFileSuffix(Objects.requireNonNull(file.getOriginalFilename()));
        // 新文件名
        return fileName + "." + suffix;
    }

    public static void createFileDirectory(String realPath) {
        // 判断该文件是否存在
        File filePath = new File(realPath);
        if (!(filePath.isDirectory() && filePath.exists())) {
            filePath.mkdirs();
        }
    }

    public static String getRealPath(HttpServletRequest request) {
        // 获取文件文件的位置
        return request.getServletContext().getRealPath(FILE_PATH);
    }

    public static String getFileSuffix(String originalFilename) {
        // 获取文件的后缀   xx.png
        int index = originalFilename.lastIndexOf(".");
        return originalFilename.substring(index + 1);
    }
}
