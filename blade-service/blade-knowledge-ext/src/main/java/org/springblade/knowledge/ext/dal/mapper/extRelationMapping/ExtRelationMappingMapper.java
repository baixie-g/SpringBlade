package org.springblade.knowledge.ext.dal.mapper.extRelationMapping;

import org.springblade.common.core.page.PageResult;
import org.springblade.knowledge.ext.admin.extRelationMapping.vo.ExtRelationMappingPageReqVO;
import org.springblade.knowledge.ext.dal.dataobject.extRelationMapping.ExtRelationMappingDO;
import org.springblade.mybatis.core.mapper.BaseMapperX;
import org.springblade.mybatis.core.query.LambdaQueryWrapperX;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 关系映射Mapper接口
 *
 * @author qknow
 * @date 2025-02-25
 */
public interface ExtRelationMappingMapper extends BaseMapperX<ExtRelationMappingDO> {

    default PageResult<ExtRelationMappingDO> selectPage(ExtRelationMappingPageReqVO reqVO) {
        // 定义排序的字段（防止 SQL 注入，与数据库字段名称一致）
        Set<String> allowedColumns = new HashSet<>(Arrays.asList("id", "create_time", "update_time"));

        // 构造动态查询条件
        return selectPage(reqVO, new LambdaQueryWrapperX<ExtRelationMappingDO>()
                .eqIfPresent(ExtRelationMappingDO::getWorkspaceId, reqVO.getWorkspaceId())
                .eqIfPresent(ExtRelationMappingDO::getTaskId, reqVO.getTaskId())
                .likeIfPresent(ExtRelationMappingDO::getTableName, reqVO.getTableName())
                .eqIfPresent(ExtRelationMappingDO::getTableComment, reqVO.getTableComment())
                .likeIfPresent(ExtRelationMappingDO::getFieldName, reqVO.getFieldName())
                .eqIfPresent(ExtRelationMappingDO::getFieldComment, reqVO.getFieldComment())
                .eqIfPresent(ExtRelationMappingDO::getRelation, reqVO.getRelation())
                .eqIfPresent(ExtRelationMappingDO::getRelationTable, reqVO.getRelationTable())
                .eqIfPresent(ExtRelationMappingDO::getRelationField, reqVO.getRelationField())
                .eqIfPresent(ExtRelationMappingDO::getCreateTime, reqVO.getCreateTime())
                // 如果 reqVO.getName() 不为空，则添加 name 的精确匹配条件（name = '<name>'）
                // .likeIfPresent(ExtRelationMappingDO::getName, reqVO.getName())
                // 按照 createTime 字段降序排序
                .orderBy(reqVO.getOrderByColumn(), reqVO.getIsAsc(), allowedColumns));
    }
}
