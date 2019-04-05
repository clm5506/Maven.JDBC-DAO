package daos;

import models.Car;
import utils.DBType;
import utils.DBUtil;

import java.util.List;
import java.sql.*;

public class CarDAO extends DAO<Car> {

//    private int id;
//    private String make;
//    private String model;
//    private int year;
//    private String color;
//    private String vin;

    private static final String INSERT = "INSERT INTO car" +
            "(make, model, year, color, vin)"+
            "values(?,?,?,?,?,?,?,?)";

    private static final String GET_ONE = "SELECT * FROM player WHERE id = ?";

    public CarDAO(Connection conn) {
        super(conn);
    }

    public Car findById(int id) {

        Car car = null;

        try(PreparedStatement prepState = DBUtil.getConnection(DBType.MYSQL).prepareStatement(GET_ONE)){
            prepState.setInt(1,id);
            ResultSet rs = prepState.executeQuery();
            while(rs.next()){
                car = new Car();
                car.setId(rs.getInt("id"));
                car.setMake(rs.getString("make"));
                car.setModel(rs.getString("model"));
                car.setYear(rs.getInt("year"));
                car.setColor(rs.getString("color"));
                car.setVin(rs.getString("vin"));
            }
        } catch (SQLException e){
            DBUtil.showErrorMessage(e);
        }
        return null;
    }

    public List findAll() {
        return null;
    }

    public Car update(Car dto) {

        return null;
    }

    public Car create(Car dto) {

        int key = -1;

        try(PreparedStatement pstmt = this.connection.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)){

            pstmt.setInt(1,dto.getId());
            pstmt.setString(2,dto.getMake());
            pstmt.setString(3,dto.getModel());
            pstmt.setInt(4,dto.getYear());
            pstmt.setString(5,dto.getColor());
            pstmt.setString(6,dto.getVin());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();

            if( rs != null && rs.next()){
                key = rs.getInt(1);
            }

        } catch(SQLException e){
            DBUtil.showErrorMessage(e);
        }

        return this.findById(key);
    }

    public void delete(int id) {


    }
}
