# INFO6205_526
## Team: Individual
Rajasekhar Reddy Duddugunta
### Bin Packing Problem
In the bin packing problem, objects of different volumes must be packed into a finite number of bins or containers each of volume V in a way that minimizes the number of bins used. In computational complexity theory, it is a combinatorial NP-hard problem. The decision problem (deciding if objects will fit into a specified number of bins) is NP-complete.
I have used Genetic algorithm to solve this problem.
 ### Used Parameters
 Population size is set to 100 individuals. In each generation tournament selection is used to select two parents that will produce 50 offspring chromosomes. For each parent two rounds of roulette wheel selection is performed and the parent with better fitness is selected.


mutation size:	2 genes


only accept mutations that improve fitness:	yes


crossover points:	2


sorted population:	yes


fitness sorting:	maximization


selection type:	tournament (roulette wheel)


selection size:	2


number of offspring to produce:	50


chromosomes to replace:	50


stop criterion type:	fitness change


stop criterion depth:	100 generations

