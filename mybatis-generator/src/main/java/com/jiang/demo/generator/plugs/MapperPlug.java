package com.jiang.demo.generator.plugs;

import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;
import org.mybatis.generator.internal.ObjectFactory;

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

        List<GeneratedJavaFile> mappers = new ArrayList<>();
        JavaModelGeneratorConfiguration configuration = context.getJavaModelGeneratorConfiguration();
        String targetPackage = configuration.getTargetPackage();
        String targetProject = configuration.getTargetProject();
        String modelName = introspectedTable.getFullyQualifiedTable().getDomainObjectName();
        List<IntrospectedColumn> primaryKeyColumns = introspectedTable.getPrimaryKeyColumns();
        IntrospectedColumn column = primaryKeyColumns.get(0);
        FullyQualifiedJavaType javaType = column.getFullyQualifiedJavaType();
        String shortName = javaType.getShortName();
        String packageName = javaType.getPackageName();
        String mapperName =formatterMapperName(modelName);
        System.out.println(mapperName);
        Interface mapper = new Interface(mapperName);
        //主键
        System.out.println(packageName+"."+shortName);
        mapper.addImportedType( new FullyQualifiedJavaType(packageName+"."+shortName));
        //Model
        mapper.addImportedType( new FullyQualifiedJavaType(targetPackage+"."+modelName));
        //Example
        mapper.addImportedType(new FullyQualifiedJavaType(targetPackage+"."+modelName+"Example"));

        mapper.addImportedType(new FullyQualifiedJavaType("com.jiang.demo.BaseMapper"));

        mapper.setVisibility(JavaVisibility.PUBLIC);

        mapper.addSuperInterface(new FullyQualifiedJavaType(String.format("BaseMapper<%s,%s,%sExample>",modelName,shortName,modelName)));

        JavaFormatter javaFormatter = ObjectFactory.createJavaFormatter(context) ;
        GeneratedJavaFile generatedJavaFile = new GeneratedJavaFile(mapper, targetProject, javaFormatter);
        mappers.add(generatedJavaFile);
        return mappers;
    }

    private String formatterMapperName(String modelName){
        return String.format("com.jiang.demo.mapper.%sMapper",modelName);
    }
}
