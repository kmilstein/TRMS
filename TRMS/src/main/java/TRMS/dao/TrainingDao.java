package TRMS.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import TRMS.pojos.Training;
import TRMS.util.ConnectionUtil;

public class TrainingDao implements Dao<Training> {

	private PreparedStatement ps;
	private ResultSet rs;
	private ConnectionUtil connUtil = new ConnectionUtil();
	private static Logger log = Logger.getRootLogger();

	public void setConnUtil(ConnectionUtil connUtil) {
		this.connUtil = connUtil;
	}

	@Override
	public Optional<Training> get(long id) {
		Training t = null;

		try (Connection conn = connUtil.createConnection()) {
			ps = conn.prepareStatement("select * from training where training_id = ? ");
			ps.setLong(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				long trainingId = rs.getLong("training_id");
				long empId = rs.getLong("employee_id");
				double cost = rs.getDouble("training_cost");
				LocalDate date = rs.getDate("training_date").toLocalDate();
				long gradingId = rs.getLong("grading_format_id");
				long typeId = rs.getLong("training_type_id");
				int status = rs.getInt("training_status");

				t = new Training(trainingId, empId, cost, date, gradingId, typeId, status);
			}
		} catch (SQLException e) {
			log.error("Exception thrown by get method in TrainingDao");
			e.printStackTrace();
		}

		log.info("Return training object");
		return Optional.ofNullable(t);
	}

	@Override
	public List<Training> getAll() {
		List<Training> trainingList = new ArrayList<>();

		try (Connection conn = connUtil.createConnection()) {
			ps = conn.prepareStatement("select * from training");
			rs = ps.executeQuery();

			while (rs.next()) {
				long trainingId = rs.getLong("training_id");
				long empId = rs.getLong("employee_id");
				double cost = rs.getDouble("training_cost");
				LocalDate date = rs.getDate("training_date").toLocalDate();
				long gradingId = rs.getLong("grading_format_id");
				long typeId = rs.getLong("training_type_id");
				int status = rs.getInt("training_status");

				trainingList.add(new Training(trainingId, empId, cost, date, gradingId, typeId, status));
			}

		} catch (SQLException e) {
			log.error("Exception thrown by getAll method in TrainingDao");
			e.printStackTrace();
		}

		log.info("Return training list");
		return trainingList;
	}

	@Override
	public void save(Training t) {
		String sql = "insert into training (employee_id, training_cost, training_date, grading_format_id,"
				+ " training_type_id, training_status) values (?, ?, ?, ?, ?, ?)";

		try (Connection conn = connUtil.createConnection()) {
			ps = conn.prepareStatement(sql);

			ps.setLong(1, t.getEmployeeId());
			ps.setDouble(2, t.getCost());
			ps.setDate(3, Date.valueOf(t.getDate()));
			ps.setLong(4, t.getGradingFormatId());
			ps.setLong(5, t.getTrainingTypeId());
			ps.setLong(6, t.getTrainingStatus());

			ps.executeUpdate();
			log.info("Training has been created");
		} catch (SQLException e) {
			log.error("Exception thrown by save method in TrainingDao");
			e.printStackTrace();
		}
	}
	
	public long saveWithReturnId(Training t) {
		long trainingId = 0;
		String sql = "insert into training (employee_id, training_cost, training_date, grading_format_id,"
				+ " training_type_id, training_status) values (?, ?, ?, ?, ?, ?)";

		try (Connection conn = connUtil.createConnection()) {
			ps = conn.prepareStatement(sql);

			ps.setLong(1, t.getEmployeeId());
			ps.setDouble(2, t.getCost());
			ps.setDate(3, Date.valueOf(t.getDate()));
			ps.setLong(4, t.getGradingFormatId());
			ps.setLong(5, t.getTrainingTypeId());
			ps.setLong(6, t.getTrainingStatus());

			ps.executeUpdate();
			log.info("Training has been created");
			
			ps = conn.prepareStatement("select * from training where employee_id = ? "
					+ "and training_date = ?");
			ps.setLong(1, t.getEmployeeId());
			ps.setDate(2, Date.valueOf(t.getDate()));
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				trainingId = rs.getLong("training_id");				
			}
			
		} catch (SQLException e) {
			log.error("Exception thrown by save method in TrainingDao");
			e.printStackTrace();
		}
		
		return trainingId;
	}

	@Override
	public void update(long id, Training t) {
		String sql = "UPDATE training SET training_cost = ?, training_date = ?, "
				+ "grading_format_id = ?, training_type_id = ?, training_status = ?, training_grade = ?" + 
				" WHERE training_id = ?";

		try (Connection conn = connUtil.createConnection()) {
			ps = conn.prepareStatement(sql);

			ps.setDouble(1, t.getCost());
			ps.setDate(2, java.sql.Date.valueOf(t.getDate()));
			ps.setLong(3, t.getGradingFormatId());
			ps.setLong(4, t.getTrainingTypeId());
			ps.setInt(5, t.getTrainingStatus());
			ps.setDouble(6, t.getGrade());
			ps.setLong(7, id);

			ps.executeUpdate();
			log.info("Training has been updated");
		} catch (SQLException e) {
			log.error("Exception thrown by update method in TrainingDao");
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Training t) {
		String sql = "DELETE FROM training WHERE training_id = ? ";

		try (Connection conn = connUtil.createConnection()) {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, t.getTrainingId());
			ps.executeUpdate();

			log.info("Training has been deleted");
		} catch (SQLException e) {
			log.error("Exception thrown by delete method in TrainingDao");
		}
	}
}
