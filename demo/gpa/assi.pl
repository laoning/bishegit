#!/usr/bin/perl
use utf8 ;
use encoding 'utf8' ;


sub parse_line {
    $dict_href = shift;
    my $line = shift;
    if ( $expecting_val_end ) {
        $dict_href->{$key} .= "\n" . $line;
        if ( $line =~ m/ [}] \s* \z /xms ) {                #{
            $expecting_val_end = 0;
            # trim white space
            $dict_href->{$key} =~ s/ \A \s* (.*?) \s* \z/$1/xms;
            $dict_href->{$key} =~ s/ \A \s* [{] //xms;
            $dict_href->{$key} =~ s/ [}] \s* \z //xms;
        }
    }
    else {
        ( $key, $val ) = split /\s+/xms, $line, 2;
        # trim white space
        if ( $val =~ m/ \A \s* [{] /xms && $val !~ m/ [}] \s* \z /xms ) {
            $expecting_val_end = 1;
        }
        else {
            $val =~ s/ \A \s* (.*?) \s* \z/$1/xms;
            $val =~ s/ \A \s* [{] //xms;
            $val =~ s/ [}] \s* \z //xms;
        }
        $key =~ s/ \A \s* (.*?) \s* \z/$1/xms;
        $dict_href->{$key} = $val;
    }
}

sub create_cfg_dict {
    $fname = shift @ARGV ;
    open FCFG, $fname ;
    while (<FCFG>) {
        chomp ;
        # trim white space
        # s/^\s*(.*?)\s*$/$1/;
        $usrcfg_href = \%usrcfg;
        parse_line($usrcfg_href, $_);
    }
    close FCFG;
}

sub create_frm_txt {
    $fname = shift @ARGV ;
    open FFRM, $fname ;
    while (<FFRM>) {
        chomp ;
        push @usrfrm, $_ . "\n" ;
    }
    close FFRM;
}

sub substi_with_val_of {
    my $buff = shift ;
    if ($buff eq 'plan') {
        $buff = "";
        for ( sort (keys %plandat) ) {
            my $subplan = $plandat{$_};
            if ($subplan =~ m{ \A (.*?)                     # time
                            \s* [.] \s* \n                  # only a dot in a line as seperation
                            ( [^<]* | <[^<]* | << [^<] )    # task
                            (?:(?:<<<)(.*)(?:>>>))?         # remark
                            }xms
                ) {
                my ($time, $task, $remark) = ($1, $2, $3);
                $buff .= $time . "\t&\n" . $task . "\t&\n" . $remark . ' \\\\' ;
		# add a blank line for seprate, it's not neccessary.
                # $buff .= " \\\\[-0.8ex]"
                # $buff .= " \\\\[-0.01ex]"
            }
        }
        return $buff ;
    }
    else {
        $buff = $usrcfg{$buff};
        my $HAVE_VAR = qr/ ( [^@]* ) @@ ( [^@]+ ) @@ ( .* ) /xms;
        if ($buff =~ $HAVE_VAR) {
            while ( $buff =~ $HAVE_VAR ) {
                # Search for 'variable'
                # Replace it with user data
                $buff = $2 ;
                $buff = $usrdat{$buff} ;
                $buff = $1 . '"' . $usrdat{$2} . '"' . $3 ;
            }
            $script = '$result = ' . $buff . ';' ;
            
            eval $script ;
            return $result;
        }
        else {
            $buff = $usrdat{$buff};
            return $buff;
        }
    }
}

sub write_a_section {
    if ( keys %usrdat > 0  ) {
    # At least one section was read.
        for (@usrfrm) {
            my $lin = $_ ;
            while ( $lin =~ m/ ( [^@]* ) @@ ( [^@]+ ) @@ ( .* ) /xms ) {
                my ($head, $med, $tail) = ($1, $2, $3) ;
                $med = substi_with_val_of($med);
                $lin = $head . $med . $tail ;
            }
            print $lin;
        }
    }
}
# Main entry

create_cfg_dict;
create_frm_txt;

$usrdat_href = \%usrdat;
$plandat_href = \%plandat;
while (<>) {
    chomp;
    # skip the comment line
    next if m/ \A \s* % .* \z /xms ;

    my $trimmed_line = $_ ;
    $trimmed_line =~ s/ \A \s* (.*?) \s* \z /$1/xms;
    if ( $st_reading_plan ) {
        if ($trimmed_line eq "}!") {         #{
        # The end tag of plan lock is THAT.
            $st_reading_plan = 0;
        }
        else {
            parse_line($plandat_href, $_);
        }
    }
    else {
        my ($dat_key, $dat_val) = split /\s+/xms, $trimmed_line, 2; 
        if ( $trimmed_line eq "任务书" ) {
            write_a_section;
        }
        elsif ( $dat_key eq "进度计划" ) {
            $st_reading_plan = 1 ;
        }
        elsif ( $dat_key eq "所有任务书结束" ) {
            last ;
        }
        else {
            parse_line($usrdat_href, $_);
        }
    }
}
write_a_section;
