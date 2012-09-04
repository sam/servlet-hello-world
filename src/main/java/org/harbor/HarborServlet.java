package org.harbor;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import org.jruby.embed.ScriptingContainer;

public class HarborServlet implements Servlet {
  
  private transient ServletConfig config;
  private ScriptingContainer container;
  private HttpServlet servlet;
  
  @Override
  public void init(ServletConfig config) {
    this.config = config;
    String classpath = config.getServletContext().getRealPath("/WEB-INF/classes");
    List<String> loadPaths = Arrays.asList(classpath.split(File.pathSeparator));
    container = new ScriptingContainer();
    container.setLoadPaths(loadPaths);
  
    container.runScriptlet("require 'pathname'");
    container.runScriptlet("require(Pathname::pwd + 'src/main/ruby/hello_world')");
    servlet = (HttpServlet) container.runScriptlet("HelloWorld.new");
  }
  
  @Override
  public void service(ServletRequest request, ServletResponse response)
  throws ServletException, IOException {
    servlet.service(request, response);
  }
  
  @Override
  public void destroy() { }
  
  @Override
  public String getServletInfo() {
    return "HarborServlet";
  }
  
  public ServletConfig getServletConfig() {
    return config;
  }
}