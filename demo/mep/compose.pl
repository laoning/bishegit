#!/usr/bin/perl

use warnings;
use strict;
use YAML;
use Data::Dumper;

my %ques_lib;
my $output_file;

local $/ = undef;
my $yamlstream = <>;
my $composition = Load($yamlstream);

my $object = $composition->{'object'};
for (@$object) {
    write_makefile($_);
}

sub write_makefile {
    my $obj = shift;
    if ( $obj =~ /paper/ ) {
        my $paper = $composition->{$obj};
        if ($paper) {
            $output_file = $obj . ".inc";
            open OUTPUT, ">", $output_file
                    or die "Can't open file $output_file !\n";
            make_paper($paper);
            close OUTPUT;
        }
    }
    elsif ( $obj =~ /answer/ ) {
        my $key = $obj;
        $key =~ s/answer/paper/;
        my $paper = $composition->{$key};
        if ($paper) {
            $output_file = $obj . ".inc";
            open OUTPUT, ">", $output_file
                    or die "Can't open file $output_file !\n";
            make_answer($paper);
            close OUTPUT;
        }
    }
}

sub make_paper {
    my $paper = shift;
    for (@$paper) {
        make_p_sec($_);
    }
}

sub make_p_sec {
    my $sec = shift;
    if (ref($sec)) {
        # It is a major question including some minor questions
        print OUTPUT "\\dati\n";
        make_p_question($sec->{'qutype'}, $sec->{'major'});

        my $minor_ques = $sec->{'minor'};
        for (@$minor_ques) {
            print OUTPUT "\\xiaoti\n";
            make_p_question($sec->{'qutype'}, $_) ;
        }
    }
    else {
        # Major question alone
        print OUTPUT "\\dati\n";
        make_p_question("default", $sec);
    }
}

sub make_p_question {
    my $qutype = shift;
    my $quid = shift;
    
    $qutype = ( $qutype ) ? $qutype : "default";

    if ( ! exists $ques_lib{$qutype} ) {
        read_in_ques_lib($qutype);
    }
    my $lib = $ques_lib{$qutype};
    if ( $lib->{$quid}->{'qu'} ) {
        print OUTPUT $lib->{$quid}->{'qu'} . "\n";
    }
    else {
    # for invalid question id
        print STDERR 
"##############################################
       !!!   INVALID QUESTION ID   !!!
##############################################\n";
    die "Found invalid question id.
question type = $qutype
question id   = $quid\n
";
    }
}

sub make_answer {
    my $paper = shift;
    for (@$paper) {
        make_a_sec($_);
    }
}

sub make_a_sec {
    my $sec = shift;
    if (ref($sec)) {
        # It is a major question including some minor questions
        print OUTPUT "\\dati\n";
        make_a_question($sec->{'qutype'}, $sec->{'major'});

        my $minor_ques = $sec->{'minor'};
        for (@$minor_ques) {
            print OUTPUT "\\xiaoti\n";
            make_a_question($sec->{'qutype'}, $_) ;
        }
    }
    else {
        # Major question alone
        print OUTPUT "\\dati\n";
        make_a_question("default", $sec);
    }
}

sub make_a_question {
    my $qutype = shift;
    my $quid = shift;
    
    $qutype = ( $qutype ) ? $qutype : "default";

    if ( ! exists $ques_lib{$qutype} ) {
        read_in_ques_lib($qutype);
    }
    my $lib = $ques_lib{$qutype};
    if ($lib->{$quid}->{'an'}) {
        print OUTPUT $lib->{$quid}->{'an'} . "\n";
    }
    else {
    # for invalid question id
        print STDERR 
"##############################################
       !!!   INVALID QUESTION ID   !!!
##############################################\n";
    die "Found invalid question id.
question type = $qutype
question id   = $quid\n
";
    }
}

sub read_in_ques_lib {
    my $qutype = shift;
   
    my $qulibfile = $composition->{'libpath'} . "/" . $qutype . ".yaml";

    open FILE, $qulibfile
            or die "Can't open the file $qulibfile !\n";
    $yamlstream = <FILE>;
    my $lib = Load($yamlstream);
    $ques_lib{$qutype} = $lib;
}
