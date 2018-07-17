package acloud.simple.conf;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import javax.sql.DataSource;
import org.springframework.util.ObjectUtils;

/**
 * 数据源配置
 * MapperScan也在这里配置
 * 可以配置多个这个类，实现多数据源
 **/
@Configuration
@EnableConfigurationProperties(MybatisProperties.class)
@MapperScan(basePackages = {"acloud.simple.service.dao"},sqlSessionTemplateRef = "baseSqlSessionTemplate")//扫描dao生成bean
public class BaseDataSourceConfig {

    @Autowired
    private MybatisProperties properties;

//    @Bean(name = "baseMybatisConfiguration")
//    @ConfigurationProperties(prefix = "mybatis")
//    public org.apache.ibatis.session.Configuration getGlobleConfiguration()
//    {
//        , @Qualifier("baseMybatisConfiguration")org.apache.ibatis.session.Configuration configuration
//        return new org.apache.ibatis.session.Configuration();
//    }

    @Bean(name = "baseDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.base")
    @Primary
    public DataSource setDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "baseTransactionManager")
    @Primary
    public DataSourceTransactionManager setTransactionManager(@Qualifier("baseDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "baseSqlSessionFactory")
    @Primary
    public SqlSessionFactory setSqlSessionFactory(@Qualifier("baseDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setConfiguration(properties.getConfiguration());
        if (!ObjectUtils.isEmpty(this.properties.resolveMapperLocations())) {
            bean.setMapperLocations(this.properties.resolveMapperLocations());
        }
        //bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:cloud/simple/service/dao/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "baseSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate setSqlSessionTemplate(@Qualifier("baseSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}