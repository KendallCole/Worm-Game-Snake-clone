import java.util.ArrayList;

public class Worm {
    Vec2d pos;
    ArrayList<Vec2d> members;
    int len = 1;
    Worm(){
        this.pos = new Vec2d(0,0);
        this.members = new ArrayList<>();
        this.members.add(this.pos);
    }

    public Vec2d getPos(){
        int headX = this.getHead().x;
        int headY = this.getHead().y;

        return new Vec2d(headX, headY);
    }

    public Vec2d getHead(){
        return this.members.get(this.members.size()-1);
    }

    public void grow(){
        ++this.len;
    }
    public void grow(int amount){
        this.len += amount;
    }

    public void moveTo(Vec2d newPos){
        this.members.add(newPos);
        if (this.members.size() > this.len){
            this.members.remove(0);
        }
    }
    public ArrayList<Vec2d> getMembers(){
        return this.members;
    }
}
