# I get an HTTP 405: HTTP method GET is not supported by this URL

java_import javax.servlet.http.HttpServlet

class HelloWorld < HttpServlet
  
  def doGet(request, response)
    response.writer.println "Hello World!"
  end
end