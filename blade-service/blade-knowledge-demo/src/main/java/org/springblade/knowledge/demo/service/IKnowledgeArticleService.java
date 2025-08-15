package org.springblade.knowledge.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.knowledge.demo.entity.KnowledgeArticle;
import org.springblade.knowledge.demo.vo.KnowledgeArticleVO;

/**
 * 知识文章服务接口
 *
 * @author AI Assistant
 * @since 2025-08-16
 */
public interface IKnowledgeArticleService extends IService<KnowledgeArticle> {

    /**
     * 分页查询知识文章
     *
     * @param page 分页参数
     * @param article 查询条件
     * @return 分页结果
     */
    IPage<KnowledgeArticleVO> selectKnowledgeArticlePage(IPage<KnowledgeArticle> page, KnowledgeArticle article);

    /**
     * 根据ID查询知识文章详情
     *
     * @param id 文章ID
     * @return 知识文章详情
     */
    KnowledgeArticleVO selectKnowledgeArticleById(Long id);

    /**
     * 增加阅读次数
     *
     * @param id 文章ID
     * @return 是否成功
     */
    boolean incrementReadCount(Long id);

    /**
     * 点赞文章
     *
     * @param id 文章ID
     * @return 是否成功
     */
    boolean likeArticle(Long id);
}
