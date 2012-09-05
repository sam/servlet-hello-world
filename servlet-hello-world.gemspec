Gem::Specification.new do |s|
  s.name = "servlet-hello-world"
  s.summary = s.description = "Servlet Hello World example"
  s.author = "Sam Smoot"
  s.homepage = "https://github.com/sam/servlet-hello-world"
  s.email = "ssmoot@gmail.com"
  s.version = "0.1"
  s.platform = Gem::Platform::RUBY
  s.files         = `git ls-files`.split("\n")
  # s.test_files    = `git ls-files -- {test}/*`.split("\n")
  # s.executables   = "harbor"
  # s.require_paths = ["lib"]
end