
public class Cell {
    enum State {
        FRUIT,
        EMPTY,
        WORM,
    }
    public State state;
    public int id;
    public int IDS = 0;
    Cell(){
        this.state = State.EMPTY;
    }
}
