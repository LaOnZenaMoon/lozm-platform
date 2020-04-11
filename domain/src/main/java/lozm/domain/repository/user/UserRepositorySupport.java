package lozm.domain.repository.user;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lozm.core.vo.sign.SignVo;
import lozm.domain.entity.user.User;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;




@Repository
public class UserRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    public UserRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        super(User.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

//    public List<User> selectUserDetail(SignVo signVo) {
//        return jpaQueryFactory
//                .select(Projections.fields(
//                    User.class
//                ))
//                .from(user)
//                .where(
//                        user.identifier.eq(signVo.getIdentifier())
//                        .and(user.password.eq(signVo.getPassword()))
//                        .and(user.flag.eq(1))
//                )
//                .fetch();
//    }

}
