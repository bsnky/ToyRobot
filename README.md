# Robot 
 
**Author:** Mr.Nattasit Modaram 
**Type:** Java Maven Project 
 
# Git Guideline 
 
## Branch Strategy 
- main     : production-ready 
- develop  : integration branch 
- feature/*: new feature 
- hotfix/* : urgent fix 
 
## Commit Rules 
- One logical change per commit 
- Meaningful commit message 
- No build artifacts in repo 
 
## DO NOT COMMIT 
- target/ 
- logs 
- secrets 

### Commands
- `PLACE X,Y,F`  
  Places the robot at `(X,Y)` facing `F` where `F` is one of: `NORTH | EAST | SOUTH | WEST`  
  A `PLACE` outside the table is ignored.
- `MOVE`  
  Moves forward 1 unit (ignored if it would fall off).
- `LEFT` / `RIGHT`  
  Rotates the robot 90 degrees (position unchanged).
- `REPORT`  
  Prints the current state as `X,Y,F`.

### Examples
Example A:
```
PLACE 0,0,NORTH
MOVE
REPORT
```
Output:
```
0,1,NORTH
```

Example B:
```
PLACE 0,0,NORTH
LEFT
REPORT
```
Output:
```
0,0,WEST
```

Example C:
```
PLACE 1,2,EAST
MOVE
MOVE
LEFT
MOVE
REPORT
```
Output:
```
3,3,NORTH
```

#### Option A: Run with Maven (recommended)
```bash
mvn -q test
mvn -q package
```

Run from a file:
```bash
java -jar ./target/ToyRobot-0.0.1-SNAPSHOT-jar-with-dependencies.jar examples/example-c.txt
```

Run from standard input:
```bash
java -jar ./target/ToyRobot-0.0.1-SNAPSHOT-jar-with-dependencies.jar
```
Then type commands line-by-line and press Enter. Use `EXIT` to quit.

#### Option B: Compile in IDE
```bash
Git clone the repository
Go to "com.toyrobot.ToyRobotApp" then run the main Application
```
