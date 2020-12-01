package TRMS.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import TRMS.pojos.GradingFormat;
import TRMS.util.ConnectionUtil;

public class GradingFormatDao implements Dao<GradingFormat> {
	
	private PreparedStatement ps;
	private ResultSet rs;
	private ConnectionUtil connUtil = new ConnectionUtil();
	private static Logger log = Logger.getRootLogger();


	public void setConnUtil(ConnectionUtil connUtil) {
		this.connUtil = connUtil;
	}

	@Override
	public Optional<GradingFormat> get(long id) {
		GradingFormat gf = null;
		
		try (Connection conn = connUtil.createConnection()) {
			ps = conn.prepareStatement("select * from grading_format where grading_format_id = ? ");
			ps.setLong(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				long formatId = rs.getLong("grading_format_id");
				String name = rs.getString("grading_format_name");
				int minPass = rs.getInt("grading_pass_minimum");
				
				gf = new GradingFormat(formatId, name, minPass);
			}
			
		} catch (SQLException e) {
			log.error("Exception thrown by get method in GradingFormatDao");
			e.printStackTrace();
		}
		
		log.info("Return grading object");
		return Optional.ofNullable(gf);
	}

	@Override
	public List<GradingFormat> getAll() {
		List<GradingFormat> gfList = new ArrayList<>();
		
		try (Connection conn = connUtil.createConnection()) {
			ps = conn.prepareStatement("select * from grading_format");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				long formatId = rs.getLong("grading_format_id");
				String name = rs.getString("grading_format_name");
				int minPass = rs.getInt("grading_pass_minimum");
				
				gfList.add(new GradingFormat(formatId, name, minPass));
			}
			
		} catch (SQLException e) {
			log.error("Exception thrown by getAll method in GradingFormatDao");
		}
		
		log.info("Return grading format list");
		return gfList;
	}

	@Override
	public void save(GradingFormat gf) {
		String sql = "insert into grading_format (grading_format_name) values (?)";
		
		try (Connection conn = connUtil.createConnection()) {
			ps = conn.prepareStatement(sql);
			ps.setString(1, gf.getName());
			ps.executeUpdate();
			
			log.info("Grading Format has been created");
		} catch (SQLException e) {
			log.error("Exception thrown by save method in GradingFormatDao");
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(long id, GradingFormat gf) {
		String sql = "UPDATE grading_format SET grading_format_name = ?, grading_pass_minimum = ? "
				+ "WHERE training_type_id = ?";
		
		try (Connection conn = connUtil.createConnection()) {
			ps = conn.prepareStatement(sql);
			ps.setString(1, gf.getName());
			ps.setInt(2, gf.getMinimumPassingScore());
			ps.setLong(3, id);

			ps.executeUpdate();
			log.info("Grading Format has been updated");
		} catch (SQLException e) {
			log.error("Exception thrown by update method in GradingFormatDao");
			e.printStackTrace();
		}
	}

	@Override
	public void delete(GradingFormat gf) {
		String sql = "DELETE from grading_format WHERE grading_format_id = ?";
		
		try (Connection conn = connUtil.createConnection()) {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, gf.getGradingFormatId());
			ps.executeUpdate();

			log.info("Grading Format has been deleted");
		} catch (SQLException e) {
			log.error("Exception thrown by delete method in GradingFormatDao");
			e.printStackTrace();
		}
	}
}
