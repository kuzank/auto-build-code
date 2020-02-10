package com.kuzank.autobuildcode.datasource.config;

import com.github.pagehelper.PageHelper;
import com.kuzank.autobuildcode.common.exception.AutoBuildCodeException;
import com.kuzank.autobuildcode.datasource.util.MybatisResourceFileUtil;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.File;
import java.util.List;
import java.util.Properties;

/**
 * <p>Description: 配置数据源</p>
 *
 * @author kuzank
 */
@Configuration
public class MyBatisConfig {

    private final String mapperScannerPackage = "com.kuzank.autobuildcode.datasource.dao,com.kuzank.autobuildcode.datasource.dao";
    private final String typeAliasesPackage = "com.kuzank.autobuildcode.datasource.entity.po,com.kuzank.autobuildcode.datasource.entity";
    private final String mybatisConfigurateFile = "mybatis-config.xml";
    private final String mapperLocations = "./mapper";


    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        HikariConfig config = new HikariConfig("/hikari.properties");
        HikariDataSource dataSource = new HikariDataSource(config);
        return dataSource;
    }


    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory getSqlSessionFactory(DataSource dataSource) throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(mybatisConfigurateFile));
        sqlSessionFactoryBean.setMapperLocations(getMapperLocations(mapperLocations));
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);

        return sqlSessionFactoryBean.getObject();
    }


    @Bean(name = "manager")
    public DataSourceTransactionManager makeDataSourceTransactionManager(DataSource dataSource) {

        DataSourceTransactionManager manager = new DataSourceTransactionManager();
        manager.setDataSource(dataSource);

        return manager;
    }


    @Bean(name = "mapperScannerConfigure")
    public MapperScannerConfigurer mapperScannerConfigurer() {

        MapperScannerConfigurer configure = new MapperScannerConfigurer();
        configure.setBasePackage(mapperScannerPackage);

        return configure;
    }

    @Bean
    public PageHelper pageHelper() {

        PageHelper pageHelper = new PageHelper();

        Properties p = new Properties();

        // 当该参数设置为 true 时，会将 RowBounds 中的 offset 参数当成 pageNum 使用，可以用页码和页面大小两个参数进行分页。
        p.setProperty("offsetAsPageNum", "true");
        // 当该参数设置为true时，使用 RowBounds 分页会进行 count 查询。
        p.setProperty("rowBoundsWithCount", "true");
        //当该参数设置为 true 时，pageNum<=0 时会查询第一页， pageNum>pages（超过总数时），会查询最后一页。
        p.setProperty("reasonable", "true");

        pageHelper.setProperties(p);

        return pageHelper;
    }


    /**
     * 根据 mybatis xml相对资源路径，获取系统中所有的 mybatis xml SQL配置资源文件路径
     *
     * @param mybatisXmlPath mybatis xml资源路径，例如 ./mapper/
     * @return
     */
    private Resource[] getMapperLocations(String mybatisXmlPath) throws Exception {

        if (mybatisXmlPath == null || mybatisXmlPath.length() == 0) {
            throw new AutoBuildCodeException("mybatis configuration files require not null && not empty");
        }

        List<String> filePathList = MybatisResourceFileUtil.readFile(null, this.getClass().getClassLoader().getResource(mybatisXmlPath).getFile());

        if (filePathList.isEmpty()) {
            throw new AutoBuildCodeException("resource path <" + this.getClass().getClassLoader().getResource("").getFile() + "> has no mybatis xml configuration resource!");
        }

        Resource[] result = new Resource[filePathList.size()];

        for (int i = 0; i < filePathList.size(); i++) {
            System.out.println(filePathList.get(i));
            result[i] = new FileSystemResource(new File(filePathList.get(i)));
        }

        return result;
    }

}

