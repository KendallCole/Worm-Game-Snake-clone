import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputController extends KeyAdapter {

    public Vec2d moveDir;
    private GamePanel game;

    public InputController(GamePanel game){
        this.moveDir = new Vec2d(0,0);
        this.game = game;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'w') {
            setMoveDir(new Vec2d(0,-1));
        }else if (e.getKeyChar() == 'a') {
            setMoveDir(new Vec2d(-1,0));
        }else if (e.getKeyChar() == 's') {
            setMoveDir(new Vec2d(0,1));
        }else if (e.getKeyChar() == 'd') {
            setMoveDir(new Vec2d(1,0));
        }
        if (e.getKeyChar() == 'r') {
            System.out.println("RESET");
            this.game.reset();
        }
        if (e.getKeyChar() == 'q') {
            this.game.worm.grow();
        }

        super.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e){
        if (e.getKeyChar() == 'w' || e.getKeyChar() == 'a' ||
             e.getKeyChar() == 's' || e.getKeyChar() == 'd') {
        }
        super.keyReleased(e);
    }

    public void setMoveDir(Vec2d newMoveDir) {
        if(newMoveDir.x == this.moveDir.x * -1 && newMoveDir.y == this.moveDir.y *-1) {
            return;
        }

        this.moveDir = newMoveDir;

    }

    public Vec2d getMoveDir() {
        return this.moveDir;
    }
}
