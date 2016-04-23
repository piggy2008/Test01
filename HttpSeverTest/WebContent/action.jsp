<%@page import="javax.imageio.ImageIO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload,
					org.apache.commons.fileupload.disk.DiskFileItemFactory,
					java.io.*,
					java.awt.image.BufferedImage,
					java.util.*,
					org.apache.commons.fileupload.FileItem" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>upload.jsp</title>
</head>
<body>
<%
	if (ServletFileUpload.isMultipartContent(request)) {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//设定内存可以存放文件的最大容量，单位字节，超过的部分会以临时文件的形式存放在硬盘上，这里设置成10兆
		factory.setSizeThreshold(10485760);
		File temp = new File("/temp");  
       if(!temp.exists()){  
          temp.mkdirs();  
        }
		//设置临时文件存放的位置
       factory.setRepository(temp);
		
       ServletFileUpload upload = new ServletFileUpload(factory);
		//设置单个文件最大容量限制，-1表示没有限制  
		upload.setFileSizeMax(-1);
		//设置整个请求的上传容量限制，-1表示没有限制  
		upload.setSizeMax(-1);
		
		List<FileItem> items = upload.parseRequest(request);  
		Iterator<FileItem> iterator = items.iterator();
		FileItem upload_item = null;
		String desc = "";
		while (iterator.hasNext()) {
			FileItem item = iterator.next();
			if(item.isFormField()){
				//普通字段
				//out.print(item.getFieldName());//得到字段name属性的值 
				//out.print(item.getString("UTF-8"));//得到字段的值
				desc = item.getString("UTF-8");
			}else{
				//文件字段
				out.print("--------------");
				out.print(item.getFieldName());
				out.println(item.getContentType());//文件类型《application/pdf》
				out.println(item.getName());//文件名称《入门.pdf》
				System.out.println(item.getFieldName());
				System.out.println(item.getName());
				upload_item = item;
				BufferedImage img = ImageIO.read(upload_item.getInputStream());
				System.out.println("width:" + img.getWidth() + "----- height:" + img.getHeight());
			}
		}
	}
%>