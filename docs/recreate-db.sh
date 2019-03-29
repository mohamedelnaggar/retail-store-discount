#!/bin/bash
echo this command is to drop and create new database you can use it to start a fresh installation
echo you need to enter mysql user password
mysql -uroot -p --execute='drop database if exists retail_store_discount; create database retail_store_discount DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci; '
