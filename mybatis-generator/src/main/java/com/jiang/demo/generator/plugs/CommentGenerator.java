package com.jiang.demo.generator.plugs;

import com.mysql.jdbc.StringUtils;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.DefaultCommentGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Jiang
 * @date 2018/12/7
 * @time 11:54
 */
public class CommentGenerator extends DefaultCommentGenerator {

    private Properties properties = new Properties();

    @Override
    public void addConfigurationProperties(Properties properties) {
        this.properties.putAll(properties);
        super.addConfigurationProperties(properties);
    }

    //将 namespace修改掉
    @Override
    public void addRootComment(XmlElement rootElement) {

        String xmlNameSpace = (String) properties.get("xmlNameSpace");
        if (StringUtils.isNullOrEmpty(xmlNameSpace)) {
            return;
        }
        List<Attribute> attributes = rootElement.getAttributes();
        List<Attribute> namespaces = new ArrayList<>(attributes);

        attributes.clear();
        namespaces.forEach(namespace -> {
            String[] strings = namespace.getValue().split("\\.");
            Attribute attribute = new Attribute(namespace.getName(), xmlNameSpace + "." + strings[strings.length - 1]);
            attributes.add(attribute);
        });
    }
}
