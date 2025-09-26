#!/bin/bash

echo "开始批量替换包名..."

# 替换tech.qiantong.qknow.common.annotation为org.springblade.common.annotation
find . -name "*.java" -exec sed -i 's/tech\.qiantong\.qknow\.common\.annotation/org.springblade.common.annotation/g' {} \;
echo "替换完成: tech.qiantong.qknow.common.annotation -> org.springblade.common.annotation"

# 替换tech.qiantong.qknow.common.core.controller为org.springblade.common.core.controller
find . -name "*.java" -exec sed -i 's/tech\.qiantong\.qknow\.common\.core\.controller/org.springblade.common.core.controller/g' {} \;
echo "替换完成: tech.qiantong.qknow.common.core.controller -> org.springblade.common.core.controller"

# 替换tech.qiantong.qknow.common.core.domain为org.springblade.common.core.domain
find . -name "*.java" -exec sed -i 's/tech\.qiantong\.qknow\.common\.core\.domain/org.springblade.common.core.domain/g' {} \;
echo "替换完成: tech.qiantong.qknow.common.core.domain -> org.springblade.common.core.domain"

# 替换tech.qiantong.qknow.common.core.page为org.springblade.common.core.page
find . -name "*.java" -exec sed -i 's/tech\.qiantong\.qknow\.common\.core\.page/org.springblade.common.core.page/g' {} \;
echo "替换完成: tech.qiantong.qknow.common.core.page -> org.springblade.common.core.page"

# 替换tech.qiantong.qknow.common.enums为org.springblade.common.enums
find . -name "*.java" -exec sed -i 's/tech\.qiantong\.qknow\.common\.enums/org.springblade.common.enums/g' {} \;
echo "替换完成: tech.qiantong.qknow.common.enums -> org.springblade.common.enums"

# 替换tech.qiantong.qknow.common.utils.object为org.springblade.common.utils.object
find . -name "*.java" -exec sed -i 's/tech\.qiantong\.qknow\.common\.utils\.object/org.springblade.common.utils.object/g' {} \;
echo "替换完成: tech.qiantong.qknow.common.utils.object -> org.springblade.common.utils.object"

# 替换tech.qiantong.qknow.common.utils.poi为org.springblade.common.utils.poi
find . -name "*.java" -exec sed -i 's/tech\.qiantong\.qknow\.common\.utils\.poi/org.springblade.common.utils.poi/g' {} \;
echo "替换完成: tech.qiantong.qknow.common.utils.poi -> org.springblade.common.utils.poi"

# 替换tech.qiantong.qknow.common.ext.dataobject为org.springblade.knowledge.ext.dal.dataobject
find . -name "*.java" -exec sed -i 's/tech\.qiantong\.qknow\.common\.ext\.dataobject/org.springblade.knowledge.ext.dal.dataobject/g' {} \;
echo "替换完成: tech.qiantong.qknow.common.ext.dataobject -> org.springblade.knowledge.ext.dal.dataobject"

# 替换tech.qiantong.qknow.module.ext.controller.admin为org.springblade.knowledge.ext.admin
find . -name "*.java" -exec sed -i 's/tech\.qiantong\.qknow\.module\.ext\.controller\.admin/org.springblade.knowledge.ext.admin/g' {} \;
echo "替换完成: tech.qiantong.qknow.module.ext.controller.admin -> org.springblade.knowledge.ext.admin"

# 替换tech.qiantong.qknow.module.ext.convert为org.springblade.knowledge.ext.convert
find . -name "*.java" -exec sed -i 's/tech\.qiantong\.qknow\.module\.ext\.convert/org.springblade.knowledge.ext.convert/g' {} \;
echo "替换完成: tech.qiantong.qknow.module.ext.convert -> org.springblade.knowledge.ext.convert"

# 替换tech.qiantong.qknow.module.ext.service为org.springblade.knowledge.ext.service
find . -name "*.java" -exec sed -i 's/tech\.qiantong\.qknow\.module\.ext\.service/org.springblade.knowledge.ext.service/g' {} \;
echo "替换完成: tech.qiantong.qknow.module.ext.service -> org.springblade.knowledge.ext.service"

# 替换tech.qiantong.qknow.module.ext.extEnum为org.springblade.knowledge.ext.extEnum
find . -name "*.java" -exec sed -i 's/tech\.qiantong\.qknow\.module\.ext\.extEnum/org.springblade.knowledge.ext.extEnum/g' {} \;
echo "替换完成: tech.qiantong.qknow.module.ext.extEnum -> org.springblade.knowledge.ext.extEnum"

# 替换javax.validation为jakarta.validation
find . -name "*.java" -exec sed -i 's/javax\.validation/jakarta.validation/g' {} \;
echo "替换完成: javax.validation -> jakarta.validation"

echo "所有包名替换完成！"
