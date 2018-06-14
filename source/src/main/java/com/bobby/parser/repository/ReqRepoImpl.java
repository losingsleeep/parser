package com.bobby.parser.repository;

import com.bobby.parser.model.Request;

/**
 * @author Babak Eghbali (Bob)
 * @since 2018/06/11
 */

public class ReqRepoImpl implements ReqRepo{

    RequestRepository repository;

    public ReqRepoImpl(RequestRepository repository){
        this.repository = repository;
    }

    @Override
    public Request persist(Request entity) {
        return repository.save(entity);
    }

    @Override
    public void persistAll(Iterable<Request> list) {
        repository.saveAll(list);
    }
}
