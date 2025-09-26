package org.springblade.knowledge.ext.dal.mapper.extSchemaRelation;

import org.springblade.common.core.page.PageResult;
import org.springblade.knowledge.ext.admin.extSchemaRelation.vo.ExtSchemaRelationPageReqVO;
import org.springblade.knowledge.ext.dal.dataobject.extSchemaRelation.ExtSchemaRelationDO;
import org.springblade.mybatis.core.mapper.BaseMapperX;
import org.springblade.mybatis.core.query.LambdaQueryWrapperX;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 关系配置Mapper接口
 *
 * @author qknow
 * @date 2025-02-18
 */
public interface ExtSchemaRelationMapper extends BaseMapperX<ExtSchemaRelationDO> {

    default PageResult<ExtSchemaRelationDO> selectPage(ExtSchemaRelationPageReqVO reqVO) {
        // 定义排序的字段（防止 SQL 注入，与数据库字段名称一致）
        Set<String> allowedColumns = new HashSet<>(Arrays.asList("id", "create_time", "update_time"));

        // 构造动态查询条件
        LambdaQueryWrapperX<ExtSchemaRelationDO> queryWrapper = new LambdaQueryWrapperX<>();

        // 普通的条件
        queryWrapper.eqIfPresent(ExtSchemaRelationDO::getWorkspaceId, reqVO.getWorkspaceId())
                .eqIfPresent(ExtSchemaRelationDO::getStartSchemaId, reqVO.getStartSchemaId())
                .eqIfPresent(ExtSchemaRelationDO::getRelation, reqVO.getRelation())
                .eqIfPresent(ExtSchemaRelationDO::getEndSchemaId, reqVO.getEndSchemaId())
                .eqIfPresent(ExtSchemaRelationDO::getInverseFlag, reqVO.getInverseFlag())
                .eqIfPresent(ExtSchemaRelationDO::getCreateTime, reqVO.getCreateTime());

        // 判断传入的 startEndId，如果不为空，添加查询条件
        if (reqVO.getStartEndId() != null) {
            queryWrapper.eq(ExtSchemaRelationDO::getStartSchemaId, reqVO.getStartEndId())
                    .or()
                    .eq(ExtSchemaRelationDO::getEndSchemaId, reqVO.getStartEndId());
        }

        // 按照 createTime 字段降序排序
        queryWrapper.orderBy(reqVO.getOrderByColumn(), reqVO.getIsAsc(), allowedColumns);

        return selectPage(reqVO, queryWrapper);
    }
}
