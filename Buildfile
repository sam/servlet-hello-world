repositories.remote << "http://mirrors.ibiblio.org/pub/mirrors/maven2"
repositories.remote << "http://repo1.maven.org/maven2/"

define "hello-world-servlet" do
  project.version = "0.1.0"
  
  compile.with transitive("org.mortbay.jetty:servlet-api:jar:3.0.20100224")

  # compile.with transitive("org.jruby:jruby:jar:1.7.0.preview2")
  
  package(:gem).spec do |spec|
    spec.name                = "servlet-hello-world"
    spec.summary             = "Hello World example using a Servlet"
    spec.author              = "Sam Smoot"
    spec.homepage            = "https://github.com/sam/servlet-hello-world"
    spec.email               = "ssmoot+servlet-hello-world@gmail.com"

    spec.platform = "java"
    spec.files = Dir["src/main/ruby/**/*.rb"]
    # spec.require_path = "lib"
    
    spec.rdoc_options = [
      "--line-numbers",
      "--main", "README.textile",
      "--title", "Harbor Documentation"
    ]
    spec.add_dependency "buildr"
  end
  
  install do
    addon package(:gem)
  end
end