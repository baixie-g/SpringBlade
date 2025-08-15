package org.springblade.knowledge.ext.admin.unstructTaskRelation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import tech.qiantong.qknow.module.ext.controller.admin.unstructTaskRelation.vo.ExtUnstructTaskRelationPageReqVO;
import tech.qiantong.qknow.module.ext.controller.admin.unstructTaskRelation.vo.ExtUnstructTaskRelationRespVO;
import tech.qiantong.qknow.module.ext.controller.admin.unstructTaskRelation.vo.ExtUnstructTaskRelationSaveReqVO;
import tech.qiantong.qknow.module.ext.convert.unstructTaskRelation.ExtUnstructTaskRelationConvert;
import tech.qiantong.qknow.module.ext.dal.dataobject.unstructTaskRelation.ExtUnstructTaskRelationDO;
import tech.qiantong.qknow.module.ext.service.unstructTaskRelation.IExtUnstructTaskRelationService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * 任务文件关联Controller
 *
 * @author qknow
 * @date 2025-04-03
 */
@Tag(name = "任务文件关联")
@RestController
@RequestMapping("/ext/unstructTaskRelation")
@Validated
public class ExtUnstructTaskRelationController extends BaseController {
    @Resource
    private IExtUnstructTaskRelationService extUnstructTaskRelationService;

    @Operation(summary = "查询任务文件关联列表")
    @PreAuthorize("@ss.hasPermi('ext:unstructTaskRelation:unstructtaskrelation:list')")
    @GetMapping("/list")
    public CommonResult<PageResult<ExtUnstructTaskRelationRespVO>> list(ExtUnstructTaskRelationPageReqVO extUnstructTaskRelation) {
        PageResult<ExtUnstructTaskRelationDO> page = extUnstructTaskRelationService.getExtUnstructTaskRelationPage(extUnstructTaskRelation);
        return CommonResult.success(BeanUtils.toBean(page, ExtUnstructTaskRelationRespVO.class));
    }

    @Operation(summary = "导出任务文件关联列表")
    @PreAuthorize("@ss.hasPermi('ext:unstructTaskRelation:unstructtaskrelation:export')")
    @Log(title = "任务文件关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ExtUnstructTaskRelationPageReqVO exportReqVO) {
        exportReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ExtUnstructTaskRelationDO> list = (List<ExtUnstructTaskRelationDO>) extUnstructTaskRelationService.getExtUnstructTaskRelationPage(exportReqVO).getRows();
        ExcelUtil<ExtUnstructTaskRelationRespVO> util = new ExcelUtil<>(ExtUnstructTaskRelationRespVO.class);
        util.exportExcel(response, ExtUnstructTaskRelationConvert.INSTANCE.convertToRespVOList(list), "应用管理数据");
    }

    @Operation(summary = "导入任务文件关联列表")
    @PreAuthorize("@ss.hasPermi('ext:unstructTaskRelation:unstructtaskrelation:import')")
    @Log(title = "任务文件关联", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<ExtUnstructTaskRelationRespVO> util = new ExcelUtil<>(ExtUnstructTaskRelationRespVO.class);
        List<ExtUnstructTaskRelationRespVO> importExcelList = util.importExcel(file.getInputStream());
        String operName = getUsername();
        String message = extUnstructTaskRelationService.importExtUnstructTaskRelation(importExcelList, updateSupport, operName);
        return success(message);
    }

    @Operation(summary = "获取任务文件关联详细信息")
    @PreAuthorize("@ss.hasPermi('ext:unstructTaskRelation:unstructtaskrelation:query')")
    @GetMapping(value = "/{id}")
    public CommonResult<ExtUnstructTaskRelationRespVO> getInfo(@PathVariable("id") Long id) {
        ExtUnstructTaskRelationDO extUnstructTaskRelationDO = extUnstructTaskRelationService.getExtUnstructTaskRelationById(id);
        return CommonResult.success(BeanUtils.toBean(extUnstructTaskRelationDO, ExtUnstructTaskRelationRespVO.class));
    }

    @Operation(summary = "新增任务文件关联")
    @PreAuthorize("@ss.hasPermi('ext:unstructTaskRelation:unstructtaskrelation:add')")
    @Log(title = "任务文件关联", businessType = BusinessType.INSERT)
    @PostMapping
    public CommonResult<Long> add(@Valid @RequestBody ExtUnstructTaskRelationSaveReqVO extUnstructTaskRelation) {
        return CommonResult.toAjax(extUnstructTaskRelationService.createExtUnstructTaskRelation(extUnstructTaskRelation));
    }

    @Operation(summary = "修改任务文件关联")
    @PreAuthorize("@ss.hasPermi('ext:unstructTaskRelation:unstructtaskrelation:edit')")
    @Log(title = "任务文件关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public CommonResult<Integer> edit(@Valid @RequestBody ExtUnstructTaskRelationSaveReqVO extUnstructTaskRelation) {
        return CommonResult.toAjax(extUnstructTaskRelationService.updateExtUnstructTaskRelation(extUnstructTaskRelation));
    }

    @Operation(summary = "删除任务文件关联")
    @PreAuthorize("@ss.hasPermi('ext:unstructTaskRelation:unstructtaskrelation:remove')")
    @Log(title = "任务文件关联", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public CommonResult<Integer> remove(@PathVariable Long[] ids) {
        return CommonResult.toAjax(extUnstructTaskRelationService.removeExtUnstructTaskRelation(Arrays.asList(ids)));
    }

}
