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
  private HttpServlet servlet;
  
  @Override
  public void init() {
    String classpath = getServletContext().getRealPath("/WEB-INF/classes");
    List<String> loadPaths = Arrays.asList(classpath.split(File.pathSeparator));
    container = new ScriptingContainer();
    container.getProvider().setLoadPaths(loadPaths);
  
    container.runScriptlet("require 'pathname'");
    container.runScriptlet("require(Pathname::pwd + 'src/main/ruby/hello_world')");
    servlet = (HttpServlet) container.runScriptlet("HelloWorld.new");
  }
  
  @Override
  public void service(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {
    servlet.service(request, response);
  }
  
}