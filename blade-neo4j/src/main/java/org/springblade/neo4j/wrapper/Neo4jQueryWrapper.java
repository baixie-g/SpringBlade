package org.springblade.neo4j.wrapper;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.Getter;
import org.springblade.neo4j.utils.LambdaUtils;

import java.util.*;

/**
 * 动态查询，方便neo4j查询方式
 * @author wang
 * @date 2025/02/27 17:44
 **/
public class Neo4jQueryWrapper<T> {
    @Getter
    private final Class<T> entityClass;
    private final List<String> conditions = Lists.newArrayList();
    private final Set<String> labels = Sets.newHashSet();
    @Getter
    private final Map<String, Object> params = Maps.newHashMap();
    private String orderBy;
    private int offset;
    private int limit;

    public Neo4jQueryWrapper(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    // 处理多标签查询
    public Neo4jQueryWrapper<T> addLabels(String... labels) {
        this.labels.addAll(Arrays.asList(labels));
        return this;
    }

    // 公共条件构建方法
    private <R> Neo4jQueryWrapper<T> addCondition(String propertyName, String operator, R value) {
        // 修复id字段的处理逻辑
        if ("id".equals(propertyName)) {
            // 查询节点的id属性，而不是使用id()函数
            conditions.add("n." + propertyName + " " + operator + " $" + propertyName);
            params.put(propertyName, value);
        } else {
            conditions.add("n." + propertyName + " " + operator + " $" + propertyName);
            params.put(propertyName, value);
        }
        return this;
    }

    // 处理like的特殊情况
    private <R> Neo4jQueryWrapper<T> addLikeCondition(String propertyName, R value) {
        if (value instanceof Number) {
            // CONTAINS实现方式
            conditions.add("toString(n." + propertyName + ") CONTAINS $" + propertyName);
            params.put(propertyName, String.valueOf(value));
            // 正则实现方式
            //conditions.add("toString(n." + propertyName + ") =~ $" + propertyName);
            //params.put(propertyName, ".*" + value + ".*");
        } else {
            // CONTAINS实现方式
            conditions.add("n." + propertyName + " CONTAINS $" + propertyName);
            params.put(propertyName, String.valueOf(value));
            // 正则实现方式
            //conditions.add("n." + propertyName + " =~ $" + propertyName);
            //params.put(propertyName, ".*" + value + ".*");
        }
        return this;
    }

    // 处理between的特殊情况
    private <R extends Comparable<R>> Neo4jQueryWrapper<T> addBetweenCondition(String propertyName, R start, R end) {
        String startKey = propertyName + "Start";
        String endKey = propertyName + "End";
        conditions.add("n." + propertyName + " >= $" + startKey + " AND n." + propertyName + " <= $" + endKey);
        params.put(startKey, start);
        params.put(endKey, end);
        return this;
    }

    // 以下为Lambda属性方法
    public <R> Neo4jQueryWrapper<T> eq(LambdaUtils.PropertyFunction<T, R> propertyFunction, R value) {
        return addCondition(LambdaUtils.getPropertyName(propertyFunction), "=", value);
    }

    public <R> Neo4jQueryWrapper<T> like(LambdaUtils.PropertyFunction<T, R> propertyFunction, R value) {
        return addLikeCondition(LambdaUtils.getPropertyName(propertyFunction), value);
    }

    public <R extends Comparable<R>> Neo4jQueryWrapper<T> gt(LambdaUtils.PropertyFunction<T, R> propertyFunction, R value) {
        return addCondition(LambdaUtils.getPropertyName(propertyFunction), ">", value);
    }

    public <R extends Comparable<R>> Neo4jQueryWrapper<T> lt(LambdaUtils.PropertyFunction<T, R> propertyFunction, R value) {
        return addCondition(LambdaUtils.getPropertyName(propertyFunction), "<", value);
    }

    public <R> Neo4jQueryWrapper<T> in(LambdaUtils.PropertyFunction<T, R> propertyFunction, List<R> values) {
        return addCondition(LambdaUtils.getPropertyName(propertyFunction), "IN", values);
    }

    public <R> Neo4jQueryWrapper<T> notIn(LambdaUtils.PropertyFunction<T, R> propertyFunction, List<R> values) {
        return addCondition(LambdaUtils.getPropertyName(propertyFunction), "NOT IN", values);
    }

    public <R extends Comparable<R>> Neo4jQueryWrapper<T> between(LambdaUtils.PropertyFunction<T, R> propertyFunction, R start, R end) {
        return addBetweenCondition(LambdaUtils.getPropertyName(propertyFunction), start, end);
    }

    // 以下为字符串属性名方法
    public <R> Neo4jQueryWrapper<T> eq(String propertyName, R value) {
        return addCondition(propertyName, "=", value);
    }

    public <R> Neo4jQueryWrapper<T> like(String propertyName, R value) {
        return addLikeCondition(propertyName, value);
    }

    public <R extends Comparable<R>> Neo4jQueryWrapper<T> gt(String propertyName, R value) {
        return addCondition(propertyName, ">", value);
    }

    public <R extends Comparable<R>> Neo4jQueryWrapper<T> lt(String propertyName, R value) {
        return addCondition(propertyName, "<", value);
    }

    public <R> Neo4jQueryWrapper<T> in(String propertyName, List<R> values) {
        return addCondition(propertyName, "IN", values);
    }

    public <R> Neo4jQueryWrapper<T> notIn(String propertyName, List<R> values) {
        return addCondition(propertyName, "NOT IN", values);
    }

    public <R extends Comparable<R>> Neo4jQueryWrapper<T> between(String propertyName, R start, R end) {
        return addBetweenCondition(propertyName, start, end);
    }

    public Neo4jQueryWrapper<T> orderByAsc(String propertyName) {
        this.orderBy = "n." + propertyName + " ASC";
        return this;
    }

    public Neo4jQueryWrapper<T> orderByDesc(String propertyName) {
        this.orderBy = "n." + propertyName + " DESC";
        return this;
    }

    public Neo4jQueryWrapper<T> page(int offset, int limit) {
        this.offset = (offset - 1) * limit; // 跳过的数据量
        this.limit = limit; // 返回总数
        return this;
    }

    /**
     * 根据条件生成基本查询语句
     * @return 查询实体关系语句
     */
    public String getCypherQuery() {
        StringBuilder query = new StringBuilder("MATCH (n:" + getLabel() + ")");
        if (!conditions.isEmpty()) {
            query.append(" WHERE ").append(String.join(" AND ", conditions));
        }
        // 添加 OPTIONAL MATCH 捕获所有关系
        query.append(" OPTIONAL MATCH (n)-[r]->(related)");
        query.append(" RETURN n, collect(r), collect(related)");
        if (orderBy != null) {
            query.append(" ORDER BY ").append(orderBy);
        }
        if (limit > 0) {
            query.append(" SKIP ").append(offset).append(" LIMIT ").append(limit);
        }
        return query.toString();
    }

    /**
     * 根据条件生成关系链查询语句
     * @return 查询语句
     */
    public String getRelationChainCypherQuery() {
        StringBuilder query = new StringBuilder("MATCH (n:" + getLabel() + ")");
        if (!conditions.isEmpty()) {
            query.append(" WHERE ").append(String.join(" AND ", conditions));
        }

        query.append(" OPTIONAL MATCH pathPattern = (n)-[*0..5]-(pathEndNode)");
        query.append(" WITH pathPattern");
        query.append(" UNWIND nodes(pathPattern) AS currentNode");
        query.append(" OPTIONAL MATCH (currentNode)-[r]->(related)");
        query.append(" WITH currentNode, r, related");
        query.append(" RETURN currentNode, collect(r), collect(related)");
        return query.toString();
    }


        /**
         * 根据条件生成count语句
         * @return 查询语句
         */
    public String getCypherCountQuery() {
        StringBuilder query = new StringBuilder("MATCH (n:" + getLabel() + ")");
        if (!conditions.isEmpty()) {
            query.append(" WHERE ").append(String.join(" AND ", conditions));
        }
        query.append(" RETURN count(n)");
        return query.toString();
    }

    private String getLabel() {
        StringBuilder labelBuilder = new StringBuilder();
        String simpleName = entityClass.getSimpleName();
        labelBuilder.append(simpleName);
        labels.forEach(label -> labelBuilder.append(":").append(label));
        return labelBuilder.toString();
    }

    /**
     * 查询节点
     * @return
     */
    public String getNodeQuery(Set<String> labels) {
        StringBuilder query = new StringBuilder("MATCH (n)");
        if (!conditions.isEmpty()) {
            query.append(" WHERE ").append(String.join(" AND ", conditions));
        }
        if (!labels.isEmpty()){
            List<String> labelList = new ArrayList<>(labels);
            // 构建条件表达式的字符串部分
            StringBuilder labelConditions = new StringBuilder();
            for (int i = 0; i < labelList.size(); i++) {
                labelConditions.append("'" + labelList.get(i) + "' IN labels(n)");
                if (i < labelList.size() - 1) {
                    labelConditions.append(" OR ");
                }
            }
            query.append(" AND (").append(labelConditions).append(")");
        }
        query.append(" RETURN n");
        return query.toString();
    }

    /**
     * 查询关系
     * @return
     */
    public String getRelQuery(Set<String> labels) {
        StringBuilder query = new StringBuilder("OPTIONAL MATCH (n)-[r]->(m)");
        if (!conditions.isEmpty()) {
            query.append(" WHERE ").append(String.join(" AND ", conditions));
        }
        if (!labels.isEmpty()){
            List<String> labelList = new ArrayList<>(labels);
            // 构建条件表达式的字符串部分
            StringBuilder labelConditions = new StringBuilder();
            for (int i = 0; i < labelList.size(); i++) {
                labelConditions.append("'" + labelList.get(i) + "' IN labels(n)");
                if (i < labelList.size() - 1) {
                    labelConditions.append(" OR ");
                }
            }
            query.append(" AND (").append(labelConditions).append(")");
        }
        query.append(" RETURN n, r, m");
        return query.toString();
    }

}
