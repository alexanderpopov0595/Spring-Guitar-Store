package com.springguitarshop.service;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import com.springguitarshop.domain.Guitar;
import com.springguitarshop.domain.User;

public class GuitarShopServiceJdbcDAO implements GuitarShopService {	
	//SQL for USER
	
	//INSERT, DELETE and UPDATE USER
	private String SQL_INSERT_USER="INSERT INTO users (login, password, name, phone) VALUES (?,?,?,?)";
	private String SQL_UPDATE_USER="UPDATE users SET login=?, password=?, name=?, phone=? WHERE id=?";
	private String SQL_DELETE_USER="DELETE FROM users WHERE id=?";
	
	//SELECT USER
	private String SQL_SELECT_USER_BY_ID="SELECT id, login, password, name, phone FROM users WHERE id=?";
	private String SQL_SELECT_USER_BY_LOGIN="SELECT id, login, password, name, phone FROM users WHERE login=?";
	
	//SQL for GUITAR
	
	//INSERT, DELETE and UPDATE GUITAR
	private String SQL_INSERT_GUITAR="INSERT INTO guitars (id_user, name, type, price, description) VALUES (?,?,?,?,?)";
	private String SQL_UPDATE_GUITAR="UPDATE guitars SET  name=?, type=?, price=?, description=? WHERE id=?";
	private String SQL_DELETE_GUITAR="DELETE FROM guitars WHERE id=?";
	
	//SELECT GUITAR
	private String SQL_SELECT_GUITAR_BY_ID="SELECT * FROM guitars, users WHERE guitars.id=? AND guitars.id_user=users.id";
	private String SQL_SELECT_GUITARS="SELECT * FROM guitars WHERE name LIKE ? AND type LIKE ? AND price BETWEEN ? AND ? ORDER BY price LIMIT ?, ?";
	private String SQL_SELECT_ALL_GUITARS_FOR_USER="SELECT * FROM guitars WHERE guitars.id_user=?";
	
	//insert jdbcTemplate
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
	}
	//methods for users
	public void addUser(User user) {
		jdbcTemplate.update(SQL_INSERT_USER, user.getLogin(), user.getPassword(), user.getName(), user.getPhone());		
	}
	public void saveUser(User user) {
		jdbcTemplate.update(SQL_UPDATE_USER, user.getLogin(), user.getPassword(), user.getName(), user.getPhone(), user.getId());		
	}
	public User getUser(long id) {
		return jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_ID, new RowMapper<User>() {

			public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				User user=new User();
				user.setId(resultSet.getLong(1));
				user.setLogin(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				user.setName(resultSet.getString(4));
				user.setPhone(resultSet.getString(5));
				return user;
			}			
		}, id);		
	}
	public User getUser(String login) {		
		return jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_LOGIN, new RowMapper<User>() {
			public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				User user=new User();
				user.setId(resultSet.getLong(1));
				user.setLogin(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				user.setName(resultSet.getString(4));
				user.setPhone(resultSet.getString(5));
				return user;
				}			
		}, login);
	}
	public void deleteUser(long id) {
		jdbcTemplate.update(SQL_DELETE_USER, id);		
	}	
	//methods for guitars
	public long addGuitar(final Guitar guitar){    	
    	KeyHolder keyHolder = new GeneratedKeyHolder();
    	jdbcTemplate.update(
    	    new PreparedStatementCreator() {
    	        public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
    	            PreparedStatement pst =
    	                con.prepareStatement(SQL_INSERT_GUITAR, Statement.RETURN_GENERATED_KEYS);
    	         
    	            pst.setLong(1, guitar.getUser().getId());
    	            pst.setString(2, guitar.getName());
    	            pst.setString(3, guitar.getType());
    	            pst.setInt(4, guitar.getPrice());
    	            pst.setString(5,  guitar.getDescription());    	            
    	            return pst;
    	        }
    	    },
    	    keyHolder);      	
    	return keyHolder.getKey().longValue();
    }
	public void saveGuitar(Guitar guitar) {
		jdbcTemplate.update(SQL_UPDATE_GUITAR,  guitar.getName(), guitar.getType(), guitar.getPrice(), guitar.getDescription(), guitar.getId());		
	}
	public Guitar getGuitar(long id) {
		return jdbcTemplate.queryForObject(SQL_SELECT_GUITAR_BY_ID, new RowMapper<Guitar>() {
			public Guitar mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				Guitar guitar=new Guitar();
				guitar.setId(resultSet.getLong(1));				
				guitar.setName(resultSet.getString(3));
				guitar.setType(resultSet.getString(4));
				guitar.setPrice(resultSet.getInt(5));
				guitar.setDescription(resultSet.getString(6));
				guitar.setDate(resultSet.getDate(7));	
				User user=new User();
				user.setId(resultSet.getLong(8));
				user.setLogin(resultSet.getString(9));
				user.setName(resultSet.getString(11));
				user.setPhone(resultSet.getString(12));
				guitar.setUser(user);
				return guitar;
				}			
		}, id);	
	}	
	public void deleteGuitar(int id) {
		jdbcTemplate.update(SQL_DELETE_GUITAR, id);		
	}
	public List<Guitar> getAllGuitarsForUser( final User user) {
		return jdbcTemplate.query(SQL_SELECT_ALL_GUITARS_FOR_USER, new RowMapper() {
			public Guitar mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				Guitar guitar=new Guitar();
				guitar.setId(resultSet.getInt(1));					
				guitar.setUser(user);		
				guitar.setName(resultSet.getString(3));
				guitar.setType(resultSet.getString(4));		
				guitar.setPrice(resultSet.getInt(5));	
				guitar.setDescription(resultSet.getString(6));	
				guitar.setDate(resultSet.getDate(7));			
				return guitar;
			}			
		}, user.getId());
	}	
	public List<Guitar> selectGuitars(Map<String, String> params, int pageid, int perpage) {
		//change sql if order is lower
		if(params.get("order").equals("lower")) {
			SQL_SELECT_GUITARS="SELECT * FROM guitars WHERE name LIKE ? AND type LIKE ? AND price BETWEEN ? AND ?  order by price desc limit ?, ?";
		}
		//change sql if order is not set
		else if(params.get("order").equals("")) {
			SQL_SELECT_GUITARS="SELECT * FROM guitars WHERE name LIKE ? AND type LIKE ? AND price BETWEEN ? AND ?  order by date desc limit ?, ?";
		}
		return jdbcTemplate.query(SQL_SELECT_GUITARS, new RowMapper() {
			public Guitar mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				Guitar guitar=new Guitar();
				guitar.setId(resultSet.getInt(1));				
				guitar.setName(resultSet.getString(3));
				guitar.setType(resultSet.getString(4));
				guitar.setPrice(resultSet.getInt(5));
				guitar.setDescription(resultSet.getString(6));
				guitar.setDate(resultSet.getDate(7));					
				return guitar;			}			
		}, params.get("name"), params.get("type"), params.get("minprice"), params.get("maxprice"), pageid, perpage);
	}
	public int selectCount(Map<String, String> params) {	
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM guitars  WHERE name LIKE '"+params.get("name")+"' AND type LIKE '"+params.get("type")+"' AND price BETWEEN "+params.get("minprice")+" AND "+params.get("maxprice"), Integer.class);
	}
}
	
	


