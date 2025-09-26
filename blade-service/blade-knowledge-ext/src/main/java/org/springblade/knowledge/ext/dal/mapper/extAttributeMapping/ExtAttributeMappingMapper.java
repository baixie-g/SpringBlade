package org.springblade.knowledge.ext.dal.mapper.extAttributeMapping;

import org.springblade.common.core.page.PageResult;
import org.springblade.knowledge.ext.admin.extAttributeMapping.vo.ExtAttributeMappingPageReqVO;
import org.springblade.knowledge.ext.dal.dataobject.extAttributeMapping.ExtAttributeMappingDO;
import org.springblade.mybatis.core.mapper.BaseMapperX;
import org.springblade.mybatis.core.query.LambdaQueryWrapperX;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 属性映射Mapper接口
 *
 * @author qknow
 * @date 2025-02-25
 */
public interface ExtAttributeMappingMapper extends BaseMapperX<ExtAttributeMappingDO> {

    default PageResult<ExtAttributeMappingDO> selectPage(ExtAttributeMappingPageReqVO reqVO) {
        // 定义排序的字段（防止 SQL 注入，与数据库字段名称一致）
        Set<String> allowedColumns = new HashSet<>(Arrays.asList("id", "create_time", "update_time"));

        // 构造动态查询条件
        return selectPage(reqVO, new LambdaQueryWrapperX<ExtAttributeMappingDO>()
                .eqIfPresent(ExtAttributeMappingDO::getWorkspaceId, reqVO.getWorkspaceId())
                .eqIfPresent(ExtAttributeMappingDO::getTaskId, reqVO.getTaskId())
                .likeIfPresent(ExtAttributeMappingDO::getTableName, reqVO.getTableName())
                .eqIfPresent(ExtAttributeMappingDO::getTableComment, reqVO.getTableComment())
                .likeIfPresent(ExtAttributeMappingDO::getFieldName, reqVO.getFieldName())
                .eqIfPresent(ExtAttributeMappingDO::getFieldComment, reqVO.getFieldComment())
                .eqIfPresent(ExtAttributeMappingDO::getAttributeId, reqVO.getAttributeId())
                .likeIfPresent(ExtAttributeMappingDO::getAttributeName, reqVO.getAttributeName())
                .eqIfPresent(ExtAttributeMappingDO::getCreateTime, reqVO.getCreateTime())
                // 如果 reqVO.getName() 不为空，则添加 name 的精确匹配条件（name = '<name>'）
                // .likeIfPresent(ExtAttributeMappingDO::getName, reqVO.getName())
                // 按照 createTime 字段降序排序
                .orderBy(reqVO.getOrderByColumn(), reqVO.getIsAsc(), allowedColumns));
    }
}
