#!/bin/bash

echo "开始替换剩余的包名..."

# 替换tech.qiantong.qknow.neo4j.repository为org.springblade.neo4j.repository
find . -name "*.java" -exec sed -i 's/tech\.qiantong\.qknow\.neo4j\.repository/org.springblade.neo4j.repository/g' {} \;
echo "替换完成: tech.qiantong.qknow.neo4j.repository -> org.springblade.neo4j.repository"

# 替换tech.qiantong.qknow.neo4j.domain为org.springblade.neo4j.domain
find . -name "*.java" -exec sed -i 's/tech\.qiantong\.qknow\.neo4j\.domain/org.springblade.neo4j.domain/g' {} \;
echo "替换完成: tech.qiantong.qknow.neo4j.domain -> org.springblade.neo4j.domain"

# 替换tech.qiantong.qknow.framework.mybatis.core.mapper为org.springblade.mybatis.core.mapper
find . -name "*.java" -exec sed -i 's/tech\.qiantong\.qknow\.framework\.mybatis\.core\.mapper/org.springblade.mybatis.core.mapper/g' {} \;
echo "替换完成: tech.qiantong.qknow.framework.mybatis.core.mapper -> org.springblade.mybatis.core.mapper"

# 替换tech.qiantong.qknow.framework.mybatis.core.query为org.springblade.mybatis.core.query
find . -name "*.java" -exec sed -i 's/tech\.qiantong\.qknow\.framework\.mybatis\.core\.query/org.springblade.mybatis.core.query/g' {} \;
echo "替换完成: tech.qiantong.qknow.framework.mybatis.core.query -> org.springblade.mybatis.core.query"

# 替换tech.qiantong.qknow.common.exception为org.springblade.common.exception
find . -name "*.java" -exec sed -i 's/tech\.qiantong\.qknow\.common\.exception/org.springblade.common.exception/g' {} \;
echo "替换完成: tech.qiantong.qknow.common.exception -> org.springblade.common.exception"

# 替换tech.qiantong.qknow.common.database为org.springblade.common.database
find . -name "*.java" -exec sed -i 's/tech\.qiantong\.qknow\.common\.database/org.springblade.common.database/g' {} \;
echo "替换完成: tech.qiantong.qknow.common.database -> org.springblade.common.database"

# 替换tech.qiantong.qknow.common.utils为org.springblade.common.utils
find . -name "*.java" -exec sed -i 's/tech\.qiantong\.qknow\.common\.utils/org.springblade.common.utils/g' {} \;
echo "替换完成: tech.qiantong.qknow.common.utils -> org.springblade.common.utils"

echo "剩余包名替换完成！"
