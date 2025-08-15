package org.springblade.knowledge.kmc.convert.kmcCategory;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springblade.knowledge.kmc.admin.kmcCategory.vo.KmcCategoryPageReqVO;
import org.springblade.knowledge.kmc.admin.kmcCategory.vo.KmcCategoryRespVO;
import org.springblade.knowledge.kmc.admin.kmcCategory.vo.KmcCategorySaveReqVO;
import org.springblade.knowledge.kmc.dal.dataobject.kmcCategory.KmcCategoryDO;

import java.util.List;

/**
 * 知识分类 Convert
 *
 * @author qknow
 * @date 2025-02-13
 */
@Mapper
public interface KmcCategoryConvert {
    KmcCategoryConvert INSTANCE = Mappers.getMapper(KmcCategoryConvert.class);

    /**
     * PageReqVO 转换为 DO
     * @param hubCategoryPageReqVO 请求参数
     * @return HubCategoryDO
     */
    KmcCategoryDO convertToDO(KmcCategoryPageReqVO hubCategoryPageReqVO);

    /**
     * SaveReqVO 转换为 DO
     * @param hubCategorySaveReqVO 保存请求参数
     * @return HubCategoryDO
     */
    KmcCategoryDO convertToDO(KmcCategorySaveReqVO hubCategorySaveReqVO);

    /**
     * DO 转换为 RespVO
     * @param hubCategoryDO 实体对象
     * @return HubCategoryRespVO
     */
    KmcCategoryRespVO convertToRespVO(KmcCategoryDO hubCategoryDO);

    /**
     * DOList 转换为 RespVOList
     * @param hubCategoryDOList 实体对象列表
     * @return List<HubCategoryRespVO>
     */
    List<KmcCategoryRespVO> convertToRespVOList(List<KmcCategoryDO> hubCategoryDOList);
}
