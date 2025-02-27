package likelion.RegisterExample;
import jakarta.persistence.EntityManager;
import likelion.RegisterExample.repository.*;
import likelion.RegisterExample.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final MemberRepository memberRepository;
    private final DataSource dataSource;
    private final EntityManager em;
    public SpringConfig(MemberRepository memberRepository, DataSource dataSource, EntityManager em) {
        this.memberRepository = memberRepository;
        this.dataSource = dataSource;
        this.em = em;
    }
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }
    @Bean
    public MemberRepository memberRepository() {
// return new MemoryMemberRepository();
// return new JdbcMemberRepository(dataSource);
// return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);

    }
}