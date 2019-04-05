import daos.CarDAO;
import models.Car;
import utils.DBType;
import utils.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException{
        Connection conn = null;

            try {
                conn = DBUtil.getConnection(DBType.MYSQL);

                CarDAO carDAO = new CarDAO(conn);

                Car car = new Car(null, "Honda", "Civic", 2018, "Grey", "234KLJ");

                Car carReturned = carDAO.create(car);

                System.out.println(carDAO.findById(carReturned.getId()));

            } catch (SQLException e) {
                e.printStackTrace();
            }finally{
                conn.close();
            }
    }
}
