#! /bin/bash

#pwd

cd xyz

#echo $?

#判断上一个命令执行的返回值是否为0
if [ $? -eq 0 ]; then
    echo "success"
else
    echo "fail"
fi