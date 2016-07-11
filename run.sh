#!/usr/bin/env bash

# example of the run script for running the rolling_median calculation with a python file, 
# but could be replaced with similar files from any major language

# I'll execute my programs, with the input directory venmo_input and output the files in the directory venmo_output
# python ./src/rolling_median.py ./venmo_input/venmo-trans.txt ./venmo_output/output.txt

# For this coding challenge Java is chosen for source code

# Compile all java classes
export CLASSPATH = $CLASSPATH "/Users/Nazli/Downloads/*.jar"
javac  ./src/*.java


echo "Finished compile ..."
# Run main class
java ./out/InsightPaymentGraph
