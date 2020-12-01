package TRMS.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import TRMS.pojos.TrainingType;
import TRMS.util.ConnectionUtil;

public class TrainingTypeDao implements Dao<TrainingType> {

	private PreparedStatement ps;
	private ResultSet rs;
	private ConnectionUtil connUtil = new ConnectionUtil();
	private static Logger log = Logger.getRootLogger();

	public void setConnUtil(ConnectionUtil connUtil) {
		this.connUtil = connUtil;
	}

	@Override
	public Optional<TrainingType> get(long id) {
		TrainingType tt = null;

		try (Connection conn = connUtil.createConnection()) {
			ps = conn.prepareStatement("select * from training_type where training_type_id = ?");
			ps.setLong(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				long typeId = rs.getLong("training_type_id");
				String name = rs.getString("training_type_name");
				double coverage = rs.getDouble("training_type_coverage");

				tt = new TrainingType(typeId, name, coverage);

			}

		} catch (SQLException e) {
			log.error("Exception thrown by get method in TrainingTypeDao");
			e.printStackTrace();
		}

		log.info("Return Training type object");
		return Optional.ofNullable(tt);
	}

	@Override
	public List<TrainingType> getAll() {
		List<TrainingType> ttList = new ArrayList<>();
		
		try (Connection conn = connUtil.createConnection()) {
			ps = conn.prepareStatement("select * from training_type");
			rs = ps.executeQuery();

			while (rs.next()) {
				long typeId = rs.getLong("training_type_id");
				String name = rs.getString("training_type_name");
				double coverage = rs.getDouble("training_type_coverage");

				ttList.add(new TrainingType(typeId, name, coverage));
			}

		} catch (SQLException e) {
			log.error("Exception thrown by getAll method in TrainingTypeDao");
			e.printStackTrace();
		}
		
		log.info("Return training type list");
		return ttList;
	}

	@Override
	public void save(TrainingType t) {
		String sql = "insert into training_type (training_type_name, training_type_coverage) values (?, ?)";

		try (Connection conn = connUtil.createConnection()) {
			ps = conn.prepareStatement(sql);
			ps.setString(1, t.getName());
			ps.setDouble(2, t.getPercentCovered());
			ps.executeUpdate();
			
			log.info("Training Type has been created");
		} catch (SQLException e) {
			log.error("Exception thrown by save method in TrainingTypeDao");
			e.printStackTrace();
		}

	}

	@Override
	public void update(long id, TrainingType t) {
		String sql = "UPDATE training_type SET training_type_name = ?, training_type_coverage = ? "
				+ "WHERE training_type_id = ?";

		try (Connection conn = connUtil.createConnection()) {
			ps = conn.prepareStatement(sql);
			ps.setString(1, t.getName());
			ps.setDouble(2, t.getPercentCovered());
			ps.setLong(3, id);

			ps.executeUpdate();
			log.info("Training Type has been updated");
		} catch (SQLException e) {
			log.error("Exception thrown by update method in TrainingTypeDao");
			e.printStackTrace();
		}
	}

	@Override
	public void delete(TrainingType t) {
		String sql = "DELETE from training_type WHERE training_type_id = ?";

		try (Connection conn = connUtil.createConnection()) {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, t.getTrainingTypeId());
			ps.executeUpdate();

			log.info("Training type has been deleted");
		} catch (SQLException e) {
			log.error("Exception thrown by delete method in TrainingTypeDao");
			e.printStackTrace();
		}
	}
}
