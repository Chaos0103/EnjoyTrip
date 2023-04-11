package member.repository;

import util.DBConnectionUtil;

public class LoginJdbcRepository implements LoginRepository {

    private static final LoginRepository loginRepository = new LoginJdbcRepository();
    private final DBConnectionUtil dbConnectionUtil;

    private LoginJdbcRepository() {
        dbConnectionUtil = DBConnectionUtil.getInstance();
    }

    public static LoginRepository getLoginRepository() {
        return loginRepository;
    }
}
