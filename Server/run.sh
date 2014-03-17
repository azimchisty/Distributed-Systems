#!/bin/bash

echo "Check whether you are in Server Directory:"
javac ReadWriteServer.java
java ReadWriteServer $1
#java ReadWriteServer 12340

exit
