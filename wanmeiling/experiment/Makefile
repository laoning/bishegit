.PHONY: all
all: assignment.pdf

assignment.pdf: dat/assi.dat
	./assi.pl cfg/assi.cfg mdl/assi.frm.tex dat/assi.dat > input.tex
	cp mdl/assi.mdl.tex assignment.tex
	xelatex assignment.tex
dat/assi.dat: dat/common dat/task.yaml dat/student
	./compose.pl > dat/assi.dat
dat/task.yaml: dat/assi.md md2yaml.pl
	perl md2yaml.pl dat/assi.md > dat/task.yaml
tidy :
	rm -f *.aux *.log *.dvi *.ps
clean : tidy
	rm -f *.tex
clobber : clean
	rm -f *.pdf
