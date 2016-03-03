package com.lehanh.pama.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.lehanh.pama.catagory.Catagory;
import com.lehanh.pama.catagory.CatagoryType;
import com.lehanh.pama.db.DatabaseManager;

public class CatagoryDao {

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
		Catagory cat = new Catagory(rs.getLong(ID_COL), CatagoryType.valueOf(rs.getString(TYPE_COL)));
		cat.setName(rs.getString(NAME_COL));
		cat.setDesc(rs.getString(DESC_COL));
		cat.setRefIdsText(rs.getString(REFIDS_COL));
		cat.setOtherDataText(rs.getString(OTHERDATA_COL));
		return cat;
	}

}