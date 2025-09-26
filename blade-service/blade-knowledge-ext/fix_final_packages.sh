#!/bin/bash

echo "开始替换最后的包名..."

# 替换tech.qiantong.qknow.common.ext.mapper为org.springblade.knowledge.ext.dal.mapper
find . -name "*.java" -exec sed -i 's/tech\.qiantong\.qknow\.common\.ext\.mapper/org.springblade.knowledge.ext.dal.mapper/g' {} \;
echo "替换完成: tech.qiantong.qknow.common.ext.mapper -> org.springblade.knowledge.ext.dal.mapper"

# 替换tech.qiantong.qknow.neo4j.wrapper为org.springblade.neo4j.wrapper
find . -name "*.java" -exec sed -i 's/tech\.qiantong\.qknow\.neo4j\.wrapper/org.springblade.neo4j.wrapper/g' {} \;
echo "替换完成: tech.qiantong.qknow.neo4j.wrapper -> org.springblade.neo4j.wrapper"

# 替换tech.qiantong.qknow.module.ext.api为org.springblade.knowledge.ext.api
find . -name "*.java" -exec sed -i 's/tech\.qiantong\.qknow\.module\.ext\.api/org.springblade.knowledge.ext.api/g' {} \;
echo "替换完成: tech.qiantong.qknow.module.ext.api -> org.springblade.knowledge.ext.api"

echo "最终包名替换完成！"
