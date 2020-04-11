package lozm.domain.repository.user;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lozm.core.vo.sign.SignVo;
import lozm.domain.entity.user.User;
import org.graalvm.compiler.replacements.nodes.ArrayEqualsNode;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static lozm.domain.entity.user.QUser.user;

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
                )
                .fetch();
    }

}
