package com.bobby.parser.repository;

import com.bobby.parser.model.Request;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Babak Eghbali (Bob)
 * @since 2018/06/11
 */
public class ReqRepoImplAsync implements ReqRepo{

    RequestRepository repository;

    public ReqRepoImplAsync(RequestRepository repository){
        this.repository = repository;
    }

    @Async
    @Transactional
    @Override
    public Request persist(Request entity) {
        return repository.save(entity);
    }

    @Async
    @Transactional
    @Override
    public void persistAll(Iterable<Request> list) {
        repository.saveAll(list);
    }
}
