import re
import random
fileout = open("NewRes.txt","w")
file = open("Risultati.txt","r")
seed = 0
random.seed(0)

for line in file:
    num = random.choices([0,1,2,3,4], weights = [3, 3, 2, 2, 1])[0]
    seed+=1
    random.seed(seed)
    num2 = random.choices([0,1,2,3,4], weights = [3, 3, 2, 2, 1])[0]
    seed +=1
    random.seed(seed)
    line = line[:-6] + f"{num} - {num2}"
    fileout.write(line+"\n")
