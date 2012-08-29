java_import javax.servlet.http.HttpServlet

class HelloWorld < HttpServlet

  def doGet(request, response)
    writer = response.writer
    writer.println "Hello World!"
    if id = request.get_parameter("id")
      writer.println "The value of \"id\" is #{id.inspect}"
    end
  end
end