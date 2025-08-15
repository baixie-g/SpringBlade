package org.springblade.common.core.page;

import lombok.Data;
import java.io.Serializable;
import java.util.List;

/**
 * 分页参数基类
 * @author SpringBlade
 */
@Data
public class PageParam implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 不分页
     */
    public static final Integer PAGE_SIZE_NONE = -1;
    
    /**
     * 当前页码
     */
    private Integer pageNum = 1;
    
    /**
     * 每页大小
     */
    private Integer pageSize = 10;
    
    /**
     * 排序字段
     */
    private String orderBy;
    
    /**
     * 排序方向
     */
    private String orderDirection = "desc";
    
    public PageParam() {}
    
    public PageParam(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
    
    public PageParam(Integer pageNum, Integer pageSize, String orderBy) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.orderBy = orderBy;
    }
}
