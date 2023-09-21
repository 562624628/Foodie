package com.imooc.resource;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "file")
@PropertySource("classpath:file-upload-prd.properties")
@Data
public class FileUpload {
    private String imgeUserFaceLocaltion;
    private String imageServerUrl;
}
