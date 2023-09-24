import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
    static final int TIME_BETWEEN_FRAMES = 50;
    static final int WIDTH = 500;
    static final int HEIGHT = 500;
    static final int UNIT = 20;
    static final int UNIT_TOTAL = (WIDTH * HEIGHT) / (UNIT * UNIT);
    Board board;
    Worm worm;
    InputController inputController;

    GamePanel(){
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setFocusable(true);
        this.addKeyListener(new InputController(this)); // Construct via addKeyListener and then grab for ref
        this.inputController = (InputController) (this.getKeyListeners()[0]); // ugly cast lul
        this.board = new Board(25, 25);
        this.worm = new Worm();
        placeFood();
        run();
    }
    public void reset(){
        this.worm = new Worm();
        this.inputController.setMoveDir(new Vec2d(0,0));
        for (Cell[] thisRow : this.board.getGrid()) {
            for (Cell thisCell : thisRow) {
                thisCell.state = Cell.State.EMPTY;
            }
        }
        placeFood();
    }
    public void run(){
        Timer timer = new Timer(TIME_BETWEEN_FRAMES, this);
        timer.start();
    }
    public void placeFood(){
        Random random = new Random();
        int full = 0;
        while(full++ < this.board.size){
            int x = random.nextInt(this.board.Width);
            int y = random.nextInt(this.board.Height);
            if(this.board.Grid[x][y].state == Cell.State.EMPTY){
                this.board.Grid[x][y].state = Cell.State.FRUIT;
                break;
            }
        }
        System.out.println("Can no longer place food :( ");
    }
    public void checkForFood(Vec2d curPos){
        if (this.board.Grid[curPos.x][curPos.y].state == Cell.State.FRUIT){
            this.board.Grid[curPos.x][curPos.y].state = Cell.State.EMPTY;
            this.worm.grow();
            placeFood();
        }
    }
    public boolean hasCollided(Vec2d curPos){
        if (curPos.x < 0 || curPos.y < 0 || curPos.x >= this.board.Width || curPos.y >= this.board.Height){
           return true;
        }
        Vec2d head = this.worm.getHead();

        int i = 0;
        for (Vec2d member : this.worm.getMembers()){
            if (member != head){
                if (member.x == head.x && member.y == head.y){
                   return true;
                }
            }else{
                checkForFood(curPos);
            }
        }
        return false;
    }
    public void updateGame(){
        Vec2d input = this.inputController.getMoveDir();
        Vec2d wormPos = this.worm.getPos();
        Vec2d newPos = new Vec2d(wormPos.x + input.x, wormPos.y + input.y);
        worm.moveTo(newPos);

        if (hasCollided(newPos)){
            reset();
        }
    }
    public void draw(Graphics g){
        Random rand = new Random();



        int x = 0;
        int y = 0;
        for (Cell[] thisRow : this.board.getGrid()){
            for(Cell thisCell : thisRow){
                if (thisCell.state == Cell.State.FRUIT){
//                    float cr = rand.nextFloat();
//                    float cg = rand.nextFloat();
//                    float cb = rand.nextFloat();
//                    Color randomColor = new Color(cr, cg, cb);

                    g.setColor(Color.RED);

                }else{
                    g.setColor(Color.black);
                }
                g.fillRect(UNIT * x , UNIT * y, UNIT, UNIT);
                y = (y + 1) % thisRow.length;
            }
            ++x;
        }

        //draw worm
        g.setColor(Color.pink);
        for (Vec2d wormMember: worm.getMembers()){
            g.fillRect(UNIT * wormMember.x , UNIT * wormMember.y, UNIT, UNIT);
        }


    }
    @Override
    public void actionPerformed(ActionEvent arg0) {
        updateGame();
        repaint();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
}
