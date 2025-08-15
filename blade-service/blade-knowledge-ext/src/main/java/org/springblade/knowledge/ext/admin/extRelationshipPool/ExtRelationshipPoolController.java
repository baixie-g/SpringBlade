package org.springblade.knowledge.ext.admin.extRelationshipPool;

import cn.hutool.core.date.DateUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;
import tech.qiantong.qknow.common.annotation.Log;
import tech.qiantong.qknow.common.core.controller.BaseController;
import tech.qiantong.qknow.common.core.domain.AjaxResult;
import tech.qiantong.qknow.common.core.domain.CommonResult;
import tech.qiantong.qknow.common.core.page.PageParam;
import tech.qiantong.qknow.common.core.page.PageResult;
import tech.qiantong.qknow.common.enums.BusinessType;
import tech.qiantong.qknow.common.utils.object.BeanUtils;
import tech.qiantong.qknow.common.utils.poi.ExcelUtil;
import tech.qiantong.qknow.module.ext.controller.admin.extRelationshipPool.vo.ExtRelationshipPoolPageReqVO;
import tech.qiantong.qknow.module.ext.controller.admin.extRelationshipPool.vo.ExtRelationshipPoolRespVO;
import tech.qiantong.qknow.module.ext.controller.admin.extRelationshipPool.vo.ExtRelationshipPoolSaveReqVO;
import tech.qiantong.qknow.module.ext.convert.extRelationshipPool.ExtRelationshipPoolConvert;
import tech.qiantong.qknow.common.ext.dataobject.extRelationshipPool.ExtRelationshipPoolDO;
import tech.qiantong.qknow.module.ext.service.extRelationshipPool.IExtRelationshipPoolService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 关系池 Controller
 *
 * @author qknow
 * @date 2025-01-20
 */
@Tag(name = "管理后台 - 关系池")
@RestController
@RequestMapping("/ext/relationshipPool")
@Validated
@Slf4j
public class ExtRelationshipPoolController extends BaseController {

    @Resource
    private IExtRelationshipPoolService extRelationshipPoolService;

    /**
     * 查询关系池列表
     *
     * @param pageReqVO 查询条件
     * @return 关系池分页
     */
    @GetMapping("/list")
    @Operation(summary = "查询关系池列表")
    @PreAuthorize("@ss.hasPermi('ext:extRelationshipPool:list')")
    public CommonResult<PageResult<ExtRelationshipPoolRespVO>> list(ExtRelationshipPoolPageReqVO pageReqVO) {
        PageResult<ExtRelationshipPoolRespVO> pageResult = extRelationshipPoolService.getExtRelationshipPoolPage(pageReqVO);
        return CommonResult.success(pageResult);
    }

    /**
     * 获得关系池分页
     *
     * @param pageReqVO 分页查询
     * @return 关系池分页
     */
    @GetMapping("/page")
    @Operation(summary = "获得关系池分页")
    @PreAuthorize("@ss.hasPermi('ext:extRelationshipPool:query')")
    public CommonResult<PageResult<ExtRelationshipPoolRespVO>> getExtRelationshipPoolPage(@Valid ExtRelationshipPoolPageReqVO pageReqVO) {
        PageResult<ExtRelationshipPoolRespVO> pageResult = extRelationshipPoolService.getExtRelationshipPoolPage(pageReqVO);
        return CommonResult.success(pageResult);
    }

    /**
     * 获得关系池
     *
     * @param id 编号
     * @return 关系池
     */
    @GetMapping("/get")
    @Operation(summary = "获得关系池")
    @PreAuthorize("@ss.hasPermi('ext:extRelationshipPool:query')")
    public CommonResult<ExtRelationshipPoolRespVO> getExtRelationshipPool(@RequestParam("id") Long id) {
        ExtRelationshipPoolDO extRelationshipPool = extRelationshipPoolService.getExtRelationshipPool(id);
        return CommonResult.success(BeanUtils.toBean(extRelationshipPool, ExtRelationshipPoolRespVO.class));
    }

