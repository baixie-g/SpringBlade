#!/bin/bash

echo "=========================================="
echo "  Blade Knowledge Demo 启动脚本"
echo "=========================================="

# 检查Java版本
echo "检查Java版本..."
java -version

# 检查Maven版本
echo "检查Maven版本..."
mvn -version

echo ""
echo "开始构建项目..."

# 清理并编译项目
mvn clean compile -DskipTests

if [ $? -eq 0 ]; then
    echo "项目编译成功！"
    
    echo ""
    echo "开始打包项目..."
    
    # 打包项目
    mvn clean package -DskipTests
    
    if [ $? -eq 0 ]; then
        echo "项目打包成功！"
        
        echo ""
        echo "启动知识管理演示服务..."
        echo "服务地址: http://localhost:8100"
        echo "API文档: http://localhost:8100/doc.html"
        
        # 启动服务
        java -jar target/blade-knowledge-demo.jar
    else
        echo "项目打包失败！"
        exit 1
    fi
else
    echo "项目编译失败！"
    exit 1
fi
