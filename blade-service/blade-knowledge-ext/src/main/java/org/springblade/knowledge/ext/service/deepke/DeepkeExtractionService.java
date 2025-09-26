package org.springblade.knowledge.ext.service.deepke;

import org.springblade.common.core.domain.AjaxResult;
//import org.springblade.knowledge.ext.api.extration.dto.ExtExtractionDTO;

/**
 * 知识抽取
 */
public interface DeepkeExtractionService {
    AjaxResult deepkeExtraction(String text) throws Exception;
}
