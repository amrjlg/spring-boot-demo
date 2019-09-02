package com.jiang.generator.plugins;

import com.jiang.generator.enums.ImportType;
import com.jiang.generator.utils.ClassUtil;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.JavaFormatter;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaElement;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;
import org.mybatis.generator.internal.ObjectFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


/**
 * mapper 插件 处理 mapper 及 example
 *
 * @author Jiang
 * @date 2018/12/6
 * @time 15:24
 */
public class MapperPlugin extends PluginAdapter {

    private static final String BASE_EXAMPLE = "com.jiang.base.dao.criterion.BaseExample";
    private static final String SUPER_CRITERIA = "com.jiang.base.dao.criterion.BaseGeneratedCriteria";
    private static final String BASE_MAPPER = "com.jiang.base.dao.BaseMapper";
    private static final String CRITERIA = "Criteria";

    /**
     * 插件是否启用
     */
    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    List<Method> methods;

    /**
     * mapper生成
     *
     * @param interfaze         mapper类信息
     * @param introspectedTable 表信息
     */
    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        addJavaDocLine(interfaze);
        initMapper(interfaze, introspectedTable, BASE_MAPPER);
        return true;
    }

    /**
     * 添加class javadoc
     *
     * @param element
     */
    private void addJavaDocLine(JavaElement element) {
        String[] time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).split(" ");
        element.addJavaDocLine("/**");
        element.addJavaDocLine(" * TODO");
        element.addJavaDocLine(" *");
        element.addJavaDocLine(" * @author jiang");
        element.addJavaDocLine(" * @date " + time[0]);
        element.addJavaDocLine(" * @time " + time[1]);
        element.addJavaDocLine(" */");
    }

    private void initMapper(Interface mapper, IntrospectedTable introspectedTable, String baseMapper) {
        JavaModelGeneratorConfiguration configuration = context.getJavaModelGeneratorConfiguration();
        String modelName = introspectedTable.getFullyQualifiedTable().getDomainObjectName();
        String modelPackage = configuration.getTargetPackage();
        //清空原有包
        mapper.getImportedTypes().clear();
        //清空mapper原有方法
        mapper.getMethods().clear();
        //Model
        mapper.addImportedType(new FullyQualifiedJavaType(modelPackage + "." + modelName));
        //Example
        mapper.addImportedType(new FullyQualifiedJavaType(modelPackage + "." + modelName + "Example"));
        //导入Mapper父类
        mapper.addImportedType(new FullyQualifiedJavaType(baseMapper));
        //添加继承
        mapper.addSuperInterface(new FullyQualifiedJavaType(String.format("BaseMapper<%s,%sExample>", modelName, modelName)));
    }

    /**
     * model 生成
     *
     * @param topLevelClass     model类信息
     * @param introspectedTable 表信息
     */
    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        addJavaDocLine(topLevelClass);
        Class apiModelProperty = ClassUtil.getClassForName(ImportType.SWAGGER_API_MODEL_PROPERTY.getType());
        Class lombokData = ClassUtil.getClassForName(ImportType.LOMBOK_DATA.getType());
        if (null != apiModelProperty) {
            topLevelClass.addImportedType(ImportType.SWAGGER_API_MODEL_PROPERTY.getType());
        }
        if (null != lombokData) {
            topLevelClass.addImportedType(ImportType.LOMBOK_DATA.getType());
            topLevelClass.addAnnotation(ImportType.LOMBOK_DATA.getAnnotation());
            topLevelClass.getMethods().clear();
        }
        return true;
    }

    /**
     * example 文件生成
     *
     * @param topLevelClass     example类信息
     * @param introspectedTable 表信息
     */
    @Override
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        addJavaDocLine(topLevelClass);

        parseExample(topLevelClass);

        return true;
    }

    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        List<GeneratedJavaFile> files = new ArrayList<>(1);
        String criteria = introspectedTable.getBaseRecordType() + CRITERIA;
        GeneratedJavaFile criteriaClass = generatedCriteriaClass(criteria);
        files.add(criteriaClass);
        return files;
    }

    private GeneratedJavaFile generatedCriteriaClass(String criteria) {
        JavaFormatter javaFormatter = ObjectFactory.createJavaFormatter(context);
        TopLevelClass criteriaClass = generateCriteriaClass(criteria);
        parseCriteriaClassMethods(criteriaClass);
        addJavaDocLine(criteriaClass);
        String targetProject = context.getJavaModelGeneratorConfiguration().getTargetProject();
        return new GeneratedJavaFile(criteriaClass, targetProject, javaFormatter);
    }
    private TopLevelClass  generateCriteriaClass(String criteria){
        TopLevelClass criteriaClass = new TopLevelClass(criteria);
        criteriaClass.setVisibility(JavaVisibility.PUBLIC);
        FullyQualifiedJavaType javaType = new FullyQualifiedJavaType(SUPER_CRITERIA);
        criteriaClass.addImportedType(javaType);
        criteriaClass.setSuperClass(javaType.getShortName());
        return criteriaClass;
    }
    private void parseCriteriaClassMethods(TopLevelClass criteriaClass){
        methods.forEach(method -> {
            method.setReturnType(criteriaClass.getType());
            List<String> bodyLines = method.getBodyLines();
            bodyLines.set(bodyLines.size()-1,"return this;");
        });
        criteriaClass.getMethods().addAll(methods);
    }

    /**
     * 处理 example 减少类 冗余
     *
     * @param example 查询 example
     */
    private void parseExample(TopLevelClass example) {
        List<InnerClass> innerClasses = example.getInnerClasses();
        parseGeneratedCriteria(innerClasses.get(0));
        example.getImportedTypes().clear();
        example.setSuperClass(new FullyQualifiedJavaType(String.format("BaseExample<%sCriteria>", example.getType().getShortName().replace("Example",""))));
        example.addImportedType(new FullyQualifiedJavaType(BASE_EXAMPLE));
        example.getMethods().clear();
        example.getFields().clear();
        innerClasses.clear();
    }

    /**
     * 清空生成条件类的冗余方法 字段
     *
     * @param generatedCriteria 生成的条件类
     */
    private void parseGeneratedCriteria(InnerClass generatedCriteria) {
        this.methods = generatedCriteria.getMethods().subList(7, generatedCriteria.getMethods().size());
    }
}
