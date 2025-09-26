#!/bin/bash

echo "开始替换最后的包名..."

# 替换tech.qiantong.qknow.generator.domain为org.springblade.generator.domain
find . -name "*.java" -exec sed -i 's/tech\.qiantong\.qknow\.generator\.domain/org.springblade.generator.domain/g' {} \;
echo "替换完成: tech.qiantong.qknow.generator.domain -> org.springblade.generator.domain"

# 替换tech.qiantong.qknow.module.dm.api为org.springblade.knowledge.dm.api
find . -name "*.java" -exec sed -i 's/tech\.qiantong\.qknow\.module\.dm\.api/org.springblade.knowledge.dm.api/g' {} \;
echo "替换完成: tech.qiantong.qknow.module.dm.api -> org.springblade.knowledge.dm.api"

# 替换tech.qiantong.qknow.module.app.enums为org.springblade.knowledge.app.enums
find . -name "*.java" -exec sed -i 's/tech\.qiantong\.qknow\.module\.app\.enums/org.springblade.knowledge.app.enums/g' {} \;
echo "替换完成: tech.qiantong.qknow.module.app.enums -> org.springblade.knowledge.app.enums"

# 替换tech.qiantong.qknow.neo4j.enums为org.springblade.neo4j.enums
find . -name "*.java" -exec sed -i 's/tech\.qiantong\.qknow\.neo4j\.enums/org.springblade.neo4j.enums/g' {} \;
echo "替换完成: tech.qiantong.qknow.neo4j.enums -> org.springblade.neo4j.enums"

# 替换tech.qiantong.qknow.redis.service为org.springblade.redis.service
find . -name "*.java" -exec sed -i 's/tech\.qiantong\.qknow\.redis\.service/org.springblade.redis.service/g' {} \;
echo "替换完成: tech.qiantong.qknow.redis.service -> org.springblade.redis.service"

# 替换tech.qiantong.qknow.module.ext.repository为org.springblade.knowledge.ext.dal.repository
find . -name "*.java" -exec sed -i 's/tech\.qiantong\.qknow\.module\.ext\.repository/org.springblade.knowledge.ext.dal.repository/g' {} \;
echo "替换完成: tech.qiantong.qknow.module.ext.repository -> org.springblade.knowledge.ext.dal.repository"

# 替换tech.qiantong.qknow.module.ext.enums为org.springblade.knowledge.ext.enums
find . -name "*.java" -exec sed -i 's/tech\.qiantong\.qknow\.module\.ext\.enums/org.springblade.knowledge.ext.enums/g' {} \;
echo "替换完成: tech.qiantong.qknow.module.ext.enums -> org.springblade.knowledge.ext.enums"

echo "最后的包名替换完成！"
