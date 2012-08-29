java_import javax.servlet.http.HttpServlet

class HelloWorld < HttpServlet

  def doGet(request, response)
    writer = response.writer
    writer.println "Hello World!"
    # NOTE: A Symbol is used here, which matches the 
    # Signature of HttpServletRequest#getParameter(String name)
    # So Symbols, which are interned strings in Ruby, must be
    # treated as Strings by JRuby. Meaning you can freely use
    # Symbols on Java Methods requiring a String Argument.
    # Long story short, if you're accessing a Java Map, then
    # there's no need for something like the common Ruby idiom
    # of a HashWithIndifferentAccess since both Ruby Symbols
    # and Ruby Strings will be dispatched appropriately to
    # Java methods requiring a String in the signature.
    if id = request.get_parameter(:id)
      writer.println "The value of \"id\" is #{id.inspect}"
    end
  end
end