package org.springblade.knowledge.ext.admin.extUnstructTaskText;

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
import org.springblade.knowledge.ext.admin.extUnstructTaskText.vo.ExtUnstructTaskTextPageReqVO;
import org.springblade.knowledge.ext.admin.extUnstructTaskText.vo.ExtUnstructTaskTextRespVO;
import org.springblade.knowledge.ext.admin.extUnstructTaskText.vo.ExtUnstructTaskTextSaveReqVO;
import org.springblade.knowledge.ext.convert.extUnstructTaskText.ExtUnstructTaskTextConvert;
import org.springblade.knowledge.ext.dal.dataobject.extUnstructTaskText.ExtUnstructTaskTextDO;
import org.springblade.knowledge.ext.service.extUnstructTaskText.IExtUnstructTaskTextService;

import jakarta.annotation.Resource;
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
 * 任务文件段落关联Controller
 *
 * @author qknow
 * @date 2025-02-21
 */
@Tag(name = "任务文件段落关联")
@RestController
@RequestMapping("/ext/text")
@Validated
public class ExtUnstructTaskTextController extends BaseController {
    @Resource
    private IExtUnstructTaskTextService extUnstructTaskTextService;

    @Operation(summary = "查询任务文件段落关联列表")
    @PreAuthorize("@ss.hasPermi('ext:extUnstructTaskText:t:list')")
    @GetMapping("/list")
    public CommonResult<PageResult<ExtUnstructTaskTextRespVO>> list(ExtUnstructTaskTextPageReqVO extUnstructTaskText) {
        PageResult<ExtUnstructTaskTextDO> page = extUnstructTaskTextService.getExtUnstructTaskTextPage(extUnstructTaskText);
        return CommonResult.success(BeanUtils.toBean(page, ExtUnstructTaskTextRespVO.class));
    }

    @Operation(summary = "导出任务文件段落关联列表")
    @PreAuthorize("@ss.hasPermi('ext:extUnstructTaskText:t:export')")
    @Log(title = "任务文件段落关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ExtUnstructTaskTextPageReqVO exportReqVO) {
        exportReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ExtUnstructTaskTextDO> list = (List<ExtUnstructTaskTextDO>) extUnstructTaskTextService.getExtUnstructTaskTextPage(exportReqVO).getRows();
        ExcelUtil<ExtUnstructTaskTextRespVO> util = new ExcelUtil<>(ExtUnstructTaskTextRespVO.class);
        util.exportExcel(response, ExtUnstructTaskTextConvert.INSTANCE.convertToRespVOList(list), "应用管理数据");
    }

    @Operation(summary = "导入任务文件段落关联列表")
    @PreAuthorize("@ss.hasPermi('ext:extUnstructTaskText:t:import')")
    @Log(title = "任务文件段落关联", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<ExtUnstructTaskTextRespVO> util = new ExcelUtil<>(ExtUnstructTaskTextRespVO.class);
        List<ExtUnstructTaskTextRespVO> importExcelList = util.importExcel(file.getInputStream());
        String operName = getUsername();
        String message = extUnstructTaskTextService.importExtUnstructTaskText(importExcelList, updateSupport, operName);
        return success(message);
    }

    @Operation(summary = "获取任务文件段落关联详细信息")
    @PreAuthorize("@ss.hasPermi('ext:extUnstructTaskText:t:query')")
    @GetMapping(value = "/{id}")
    public CommonResult<ExtUnstructTaskTextRespVO> getInfo(@PathVariable("id") Long id) {
        ExtUnstructTaskTextDO extUnstructTaskTextDO = extUnstructTaskTextService.getExtUnstructTaskTextById(id);
        return CommonResult.success(BeanUtils.toBean(extUnstructTaskTextDO, ExtUnstructTaskTextRespVO.class));
    }

    @Operation(summary = "新增任务文件段落关联")
    @PreAuthorize("@ss.hasPermi('ext:extUnstructTaskText:t:add')")
    @Log(title = "任务文件段落关联", businessType = BusinessType.INSERT)
    @PostMapping
    public CommonResult<Long> add(@Valid @RequestBody ExtUnstructTaskTextSaveReqVO extUnstructTaskText) {
        return CommonResult.toAjax(extUnstructTaskTextService.createExtUnstructTaskText(extUnstructTaskText));
    }

    @Operation(summary = "修改任务文件段落关联")
    @PreAuthorize("@ss.hasPermi('ext:extUnstructTaskText:t:edit')")
    @Log(title = "任务文件段落关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public CommonResult<Integer> edit(@Valid @RequestBody ExtUnstructTaskTextSaveReqVO extUnstructTaskText) {
        return CommonResult.toAjax(extUnstructTaskTextService.updateExtUnstructTaskText(extUnstructTaskText));
    }

    @Operation(summary = "删除任务文件段落关联")
    @PreAuthorize("@ss.hasPermi('ext:extUnstructTaskText:t:remove')")
    @Log(title = "任务文件段落关联", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public CommonResult<Integer> remove(@PathVariable Long[] ids) {
        return CommonResult.toAjax(extUnstructTaskTextService.removeExtUnstructTaskText(Arrays.asList(ids)));
    }

}
