package org.springblade.knowledge.ext.feign;

import org.springframework.stereotype.Service;
import org.springblade.knowledge.ext.api.service.IExtUnstructTaskTextApiService;
import org.springblade.knowledge.ext.dal.dataobject.extUnstructTaskText.ExtUnstructTaskTextDO;
import org.springblade.knowledge.ext.service.extUnstructTaskText.IExtUnstructTaskTextService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ExtUnstructTaskTextApiServiceImpl implements IExtUnstructTaskTextApiService {
    @Resource
    private IExtUnstructTaskTextService extUnstructTaskTextService;

    public List<ExtUnstructTaskTextDO> getUnstructTaskTextList() {
        return extUnstructTaskTextService.getUnstructTaskTextList();
    }
}

