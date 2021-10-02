package com.ssafy.db.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.QArrestRate;
import com.ssafy.db.entity.QDong;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class dongRepositorySupport extends QuerydslRepositorySupport {

    QArrestRate qArrestRate = QArrestRate.arrestRate;
    QDong qDong = QDong.dong1;
    private final JPAQueryFactory jpaQueryFactory;

    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     *
     * @param jpaQueryFactory must not be {@literal null}.
     */
    public dongRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        super(QDong.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public Long getRanking(Long id){
        return jpaQueryFactory.select(qDong.count().add(1).as("ranking"))
            .from(qDong).where(
                qDong.safetyIndex
                    .gtAll(JPAExpressions.select(qDong.safetyIndex)
                        .from(qDong)
                    .where(qDong.id.eq(id)))
            ).fetchOne();
    }

    public Long getGuRanking(Long id){
        return jpaQueryFactory.select(qDong.count().add(1).as("ranking"))
            .from(qDong).where(
                qDong.safetyIndex
                    .gtAll(JPAExpressions.select(qDong.safetyIndex)
                        .from(qDong)
                        .where(qDong.id.eq(id)))
                .and(qDong.siGunGu.id.eq(
                    JPAExpressions.select(qDong.siGunGu.id).from(qDong).where(qDong.id.eq(id))
                ))
            )
            .fetchOne();
    }

    public Long getSpaceRanking(String type, Long id){
        BooleanBuilder builder = new BooleanBuilder();

        if (type.equals("cctv")) {
            builder.and(qDong.cctvCnt
                .gtAll(JPAExpressions.select(qDong.cctvCnt)
                    .from(qDong)
                    .where(qDong.id.eq(id))));
        }
        else if (type.equals("police")) {
            builder.and(qDong.cctvCnt
                .gtAll(JPAExpressions.select(qDong.policeCnt)
                    .from(qDong)
                    .where(qDong.id.eq(id))));
        }
        else if (type.equals("light")) {
            builder.and(qDong.lightCnt
                .gtAll(JPAExpressions.select(qDong.lightCnt)
                    .from(qDong)
                    .where(qDong.id.eq(id))));
        }
        else if (type.equals("guard")) {
            builder.and(qDong.guardHouseCnt
                .gtAll(JPAExpressions.select(qDong.guardHouseCnt)
                    .from(qDong)
                    .where(qDong.id.eq(id))));
        }
        else {
            builder.and(qDong.barCnt
                .gtAll(JPAExpressions.select(qDong.barCnt)
                    .from(qDong)
                    .where(qDong.id.eq(id))));
        }

        return jpaQueryFactory.select(qDong.count().add(1).as("ranking"))
            .from(qDong)
            .where(builder).fetchOne();
    }

    public Double getSpaceAvg(String type){


        if (type.equals("cctv")) {
            return jpaQueryFactory.select(qDong.cctvCnt.avg()).from(qDong).fetchOne();
        }
        else if (type.equals("police")) {
            return jpaQueryFactory.select(qDong.policeCnt.avg()).from(qDong).fetchOne();
        }
        else if (type.equals("light")) {
            return jpaQueryFactory.select(qDong.lightCnt.avg()).from(qDong).fetchOne();
        }
        else if (type.equals("guard")) {
            return jpaQueryFactory.select(qDong.guardHouseCnt.avg()).from(qDong).fetchOne();
        }
        else {
            return jpaQueryFactory.select(qDong.barCnt.avg()).from(qDong).fetchOne();
        }
    }
}