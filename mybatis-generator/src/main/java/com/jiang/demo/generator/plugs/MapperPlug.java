package com.jiang.demo.generator.plugs;

import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;
import org.mybatis.generator.internal.ObjectFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiang
 * @date 2018/12/6
 * @time 15:24
 */
public class MapperPlug extends PluginAdapter {
    @Override
    public boolean validate(List<String> warnings) {
        warnings.forEach(System.out::println);
        return true;
    }

    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        String baseMapper = context.getProperties().getProperty("baseMapper");
        String mapperPackage = context.getProperties().getProperty("mapperPackage");
        String mapperTarget = context.getProperties().getProperty("mapperTarget");

        List<GeneratedJavaFile> mappers = new ArrayList<>();
        JavaModelGeneratorConfiguration configuration = context.getJavaModelGeneratorConfiguration();
        String modelPackage = configuration.getTargetPackage();
        String modelName = introspectedTable.getFullyQualifiedTable().getDomainObjectName();
        if (StringUtils.isEmpty(mapperTarget)){
            mapperTarget =configuration.getTargetProject();
        }
        List<IntrospectedColumn> primaryKeyColumns = introspectedTable.getPrimaryKeyColumns();
        IntrospectedColumn column = primaryKeyColumns.get(0);
        FullyQualifiedJavaType javaType = column.getFullyQualifiedJavaType();
        String primaryKeyTypeName = javaType.getShortName();
        String primaryKeyTypePackage = javaType.getPackageName();
        String mapperName =formatterMapperName(mapperPackage,modelName);
        try {
            Class<?> name = Class.forName(mapperName);
            if (null !=name){
                return mappers;
            }
        }catch (Exception e){

        }
        Interface mapper = new Interface(mapperName);
        mapper.setVisibility(JavaVisibility.PUBLIC);
        //主键
        System.out.println(primaryKeyTypePackage+"."+primaryKeyTypeName);
        mapper.addImportedType( new FullyQualifiedJavaType(primaryKeyTypePackage+"."+primaryKeyTypeName));
        //Model
        mapper.addImportedType( new FullyQualifiedJavaType(modelPackage+"."+modelName));
        //Example
        mapper.addImportedType(new FullyQualifiedJavaType(modelPackage+"."+modelName+"Example"));
        //导入Mapper父类
        mapper.addImportedType(new FullyQualifiedJavaType(baseMapper));
        //添加继承
        mapper.addSuperInterface(new FullyQualifiedJavaType(String.format("BaseMapper<%s,%s,%sExample>",modelName,primaryKeyTypeName,modelName)));

        JavaFormatter javaFormatter = ObjectFactory.createJavaFormatter(context) ;
        GeneratedJavaFile generatedJavaFile = new GeneratedJavaFile(mapper, mapperTarget, javaFormatter);
        mappers.add(generatedJavaFile);
        return mappers;
    }

    private String formatterMapperName(String mapperPakage,String modelName){
        return String.format("%s.%sMapper",mapperPakage,modelName);
    }
}
