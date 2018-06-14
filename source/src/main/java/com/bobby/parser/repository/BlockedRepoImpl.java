package com.bobby.parser.repository;

import com.bobby.parser.model.Blocked;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Babak Eghbali (Bob)
 * @since 2018/06/12
 */
public class BlockedRepoImpl implements BlockedRepo {

    BlockedRepository repository;

    public BlockedRepoImpl(BlockedRepository repository){
        this.repository = repository;
    }

    @Transactional
    @Override
    public Blocked persist(Blocked entity) {
        return repository.save(entity);
    }
}
