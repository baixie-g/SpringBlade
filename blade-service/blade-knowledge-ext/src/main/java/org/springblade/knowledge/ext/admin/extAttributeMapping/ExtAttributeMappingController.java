package org.springblade.knowledge.ext.admin.extAttributeMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;
import org.springblade.common.annotation.Log;
import org.springblade.common.core.controller.BaseController;
import org.springblade.common.core.domain.AjaxResult;
import org.springblade.common.core.domain.CommonResult;
import org.springblade.common.core.page.PageParam;
import org.springblade.common.core.page.PageResult;
import org.springblade.common.enums.BusinessType;
import org.springblade.common.utils.object.BeanUtils;
import org.springblade.common.utils.poi.ExcelUtil;
import org.springblade.knowledge.ext.admin.extAttributeMapping.vo.ExtAttributeMappingPageReqVO;
import org.springblade.knowledge.ext.admin.extAttributeMapping.vo.ExtAttributeMappingRespVO;
import org.springblade.knowledge.ext.admin.extAttributeMapping.vo.ExtAttributeMappingSaveReqVO;
import org.springblade.knowledge.ext.convert.extAttributeMapping.ExtAttributeMappingConvert;
import org.springblade.knowledge.ext.dal.dataobject.extAttributeMapping.ExtAttributeMappingDO;
import org.springblade.knowledge.ext.service.extAttributeMapping.IExtAttributeMappingService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.Arrays;
import java.util.List;

/**
 * 属性映射Controller
 *
 * @author qknow
 * @date 2025-02-25
 */
@Tag(name = "属性映射")
@RestController
@RequestMapping("/ext/extAttribute")
@Validated
public class ExtAttributeMappingController extends BaseController {
    @Resource
    private IExtAttributeMappingService extAttributeMappingService;

    @Operation(summary = "查询属性映射列表")
    @PreAuthorize("@ss.hasPermi('ext:extAttributeMapping:mapping:list')")
    @GetMapping("/list")
    public CommonResult<PageResult<ExtAttributeMappingRespVO>> list(ExtAttributeMappingPageReqVO extAttributeMapping) {
        PageResult<ExtAttributeMappingDO> page = extAttributeMappingService.getExtAttributeMappingPage(extAttributeMapping);
        return CommonResult.success(BeanUtils.toBean(page, ExtAttributeMappingRespVO.class));
    }

    @Operation(summary = "导出属性映射列表")
    @PreAuthorize("@ss.hasPermi('ext:extAttributeMapping:mapping:export')")
    @Log(title = "属性映射", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ExtAttributeMappingPageReqVO exportReqVO) {
        exportReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ExtAttributeMappingDO> list = (List<ExtAttributeMappingDO>) extAttributeMappingService.getExtAttributeMappingPage(exportReqVO).getRows();
        ExcelUtil<ExtAttributeMappingRespVO> util = new ExcelUtil<>(ExtAttributeMappingRespVO.class);
        util.exportExcel(response, ExtAttributeMappingConvert.INSTANCE.convertToRespVOList(list), "应用管理数据");
    }

    @Operation(summary = "导入属性映射列表")
    @PreAuthorize("@ss.hasPermi('ext:extAttributeMapping:mapping:import')")
    @Log(title = "属性映射", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<ExtAttributeMappingRespVO> util = new ExcelUtil<>(ExtAttributeMappingRespVO.class);
        List<ExtAttributeMappingRespVO> importExcelList = util.importExcel(file.getInputStream());
        String operName = getUsername();
        String message = extAttributeMappingService.importExtAttributeMapping(importExcelList, updateSupport, operName);
        return success(message);
    }

    @Operation(summary = "获取属性映射详细信息")
    @PreAuthorize("@ss.hasPermi('ext:extAttributeMapping:mapping:query')")
    @GetMapping(value = "/{id}")
    public CommonResult<ExtAttributeMappingRespVO> getInfo(@PathVariable("id") Long id) {
        ExtAttributeMappingDO extAttributeMappingDO = extAttributeMappingService.getExtAttributeMappingById(id);
        return CommonResult.success(BeanUtils.toBean(extAttributeMappingDO, ExtAttributeMappingRespVO.class));
    }

    @Operation(summary = "新增属性映射")
    @PreAuthorize("@ss.hasPermi('ext:extAttributeMapping:mapping:add')")
    @Log(title = "属性映射", businessType = BusinessType.INSERT)
    @PostMapping
    public CommonResult<Long> add(@Valid @RequestBody ExtAttributeMappingSaveReqVO extAttributeMapping) {
        return CommonResult.toAjax(extAttributeMappingService.createExtAttributeMapping(extAttributeMapping));
    }

    @Operation(summary = "修改属性映射")
    @PreAuthorize("@ss.hasPermi('ext:extAttributeMapping:mapping:edit')")
    @Log(title = "属性映射", businessType = BusinessType.UPDATE)
    @PutMapping
    public CommonResult<Integer> edit(@Valid @RequestBody ExtAttributeMappingSaveReqVO extAttributeMapping) {
        return CommonResult.toAjax(extAttributeMappingService.updateExtAttributeMapping(extAttributeMapping));
    }

    @Operation(summary = "删除属性映射")
    @PreAuthorize("@ss.hasPermi('ext:extAttributeMapping:mapping:remove')")
    @Log(title = "属性映射", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public CommonResult<Integer> remove(@PathVariable Long[] ids) {
        return CommonResult.toAjax(extAttributeMappingService.removeExtAttributeMapping(Arrays.asList(ids)));
    }

}
