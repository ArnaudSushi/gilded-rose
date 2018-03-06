# Gilded-Rose
[![Build Status](https://travis-ci.org/Stalkerz312/gilded-rose.svg?branch=master)](https://travis-ci.org/Stalkerz312/gilded-rose)
[![Coverage Status](https://coveralls.io/repos/github/Stalkerz312/gilded-rose/badge.svg?branch=master)](https://coveralls.io/github/Stalkerz312/gilded-rose?branch=master)


This project is a refactoring of the original [GildedRose-Refactoring-Kata](https://github.com/emilybache/GildedRose-Refactoring-Kata/tree/master/Java)
using the guidelines established for the [Project 4A](https://github.com/ledoyen/tp-java/tree/master/projet/4A_2018).

## Class Diagram

![Class Diagram](https://i.imgur.com/tLxnjUA.png)

## Spring API Rest

|       URL      	|                      Description                     	|              Parameters              	|       Return       	|
|:--------------:	|:----------------------------------------------------:	|:------------------------------------:	|:------------------:	|
| /get_types     	| Get a list of all possible items than can be created 	|                 none                 	| String list        	|
| /list_items    	| Get a list of all items currently in the database    	|                 none                 	| Item list          	|
| /buy_object    	| Get an item from the database                        	| type, name, quantity                 	| Item               	|
| /create_object 	| Add an item to the database                          	| type, name, price, quality, quantity 	| Id of item created 	|

## Examples

|                                    Example                                   	|                                                                   Sample Result                                                                   	|
|:----------------------------------------------------------------------------:	|:-------------------------------------------------------------------------------------------------------------------------------------------------:	|
| /get_types                                                                   	| ["AGED_BRIE","BACKSTAGE","SULFURAS","ITEM"]                                                                                                       	|
| /list_items                                                                  	| [{"name":"Sulfuras1","sellIn":500,"quality":100},{"name":"Sulfuras1","sellIn":500,"quality":100},{"name":"Sulfuras1","sellIn":500,"quality":100}] 	|
| /create_object?type=SULFURAS&name=Sulfuras1&price=500&quality=100&quantity=3 	| [1,2,3]                                                                                                                                           	|
| /buy_object?type=SULFURAS&name=Sulfuras1&quantity=1                          	| [{"name":"Sulfuras1","sellIn":500,"quality":100}]        