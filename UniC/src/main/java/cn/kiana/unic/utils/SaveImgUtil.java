package cn.kiana.unic.utils;

import cn.hutool.core.io.FileUtil;
import cn.kiana.unic.pojo.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import static cn.kiana.unic.utils.UUIDUtils.getUUID;

public class SaveImgUtil {
    public String fileName;

    private static final String ROOT_PATH =  System.getProperty("user.dir")+ File.separator +"images";

    public boolean saveImg(MultipartFile file) throws IOException {
        if (file == null) {
            return false;
        }
        String originalFilename = file.getOriginalFilename();
        String ext = FileUtil.extName(originalFilename);

        if (!FileUtil.exist(ROOT_PATH)) {
            FileUtil.mkdir(ROOT_PATH); //当前文件的父级目录不存在则创建
        }

        String newFileName;
        do {
            newFileName = getUUID() + "." + ext;
        } while (FileUtil.exist(ROOT_PATH + File.separator + newFileName));

        File saveFile = new File(ROOT_PATH + File.separator + newFileName);
        file.transferTo(saveFile);

        this.fileName = newFileName;
        return true;
    }

    // 删除图片的函数
    public boolean deleteImg(String imgName) {
        if (imgName == null) {
            return false;
        }
        File file = new File(ROOT_PATH + File.separator + imgName);
        if (file.exists()) {
            return file.delete();
        }
        return true; // 如果文件不存在，也可以认为删除成功（因为没有需要删除的内容）
    }
}
