#!/usr/bin/env bash

#echo "start shell"
#
#echo "shell file name="$0
#
#echo "params="$1
#
#echo "all params count="$#
#echo "all params="$@
#echo "all params="$*
#
#for i in "$@";do
#    echo $i
#done
#
#
#echo "一共输入：$# 个参数"
#
#while [ "$1" != "" ]; do
#    echo "剩下$#个参数"
#    echo "参数：$1"
#    shift
#done


if cd /path/to/somewhere; then
  rm *
else
  echo "Could not change directory! Aborting." 1>&2
  exit 1
fi