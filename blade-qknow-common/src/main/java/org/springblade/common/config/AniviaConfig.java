package org.springblade.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 文件上传配置
 * @author SpringBlade
 */
@Component
public class AniviaConfig {
    
    @Value("${spring.servlet.multipart.max-file-size:50MB}")
    private String maxFileSize;
    
    @Value("${spring.servlet.multipart.max-request-size:100MB}")
    private String maxRequestSize;
    
    @Value("${blade.upload.path:${user.home}/blade/upload}")
    private String uploadPath;
    
    @Value("${blade.address.enabled:false}")
    private static boolean addressEnabled;
    
    @Value("${blade.import.path:${user.home}/blade/import}")
    private static String importPath;
    
    /**
     * 获取文件上传路径
     */
    public static String getProfile() {
        // 这里可以根据需要从配置文件或环境变量获取
        return System.getProperty("user.home") + "/blade/upload";
    }
    
    /**
     * 是否启用地址解析
     */
    public static boolean isAddressEnabled() {
        return addressEnabled;
    }
    
    /**
     * 获取导入路径
     */
    public static String getImportPath() {
        return importPath;
    }
    
    public String getMaxFileSize() {
        return maxFileSize;
    }
    
    public void setMaxFileSize(String maxFileSize) {
        this.maxFileSize = maxFileSize;
    }
    
    public String getMaxRequestSize() {
        return maxRequestSize;
    }
    
    public void setMaxRequestSize(String maxRequestSize) {
        this.maxRequestSize = maxRequestSize;
    }
    
    public String getUploadPath() {
        return uploadPath;
    }
    
    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }
    
    public static void setAddressEnabled(boolean addressEnabled) {
        AniviaConfig.addressEnabled = addressEnabled;
    }
    
    public static void setImportPath(String importPath) {
        AniviaConfig.importPath = importPath;
    }
}
