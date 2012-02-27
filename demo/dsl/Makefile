CLASS = $(basename $(basename $@))
TARGET = $(subst .,,$(suffix $(basename $@)))

.SECONDARY:
.PHONY: all

all:  paper.a.pdf answer.a.pdf paper.b.pdf answer.b.pdf submit.pdf

%.pdf: %.tex %.inc %.aux.sam
	xelatex $(basename $@).tex
%.inc: cmps.yaml
	perl compose.pl cmps.yaml
%.tex: cfg/*.cfg common.dat mdl/*.mdl
	sed "s/abpaper[ \t]\+[ABC]/abpaper\t\t$(TARGET)/" common.dat > common.ddd
	perl mep.pl cfg/$(CLASS).cfg common.ddd mdl/$(CLASS).mdl > $@
%.aux.sam: %.aux
	-cmp -s $< $@ || cp $< $@
%.aux: %.tex %.inc
	if [ -f $@ ] ; then true ; else xelatex $< ; fi
tidy :
	rm -f *.aux *.log *.dvi *.ps *.sam *.ddd
clean : tidy
	rm -f *.tex
clobber : clean
	rm -f *.inc
	rm -f *.pdf
