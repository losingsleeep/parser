package com.bobby.parser.repository;

import com.bobby.parser.model.Blocked;

/**
 * @author Babak Eghbali (Bob)
 * @since 2018/06/12
 */
public interface BlockedRepo {
    Blocked persist(Blocked entity);
}
