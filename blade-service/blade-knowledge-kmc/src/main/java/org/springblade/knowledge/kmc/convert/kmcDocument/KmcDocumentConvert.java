package org.springblade.knowledge.kmc.convert.kmcDocument;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springblade.knowledge.kmc.admin.kmcDocument.vo.KmcDocumentPageReqVO;
import org.springblade.knowledge.kmc.admin.kmcDocument.vo.KmcDocumentRespVO;
import org.springblade.knowledge.kmc.admin.kmcDocument.vo.KmcDocumentSaveReqVO;
import org.springblade.knowledge.kmc.dal.dataobject.document.KmcDocumentDO;

import java.util.List;


/**
 * 知识文件 Convert
 *
 * @author qknow
 * @date 2025-02-14
 */
@Mapper
public interface KmcDocumentConvert {
    KmcDocumentConvert INSTANCE = Mappers.getMapper(KmcDocumentConvert.class);

    /**
     * PageReqVO 转换为 DO
     * @param kmcDocumentPageReqVO 请求参数
     * @return KmcDocumentDO
     */
    KmcDocumentDO convertToDO(KmcDocumentPageReqVO kmcDocumentPageReqVO);

    /**
     * SaveReqVO 转换为 DO
     * @param kmcDocumentSaveReqVO 保存请求参数
     * @return KmcDocumentDO
     */
    KmcDocumentDO convertToDO(KmcDocumentSaveReqVO kmcDocumentSaveReqVO);

    /**
     * DO 转换为 RespVO
     * @param kmcDocumentDO 实体对象
     * @return KmcDocumentRespVO
     */
    KmcDocumentRespVO convertToRespVO(KmcDocumentDO kmcDocumentDO);

    /**
     * DOList 转换为 RespVOList
     * @param kmcDocumentDOList 实体对象列表
     * @return List<KmcDocumentRespVO>
     */
    List<KmcDocumentRespVO> convertToRespVOList(List<KmcDocumentDO> kmcDocumentDOList);
}
