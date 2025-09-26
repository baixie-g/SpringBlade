package org.springblade.knowledge.ext.api.extration.dto;

import lombok.Data;
import org.springblade.common.core.domain.BaseEntity;

@Data
public class ExtExtractionDTO extends BaseEntity {
    private String text;
    private Integer paragraphIndex;
    private Long taskId;
    private Long docId;
    private Long workspaceId;
    private String head;
}
