package org.springblade.common.database.datasource;


import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;
import org.springblade.common.database.DataSourceFactory;
import org.springblade.common.database.DbDialect;
import org.springblade.common.database.DbQuery;
import org.springblade.common.database.DialectFactory;
import org.springblade.common.database.constants.DbQueryProperty;
import org.springblade.common.database.constants.DbType;
import org.springblade.common.database.exception.DataQueryException;
import org.springblade.common.database.query.AbstractDbQueryFactory;
import org.springblade.common.database.query.CacheDbQueryFactoryBean;

import javax.sql.DataSource;

public abstract class AbstractDataSourceFactory implements DataSourceFactory {

    @Override
    public DbQuery createDbQuery(DbQueryProperty property) {
        property.viald();
        DbType dbType = DbType.getDbType(property.getDbType());
        DataSource dataSource = createDataSource(property);
        DbQuery dbQuery = createDbQuery(property, dataSource, dbType);
        return dbQuery;
    }

    public DbQuery createDbQuery(DbQueryProperty dbQueryProperty, DataSource dataSource, DbType dbType) {
        DbDialect dbDialect = DialectFactory.getDialect(dbType);
        if (dbDialect == null) {
            throw new DataQueryException("该数据库类型正在开发中");
        }
        AbstractDbQueryFactory dbQuery = new CacheDbQueryFactoryBean();
        dbQuery.setDbQueryProperty(dbQueryProperty);
        dbQuery.setDataSource(dataSource);
        dbQuery.setJdbcTemplate(new JdbcTemplate(dataSource));
        dbQuery.setDbDialect(dbDialect);
        return dbQuery;
    }

    public DataSource createDataSource(DbQueryProperty property) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(trainToJdbcUrl(property));
        dataSource.setUsername(property.getUsername());
        dataSource.setPassword(property.getPassword());
        return dataSource;
    }

    protected String trainToJdbcUrl(DbQueryProperty property) {
        String url = DbType.getDbType(property.getDbType()).getUrl();
        if (StringUtils.isEmpty(url)) {
            throw new DataQueryException("无效数据库类型!");
        }
        url = url.replace("${host}", property.getHost());
        url = url.replace("${port}", String.valueOf(property.getPort()));
        if (DbType.ORACLE.getDb().equals(property.getDbType()) || DbType.ORACLE_12C.getDb().equals(property.getDbType())) {
            url = url.replace("${sid}", property.getSid());
        } else {
            url = url.replace("${dbName}", property.getDbName());
        }
        return url;
    }
}
