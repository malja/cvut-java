# Average from big numbers

## Assignment
From Soubory\Výukové materiály\data.zip, unzip the numbers.txt file and save it to disk.
It is a text file and contains all integers. Write an application that loads the file
and calculates the average of these numbers. Attach the source code. 

## Note

This assignment was quite easy. The only problem is, that the sum of all numbers overflows maximum value of
Integer. So I had to use BigInteger class instead.

## Compilation

    cd 07-average
    javac ./solution/*.java

## Run

    java -cp . solution/Main

## Example output

    Loaded 15942 numbers.
    The sum is: 8295387272 and the average is: 520347