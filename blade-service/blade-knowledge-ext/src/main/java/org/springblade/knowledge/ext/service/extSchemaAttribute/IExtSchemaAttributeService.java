package org.springblade.knowledge.ext.service.extSchemaAttribute;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.common.core.page.PageResult;
import org.springblade.knowledge.ext.admin.extSchemaAttribute.vo.ExtSchemaAttributePageReqVO;
import org.springblade.knowledge.ext.admin.extSchemaAttribute.vo.ExtSchemaAttributeRespVO;
import org.springblade.knowledge.ext.admin.extSchemaAttribute.vo.ExtSchemaAttributeSaveReqVO;
import org.springblade.knowledge.ext.dal.dataobject.extSchemaAttribute.ExtSchemaAttributeDO;

import java.util.Collection;
import java.util.List;
import java.util.Map;
/**
 * 概念属性Service接口
 *
 * @author qknow
 * @date 2025-02-17
 */
public interface IExtSchemaAttributeService extends IService<ExtSchemaAttributeDO> {

    /**
     * 获得概念属性分页列表
     *
     * @param pageReqVO 分页请求
     * @return 概念属性分页列表
     */
    PageResult<ExtSchemaAttributeDO> getExtSchemaAttributePage(ExtSchemaAttributePageReqVO pageReqVO);

    /**
     * 创建概念属性
     *
     * @param createReqVO 概念属性信息
     * @return 概念属性编号
     */
    Long createExtSchemaAttribute(ExtSchemaAttributeSaveReqVO createReqVO);

    /**
     * 更新概念属性
     *
     * @param updateReqVO 概念属性信息
     */
    int updateExtSchemaAttribute(ExtSchemaAttributeSaveReqVO updateReqVO);

    /**
     * 删除概念属性
     *
     * @param idList 概念属性编号
     */
    int removeExtSchemaAttribute(Collection<Long> idList);

    /**
     * 获得概念属性详情
     *
     * @param id 概念属性编号
     * @return 概念属性
     */
    ExtSchemaAttributeDO getExtSchemaAttributeById(Long id);

    /**
     * 获得全部概念属性列表
     *
     * @return 概念属性列表
     */
    List<ExtSchemaAttributeDO> getExtSchemaAttributeList();

    /**
     * 获得全部概念属性 Map
     *
     * @return 概念属性 Map
     */
    Map<Long, ExtSchemaAttributeDO> getExtSchemaAttributeMap();


    /**
     * 导入概念属性数据
     *
     * @param importExcelList 概念属性数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    String importExtSchemaAttribute(List<ExtSchemaAttributeRespVO> importExcelList, boolean isUpdateSupport, String operName);

    List<ExtSchemaAttributeDO> getAllExtSchemaAttributeList(ExtSchemaAttributeDO extSchemaAttributeDO);
}
