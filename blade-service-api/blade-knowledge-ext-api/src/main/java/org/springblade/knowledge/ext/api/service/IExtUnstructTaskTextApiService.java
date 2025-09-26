package org.springblade.knowledge.ext.api.service;

import org.springblade.knowledge.ext.api.extUnstructTask.dto.ExtUnstructTaskRespDTO;

import java.util.List;

public interface IExtUnstructTaskTextApiService {
    public List<ExtUnstructTaskRespDTO> getUnstructTaskTextList();
}
