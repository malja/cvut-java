# Assignment

1. Create a `BruteForce` class with a static variable `tickets`, a static variable `towns` and a member method of the class `bruteForce`.   
2. The variable `towns` is an array of strings that contains the names of cities: Prague, New York, London, Moscow, Beijing. 
3. The variable `tickets` is a table of fares between the cities given the `towns` . It is a two-dimensional array of numbers that 
signify prices. At the same time, "0" means that the connection between the cities does not exist or does not make sense. 
The table does not have to be symmetric. 

Example: 

| ↓ from   to: | Prague | New York | London | Moscow | Peking |
|--------------|--------|----------|--------|--------|--------|
| Prague | 0 | 4270 | 1799 | 2602 | 10236 |
| New York | 4270 | 0 | 2017 | 9873 | 14300 |
| London | 1799 | 3922 | 0 | 0 | 12110 |
| Moscow | 2602 | 9873 | 2435 | 0 | 7503 | 
| Peking | 10236 | 14300 | 12110 | 7503 | 0 | 

While the price of Prague -> London is the same as the price of London -> Prague, the direct connection London -> Moscow is not available, they have to fly via Prague. 

4. The `bruteForce` method returns a string and has two parameters, both of which are strings.  

5. The `bruteForce` method first performs input checks. If it detects an error, it terminates the calculation and returns a string with a brief description of the error. At least the following will be checked: 
    
    - if the variables `tickets` and `towns` exist, 
    - if `towns` has 5 elements and all of them are filled in, 
    - if the `tickets` are 5x5, if there are zeros on the main diagonal, if all distances are non-negative.  

6. If the entry is correct, the `bruteForce` method finds the shortest distance between the cities specified as parameters. It is sought by "brute force", ie. by calculating all combinations. For example, if the parameters "London" and "Moscow" are entered, then all combinations of cities through which you can get from London to Moscow are searched and the cheapest route is selected.  

7. The output of the `bruteForce` method is a string in which all cities to be flown through are listed. 

In the main program, create an instance of the BruteForce class , call its bruteForce method, and print the result for the journey from London to Beijing. 

# Note
For some reason, I tried finishing this assignment with A* algorithm. Solution is in `AStar.java` and `Node.java`. Solution for BruteForce is in `Bruteforce.java` and `Itinerary.java`.

# Compilation

    cd 06-bruteforce
    javac ./solution/*.java

# Run

    java -cp . solution/Main
