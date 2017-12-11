package com.mybatis;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.keygen.SelectKeyGenerator;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Lenovo-PC-QR on 2016/11/9.
 */
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {
                MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {
                MappedStatement.class, Object.class, RowBounds.class,
                ResultHandler.class})})
public class DynamicDataSourceInterceptor implements Interceptor {

    private static final String REGEX = ".*insert\\u0020.*|.*delete\\u0020.*|.*update\\u0020.*";

    private static final Map<String, DynamicDataSourceGlobal> cacheMap = new ConcurrentHashMap<>();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //是否拥有事务上下文
        boolean synchronizationActive = TransactionSynchronizationManager.isSynchronizationActive();
        if (!synchronizationActive) {
            Object[] objects = invocation.getArgs();
            MappedStatement ms = (MappedStatement) objects[0];
            DynamicDataSourceGlobal dynamicDataSourceGlobal = cacheMap.get(ms.getId());
            if (dynamicDataSourceGlobal == null) {
                //读方法
                if (ms.getSqlCommandType().equals(SqlCommandType.SELECT)) {
                    if (ms.getId().contains(SelectKeyGenerator.SELECT_KEY_SUFFIX)) {
                        //!selectKey 为自增id查询主键(SELECT LAST_INSERT_ID() )方法，使用主库
                        dynamicDataSourceGlobal = DynamicDataSourceGlobal.WRITE;
                    } else {
                        BoundSql boundSql = ms.getSqlSource().getBoundSql(objects[1]);
                        String sql = boundSql.getSql().toLowerCase(Locale.CHINA).replaceAll("[\\t\\n\\r]", " ");
                        if (sql.matches(REGEX)) {
                            dynamicDataSourceGlobal = DynamicDataSourceGlobal.WRITE;
                        } else {
                            dynamicDataSourceGlobal = DynamicDataSourceGlobal.READ;
                        }
                    }
                } else {
                    dynamicDataSourceGlobal = DynamicDataSourceGlobal.WRITE;
                }
                cacheMap.put(ms.getId(), dynamicDataSourceGlobal);
                DynamicDataSourceHolder.putDataSource(dynamicDataSourceGlobal);
            } else {
                DynamicDataSourceHolder.putDataSource(cacheMap.get(ms.getId()));
            }
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {
        //
    }
}
