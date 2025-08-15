package org.springblade.mybatis.core.util;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void insertFill(MetaObject metaObject) {
        // 检查是否有名为createTime的字段，如果有则自动填充当前时间
        boolean hasCreateTime = metaObject.hasSetter("createTime");
        if (hasCreateTime) {
            metaObject.setValue("createTime", new Date());
        }
        // 检查是否有名为updateTime的字段，如果有则自动填充当前时间 部分表设置的updateTime不能为空
        boolean hasUpdateTime = metaObject.hasSetter("updateTime");
        if (hasUpdateTime) {
            metaObject.setValue("updateTime", new Date());
        }
        boolean hasCreatorId = metaObject.hasSetter("creatorId");
        boolean hasCreateBy = metaObject.hasSetter("createBy");
        boolean hasUpdatorId = metaObject.hasSetter("updatorId");
        boolean HasUpdateBy = metaObject.hasSetter("updateBy");
        
        try {
            // 先检查 Authentication 是否为 null
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.getPrincipal() != null) {
                Object principal = authentication.getPrincipal();
                if (principal instanceof org.springframework.security.core.userdetails.UserDetails) {
                    org.springframework.security.core.userdetails.UserDetails userDetails = 
                        (org.springframework.security.core.userdetails.UserDetails) principal;
                    
                    if (hasCreatorId) {
                        metaObject.setValue("creatorId", userDetails.getUsername());
                    }
                    if (hasCreateBy) {
                        metaObject.setValue("createBy", userDetails.getUsername());
                    }
                    if (hasUpdatorId) {
                        metaObject.setValue("updatorId", userDetails.getUsername());
                    }
                    if (HasUpdateBy) {
                        metaObject.setValue("updateBy", userDetails.getUsername());
                    }
                }
            }
        } catch (Exception e) {
            logger.info("获取用户信息异常:{}", e.getMessage());
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 检查是否有名为updateTime的字段，如果有则自动填充当前时间
        boolean hasUpdateTime = metaObject.hasSetter("updateTime");
        if (hasUpdateTime) {
            metaObject.setValue("updateTime", new Date());
        }

        boolean hasUpdatorId = metaObject.hasSetter("updatorId");
        boolean HasUpdateBy = metaObject.hasSetter("updateBy");

        try {
            // 先检查 Authentication 是否为 null
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.getPrincipal() != null) {
                Object principal = authentication.getPrincipal();
                if (principal instanceof org.springframework.security.core.userdetails.UserDetails) {
                    org.springframework.security.core.userdetails.UserDetails userDetails = 
                        (org.springframework.security.core.userdetails.UserDetails) principal;
                    
                    if (hasUpdatorId) {
                        metaObject.setValue("updatorId", userDetails.getUsername());
                    }
                    if (HasUpdateBy) {
                        metaObject.setValue("updateBy", userDetails.getUsername());
                    }
                }
            }
        } catch (Exception e) {
            logger.info("获取用户信息异常:{}", e.getMessage());
        }
    }
}
