package com.uway.sports.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.uway.sports.web.entity.TableEntity;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class TableUtils {
	
	public static String getTableJsonString(List<?> list,int total,int draw){		
		StringBuilder buffer = new StringBuilder();
		
		buffer.append("{\"draw\":"+ draw +",\"recordsTotal\":" + total +",\"recordsFiltered\":" + total +",\"data\":");
		buffer.append(JSONObject.toJSONString(list));
		buffer.append("}");
		return buffer.toString();
	}
	
	public static String getTableJsonString(String jsonData,int total,int draw){		
		StringBuilder buffer = new StringBuilder();
		
		buffer.append("{\"draw\":"+ draw +",\"recordsTotal\":" + total +",\"recordsFiltered\":" + total +",\"data\":");
		buffer.append(jsonData);
		buffer.append("}");
		return buffer.toString();
	}


	public static String getTableJsonString(String jsonData,int total,int draw,String startTime){
		StringBuilder buffer = new StringBuilder();

		buffer.append("{\"draw\":"+ draw +",\"startTime\":\""+startTime+"\",\"recordsTotal\":" + total +",\"recordsFiltered\":" + total +",\"data\":");
		buffer.append(jsonData);
		buffer.append("}");
		return buffer.toString();
	}
	public static TableEntity getTableEntity(HttpServletRequest request){
		TableEntity tableHelper = new TableEntity();
		tableHelper.setDraw(Integer.parseInt(request.getParameter("draw")));
		tableHelper.setRowStart(Integer.parseInt(request.getParameter("start")));
		tableHelper.setPageSize(Integer.parseInt(request.getParameter("length")));
		tableHelper.setSearchValue(request.getParameter("search[value]"));
		String orderBy = "";
		for (int i = 0; i< 5; i++) {
            String colIndex =request.getParameter( "order[" + i + "][column]");
			if (StringUtils.isNoneBlank(colIndex)) {
                String colName = request.getParameter("columns[" + colIndex + "][name]");
                if(colName==null||colName.equals(""))
                    colName = request.getParameter("columns[" + colIndex + "][data]");
                String orderDir = request.getParameter("order[" + i + "][dir]");
				orderBy += StringUtils.toUnderScoreCase(colName) + " " + orderDir + ",";
			}
		}
		if (StringUtils.isNoneBlank(orderBy)) {
			orderBy = orderBy.substring(0, orderBy.length() - 1);
			tableHelper.setOrderBy(orderBy);
		}
		/*String orderColumnIndex = request.getParameter("order[0][column]");
		tableHelper.setOrderField(request.getParameter("columns[" + orderColumnIndex + "][data]"));
		tableHelper.setOrderDir(request.getParameter("order[0][dir]"));*/
		return tableHelper;
	}
	
	public static void getTableEntity(HttpServletRequest request, TableEntity entity){
		if (entity == null || request == null) {
			return;
		}
		entity.setDraw(Integer.parseInt(request.getParameter("draw")));
		entity.setRowStart(Integer.parseInt(request.getParameter("start")));
		//entity.setPageSize(Integer.parseInt(request.getParameter("length")));//原来默认前端传递的每页行数，暂时注释掉，改成默认20行
		entity.setPageSize(500);
		entity.setSearchValue(request.getParameter("search[value]"));
		String orderBy = "";
		for (int i = 0; i< 5; i++) {
            String colIndex = request.getParameter("order[" + i + "][column]");
			if (StringUtils.isNoneBlank(colIndex)) {
                String colName = request.getParameter("columns[" + colIndex + "][data]");
                String orderDir = request.getParameter("order[" + i + "][dir]");
				orderBy += colName + " " + orderDir + ",";
				/*} else {
					orderBy += StringUtils.toUnderScoreCase(colName) + " " + orderDir + ",";
				}*/
			}
		}
		if (StringUtils.isNoneBlank(orderBy)) {
			orderBy = orderBy.substring(0, orderBy.length() - 1);
			entity.setOrderBy(orderBy);
		}
	}
	
	public static void getTableEntityForLog(HttpServletRequest request, TableEntity entity){
		if (entity == null || request == null) {
			return;
		}
		entity.setDraw(Integer.parseInt(request.getParameter("draw")));
		entity.setRowStart(Integer.parseInt(request.getParameter("start")));
		entity.setPageSize(Integer.parseInt(request.getParameter("length")));
		entity.setSearchValue(request.getParameter("search[value]"));
		String orderBy = "";
		for (int i = 0; i< 5; i++) {
            String colIndex = request.getParameter("order[" + i + "][column]");
			if (StringUtils.isNoneBlank(colIndex)) {
                String colName = request.getParameter("columns[" + colIndex + "][name]");
                if(colName==null||colName.equals(""))
                    colName = request.getParameter("columns[" + colIndex + "][data]");
                String orderDir =  request.getParameter("order[" + i + "][dir]");
				orderBy += StringUtils.toUnderScoreCase(colName) + " " + orderDir + ",";
			}
		}
		if (StringUtils.isNoneBlank(orderBy)) {
			orderBy = orderBy.substring(0, orderBy.length() - 1);
			entity.setOrderBy(orderBy);
		}
	}


}
