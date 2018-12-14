package com.jiang.generator.plugins;

import com.jiang.generator.enums.Config;
import com.jiang.generator.enums.ImportType;
import com.jiang.generator.utils.ClassUtil;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.JavaClientGeneratorConfiguration;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.List;


/**
 * @author Jiang
 * @date 2018/12/6
 * @time 15:24
 */
public class MapperPlugin extends PluginAdapter {

    /**
     * 插件是否启用
     */
    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    /**
     * mapper生成
     *
     * @param interfaze         mapper类信息
     * @param introspectedTable 表信息
     */
    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        JavaClientGeneratorConfiguration mapperConfig = context.getJavaClientGeneratorConfiguration();
        String rootInterface = mapperConfig.getProperty(Config.CLIENT_ROOT_INTERFACE.getProperty());
        if (StringUtility.stringHasValue(rootInterface)) {
            initMapper(interfaze, introspectedTable, rootInterface);
        }
        return true;
    }

    /**
     * model 生成
     *
     * @param topLevelClass     model类信息
     * @param introspectedTable 表信息
     */
    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        Class apiModelProperty = ClassUtil.getClassForName(ImportType.SWAGGER_API_MODEL_PROPERTY.getType());
        Class lombokData = ClassUtil.getClassForName(ImportType.LOMBOK_DATA.getType());
        if (null != apiModelProperty){
            topLevelClass.addImportedType(apiModelProperty.getTypeName());
        }
        if (null != lombokData){
            topLevelClass.addImportedType(lombokData.getName());
            topLevelClass.addAnnotation(ImportType.SWAGGER_API_MODEL_PROPERTY.getAnnotation());
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
        return true;
    }

    private void initMapper(Interface mapper, IntrospectedTable introspectedTable, String baseMapper) {
        JavaModelGeneratorConfiguration configuration = context.getJavaModelGeneratorConfiguration();
        String modelName = introspectedTable.getFullyQualifiedTable().getDomainObjectName();
        List<IntrospectedColumn> primaryKeyColumns = introspectedTable.getPrimaryKeyColumns();
        IntrospectedColumn column = primaryKeyColumns.get(0);
        FullyQualifiedJavaType javaType = column.getFullyQualifiedJavaType();
        String primaryKeyTypeName = javaType.getShortName();
        String modelPackage = configuration.getTargetPackage();
        //清空原有包
        mapper.getImportedTypes().clear();
        //清空mapper原有方法
        mapper.getMethods().clear();
        //主键
        mapper.addImportedType(javaType);
        //Model
        mapper.addImportedType(new FullyQualifiedJavaType(modelPackage + "." + modelName));
        //Example
        mapper.addImportedType(new FullyQualifiedJavaType(modelPackage + "." + modelName + "Example"));
        //导入Mapper父类
        mapper.addImportedType(new FullyQualifiedJavaType(baseMapper));
        //添加继承
        mapper.addSuperInterface(new FullyQualifiedJavaType(String.format("BaseMapper<%s,%s,%sExample>", modelName, primaryKeyTypeName, modelName)));
    }
}
