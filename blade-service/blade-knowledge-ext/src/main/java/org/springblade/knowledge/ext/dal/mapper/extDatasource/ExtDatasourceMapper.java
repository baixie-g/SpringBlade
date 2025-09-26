package org.springblade.knowledge.ext.dal.mapper.extDatasource;

import org.springblade.common.core.page.PageResult;
import org.springblade.knowledge.ext.admin.extDatasource.vo.ExtDatasourcePageReqVO;
import org.springblade.knowledge.ext.dal.dataobject.extDatasource.ExtDatasourceDO;
import org.springblade.mybatis.core.mapper.BaseMapperX;
import org.springblade.mybatis.core.query.LambdaQueryWrapperX;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 数据源Mapper接口
 *
 * @author qknow
 * @date 2025-02-25
 */
public interface ExtDatasourceMapper extends BaseMapperX<ExtDatasourceDO> {

    default PageResult<ExtDatasourceDO> selectPage(ExtDatasourcePageReqVO reqVO) {
        // 定义排序的字段（防止 SQL 注入，与数据库字段名称一致）
        Set<String> allowedColumns = new HashSet<>(Arrays.asList("id", "create_time", "update_time"));

        // 构造动态查询条件
        return selectPage(reqVO, new LambdaQueryWrapperX<ExtDatasourceDO>()
                .likeIfPresent(ExtDatasourceDO::getName, reqVO.getName())
                .eqIfPresent(ExtDatasourceDO::getType, reqVO.getType())
                .eqIfPresent(ExtDatasourceDO::getHost, reqVO.getHost())
                .eqIfPresent(ExtDatasourceDO::getPort, reqVO.getPort())
                .likeIfPresent(ExtDatasourceDO::getUsername, reqVO.getUsername())
                .eqIfPresent(ExtDatasourceDO::getPassword, reqVO.getPassword())
                .eqIfPresent(ExtDatasourceDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ExtDatasourceDO::getCreateTime, reqVO.getCreateTime())
                // 如果 reqVO.getName() 不为空，则添加 name 的精确匹配条件（name = '<name>'）
                // .likeIfPresent(ExtDatasourceDO::getName, reqVO.getName())
                // 按照 createTime 字段降序排序
                .orderBy(reqVO.getOrderByColumn(), reqVO.getIsAsc(), allowedColumns));
    }
}
