package org.springblade.common.database;


import org.springblade.common.database.constants.DbQueryProperty;

public interface DataSourceFactory {

    /**
     * 创建数据源实例
     *
     * @param property
     * @return
     */
    DbQuery createDbQuery(DbQueryProperty property);
}
