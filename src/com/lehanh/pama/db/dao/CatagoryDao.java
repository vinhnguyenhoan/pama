package com.lehanh.pama.db.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.lehanh.pama.catagory.Catagory;
import com.lehanh.pama.catagory.CatagoryType;
import com.lehanh.pama.db.DatabaseManager;
import com.lehanh.pama.ui.util.MainApplication;
import com.lehanh.pama.util.PamaHome;

public class CatagoryDao implements IDao {

	private static final String CAT_TABLE = "catagory";
	private static final String ID_COL = "c_id";
	private static final String NAME_COL = "name";
	private static final String TYPE_COL = "c_type";
	private static final String DESC_COL = "c_desc";
	private static final String REFIDS_COL = "ref_ids";
	private static final String OTHERDATA_COL = "other_data";
	
	public List<Catagory> loadAllCatagory() throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DatabaseManager.getInstance().getConn();
			ps = conn.prepareStatement("select * from catagory");
			rs = ps.executeQuery();
			
			List<Catagory> result = new LinkedList<Catagory>();
			Catagory caC;
			while (rs.next()) {
				caC = populate(rs);
				result.add(caC);
			}
			return result;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	private Catagory populate(ResultSet rs) throws SQLException {
		Catagory cat = CatagoryType.valueOf(rs.getString(TYPE_COL)).createCatalog(rs.getLong(ID_COL));
		cat.setName(rs.getString(NAME_COL));
		cat.setDesc(rs.getString(DESC_COL));
		cat.setRefIdsText(rs.getString(REFIDS_COL));
		cat.setOtherDataText(rs.getString(OTHERDATA_COL));
		return cat;
	}

	public Long insert(Catagory item) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DatabaseManager.getInstance().getConn();
			ps = conn.prepareStatement("insert into " + CAT_TABLE + 
					" set name=?, c_type=?, c_desc=?, ref_ids=?, other_data=? ", Statement.RETURN_GENERATED_KEYS);
			int i = 1;
			ps.setString(i++, item.getName());
			ps.setString(i++, item.getType().toString());
			ps.setString(i++, item.getDesc());
			ps.setString(i++, item.getRefIdsText());
			ps.setString(i++, item.getOtherDataText());
			System.out.println("SQL: " + ps);
			int resultUpdate = ps.executeUpdate();
			if (resultUpdate <= 0) {
				return 0l;
			}
			ResultSet resultIdKey = ps.getGeneratedKeys();
			if (resultIdKey.next()) {
				Long resultId = resultIdKey.getLong(1);
				item.setId(resultId);
				return resultId;
			}
			throw new SQLException("Insert statement did not generate an AutoID"); //$NON-NLS-1$
			
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	public void update(Catagory item) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DatabaseManager.getInstance().getConn();
			ps = conn.prepareStatement("update " + CAT_TABLE + " set name, c_type, c_desc, ref_ids, other_data values(?, ?, ?, ?, ?) where item_id=?");

			int i = 1;
			ps.setString(i++, item.getName());
			ps.setString(i++, item.getType().toString());
			ps.setString(i++, item.getDesc());
			ps.setString(i++, item.getRefIdsText());
			ps.setString(i++, item.getOtherDataText());

			ps.setLong(i++, item.getId());
			System.out.println("SQL: " + ps);
			ps.executeUpdate();
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	public static void main(String[] args) {
		CatagoryDao catDao = new CatagoryDao();
		try {
			PamaHome.application = new MainApplication();
			DatabaseManager.initialize();
			List<Catagory> result = catDao.loadAllCatagory();
			System.out.println("result " + result.size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}