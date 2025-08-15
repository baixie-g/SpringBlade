package org.springblade.knowledge.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springblade.knowledge.demo.entity.KnowledgeArticle;
import org.springblade.knowledge.demo.vo.KnowledgeArticleVO;

/**
 * 知识文章Mapper接口
 *
 * @author AI Assistant
 * @since 2025-08-16
 */
@Mapper
public interface KnowledgeArticleMapper extends BaseMapper<KnowledgeArticle> {

    /**
     * 分页查询知识文章
     *
     * @param page 分页参数
     * @param article 查询条件
     * @return 分页结果
     */
    IPage<KnowledgeArticleVO> selectKnowledgeArticlePage(IPage<KnowledgeArticle> page, @Param("ew") KnowledgeArticle article);

    /**
     * 根据ID查询知识文章详情
     *
     * @param id 文章ID
     * @return 知识文章详情
     */
    KnowledgeArticleVO selectKnowledgeArticleById(@Param("id") Long id);
}
