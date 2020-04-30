package xyz.geekself;

//Logic Core
public class Simulation {
    //Setup static value to avoid confuse
    public static int DEAD = 0;
    public static int ALIVE = 1;

    int width;
    int height;
    int[][] board;

    public Simulation(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new int[width][height];
    }

    public static Simulation copySimulation(Simulation simulation){
        Simulation copySimulation = new Simulation(simulation.width,simulation.height);
        for(int y=0; y<simulation.height;y++){
            for(int x=0; x<simulation.width;x++){
                copySimulation.setState(x,y,simulation.getState(x,y));
            }
        }

        return copySimulation;
    }
    /*
    public void printBoard(){
        System.out.println("---");
        for(int y=0; y<height; y++){
            String line = "|";
            for(int x=0; x<width; x++){
                if(this.board[x][y] == DEAD){
                    line += ".";
                }else{
                    line +=  "*";
                }
            }
            line += "|";
            System.out.println(line);
        }
        System.out.println("---\n");
    }
    */
    public void setAlive(int x, int y){
        this.setState(x,y,ALIVE);
    }

    public void setDead(int x,int y){
        this.setState(x,y,DEAD);
    }

    public void setState(int x, int y, int state){
        if(x<0 || x>=width){
            return;
        }
        if(y<0 || y>= height){
            return;
        }

        this.board[x][y] = state;
    }

    //rule
    public int countAliveNeighbours(int x, int y){
        int count = 0;

        count += getState(x-1,y-1);
        count += getState(x,y-1);
        count += getState(x+1,y-1);
        count += getState(x-1,y+1);
        count += getState(x,y+1);
        count += getState(x+1,y+1);
        count += getState(x-1,y);
        count += getState(x+1,y);

        return count;
    }

    public int getState(int x, int y){
        if(x<0 || x>=width){
            return DEAD;
        }
        if(y<0 || y>= height){
            return DEAD;
        }
        return this.board[x][y];
    }

    public void step(){
        int[][] newBoard = new int[width][height];

        for(int y=0; y<height; y++){
            for(int x=0; x<width; x++){
                int aliveNeighbour = countAliveNeighbours(x,y);

                if(getState(x,y) == ALIVE){
                    if(aliveNeighbour <2){
                        newBoard[x][y] = DEAD;
                    }else if(aliveNeighbour ==2 || aliveNeighbour ==3){
                        newBoard[x][y] = ALIVE;
                    }else if(aliveNeighbour >3){
                        newBoard[x][y] = DEAD;
                    }
                }else{
                    if(aliveNeighbour == 3){
                        newBoard[x][y] = ALIVE;
                    }
                }

            }
        }
        this.board = newBoard;
    }

    public static void main(String[] args) {
        /*
        Simulation simulation = new Simulation(8,5);

        simulation.setAlive(2,2);
        simulation.setAlive(3,2);
        simulation.setAlive(4,2);

        simulation.printBoard();
        simulation.step();
        simulation.printBoard();
        simulation.step();
        simulation.printBoard();
        simulation.step();

        System.out.println(simulation.countAliveNeighbours(3,2));

         */
    }



}
