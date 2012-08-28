require "java"

java_import javax.servlet
java_import javax.servlet.http

class HelloWorld < HttpServlet
  def do_get(request, response)
    response.writer.println "Hello World!"
  end
end