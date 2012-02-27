#!/usr/bin/perl

# use Data::Dumper;

sub check_mark {
    my $yes_no = shift @_;
    if ( $yes_no ) {
        "\$\\surd\$";
    } else {
        "";
    }
}

$fname = shift @ARGV ;
open FCFG, $fname ;
while (<FCFG>) {
  chomp ;
  if ( $multi_line ) {
	  if ( /^\s*\\\s*$/ || /^\s*\}\s*$/ ) {
		# a single backslash or a single right brace
		# end multi_line mode
		$multi_line = 0;
		chomp $usrcfg{$cfg_key} ;
	  } else {
    		$usrcfg{$cfg_key} .= $_ . "\n";
	  }
  } else {
	  if ( /^[ \t]*[%#].*$/ ) {
	    # This is comment, do nothing
	  } elsif ( /[ \t]*(\S+)[ \t]+(.*$)/ ) {
	    $cfg_key = $1 ;
	    $cfg_val = $2 ;
	  if ( $cfg_val =~ /^\s*\\\s*$/ || $cfg_val =~ /^\s*\{\s*$/ ) {
		# a single backslash or a single left brace
		# begin multi_line mode
	      	$multi_line = 1;
		$usrcfg{$cfg_key} = "" ;
	    } else {
	    	$usrcfg{$cfg_key} = $cfg_val;
	    }
	  }
  }
}
close FCFG;

$fname = shift @ARGV ;
open FDAT, $fname ;
$multi_line = 0 ;
while (<FDAT>) {
  chomp ;
  if ( $multi_line ) {
	  if ( /^\s*\\\s*$/ || /^\s*\}\s*$/ ) {
		# a single backslash or a single right brace
		# end multi_line mode
		$multi_line = 0;
		chomp $usrdat{$dat_key} ;
	  } else {
    		$usrdat{$dat_key} .= $_ . "\n";
	  }
  } else {
  	if ( /^[ \t]*[%#].*$/ ) {
    	  # This is comment, do nothing
  	} elsif ( /[ \t]*(\S+)[ \t]+(.*$)/ ) {
	  $dat_key = $1 ;
	  $dat_val = $2 ;
	  if ( $dat_val =~ /^\s*\\\s*$/ || $dat_val =~ /^\s*\{\s*$/ ) {
		# a single backslash or a single left brace
		# begin multi_line mode
		$multi_line = 1;
		$usrdat{$dat_key} = "" ;
	  } else {
	  	$usrdat{$dat_key} = $dat_val;
	  }
	}
  }
}
close FDAT;

while (<>) {
  chomp;
  if ( /(^.*)@@([^@ \t,]+)@@(.*$)/ ) {
    $head = $1;
    $tail = $3;
    # Map to user config data
    $med = $usrcfg{$2};
    if ($med =~ /^\s*\{(.*)\}\s*$/ms) {
      # Find a pair of braces
      #  ...may be span multi lines.
      # That is a embeded script
      $med = $1;	# Strip the braces
      while ( $med =~ /\$([^ \t,\)]+)/ ) {
        # Search for 'variable'
        # Replace it with user data
        $med =~ s/\$([^ \t,\)]+)/"$usrdat{$1}"/;
      }
      $script = '$med = ' . $med . ";" ;
      eval $script ;
    } else {
      # General user data
      $med = $usrdat{$med};
    }
    print "$head";
    print "$med";
    print "$tail\n";
  } else {
    print "$_\n";
  }
}
#print Dumper(%usrdat);
