package com.bobby.parser.repository;

import com.bobby.parser.model.Request;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

/**
 * @author Babak Eghbali (Bob)
 * @since 2018/06/10
 */
public interface RequestRepository extends CrudRepository<Request, Long> {

}
