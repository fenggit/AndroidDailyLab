#!/usr/bin/env bash

echo "input text > "
read text # 输入文本
echo "input data = $text"

echo "================="

echo "input name and age"
read name age # 输入多个变量
echo "name = $name , age = $age"

echo "================="
echo "input more agrs"
read
echo "input more data = $REPLY"