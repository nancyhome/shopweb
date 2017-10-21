package com.tianyuHouWebMall.web.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.tianyuHouWebMall.domain.Category;
import com.tianyuHouWebMall.domain.Product;
import com.tianyuHouWebMall.service.AdminService;
import com.tianyuHouWebMall.utils.BeanFactory;
import com.tianyuHouWebMall.utils.CommonUtils;


public class AdminAddProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Product product = new Product();
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);

			List<FileItem> parseRequest = upload.parseRequest(request);
			for(FileItem item : parseRequest){
				boolean formField = item.isFormField();
				if(formField){
					String fieldName = item.getFieldName();
					String fieldValue = item.getString("UTF-8");
					
					map.put(fieldName, fieldValue);
					
				}else{
					String fileName = item.getName();
					String path = this.getServletContext().getRealPath("upload");
					InputStream in = item.getInputStream();
					OutputStream out = new FileOutputStream(path+"/"+fileName);//I:/xxx/xx/xxx/xxx.jpg
					IOUtils.copy(in, out);
					in.close();
					out.close();
					item.delete();
					
					map.put("pimage", "upload/"+fileName);
					
				}
				
			}
			
			BeanUtils.populate(product, map);
			//private String pid;
			product.setPid(CommonUtils.getUUID());
			//private Date pdate;
			product.setPdate(new Date());
			//private int pflag;
			product.setPflag(0);
			//private Category category;
			Category category = new Category();
			category.setCid(map.get("cid").toString());
			product.setCategory(category);
			
			AdminService service = (AdminService) BeanFactory.getBean("adminService");
			service.saveProduct(product);
			
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
