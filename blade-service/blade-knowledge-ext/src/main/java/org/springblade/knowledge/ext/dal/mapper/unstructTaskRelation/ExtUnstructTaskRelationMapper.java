package org.springblade.knowledge.ext.dal.mapper.unstructTaskRelation;

import org.springblade.common.core.page.PageResult;
import org.springblade.knowledge.ext.admin.unstructTaskRelation.vo.ExtUnstructTaskRelationPageReqVO;
import org.springblade.knowledge.ext.dal.dataobject.unstructTaskRelation.ExtUnstructTaskRelationDO;
import org.springblade.mybatis.core.mapper.BaseMapperX;
import org.springblade.mybatis.core.query.LambdaQueryWrapperX;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 任务文件关联Mapper接口
 *
 * @author qknow
 * @date 2025-04-03
 */
public interface ExtUnstructTaskRelationMapper extends BaseMapperX<ExtUnstructTaskRelationDO> {

    default PageResult<ExtUnstructTaskRelationDO> selectPage(ExtUnstructTaskRelationPageReqVO reqVO) {
        // 定义排序的字段（防止 SQL 注入，与数据库字段名称一致）
        Set<String> allowedColumns = new HashSet<>(Arrays.asList("id", "create_time", "update_time"));

        // 构造动态查询条件
        return selectPage(reqVO, new LambdaQueryWrapperX<ExtUnstructTaskRelationDO>()
                .eqIfPresent(ExtUnstructTaskRelationDO::getTaskId, reqVO.getTaskId())
                .eqIfPresent(ExtUnstructTaskRelationDO::getRelationId, reqVO.getRelationId())
                .eqIfPresent(ExtUnstructTaskRelationDO::getCreateTime, reqVO.getCreateTime())
                // 如果 reqVO.getName() 不为空，则添加 name 的精确匹配条件（name = '<name>'）
                // .likeIfPresent(ExtUnstructTaskRelationDO::getName, reqVO.getName())
                // 按照 createTime 字段降序排序
                .orderBy(reqVO.getOrderByColumn(), reqVO.getIsAsc(), allowedColumns));
    }
}
