package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.dto.CCTVCount;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class cctvRepositorySupport{

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

}
