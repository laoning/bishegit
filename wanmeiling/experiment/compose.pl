#!/usr/bin/perl
use strict;
use warnings;
#use utf8 ;
#use encoding 'utf8' ;
use Data::Dumper;
use YAML;



sub read_common {
    open(my $fhcommon, 'dat/common')
        or die "Could not open the file dat/common.";
    local $/ = undef;
    $main::common = <$fhcommon>
}

sub read_student {
    open(my $fhstudent, 'dat/student')
        or die "Could not open the file dat/student.";
    while (<$fhstudent>) {
        chomp;
        s/\A\s+//; # striping the leading space
        # my ($name, $class, $id, $task) = split /\s+/ ;
        # print "[$name], [$class], [$id], [$task]\n";
        if ( /^#/ ) {
            # do nothing;
        }
        else {
            my @stu = split /\s\s\s\s+/ ;
            push @main::student, \@stu;
        }
    }
}

sub read_task {
    open(my $fhtask, 'dat/task.yaml')
        or die "Could not open the file dat/task.";
    local $/ = undef;
    my $yamlstream = <$fhtask>;
    $main::task_ref = Load($yamlstream);
}

sub college {
    my $cls = shift;
    $cls =~ /\A[kK]/    ?   "康尼"
                        :   "通信工程"
                        ;
}

sub major {
    my $cls = shift;
    $cls =~ /算通/          ?   "通信工程（计算机通信）"
        :   $cls =~ /媒/    ?   "通信工程（多媒体通信）"
        :                       "专业名称（To be fixed）"
        ;
}

sub plan {
    my $tsk = shift;
    my @pln = @{$main::task_ref};
    for (@pln) {
        if ($_->{"设计题目"} eq $tsk ) {
            return $_;
        }
    }
    return undef;
}

sub compose_assignment {
    while (@main::student) {
        my ($name, $class, $id, $task, $title) = @{shift @main::student};
        print '%' x 20 . "\n";
        print "任务书\n";
        print $main::common;
        print "院系名称\t\t@{[college($class)]}\n";
        print "专业\t\t\t@{[major($class)]}\n";
        print "学生姓名\t\t$name\n";
        print "班级\t\t\t$class\n";
        if ($title) {
            print "设计题目\t\t$title\n";
        }
        else {
            print "设计题目\t\t$task\n";
        }
        my $plan = plan($task);
        if ( defined($plan) ) {
            my $origdat = $plan->{"原始数据"};
            print "原始数据\t\t{\n$origdat}\n";
            my $content = $plan->{"内容和要求"};
            print "内容和要求\t\t{\n$content}\n";
            my $tobedone = $plan->{"应完成的文件"};
            print "应完成的文件\t\t{\n$tobedone}\n";
            my $bibliography = $plan->{"参考文献"};
            print "参考文献\t\t{\n$bibliography}\n";
            my $schedule = $plan->{"进度计划"};
            print "进度计划\t\t{!\n";
            my $series = 0;
            for (@{$schedule}) {
                print "$series\t\t{\n";
                print "\t$_->[0]\n";
                print "\t.\n";
                print "\t$_->[1]";
                print "}\n";
                $series++ ;
            }
            print "}!\n";
        }
        else {
            print <<"TEXTBLOCK";
原始数据    {
}
内容和要求  {
}
应完成的文件    {
}
参考文献    {
}
进度计划    {!
0       {
            .
        }
1       {
            .
        }
2       {
            .
        }
3       {
            .
        }
4       {
            .
        }
5       {
            .
        }
6       {
            .
        }
7       {
            .
        }
8       {
            .
        }
9       {
            .
        }
}!
TEXTBLOCK
        }
    }
    print "%" x 20 . "\n";
    print "所有任务书结束\n";
}

# Main entry
our $task_ref; 
our $common;
our @student;

read_common;
read_student;
read_task;

compose_assignment;
