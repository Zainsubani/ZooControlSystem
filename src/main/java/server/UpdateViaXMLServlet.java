package server;

import hibernate.entity.Animal;
import xml.ReaderXML;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(
        name = "UpdateViaXML",
        urlPatterns = "/updateviaxml"
)
@MultipartConfig(fileSizeThreshold = 50*1024*1024, // 50 MB
        maxFileSize = 150*1024*1024, // 150 MB
        maxRequestSize = 200*1024*1024 // 200 MB
)
public class UpdateViaXMLServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
        InputStream fileContent = filePart.getInputStream();
        ReaderXML reader = new ReaderXML();
        reader.parseXML(fileContent);
    }
}
