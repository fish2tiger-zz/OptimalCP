# Color Paint Problem

The project is an attempt to solve the color paint problem.

## How algorithm works

The algorithm is a brute force approach:
```
1. Initialize an empty paint option array, and we will keep updating this to the end
2. When the customer order is not empty
    1. Loop all customer choices in the customer order
            - (1) When there's only one preferred color paint option,  
                1. Fill in this paint option when there is no conflict
                2. Or return "No solution exists" when conflicts exist
                3. Remove this customer choice from the order
                4. Conflicts may exist
            - (2) There are more than one preferred color paints
                1. Remove the the color choice 'G' from the current customer choice when there is 'M' for the same color from the paint option array
                2. Conflicts don't exist
            - (3) When there is no preferred color paint choice from this customer
                * Return "No solution exists"
    2. exit the loop (2) when there is potential conflict
3. Fill the paint option initialized in (1) with 'G' for the colors not picked by any customer 
```
***** Because the customer won't have more than one Matte.
The algorithm don't need to handle situation when one customer has more than 1 'M's:  
```
  2
  1 G 2 G
  1 M 2 M
``` 
 
## Time Complexity Analysis
The worst case's time complexity is ``O(M*N*L)``.
The `M` is the number of customer choices, and `N` is the max num of choices from one customer, `L` is the number of 
iteration it should happen to remove all the conflicts in the order.  

### Prerequisites

As the source code is in written in Java and built using Maven.
-  JDK 1.7+ 
-  Maven 3.5+

## Build the project (Maven wrapper)

```
# Test coverage report can be found in target/jacoco-ut/index.html
$ ./mvnw clean package (linux)
```
Or 
```
# Test coverage report can be found in target/jacoco-ut/index.html
$ ./mvnw.cmd clean package (windows)

```

## How to run
```
java -jar target/colorschoice-1.0-SNAPSHOT.jar [FILE_PATH]
```

## Built With
* [Maven](https://maven.apache.org/) - Dependency Management

## Author

* **Tianyang Wang** 
