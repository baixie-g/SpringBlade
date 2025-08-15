package org.springblade.knowledge.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.knowledge.demo.entity.KnowledgeArticle;
import org.springblade.knowledge.demo.service.IKnowledgeArticleService;
import org.springblade.knowledge.demo.vo.KnowledgeArticleVO;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

/**
 * 知识文章控制器
 *
 * @author AI Assistant
 * @since 2025-08-16
 */
@RestController
@AllArgsConstructor
@RequestMapping("/knowledge/article")
@Tag(name = "知识文章管理", description = "知识文章的增删改查接口")
public class KnowledgeArticleController {

    private final IKnowledgeArticleService knowledgeArticleService;

    /**
     * 分页查询知识文章
     */
    @GetMapping("/page")
    @ApiOperationSupport(order = 1)
    @Operation(summary = "分页查询", description = "传入知识文章")
    public R<IPage<KnowledgeArticleVO>> page(
            @Parameter(description = "分页参数") @RequestParam(defaultValue = "1") Integer current,
            @Parameter(description = "分页大小") @RequestParam(defaultValue = "10") Integer size,
            @Parameter(description = "查询条件") KnowledgeArticle article) {
        IPage<KnowledgeArticle> pages = new Page<>(current, size);
        IPage<KnowledgeArticleVO> result = knowledgeArticleService.selectKnowledgeArticlePage(pages, article);
        return R.data(result);
    }

    /**
     * 根据ID查询知识文章详情
     */
    @GetMapping("/detail")
    @ApiOperationSupport(order = 2)
    @Operation(summary = "详情", description = "传入id")
    public R<KnowledgeArticleVO> detail(@Parameter(description = "主键id") @RequestParam Long id) {
        KnowledgeArticleVO detail = knowledgeArticleService.selectKnowledgeArticleById(id);
        return R.data(detail);
    }

    /**
     * 新增知识文章
     */
    @PostMapping("/save")
    @ApiOperationSupport(order = 3)
    @Operation(summary = "新增", description = "传入知识文章")
    public R save(@Valid @RequestBody KnowledgeArticle article) {
        // 设置默认值
        if (article.getReadCount() == null) {
            article.setReadCount(0);
        }
        if (article.getLikeCount() == null) {
            article.setLikeCount(0);
        }
        if (article.getStatus() == null) {
            article.setStatus(0); // 默认草稿状态
        }
        if (article.getSort() == null) {
            article.setSort(0);
        }
        
        return R.status(knowledgeArticleService.save(article));
    }

    /**
     * 修改知识文章
     */
    @PostMapping("/update")
    @ApiOperationSupport(order = 4)
    @Operation(summary = "修改", description = "传入知识文章")
    public R update(@Valid @RequestBody KnowledgeArticle article) {
        return R.status(knowledgeArticleService.updateById(article));
    }

    /**
     * 删除知识文章
     */
    @PostMapping("/remove")
    @ApiOperationSupport(order = 5)
    @Operation(summary = "删除", description = "传入ids")
    public R remove(@Parameter(description = "主键集合") @RequestParam String ids) {
        return R.status(knowledgeArticleService.removeByIds(Func.toLongList(ids)));
    }

    /**
     * 增加阅读次数
     */
    @PostMapping("/read")
    @ApiOperationSupport(order = 6)
    @Operation(summary = "增加阅读次数", description = "传入文章id")
    public R incrementReadCount(@Parameter(description = "文章id") @RequestParam Long id) {
        return R.status(knowledgeArticleService.incrementReadCount(id));
    }

    /**
     * 点赞文章
     */
    @PostMapping("/like")
    @ApiOperationSupport(order = 7)
    @Operation(summary = "点赞文章", description = "传入文章id")
    public R likeArticle(@Parameter(description = "文章id") @RequestParam Long id) {
        return R.status(knowledgeArticleService.likeArticle(id));
    }

    /**
     * 发布文章
     */
    @PostMapping("/publish")
    @ApiOperationSupport(order = 8)
    @Operation(summary = "发布文章", description = "传入文章id")
    public R publishArticle(@Parameter(description = "文章id") @RequestParam Long id) {
        KnowledgeArticle article = new KnowledgeArticle();
        article.setId(id);
        article.setStatus(1); // 发布状态
        return R.status(knowledgeArticleService.updateById(article));
    }

    /**
     * 下架文章
     */
    @PostMapping("/unpublish")
    @ApiOperationSupport(order = 9)
    @Operation(summary = "下架文章", description = "传入文章id")
    public R unpublishArticle(@Parameter(description = "文章id") @RequestParam Long id) {
        KnowledgeArticle article = new KnowledgeArticle();
        article.setId(id);
        article.setStatus(2); // 下架状态
        return R.status(knowledgeArticleService.updateById(article));
    }
}
