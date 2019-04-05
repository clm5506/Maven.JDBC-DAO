package daos;

import models.Car;
import utils.DBType;
import utils.DBUtil;

import java.util.List;
import java.sql.*;

public class CarDAO extends DAO<Car> {


    private static final String INSERT = "INSERT INTO car.car_table" +
            "(make, model, year, color, vin)"+
            "values(?,?,?,?,?)";

    private static final String GET_ONE = "SELECT * FROM car.car_table WHERE id = ?";

    private static final String UPDATE = "UPDATE car.car_table set ID = ?, make = ?, model = ?, year = ?, color = ?, vin = ? where id = ?";

    private static final String DELETE = "DELETE FROM car.car_table WHERE id =?";

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
        return car;
    }

    public List<Car> findAll() {
        return null;
    }

    public Car update(Car dto) {

        Car car = null;

        try(PreparedStatement ps = this.connection.prepareStatement(UPDATE)){

        }catch(SQLException e){
            DBUtil.showErrorMessage(e);
        }

        return car;
    }

    public Car create(Car dto) {

        int key = -1;

        try(PreparedStatement pstmt = this.connection.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)){

            pstmt.setString(1,dto.getMake());
            pstmt.setString(2,dto.getModel());
            pstmt.setInt(3,dto.getYear());
            pstmt.setString(4,dto.getColor());
            pstmt.setString(5,dto.getVin());
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
        try(PreparedStatement preparedStatement = this.connection.prepareStatement(DELETE)){
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            DBUtil.showErrorMessage(e);
        }

    }
}
