package org.springblade.knowledge.ext.admin.extUnstructTaskDocRel;

import cn.hutool.core.date.DateUtil;
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
import org.springblade.knowledge.ext.admin.extUnstructTaskDocRel.vo.ExtUnstructTaskDocRelPageReqVO;
import org.springblade.knowledge.ext.admin.extUnstructTaskDocRel.vo.ExtUnstructTaskDocRelRespVO;
import org.springblade.knowledge.ext.admin.extUnstructTaskDocRel.vo.ExtUnstructTaskDocRelSaveReqVO;
import org.springblade.knowledge.ext.convert.extUnstructTaskDocRel.ExtUnstructTaskDocRelConvert;
import org.springblade.knowledge.ext.dal.dataobject.extUnstructTaskDocRel.ExtUnstructTaskDocRelDO;
import org.springblade.knowledge.ext.service.extUnstructTaskDocRel.IExtUnstructTaskDocRelService;

import jakarta.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * 任务文件关联Controller
 *
 * @author qknow
 * @date 2025-02-19
 */
@Tag(name = "任务文件关联")
@RestController
@RequestMapping("/ext/unstructTaskDocRel")
@Validated
public class ExtUnstructTaskDocRelController extends BaseController {
    @Resource
    private IExtUnstructTaskDocRelService extUnstructTaskDocRelService;

    @Operation(summary = "查询任务文件关联列表")
    @PreAuthorize("@ss.hasPermi('ext:extUnstructTaskDocRel:unstructtaskdocrel:list')")
    @GetMapping("/list")
    public CommonResult<PageResult<ExtUnstructTaskDocRelRespVO>> list(ExtUnstructTaskDocRelPageReqVO extUnstructTaskDocRel) {
        PageResult<ExtUnstructTaskDocRelDO> page = extUnstructTaskDocRelService.getExtUnstructTaskDocRelPage(extUnstructTaskDocRel);
        return CommonResult.success(BeanUtils.toBean(page, ExtUnstructTaskDocRelRespVO.class));
    }

    @Operation(summary = "导出任务文件关联列表")
    @PreAuthorize("@ss.hasPermi('ext:extUnstructTaskDocRel:unstructtaskdocrel:export')")
    @Log(title = "任务文件关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ExtUnstructTaskDocRelPageReqVO exportReqVO) {
        exportReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ExtUnstructTaskDocRelDO> list = (List<ExtUnstructTaskDocRelDO>) extUnstructTaskDocRelService.getExtUnstructTaskDocRelPage(exportReqVO).getRows();
        ExcelUtil<ExtUnstructTaskDocRelRespVO> util = new ExcelUtil<>(ExtUnstructTaskDocRelRespVO.class);
        util.exportExcel(response, ExtUnstructTaskDocRelConvert.INSTANCE.convertToRespVOList(list), "应用管理数据");
    }

    @Operation(summary = "导入任务文件关联列表")
    @PreAuthorize("@ss.hasPermi('ext:extUnstructTaskDocRel:unstructtaskdocrel:import')")
    @Log(title = "任务文件关联", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<ExtUnstructTaskDocRelRespVO> util = new ExcelUtil<>(ExtUnstructTaskDocRelRespVO.class);
        List<ExtUnstructTaskDocRelRespVO> importExcelList = util.importExcel(file.getInputStream());
        String operName = getUsername();
        String message = extUnstructTaskDocRelService.importExtUnstructTaskDocRel(importExcelList, updateSupport, operName);
        return success(message);
    }

    @Operation(summary = "获取任务文件关联详细信息")
    @PreAuthorize("@ss.hasPermi('ext:extUnstructTaskDocRel:unstructtaskdocrel:query')")
    @GetMapping(value = "/{id}")
    public CommonResult<ExtUnstructTaskDocRelRespVO> getInfo(@PathVariable("id") Long id) {
        ExtUnstructTaskDocRelDO extUnstructTaskDocRelDO = extUnstructTaskDocRelService.getExtUnstructTaskDocRelById(id);
        return CommonResult.success(BeanUtils.toBean(extUnstructTaskDocRelDO, ExtUnstructTaskDocRelRespVO.class));
    }

    @Operation(summary = "新增任务文件关联")
    @PreAuthorize("@ss.hasPermi('ext:extUnstructTaskDocRel:unstructtaskdocrel:add')")
    @Log(title = "任务文件关联", businessType = BusinessType.INSERT)
    @PostMapping
    public CommonResult<Long> add(@Valid @RequestBody ExtUnstructTaskDocRelSaveReqVO extUnstructTaskDocRel) {
        extUnstructTaskDocRel.setWorkspaceId(super.getWorkSpaceId());
        extUnstructTaskDocRel.setCreatorId(getUserId());
        extUnstructTaskDocRel.setCreateBy(getNickName());
        extUnstructTaskDocRel.setCreateTime(DateUtil.date());
        return CommonResult.toAjax(extUnstructTaskDocRelService.createExtUnstructTaskDocRel(extUnstructTaskDocRel));
    }

    @Operation(summary = "修改任务文件关联")
    @PreAuthorize("@ss.hasPermi('ext:extUnstructTaskDocRel:unstructtaskdocrel:edit')")
    @Log(title = "任务文件关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public CommonResult<Integer> edit(@Valid @RequestBody ExtUnstructTaskDocRelSaveReqVO extUnstructTaskDocRel) {
        extUnstructTaskDocRel.setUpdaterId(getUserId());
        extUnstructTaskDocRel.setUpdateBy(getNickName());
        extUnstructTaskDocRel.setUpdateTime(DateUtil.date());
        return CommonResult.toAjax(extUnstructTaskDocRelService.updateExtUnstructTaskDocRel(extUnstructTaskDocRel));
    }

    @Operation(summary = "删除任务文件关联")
    @PreAuthorize("@ss.hasPermi('ext:extUnstructTaskDocRel:unstructtaskdocrel:remove')")
    @Log(title = "任务文件关联", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public CommonResult<Integer> remove(@PathVariable Long[] ids) {
        return CommonResult.toAjax(extUnstructTaskDocRelService.removeExtUnstructTaskDocRel(Arrays.asList(ids)));
    }

}
