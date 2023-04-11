package member.repository;

import member.Member;
import util.DBConnectionUtil;

import java.util.Optional;

public class LoginJdbcRepository implements LoginRepository {

    private static final LoginRepository loginRepository = new LoginJdbcRepository();
    private final DBConnectionUtil dbConnectionUtil;

    private LoginJdbcRepository() {
        dbConnectionUtil = DBConnectionUtil.getInstance();
    }

    public static LoginRepository getLoginRepository() {
        return loginRepository;
    }

    @Override
    public int save() {
        return 0;
    }

    @Override
    public Optional<Member> findById() {
        return Optional.empty();
    }

    @Override
    public int remove() {
        return 0;
    }
}
