package com.jiang.generator.plugins;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.internal.DefaultCommentGenerator;

/**
 * @author Jiang
 * @date 2018/12/7
 * @time 11:54
 */
public class CommentGenerator extends DefaultCommentGenerator {

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        field.addJavaDocLine("/**");
        field.addJavaDocLine(" * " + introspectedColumn.getRemarks());
        addJavadocTag(field, false);
        field.addJavaDocLine(" */");
        try {
            Class.forName("io.swagger.annotations.ApiModelProperty");
            field.addAnnotation("@ApiModelProperty(value = \"" + introspectedColumn.getRemarks().replace('"', '\'') + "\")");
        }catch (ClassNotFoundException ignored) {

        }
    }
}
