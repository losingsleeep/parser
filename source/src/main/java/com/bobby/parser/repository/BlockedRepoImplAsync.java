package com.bobby.parser.repository;

import com.bobby.parser.model.Blocked;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Babak Eghbali (Bob)
 * @since 2018/06/12
 */
public class BlockedRepoImplAsync implements BlockedRepo {

    BlockedRepository repository;

    public BlockedRepoImplAsync(BlockedRepository repository){
        this.repository = repository;
    }

    @Async
    @Transactional
    @Override
    public Blocked persist(Blocked entity) {
        return repository.save(entity);
    }
}
