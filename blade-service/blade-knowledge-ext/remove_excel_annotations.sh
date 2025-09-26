#!/bin/bash

# 批量删除@Excel注解的脚本

echo "开始删除@Excel注解..."

# 删除所有@Excel注解行
find . -name "*.java" -type f -exec sed -i '/@Excel/d' {} \;

# 删除相关的import语句
find . -name "*.java" -type f -exec sed -i '/import org\.springblade\.common\.annotation\.Excel;/d' {} \;

echo "@Excel注解删除完成！"
