BIN = bin/
SRC = src
STRUCTURE := $(shell find $(SRCDIR) -type d) 
CODEFILES := $(addsuffix /*,$(STRUCTURE))
CODEFILES := $(wildcard $(CODEFILES))
SRCFILES := $(filter %.java,$(CODEFILES))
output :
	javac -g -d $(BIN) $(SRCFILES)
	java -cp bin/ LispInterpreterPackage.LispInterpreterMain
clean :
	rm -rf $(BIN)*