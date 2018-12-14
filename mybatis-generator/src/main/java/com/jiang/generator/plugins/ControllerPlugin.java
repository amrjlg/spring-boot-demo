package com.jiang.generator.plugins;

import com.jiang.generator.enums.Config;
import com.jiang.generator.enums.ImportType;
import com.jiang.generator.utils.ClassUtil;
import com.jiang.generator.utils.StringUtils;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.JavaFormatter;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.ObjectFactory;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author jiang
 * @date 2018/12/14
 * @time 14:18
 **/
public class ControllerPlugin extends PluginAdapter {
    private String controllerPackage;
    private String controllerProject;
    private boolean restController;
    private boolean controller;
    private String servicePackage;

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        controllerPackage = properties.getProperty(Config.CONTROLLER_PACKAGE.getProperty());
        restController = StringUtility.stringHasValue(properties.getProperty(Config.REST_CONTROLLER.getProperty()));
        controller = StringUtility.stringHasValue(properties.getProperty(Config.CONTROLLER.getProperty()));
        controllerProject = properties.getProperty(Config.CONTROLLER_PROJECT.getProperty());
        servicePackage = properties.getProperty(Config.SERVICE_PACKAGE.getProperty(), "com.jiang.web.service");
    }

    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        List<GeneratedJavaFile> files = new ArrayList<>();
        String model = introspectedTable.getFullyQualifiedTable().getDomainObjectName();
        String service = servicePackage + "." + model + "Service";
        JavaFormatter javaFormatter = ObjectFactory.createJavaFormatter(context);
        generatorController(model, service, files, javaFormatter);
        return files;
    }

    private void generatorController(String modelName, String serviceFullName, List<GeneratedJavaFile> files, JavaFormatter javaFormatter) {
        if (StringUtility.stringHasValue(controllerPackage)) {
            String serviceFeild = StringUtils.lowerFirst(modelName + "Service");
            String restContoller = controllerPackage + ".json." + modelName + "Controller";
            String controller = controllerPackage + ".page." + modelName + "Controller";
            Class restControllerClass = ClassUtil.getClassForName(restContoller);
            Class controllerClass = ClassUtil.getClassForName(controller);
            if (restController && restControllerClass==null) {
                TopLevelClass resetControllerClass = new TopLevelClass(restContoller);
                resetControllerClass.setVisibility(JavaVisibility.PUBLIC);
                resetControllerClass.addImportedType(serviceFullName);
                resetControllerClass.addImportedType(ImportType.SPRING_REST_CONTROLLER.getType());
                resetControllerClass.addImportedType(ImportType.SPRING_AUTOWIRED.getType());
                resetControllerClass.addAnnotation(ImportType.SPRING_REST_CONTROLLER.getAnnotation());
                Field service = new Field(serviceFeild, new FullyQualifiedJavaType(serviceFullName));
                service.addAnnotation(ImportType.SPRING_AUTOWIRED.getAnnotation());
                resetControllerClass.addField(service);
                GeneratedJavaFile restController = new GeneratedJavaFile(resetControllerClass, controllerProject, javaFormatter);
                files.add(restController);
            }
            if (this.controller && controllerClass == null) {
                TopLevelClass resetControllerClass = new TopLevelClass(controller);
                resetControllerClass.setVisibility(JavaVisibility.PUBLIC);
                resetControllerClass.addImportedType(serviceFullName);
                resetControllerClass.addImportedType(ImportType.SPRING_CONTROLLER.getType());
                resetControllerClass.addImportedType(ImportType.SPRING_AUTOWIRED.getType());
                resetControllerClass.addAnnotation(ImportType.SPRING_CONTROLLER.getAnnotation());
                Field service = new Field(serviceFeild, new FullyQualifiedJavaType(serviceFullName));
                service.addAnnotation(ImportType.SPRING_AUTOWIRED.getAnnotation());
                resetControllerClass.addField(service);
                GeneratedJavaFile restController = new GeneratedJavaFile(resetControllerClass, controllerProject, javaFormatter);
                files.add(restController);
            }
        }
    }

}
