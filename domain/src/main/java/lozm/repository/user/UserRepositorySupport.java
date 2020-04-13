package lozm.repository.user;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lozm.vo.sign.SignVo;
import lozm.entity.user.User;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static lozm.entity.user.QUser.user;


@Repository
public class UserRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    public UserRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        super(User.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<User> selectUserDetail(SignVo signVo) {
        return jpaQueryFactory
                .select(Projections.fields(
                    User.class
                ))
                .from(user)
                .where(
                        user.identifier.eq(signVo.getIdentifier())
                        .and(user.password.eq(signVo.getPassword()))
                        .and(user.flag.eq(1))
                )
                .fetch();
    }

}
