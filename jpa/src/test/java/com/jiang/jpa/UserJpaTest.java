package com.jiang.jpa;


import com.jiang.App;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * description this file
 *
 * @author jiang
 * 2020/11/26
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {App.class})
public class UserJpaTest {

    @Autowired
    private UserJpa userJpa;

    @PersistenceContext
    private EntityManager entityManager;

    private Class domainClass;

    @Before
    public void initDomainClass() {
        domainClass = Document.class;
    }

    @Test
    @Transactional
    public void criteriaBuilder() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Document> query = criteriaBuilder.createQuery(Document.class);
        Root<Document> from = query.from(Document.class);
        List<Predicate> predicates = new ArrayList<>();
        Join<Object, Object> user = from.join("user", JoinType.LEFT);
        Predicate userId = criteriaBuilder.equal(user.get("id"), 1);
        predicates.add(userId);
        query.where(predicates.toArray(new Predicate[0]));
        TypedQuery<Document> typedQuery = entityManager.createQuery(query);
        List<Document> list = typedQuery.getResultList();
        System.out.println(list);
    }
}