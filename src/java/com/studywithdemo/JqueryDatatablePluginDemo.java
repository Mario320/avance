package com.studywithdemo;

import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.json.*;

@SuppressWarnings("serial")
public class JqueryDatatablePluginDemo extends HttpServlet {

	 private String GLOBAL_SEARCH_TERM;
	private String COLUMN_NAME;
	private String DIRECTION;
	private int INITIAL;
	private int RECORD_SIZE;
	private String id_venta_SEARCH_TERM,id_linea_SEARCH_TERM,fecha_venta_SEARCH_TERM,descripcion_SEARCH_TERM;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String[] columnNames = { "id_venta", "id_linea", "fecha_venta", "descripcion"};

		JSONObject jsonResult = new JSONObject();
		int listDisplayAmount = 10;
		int start = 0;
		int column = 0;
		String dir = "asc";
		String pageNo = request.getParameter("iDisplayStart");
		String pageSize = request.getParameter("iDisplayLength");
		String colIndex = request.getParameter("iSortCol_0");
		String sortDirection = request.getParameter("sSortDir_0");
		
		if (pageNo != null) {
			start = Integer.parseInt(pageNo);
			if (start < 0) {
				start = 0;
			}
		}
		if (pageSize != null) {
			listDisplayAmount = Integer.parseInt(pageSize);
			if (listDisplayAmount < 10 || listDisplayAmount > 50) {
				listDisplayAmount = 10;
			}
		}
		if (colIndex != null) {
			column = Integer.parseInt(colIndex);
			if (column < 0 || column > 5)
				column = 0;
		}
		if (sortDirection != null) {
			if (!sortDirection.equals("asc"))
				dir = "desc";
		}

		String colName = columnNames[column];
		int totalRecords= -1;
		try {
			totalRecords = getTotalRecordCount();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		RECORD_SIZE = listDisplayAmount;
		GLOBAL_SEARCH_TERM = request.getParameter("sSearch");
		id_venta_SEARCH_TERM=request.getParameter("sSearch_0");
		id_linea_SEARCH_TERM=request.getParameter("sSearch_1");
		fecha_venta_SEARCH_TERM=request.getParameter("sSearch_2");
		descripcion_SEARCH_TERM=request.getParameter("sSearch_3");
		//STATE_SEARCH_TERM=request.getParameter("sSearch_4");
		//PHONE_SEARCH_TERM=request.getParameter("sSearch_5");
		COLUMN_NAME = colName;
		DIRECTION = dir;
		INITIAL = start;

		try {
			jsonResult = getPersonDetails(totalRecords, request);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		PrintWriter out = response.getWriter();
		out.print(jsonResult);

	}

	public JSONObject getPersonDetails(int totalRecords, HttpServletRequest request)
			throws SQLException, ClassNotFoundException {

		int totalAfterSearch = totalRecords;
		JSONObject result = new JSONObject();
		JSONArray array = new JSONArray();
		String searchSQL = "";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String dbConnectionURL = "jdbc:mysql://localhost:3306/inventario?user=root&password=";
		Connection con = DriverManager.getConnection(dbConnectionURL);
		String sql = "SELECT " + "id_venta, id_linea,fecha_venta,descripcion,"
				+ " " + "FROM " + "ventas " + "WHERE ";

		String globeSearch = "id_venta like '%" + GLOBAL_SEARCH_TERM + "%'"
				+ "or id_linea like '%" + GLOBAL_SEARCH_TERM + "%'"
				+ "or fecha_venta like '%" + GLOBAL_SEARCH_TERM + "%'"
				+ "or descripcion like '%" + GLOBAL_SEARCH_TERM + "%'";
				//+ "or state like  '%" + GLOBAL_SEARCH_TERM + "%'"
				//+ "or phone like '%" + GLOBAL_SEARCH_TERM + "%'";
		
		String idSearch="id_venta like " + id_venta_SEARCH_TERM + "";
		String nameSearch="id_linea like '%" + id_linea_SEARCH_TERM + "%'";
		String placeSearch="fecha_venta like '%" + fecha_venta_SEARCH_TERM + "%'";
		String citySearch=" descripcion like '%" + descripcion_SEARCH_TERM + "%'";
		//String stateSearch=" state like '%" + STATE_SEARCH_TERM + "%'";	
		//String phoneSearch=" phone like '%" + PHONE_SEARCH_TERM + "%'";
        //System.out.println(phoneSearch);
		if (GLOBAL_SEARCH_TERM != "") {
			searchSQL = globeSearch;
		}
		else if(id_venta_SEARCH_TERM !="")
		{
			searchSQL=idSearch;
		}
		else if(id_linea_SEARCH_TERM !="")
		{
			searchSQL=nameSearch;
		}
		else if(fecha_venta_SEARCH_TERM!="")
		{
			searchSQL=placeSearch;
		}
		else if(descripcion_SEARCH_TERM!="")
		{
			searchSQL=citySearch;
		}
		//else if(STATE_SEARCH_TERM!="")
		//{
			//searchSQL=stateSearch;
		//}
		//else if(PHONE_SEARCH_TERM!=null)
		//{
			//searchSQL=phoneSearch;
			//System.out.println(searchSQL);
		//}
        
		sql += searchSQL;
		sql += " order by " + COLUMN_NAME + " " + DIRECTION;
		sql += " limit " + INITIAL + ", " + RECORD_SIZE;
        System.out.println(sql);
        //for searching
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			JSONArray ja = new JSONArray();
			ja.put(rs.getString("id_venta"));
			ja.put(rs.getString("id_linea"));
			ja.put(rs.getString("fecha_venta"));
			ja.put(rs.getString("descripcion"));
			//ja.put(rs.getString("state"));
			//ja.put(rs.getString("phone"));
			array.put(ja);	
		}
		stmt.close();
		rs.close();

		String query = "SELECT " + "COUNT(*) as count " + "FROM " + "ventas " + "WHERE ";

		//for pagination
		if (GLOBAL_SEARCH_TERM != ""||id_venta_SEARCH_TERM  != "" || id_linea_SEARCH_TERM != "" ||fecha_venta_SEARCH_TERM!= ""||descripcion_SEARCH_TERM != ""  ) {
			query += searchSQL;

			
			PreparedStatement st = con.prepareStatement(query);
			ResultSet results = st.executeQuery();

			if (results.next()) {
				totalAfterSearch = results.getInt("count");
			}
			st.close();
			results.close();
			con.close();
		}
		try {
			result.put("iTotalRecords", totalRecords);
			result.put("iTotalDisplayRecords", totalAfterSearch);
			result.put("aaData", array);
		} catch (Exception e) {

		}

		return result;
	}

	public int getTotalRecordCount() throws SQLException {

		int totalRecords = -1;
		String sql = "SELECT " + "COUNT(*) as count " + "FROM " + "ventas";
        String dbConnectionURL = "jdbc:mysql://localhost:3306/inventario?user=root&password=";
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection con = DriverManager.getConnection(dbConnectionURL);

		PreparedStatement statement = con.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			totalRecords = resultSet.getInt("count");
		}
		resultSet.close();
		statement.close();
		con.close();

		return totalRecords;
	}

}
