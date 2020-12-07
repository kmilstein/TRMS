package TRMS.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.Time;

import org.apache.log4j.Logger;

import TRMS.pojos.TrainingInfo;
import TRMS.util.ConnectionUtil;

public class TrainingInfoDao implements Dao<TrainingInfo> {

	private PreparedStatement ps;
	private ResultSet rs;
	private ConnectionUtil connUtil = new ConnectionUtil();
	private static Logger log = Logger.getRootLogger();

	public void setConnUtil(ConnectionUtil connUtil) {
		this.connUtil = connUtil;
	}

	@Override
	public Optional<TrainingInfo> get(long id) {
		TrainingInfo ti = null;

		try (Connection conn = connUtil.createConnection()) {
			ps = conn.prepareStatement("select * from training_info where training_id = ?");
			ps.setLong(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				long trainingId = rs.getLong("training_id");
				String description = rs.getString("training_description");
				LocalTime time = rs.getTime("training_time").toLocalTime();
				String location = rs.getString("training_location");
				String justification = rs.getString("training_justification");

				ti = new TrainingInfo(description, time, location, justification);
			}

		} catch (SQLException e) {
			log.error("Exception thrown by get method in TrainingInfoDao");
			e.printStackTrace();
		}

		log.info("Return training info object");
		return Optional.ofNullable(ti);
	}

	@Override
	public List<TrainingInfo> getAll() {
		List<TrainingInfo> ttList = new ArrayList<>();

		try (Connection conn = connUtil.createConnection()) {
			ps = conn.prepareStatement("select * from training_info");
			rs = ps.executeQuery();

			while (rs.next()) {
				long trainingId = rs.getLong("training_id");
				String description = rs.getString("training_description");
				LocalTime time = rs.getTime("training_time").toLocalTime();
				String location = rs.getString("training_location");
				String justification = rs.getString("training_justification");

				ttList.add(new TrainingInfo(description, time, location, justification));
			}

		} catch (SQLException e) {
			log.error("Exception thrown by getAll method in TrainingInfoDao");
			e.printStackTrace();
		}

		log.info("Return training info list");
		return ttList;
	}

	@Override
	public void save(TrainingInfo ti) {
		String sql = "insert into training_info (training_id, training_description, training_time, "
				+ "training_location, training_justification) values (?, ?, ?, ?, ?)";

		try (Connection conn = connUtil.createConnection()) {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, ti.getTrainingId());
			ps.setString(2, ti.getDescription());
			ps.setTime(3, Time.valueOf(ti.getTime()));
			ps.setString(4, ti.getLocation());
			ps.setString(5, ti.getJustification());

			ps.executeUpdate();
			log.info("Training Info has been created");
		} catch (SQLException e) {
			log.error("Exception thrown by save method in TrainingInfoDao");
			e.printStackTrace();
		}
	}

	@Override
	public void update(long id, TrainingInfo ti) {
		String sql = "update training_info set training_description = ?, training_time = ?, training_location = ?, "
				+ "training_justification = ? where training_id = ?";

		try (Connection conn = connUtil.createConnection()) {
			ps = conn.prepareStatement(sql);
			ps.setString(1, ti.getDescription());
			ps.setTime(2, Time.valueOf(ti.getTime()));
			ps.setString(3, ti.getLocation());
			ps.setString(4, ti.getJustification());
			ps.setLong(5, ti.getTrainingId());

			ps.executeUpdate();
			log.info("Training Info has been updated");
		} catch (SQLException e) {
			log.error("Exception thrown by update method in TrainingInfoDao");
			e.printStackTrace();
		}
	}

	@Override
	public void delete(TrainingInfo ti) {
		String sql = "DELETE from training_info WHERE training_id = ?";

		try (Connection conn = connUtil.createConnection()) {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, ti.getTrainingId());
			ps.executeUpdate();

			log.info("Training info has been deleted");
		} catch (SQLException e) {
			log.error("Exception thrown by delete method in TrainingInfoDao");
			e.printStackTrace();
		}
	}
}