    /**
     * 创建关系池
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    @PostMapping("/create")
    @Operation(summary = "创建关系池")
    @PreAuthorize("@ss.hasPermi('ext:extRelationshipPool:create')")
    @Log(title = "关系池", businessType = BusinessType.INSERT)
    public CommonResult<Long> createExtRelationshipPool(@Valid @RequestBody ExtRelationshipPoolSaveReqVO createReqVO) {
        createReqVO.setCreatorId(getUserId());
        createReqVO.setCreateBy(getNickName());
        createReqVO.setCreateTime(DateUtil.date());
        createReqVO.setWorkspaceId(super.getWorkSpaceId());
        return CommonResult.success(extRelationshipPoolService.createExtRelationshipPool(createReqVO));
    }

    /**
     * 更新关系池
     *
     * @param updateReqVO 更新信息
     */
    @PutMapping("/update")
    @Operation(summary = "更新关系池")
    @PreAuthorize("@ss.hasPermi('ext:extRelationshipPool:update')")
    @Log(title = "关系池", businessType = BusinessType.UPDATE)
    public CommonResult<Integer> updateExtRelationshipPool(@Valid @RequestBody ExtRelationshipPoolSaveReqVO updateReqVO) {
        updateReqVO.setUpdaterId(getUserId());
        updateReqVO.setUpdateBy(getNickName());
        updateReqVO.setUpdateTime(DateUtil.date());
        extRelationshipPoolService.updateExtRelationshipPool(updateReqVO);
        return CommonResult.success(1);
    }

    /**
     * 删除关系池
     *
     * @param id 编号
     */
    @DeleteMapping("/delete")
    @Operation(summary = "删除关系池")
    @PreAuthorize("@ss.hasPermi('ext:extRelationshipPool:delete')")
    @Log(title = "关系池", businessType = BusinessType.DELETE)
    public CommonResult<Integer> deleteExtRelationshipPool(@RequestParam("id") Long id) {
        extRelationshipPoolService.deleteExtRelationshipPool(id);
        return CommonResult.success(1);
    }

    /**
     * 批量删除关系池
     *
     * @param ids 编号数组
     */
    @DeleteMapping("/{ids}")
    @Operation(summary = "批量删除关系池")
    @PreAuthorize("@ss.hasPermi('ext:extRelationshipPool:delete')")
    @Log(title = "关系池", businessType = BusinessType.DELETE)
    public CommonResult<Integer> removeExtRelationshipPool(@PathVariable Long[] ids) {
        return CommonResult.success(extRelationshipPoolService.removeExtRelationshipPool(Arrays.asList(ids)));
    }

