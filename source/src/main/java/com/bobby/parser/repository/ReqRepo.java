package com.bobby.parser.repository;

import com.bobby.parser.model.Request;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.EntityManager;

/**
 * @author Babak Eghbali (Bob)
 * @since 2018/06/11
 */
public interface ReqRepo {
    Request persist(Request entity);
    void persistAll(Iterable<Request> list);
}
