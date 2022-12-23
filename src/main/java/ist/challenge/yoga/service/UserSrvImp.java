package ist.challenge.yoga.service;

import ist.challenge.yoga.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Transactional
@Repository
public class UserSrvImp implements UserServ {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    class UserRowMap implements RowMapper<UserModel> {
        @Override
        public UserModel mapRow(ResultSet rs, int i) throws SQLException {
            UserModel userModel = new UserModel();
            userModel.setIduser(rs.getInt("iduser"));
            userModel.setUsername(rs.getString("username"));
            userModel.setPsswd(rs.getString("psswd"));
            userModel.setStatus(rs.getInt("status"));
            return userModel;
        }
    }

    @Override
    public List<UserModel> getUser() {
        String sql = "SELECT * FROM t_user WHERE status = 1";
        RowMapper<UserModel> rowMapper = new UserRowMap();
        return this.jdbcTemplate.query(sql,rowMapper);
    }

    @Override
    public boolean UserInput(UserModel userModel) {

        String sql = "INSERT INTO t_user (" +
                "iduser," +
                "username," +
                "psswd," +
                "status)" +
                "VALUES (?,?,?,?)";
        jdbcTemplate.update(sql,
                userModel.getIduser(),
                userModel.getUsername(),
                userModel.getPsswd(),
                userModel.getStatus()
        );
        return false;
    }

    @Override
    public boolean isNameExist(String username) {
        String sql = "SELECT COUNT(*) FROM t_user WHERE username = ? AND status = 1";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, username);
        if(count == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isPwdExist(String psswd) {
        String sql = "SELECT COUNT(*) FROM t_user WHERE psswd = ? AND status = 1";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, psswd);
        if(count == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<UserModel> getUserPswd(String username, String psswd) {
        String sql = "SELECT * FROM t_user WHERE username = '"+username+"' AND psswd = '"+psswd+"' AND status = 1";
        RowMapper<UserModel> rowMapper = new UserRowMap();
        return this.jdbcTemplate.query(sql,rowMapper);
    }

    @Override
    public List<UserModel> getUser(String username) {
        String sql = "SELECT * FROM t_user WHERE username = '"+username+"'  AND status = 1";
        RowMapper<UserModel> rowMapper = new UserRowMap();
        return this.jdbcTemplate.query(sql,rowMapper);
    }

    @Override
    public List<UserModel> getPswd(String psswd) {
        String sql = "SELECT * FROM t_user WHERE psswd = '"+psswd+"'  AND status = 1";
        RowMapper<UserModel> rowMapper = new UserRowMap();
        return this.jdbcTemplate.query(sql,rowMapper);
    }


    @Override
    public void updateUser(UserModel userModel) {
        String sql = "UPDATE t_user SET " +
                "username = ? " +
                "WHERE " +
                "iduser = ?";
        jdbcTemplate.update(sql,
                userModel.getUsername(),
                userModel.getIduser());
    }

    @Override
    public void updatePsswd(UserModel userModel) {
        String sql = "UPDATE t_user SET " +
                "psswd = ? " +
                "WHERE " +
                "iduser = ?";
        jdbcTemplate.update(sql,
                userModel.getPsswd(),
                userModel.getIduser());
    }
}