    /**
     * 导出关系池 Excel
     *
     * @param exportReqVO 导出条件
     * @param response    响应对象
     */
    @Log(title = "关系池", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @Operation(summary = "导出关系池 Excel")
    @PreAuthorize("@ss.hasPermi('ext:extRelationshipPool:export')")
    public void exportExtRelationshipPool(@Valid @RequestBody ExtRelationshipPoolPageReqVO exportReqVO,
                                         HttpServletResponse response) throws IOException {
        exportReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ExtRelationshipPoolDO> list = extRelationshipPoolService.getExtRelationshipPoolList(exportReqVO);
        // 转换为RespVO
        List<ExtRelationshipPoolRespVO> respList = ExtRelationshipPoolConvert.INSTANCE.convertList(list);
        // 导出 Excel
        ExcelUtil<ExtRelationshipPoolRespVO> util = new ExcelUtil<>(ExtRelationshipPoolRespVO.class);
        util.exportExcel(response, respList, "关系池");
    }

    /**
     * 导入关系池数据
     *
     * @param file 文件
     * @param updateSupport 是否更新支持
     * @return 导入结果
     */
    @Operation(summary = "导入关系池数据")
    @PreAuthorize("@ss.hasPermi('ext:extRelationshipPool:import')")
    @Log(title = "关系池", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    public CommonResult<String> importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<ExtRelationshipPoolRespVO> util = new ExcelUtil<>(ExtRelationshipPoolRespVO.class);
        List<ExtRelationshipPoolRespVO> importExcelList = util.importExcel(file.getInputStream());
        String operName = getUsername();
        String message = extRelationshipPoolService.importExtRelationshipPool(importExcelList, updateSupport, operName);
        return CommonResult.success(message);
    }

    /**
     * 处理关系（确认或拒绝）
     *
     * @param id 关系ID
     * @param status 处理状态 1：已确认，2：已拒绝
     * @param remark 处理备注
     * @return 处理结果
     */
    @PostMapping("/process")
    @Operation(summary = "处理关系")
    @PreAuthorize("@ss.hasPermi('ext:extRelationshipPool:process')")
    @Log(title = "关系池", businessType = BusinessType.UPDATE)
    public CommonResult<Object> processRelationship(@RequestParam("id") Long id,
                                                   @RequestParam("status") Integer status,
                                                   @RequestParam(value = "remark", required = false) String remark) {
        AjaxResult result = extRelationshipPoolService.processRelationship(id, status, remark);
        if (result.isSuccess()) {
            return CommonResult.success(result.get("msg"));
        } else {
            return CommonResult.error(500, result.get("msg").toString());
        }
    }

    /**
     * 批量处理关系（确认或拒绝）
     *
     * @param idList 关系ID列表
     * @param status 处理状态 1：已确认，2：已拒绝
     * @param remark 处理备注
     * @return 处理结果
     */
    @PostMapping("/batch-process")
    @Operation(summary = "批量处理关系")
    @PreAuthorize("@ss.hasPermi('ext:extRelationshipPool:process')")
    public CommonResult<Object> batchProcessRelationships(@RequestBody Map<String, Object> requestBody) {
        // 正确处理ID列表的类型转换
        List<?> idListObj = (List<?>) requestBody.get("idList");
        List<Long> idList = new ArrayList<>();
        for (Object id : idListObj) {
            if (id instanceof Integer) {
                idList.add(((Integer) id).longValue());
            } else if (id instanceof Long) {
                idList.add((Long) id);
            } else {
                idList.add(Long.valueOf(id.toString()));
            }
        }

        Integer status = (Integer) requestBody.get("status");
        String remark = (String) requestBody.get("remark");

        AjaxResult result = extRelationshipPoolService.batchProcessRelationships(idList, status, remark);
        if (result.isSuccess()) {
            return CommonResult.success(result.get("data"));
        } else {
            return CommonResult.error(500, result.get("msg").toString());
        }
    }

    /**
     * 根据实体ID查询相关关系
     *
     * @param entityId 实体ID
     * @param taskId 任务ID
     * @return 关系列表
     */
    @GetMapping("/by-entity")
    @Operation(summary = "根据实体ID查询相关关系")
    @PreAuthorize("@ss.hasPermi('ext:extRelationshipPool:query')")
    public CommonResult<List<ExtRelationshipPoolDO>> getRelationshipsByEntityId(
            @RequestParam("entityId") String entityId,
            @RequestParam("taskId") Long taskId) {
        List<ExtRelationshipPoolDO> relationships = extRelationshipPoolService.getRelationshipsByEntityId(entityId, taskId);
        return CommonResult.success(relationships);
    }

    /**
     * 导入关系池数据
     *
     * @param importExcelList 导入数据列表
     * @param updateSupport 是否更新支持
     * @param operName 操作用户
     * @return 导入结果
     */
    @PostMapping("/import")
    @Operation(summary = "导入关系池数据")
    @PreAuthorize("@ss.hasPermi('ext:extRelationshipPool:import')")
    public CommonResult<String> importExtRelationshipPool(
            @RequestBody List<ExtRelationshipPoolRespVO> importExcelList,
            @RequestParam(value = "updateSupport", defaultValue = "false") boolean updateSupport,
            @RequestParam("operName") String operName) {
        String result = extRelationshipPoolService.importExtRelationshipPool(importExcelList, updateSupport, operName);
        return CommonResult.success(result);
    }
}
