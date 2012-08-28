package org.harbor;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jruby.embed.PathType;
import org.jruby.embed.ScriptingContainer;
import org.jruby.javasupport.JavaEmbedUtils.EvalUnit;

public class HarborServlet extends HttpServlet {
  
  private ScriptingContainer container;
  private EvalUnit ruby_unit;
  
  @Override
  public void init() {
    String classpath = getServletContext().getRealPath("/WEB-INF/classes");
    List<String> loadPaths = Arrays.asList(classpath.split(File.pathSeparator));
    container = new ScriptingContainer();
    container.getProvider().setLoadPaths(loadPaths);
  
    try {
      File directory = new File (".");
      System.out.println ("Current directory's canonical path: " 
        + directory.getCanonicalPath());
    } catch(IOException e) {
      System.out.println("Exceptione is =" + e.getMessage());
    }

        
    ruby_unit = container.parse(PathType.CLASSPATH, "src/main/ruby/hello_world.rb");
  }
  
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    container.setWriter(out);
    try {
      out.println("<html>");
      out.println("<head>");
      out.println("<title>Servlet ParsenRunServlet</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h3>Servlet ParsenRunServlet at " + request.getContextPath() + "</h3><div>");
        
      ruby_unit.run();
      
      out.println("</body>");
      out.println("</html>");
    } finally {
      out.close();
    }
  }
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {
    processRequest(request, response);
  }
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {
    processRequest(request, response);
  }
  
  @Override
  public String getServletInfo() {
    return "Parse-once-eval-many-times Sample";
  }
  
}