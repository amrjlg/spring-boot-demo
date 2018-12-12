package com.jiang.demo.generator.plugs;

import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.JavaClientGeneratorConfiguration;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;
import org.mybatis.generator.internal.ObjectFactory;
import org.mybatis.generator.internal.util.StringUtility;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Jiang
 * @date 2018/12/6
 * @time 15:24
 */
public class MapperPlug extends PluginAdapter {
    private final String CLIENT_ROOT_INTERFACE = "jiang-define-root-interface";
    @Override
    public boolean validate(List<String> warnings) {
        return false;
    }

    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        JavaClientGeneratorConfiguration mapperConfig = context.getJavaClientGeneratorConfiguration();
        String rootInterface = mapperConfig.getProperty(CLIENT_ROOT_INTERFACE);
        if (StringUtility.stringHasValue(rootInterface)) {
            initMapper(interfaze,introspectedTable,rootInterface);
            //清空mapper原有方法
            interfaze.getMethods().clear();
        }
        return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
    }
    private void initMapper(Interface mapper, IntrospectedTable introspectedTable,String baseMapper){
        JavaModelGeneratorConfiguration configuration = context.getJavaModelGeneratorConfiguration();
        String modelName = introspectedTable.getFullyQualifiedTable().getDomainObjectName();
        List<IntrospectedColumn> primaryKeyColumns = introspectedTable.getPrimaryKeyColumns();
        IntrospectedColumn column = primaryKeyColumns.get(0);
        FullyQualifiedJavaType javaType = column.getFullyQualifiedJavaType();
        String primaryKeyTypeName = javaType.getShortName();
        String primaryKeyTypePackage = javaType.getPackageName();
        String modelPackage = configuration.getTargetPackage();
        mapper.getImportedTypes().clear();
        //主键
        mapper.addImportedType( new FullyQualifiedJavaType(primaryKeyTypePackage+"."+primaryKeyTypeName));
        //Model
        mapper.addImportedType( new FullyQualifiedJavaType(modelPackage+"."+modelName));
        //Example
        mapper.addImportedType(new FullyQualifiedJavaType(modelPackage+"."+modelName+"Example"));
        //导入Mapper父类
        mapper.addImportedType(new FullyQualifiedJavaType(baseMapper));
        //添加继承
        mapper.addSuperInterface(new FullyQualifiedJavaType(String.format("BaseMapper<%s,%s,%sExample>",modelName,primaryKeyTypeName,modelName)));
    }

    private String formatterMapperName(String mapperPakage,String modelName){
        return String.format("%s.%sMapper",mapperPakage,modelName);
    }
}
