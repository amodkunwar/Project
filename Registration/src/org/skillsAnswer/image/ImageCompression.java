package org.skillsAnswer.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageCompression extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		Connection con = null;
		PreparedStatement pstmt = null;

		String qry = "insert into compress.image values(?,?,?,?,?,?,?,?)";

		String name = req.getParameter("nm");
		String id = req.getParameter("id");
		String email = req.getParameter("email");
		String pwd = req.getParameter("pwd");
		String mobile = req.getParameter("num");
		String gender = req.getParameter("gender");
		String loc = req.getParameter("loc");

		String file = req.getParameter("file");
		File input = new File(file);
		FileInputStream fis = new FileInputStream(input);
		BufferedImage image = ImageIO.read(input);

		Scanner scan = new Scanner(System.in);
		System.out.println("Give the name of the image.");
		String data = "F:/" + scan.next() + ".jpg";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=root");
			pstmt = con.prepareStatement(qry);
			pstmt.setString(1, name);
			pstmt.setString(2, id);
			pstmt.setString(3, email);
			pstmt.setString(4, pwd);
			pstmt.setString(5, mobile);
			pstmt.setString(6, gender);
			pstmt.setString(7, loc);
			
			pstmt.setBinaryStream(8, fis);
			int rs = pstmt.executeUpdate();

			if (rs == 0) {
				System.out.println("Data not Inserted!!!!");
			} else {
				System.out.println("Data successfully Inserted!!");
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		File output = new File(data);
		OutputStream out = new FileOutputStream(output);

		ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next();
		ImageOutputStream ios = ImageIO.createImageOutputStream(out);
		writer.setOutput(ios);

		ImageWriteParam param = writer.getDefaultWriteParam();

		if (param.canWriteCompressed()) {
			param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
			param.setCompressionQuality(0.5f);
		}

		System.out.println("Image Compressed");

		PrintWriter pw = resp.getWriter();
		pw.println("<html><body>" + "<h1> Welcome " + name + ". Your email id is " + email + ". Your id is " + id
				+ "</h1>"
				+ "<img src="+image+">"+ "</body></html>");
		pw.flush();
		pw.close();

		writer.write(null, new IIOImage(image, null, null), param);
		out.close();
		ios.close();
		writer.dispose();
	}
}
