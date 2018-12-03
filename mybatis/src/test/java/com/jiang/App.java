/**
 *    Copyright ${license.git.copyrightYears} the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.jiang;

import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.logging.log4j.Log4jImpl;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.session.*;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

/**
 * @author Jiang
 * @date 2018/12/3
 * @time 15:13
 */
public class App {
    @Test
    public void session(){
        final String id = "seckill";
        UnpooledDataSource dataSource = new UnpooledDataSource("com.mysql.jdbc.Driver",
                "jdbc:mysql://127.0.0.1:3306/seckill?useUnicode=true&characterEncoding=utf8",
                "root",
                "0231625530");
        JdbcTransactionFactory jdbcTransactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("defualt", jdbcTransactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.setUseGeneratedKeys(true);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setUseColumnLabel(true);
        configuration.setLogImpl(Log4jImpl.class);

        configuration.addMappedStatement(new MappedStatement.Builder(configuration, id, new StaticSqlSource(configuration,"select * from seckill"), SqlCommandType.SELECT).build());

        SqlSessionFactoryBuilder factoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = factoryBuilder.build(configuration);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.select(id, resultContext -> {
            int resultCount = resultContext.getResultCount();
            Object resultObject = resultContext.getResultObject();
            System.out.println(resultCount);
            System.out.println(resultObject);

            boolean stopped = resultContext.isStopped();
            if (!stopped){
                resultContext.stop();
            }
        });
    }
}
