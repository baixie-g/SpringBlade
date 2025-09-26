package org.springblade.knowledge.ext.dal.mapper.extSchemaMapping;

import org.springblade.common.core.page.PageResult;
import org.springblade.knowledge.ext.admin.extSchemaMapping.vo.ExtSchemaMappingPageReqVO;
import org.springblade.knowledge.ext.dal.dataobject.extSchemaMapping.ExtSchemaMappingDO;
import org.springblade.mybatis.core.mapper.BaseMapperX;
import org.springblade.mybatis.core.query.LambdaQueryWrapperX;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 概念映射Mapper接口
 *
 * @author qknow
 * @date 2025-02-25
 */
public interface ExtSchemaMappingMapper extends BaseMapperX<ExtSchemaMappingDO> {

    default PageResult<ExtSchemaMappingDO> selectPage(ExtSchemaMappingPageReqVO reqVO) {
        // 定义排序的字段（防止 SQL 注入，与数据库字段名称一致）
        Set<String> allowedColumns = new HashSet<>(Arrays.asList("id", "create_time", "update_time"));

        // 构造动态查询条件
        return selectPage(reqVO, new LambdaQueryWrapperX<ExtSchemaMappingDO>()
                .eqIfPresent(ExtSchemaMappingDO::getWorkspaceId, reqVO.getWorkspaceId())
                .eqIfPresent(ExtSchemaMappingDO::getTaskId, reqVO.getTaskId())
                .likeIfPresent(ExtSchemaMappingDO::getTableName, reqVO.getTableName())
                .eqIfPresent(ExtSchemaMappingDO::getTableComment, reqVO.getTableComment())
                .eqIfPresent(ExtSchemaMappingDO::getSchemaId, reqVO.getSchemaId())
                .likeIfPresent(ExtSchemaMappingDO::getSchemaName, reqVO.getSchemaName())
                .eqIfPresent(ExtSchemaMappingDO::getCreateTime, reqVO.getCreateTime())
                // 如果 reqVO.getName() 不为空，则添加 name 的精确匹配条件（name = '<name>'）
                // .likeIfPresent(ExtSchemaMappingDO::getName, reqVO.getName())
                // 按照 createTime 字段降序排序
                .orderBy(reqVO.getOrderByColumn(), reqVO.getIsAsc(), allowedColumns));
    }
}
