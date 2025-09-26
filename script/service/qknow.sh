#!/bin/bash

# qKnow 服务启动脚本
# 使用方法: ./qknow.sh [start|stop|restart|status]

# 服务名称
SERVICE_NAME="qknow"
# 服务目录
SERVICE_DIR="/home/g0j/桌面/苏研院/SpringBlade/blade-qKnow/qknow-server"
# 服务端口
SERVICE_PORT="8090"
# JAR文件名
JAR_NAME="qknow-server.jar"
# PID文件
PID_FILE="/tmp/${SERVICE_NAME}.pid"
# 日志文件
LOG_FILE="${SERVICE_DIR}/qknow.log"

# 检查服务目录是否存在
if [ ! -d "$SERVICE_DIR" ]; then
    echo "错误: 服务目录 $SERVICE_DIR 不存在"
    exit 1
fi

# 获取PID
get_pid() {
    if [ -f "$PID_FILE" ]; then
        cat "$PID_FILE"
    else
        ps -ef | grep "$JAR_NAME" | grep -v grep | awk '{print $2}' | head -1
    fi
}

# 启动服务
start() {
    echo "正在启动 $SERVICE_NAME 服务..."
    
    # 检查服务是否已经运行
    if [ -n "$(get_pid)" ]; then
        echo "$SERVICE_NAME 服务已经在运行中 (PID: $(get_pid))"
        return 1
    fi
    
    # 进入服务目录
    cd "$SERVICE_DIR"
    
    # 启动服务
    nohup java -jar \
        -Dspring.profiles.active=dev \
        -Dspring.cloud.nacos.config.server-addr=127.0.0.1:8848 \
        -Dspring.cloud.nacos.config.namespace=blade-dev \
        -Dspring.cloud.nacos.config.file-extension=yaml \
        -Dspring.cloud.nacos.config.shared-configs[0].data-id=blade-qknow-dev.yaml \
        -Dspring.cloud.nacos.config.shared-configs[0].refresh=true \
        "$JAR_NAME" > "$LOG_FILE" 2>&1 &
    
    # 保存PID
    echo $! > "$PID_FILE"
    
    # 等待服务启动
    sleep 3
    
    # 检查服务是否启动成功
    if [ -n "$(get_pid)" ]; then
        echo "$SERVICE_NAME 服务启动成功 (PID: $(get_pid))"
        echo "服务地址: http://localhost:$SERVICE_PORT"
        echo "日志文件: $LOG_FILE"
    else
        echo "$SERVICE_NAME 服务启动失败"
        return 1
    fi
}

# 停止服务
stop() {
    echo "正在停止 $SERVICE_NAME 服务..."
    
    local pid=$(get_pid)
    if [ -n "$pid" ]; then
        kill "$pid"
        rm -f "$PID_FILE"
        echo "$SERVICE_NAME 服务已停止"
    else
        echo "$SERVICE_NAME 服务未运行"
    fi
}

# 重启服务
restart() {
    echo "正在重启 $SERVICE_NAME 服务..."
    stop
    sleep 2
    start
}

# 查看服务状态
status() {
    local pid=$(get_pid)
    if [ -n "$pid" ]; then
        echo "$SERVICE_NAME 服务正在运行 (PID: $pid)"
        echo "服务地址: http://localhost:$SERVICE_PORT"
        echo "日志文件: $LOG_FILE"
    else
        echo "$SERVICE_NAME 服务未运行"
    fi
}

# 主逻辑
case "$1" in
    start)
        start
        ;;
    stop)
        stop
        ;;
    restart)
        restart
        ;;
    status)
        status
        ;;
    *)
        echo "使用方法: $0 {start|stop|restart|status}"
        exit 1
        ;;
esac

exit 0
