package com.jiang.generator.plugins;

import com.jiang.generator.enums.ImportType;
import com.jiang.generator.utils.ClassUtil;
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
        Class api = ClassUtil.getClassForName(ImportType.SWAGGER_API_MODEL_PROPERTY.getType());
        if (api != null) {
            field.addAnnotation(String.format("@ApiModelProperty(value=\"%s\")", introspectedColumn.getRemarks().trim().replaceAll("\"", "")));
        }
    }
}
