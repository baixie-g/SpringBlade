package org.springblade.knowledge.ext.service.extUnstructTaskText.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.qiantong.qknow.common.core.page.PageResult;
import tech.qiantong.qknow.common.exception.ServiceException;
import tech.qiantong.qknow.common.utils.StringUtils;
import tech.qiantong.qknow.common.utils.object.BeanUtils;
import tech.qiantong.qknow.module.ext.controller.admin.extUnstructTaskText.vo.ExtUnstructTaskTextPageReqVO;
import tech.qiantong.qknow.module.ext.controller.admin.extUnstructTaskText.vo.ExtUnstructTaskTextRespVO;
import tech.qiantong.qknow.module.ext.controller.admin.extUnstructTaskText.vo.ExtUnstructTaskTextSaveReqVO;
import tech.qiantong.qknow.common.ext.dataobject.extUnstructTaskText.ExtUnstructTaskTextDO;
import tech.qiantong.qknow.common.ext.mapper.extUnstructTaskText.ExtUnstructTaskTextMapper;
import tech.qiantong.qknow.module.ext.service.extUnstructTaskText.IExtUnstructTaskTextService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/**
 * 任务文件段落关联Service业务层处理
 *
 * @author qknow
 * @date 2025-02-21
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class ExtUnstructTaskTextServiceImpl  extends ServiceImpl<ExtUnstructTaskTextMapper,ExtUnstructTaskTextDO> implements IExtUnstructTaskTextService {
    @Resource
    private ExtUnstructTaskTextMapper extUnstructTaskTextMapper;

    @Override
    public PageResult<ExtUnstructTaskTextDO> getExtUnstructTaskTextPage(ExtUnstructTaskTextPageReqVO pageReqVO) {
        return extUnstructTaskTextMapper.selectPage(pageReqVO);
    }

    @Override
    public Long createExtUnstructTaskText(ExtUnstructTaskTextSaveReqVO createReqVO) {
        ExtUnstructTaskTextDO dictType = BeanUtils.toBean(createReqVO, ExtUnstructTaskTextDO.class);
        extUnstructTaskTextMapper.insert(dictType);
        return dictType.getId();
    }

    @Override
    public int updateExtUnstructTaskText(ExtUnstructTaskTextSaveReqVO updateReqVO) {
        // 相关校验

        // 更新任务文件段落关联
        ExtUnstructTaskTextDO updateObj = BeanUtils.toBean(updateReqVO, ExtUnstructTaskTextDO.class);
        return extUnstructTaskTextMapper.updateById(updateObj);
    }
    @Override
    public int removeExtUnstructTaskText(Collection<Long> idList) {
        // 批量删除任务文件段落关联
        return extUnstructTaskTextMapper.deleteBatchIds(idList);
    }

    @Override
    public ExtUnstructTaskTextDO getExtUnstructTaskTextById(Long id) {
        return extUnstructTaskTextMapper.selectById(id);
    }

    @Override
    public List<ExtUnstructTaskTextDO> getExtUnstructTaskTextList() {
        return extUnstructTaskTextMapper.selectList();
    }

    @Override
    public Map<Long, ExtUnstructTaskTextDO> getExtUnstructTaskTextMap() {
        List<ExtUnstructTaskTextDO> extUnstructTaskTextList = extUnstructTaskTextMapper.selectList();
        return extUnstructTaskTextList.stream()
                .collect(Collectors.toMap(
                        ExtUnstructTaskTextDO::getId,
                        extUnstructTaskTextDO -> extUnstructTaskTextDO,
                        // 保留已存在的值
                        (existing, replacement) -> existing
                ));
    }


        /**
         * 导入任务文件段落关联数据
         *
         * @param importExcelList 任务文件段落关联数据列表
         * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
         * @param operName 操作用户
         * @return 结果
         */
        @Override
        public String importExtUnstructTaskText(List<ExtUnstructTaskTextRespVO> importExcelList, boolean isUpdateSupport, String operName) {
            if (StringUtils.isNull(importExcelList) || importExcelList.size() == 0) {
                throw new ServiceException("导入数据不能为空！");
            }

            int successNum = 0;
            int failureNum = 0;
            List<String> successMessages = new ArrayList<>();
            List<String> failureMessages = new ArrayList<>();

            for (ExtUnstructTaskTextRespVO respVO : importExcelList) {
                try {
                    ExtUnstructTaskTextDO extUnstructTaskTextDO = BeanUtils.toBean(respVO, ExtUnstructTaskTextDO.class);
                    Long extUnstructTaskTextId = respVO.getId();
                    if (isUpdateSupport) {
                        if (extUnstructTaskTextId != null) {
                            ExtUnstructTaskTextDO existingExtUnstructTaskText = extUnstructTaskTextMapper.selectById(extUnstructTaskTextId);
                            if (existingExtUnstructTaskText != null) {
                                extUnstructTaskTextMapper.updateById(extUnstructTaskTextDO);
                                successNum++;
                                successMessages.add("数据更新成功，ID为 " + extUnstructTaskTextId + " 的任务文件段落关联记录。");
                            } else {
                                failureNum++;
                                failureMessages.add("数据更新失败，ID为 " + extUnstructTaskTextId + " 的任务文件段落关联记录不存在。");
                            }
                        } else {
                            failureNum++;
                            failureMessages.add("数据更新失败，某条记录的ID不存在。");
                        }
                    } else {
                        QueryWrapper<ExtUnstructTaskTextDO> queryWrapper = new QueryWrapper<>();
                        queryWrapper.eq("id", extUnstructTaskTextId);
                        ExtUnstructTaskTextDO existingExtUnstructTaskText = extUnstructTaskTextMapper.selectOne(queryWrapper);
                        if (existingExtUnstructTaskText == null) {
                            extUnstructTaskTextMapper.insert(extUnstructTaskTextDO);
                            successNum++;
                            successMessages.add("数据插入成功，ID为 " + extUnstructTaskTextId + " 的任务文件段落关联记录。");
                        } else {
                            failureNum++;
                            failureMessages.add("数据插入失败，ID为 " + extUnstructTaskTextId + " 的任务文件段落关联记录已存在。");
                        }
                    }
                } catch (Exception e) {
                    failureNum++;
                    String errorMsg = "数据导入失败，错误信息：" + e.getMessage();
                    failureMessages.add(errorMsg);
                    log.error(errorMsg, e);
                }
            }
            StringBuilder resultMsg = new StringBuilder();
            if (failureNum > 0) {
                resultMsg.append("很抱歉，导入失败！共 ").append(failureNum).append(" 条数据格式不正确，错误如下：");
                resultMsg.append("<br/>").append(String.join("<br/>", failureMessages));
                throw new ServiceException(resultMsg.toString());
            } else {
                resultMsg.append("恭喜您，数据已全部导入成功！共 ").append(successNum).append(" 条。");
            }
            return resultMsg.toString();
        }
}
