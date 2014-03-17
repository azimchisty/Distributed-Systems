#!/bin/bash

echo "Check whether you are in Client Directory:"
javac ReadWriteClient.java
java ReadWriteClient $1 $2 $3
#java ReadWriteClient A.txt localhost 12340

exit
