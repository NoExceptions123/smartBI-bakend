package com.lft.smart_bi.model.dto.file;

import java.io.Serializable;
import lombok.Data;

/**
*@Pakage com.lft.smart_bi.model.dto.file
*@auther 邮专第一深情
*@date 2023/7/8
*@Description   文件上传请求
*/
@Data
public class UploadFileRequest implements Serializable {

    /**
     * 业务
     */
    private String biz;

    private static final long serialVersionUID = 1L;
}