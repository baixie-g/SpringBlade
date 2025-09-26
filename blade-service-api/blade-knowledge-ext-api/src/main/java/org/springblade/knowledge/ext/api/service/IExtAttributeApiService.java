package org.springblade.knowledge.ext.api.service;


import org.springblade.knowledge.ext.api.extSchemaAttribute.dto.ExtSchemaAttributeRespDTO;

import java.util.List;

public interface IExtAttributeApiService {
    public List<ExtSchemaAttributeRespDTO> getExtSchemaAttributeList();
}