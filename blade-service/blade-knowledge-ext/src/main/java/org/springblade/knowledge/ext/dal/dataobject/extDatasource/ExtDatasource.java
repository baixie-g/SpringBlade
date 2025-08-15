package org.springblade.knowledge.ext.dal.dataobject.extDatasource;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;

@Data
public class ExtDatasource {
    @NotBlank(message = "tableId不能为空")
    private Long tableId;
    private Integer pageNum;
    private Integer pageSize;
}
