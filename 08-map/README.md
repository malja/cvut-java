# Map implementation

## Assignment
From Soubory\Výukové materiály \data.zip, unzip the patents.txt file and save it to disk. 
It is a text file and contains number-text pairs. Write an application that first loads the
file and stores the pair in a variable of class Map. Then enter numbers in the cycle from
the keyboard and the application will find and print the appropriate text for the
entered number. Example: 8639àReference electrode for electrochemical measurements.
Be sure to handle the case that the key entered is not on in the Map. Attach the source code. 

## Note

There are multiple files named patents_`[something]`.txt. Original file was in ASCII charset,
which bugs my code editor set to UTF-8. So I converted it and named it `patents.txt`. File
named `patents_small.txt` is just a test file with few lines to test that everything works 
fine on the small scale :).

## Compilation

    cd 08-map
    javac ./solution/*.java

## Run

    java -cp -Dfile.encoding=UTF-8 . solution/Main ./patents.txt

