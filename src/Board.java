public class Board {
    Cell Grid[][];
    public int Width;
    public int Height;
    public int size;
    Board(int width, int height){
        this.Width = width;
        this.Height = height;
        this.size = width * height;
        this.Grid = new Cell[width][height];
        for(int i = 0; i < this.Grid.length; i++){
            for(int j = 0; j < this.Grid[0].length; j++){
                this.Grid[i][j] = new Cell();
            }
         }
    }

    public Cell[][] getGrid(){
        return this.Grid;
    }
}
