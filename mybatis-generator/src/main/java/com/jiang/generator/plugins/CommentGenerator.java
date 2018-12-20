package com.jiang.generator.plugins;

import com.jiang.generator.enums.ImportType;
import com.jiang.generator.utils.ClassUtil;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.internal.DefaultCommentGenerator;

/**
 * @author Jiang
 * @date 2018/12/7
 * @time 11:54
 */
public class CommentGenerator extends DefaultCommentGenerator {

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        String remarks = introspectedColumn.getRemarks().trim().replace("\"", "").replace("\n","").replace("\r","");
        field.addJavaDocLine("/**");
        field.addJavaDocLine(" * " + remarks);
        addJavadocTag(field, false);
        field.addJavaDocLine(" */");
        Class api = ClassUtil.getClassForName(ImportType.SWAGGER_API_MODEL_PROPERTY.getType());
        if (api != null) {
            field.addAnnotation("@ApiModelProperty(value=\""+remarks+"\")");
        }
    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {

    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
        innerClass.addJavaDocLine("/**"); //$NON-NLS-1$
        addJavadocTag(innerClass, true);
        innerClass.addJavaDocLine(" */"); //$NON-NLS-1$
    }

    @Override
    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
        method.addJavaDocLine("/**"); //$NON-NLS-1$
        addJavadocTag(method, true);
        method.addJavaDocLine(" */"); //$NON-NLS-1$
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
        field.addJavaDocLine("/**");
        addJavadocTag(field, true);
        field.addJavaDocLine(" */");
    }
}
