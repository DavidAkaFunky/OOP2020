# $Id: Makefile,v 1.2 2011/09/25 14:49:28 david Exp $

JAVADIR=/usr/share/java
JARNAME=po-ubank-app

all:
	(cd src; javac -cp $(JAVADIR)/po-uuilib.jar:$(JAVADIR)/po-ubank-core.jar -encoding UTF-8 `find . -name \*.java`)
	(cd src; jar cvf ../$(JARNAME).jar `find . -name \*.class -o -name \*.java` )

clean:
	$(RM) $(JARNAME).jar `find . -name \*.class`
