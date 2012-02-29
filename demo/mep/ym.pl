#!/usr/bin/perl

use YAML;

local $/ = undef;

my $yamlstream = <>;

my @contents = Load($yamlstream);

# Dump the Perl data structures back into YAML.
print Dump(@contents);

# YAML::Dump is used the same way you'd use Data::Dumper::Dumper
use Data::Dumper;
print Dumper(@contents);
