package server;

import xml.WriterXML;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "DownloadXML",
        urlPatterns = "/downloadxml"
)
public class DownloadXMLServlet extends HttpServlet {

    protected void doHead(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Process request without content.
        processRequest(request, response, false);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        // Process request with content.
        processRequest(request, response, true);
    }

    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response, boolean content) throws IOException{
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=zoo.xml");
        if (content){
            WriterXML writer = new WriterXML();
            writer.writeXML(response.getOutputStream());
        }
    }
}
