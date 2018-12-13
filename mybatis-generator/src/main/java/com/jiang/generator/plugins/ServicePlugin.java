package com.jiang.generator.plugins;

import com.jiang.generator.enums.Config;
import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.ObjectFactory;

import java.util.*;

/**
 * @author jiang
 * @date 2018/12/13
 * @time 15:27
 **/
public class ServicePlugin extends PluginAdapter {
    private String servicePackage;
    private String serviceProject;
    private String serviceRoot;
    private String serviceImpl;

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        servicePackage = properties.getProperty(Config.SERVICE_PACKAGE.getProperty(), "com.jiang.web.service");
        serviceProject = properties.getProperty(Config.SERVICE_PROJECT.getProperty(), "src/main/java");
        serviceRoot = properties.getProperty(Config.CLIENT_ROOT_INTERFACE.getProperty(), "com.jiang.web.service.BaseService");
        serviceImpl = properties.getProperty(Config.SERVICE_IMPL.getProperty(), "com.jiang.web.service.DefaultService");
    }

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }


    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        List<GeneratedJavaFile> services = new ArrayList<>();
        JavaFormatter javaFormatter = ObjectFactory.createJavaFormatter(context);
        generateServiceInterFace(introspectedTable, services, javaFormatter);

        return services;
    }

    private void generateServiceInterFace(IntrospectedTable introspectedTable, List<GeneratedJavaFile> services, JavaFormatter javaFormatter) {
        IntrospectedColumn column = introspectedTable.getPrimaryKeyColumns().get(0);
        FullyQualifiedJavaType keyFullType = column.getFullyQualifiedJavaType();
        String keyFullTypeShortName = keyFullType.getShortName();
        String model = introspectedTable.getFullyQualifiedTable().getDomainObjectName();
        String modelPackage = context.getJavaModelGeneratorConfiguration().getTargetPackage();
        String service = servicePackage + "." + model + "Service";
        //service 、service imp 公共导包set
        Set<FullyQualifiedJavaType> types = new TreeSet<>();

        types.add(keyFullType);
        types.add(new FullyQualifiedJavaType(modelPackage + "." + model));
        types.add(new FullyQualifiedJavaType(modelPackage + "." + model + "Example"));

        //service接口
        Interface serviceInterface = new Interface(service);
        serviceInterface.setVisibility(JavaVisibility.PUBLIC);
        serviceInterface.addImportedTypes(types);
        serviceInterface.addSuperInterface(new FullyQualifiedJavaType(String.format("%s<%s,%s,%sExample>", serviceRoot, model, keyFullTypeShortName, model)));

        serviceInterface.addAnnotation("@Service");
        serviceInterface.addImportedType(new FullyQualifiedJavaType("org.springframework.stereotype.Service"));
        //添加service到额外生成的文件集合
        services.add(new GeneratedJavaFile(serviceInterface, serviceProject, javaFormatter));

        //service实现类
        TopLevelClass topLevelClass = new TopLevelClass(servicePackage + ".impl." + model + "ServiceImpl");
        topLevelClass.setVisibility(JavaVisibility.PUBLIC);
        topLevelClass.addImportedTypes(types);
        //引入service
        topLevelClass.addImportedType(serviceInterface.getType());
        //添加父类
        topLevelClass.setSuperClass(String.format("%s<%s,%s,%sExample>", serviceImpl, model, keyFullTypeShortName, model));
        topLevelClass.addSuperInterface(new FullyQualifiedJavaType(model + "Service"));
        services.add(new GeneratedJavaFile(topLevelClass, serviceProject, javaFormatter));
    }
}
