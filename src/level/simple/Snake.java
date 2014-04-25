package level.simple;

import java.awt.Point;
import java.util.ArrayList;

class Snake 
{
    static ArrayList<Point> body;
    static int x, y;
    static int length = 0;
    
    Snake(int x, int y)
    {
        body = new ArrayList<>();
        Snake.x = x; Snake.y = y;
        Point head = new Point(x, y);        
        body.add(head);
        Point tail = new Point(x + 1, y);
        body.add(tail);
        length = 2;
        fillField();
    }
    
    static void fillField()
    {
        try
        {
            for(Point p : body)
            {
                Level.field[p.y][p.x] = 2;
            }
        }catch(Exception e) {};
    }
    
    static public void moveUp()
    {
        x--;
        removeTail();
        add();
    }
    
    static public void moveLeft()
    {
        y--;
        removeTail();
        add();
    }
    
    static public void moveDown()
    {
        x++;
        removeTail();
        add();
    }
    
    static public void moveRight()
    {
        y++;
        removeTail();
        add();
    }
    
    static private void removeTail()
    {
        boolean c = false;
        while(!c)
            try
            {
                c = true;
                body.remove(body.size() - 1);
            }catch(Exception e)
            {
                c = false;
            }
    }
    
    static private void add()
    {
        body.add(0, new Point(x, y));
    }
    
    static private void add(int x, int y)
    {
        body.add(new Point(x, y));
    }
    
    static public void addTail()
    {
        length++;
        int lastx = body.get(body.size() - 1).y;
        int lasty = body.get(body.size() - 1).x;
        int prelastx = body.get(body.size() - 2).y;
        int prelasty = body.get(body.size() - 2).x;
        if(lastx == prelastx)
        {
            if(prelasty > lasty)
            {
                if(Level.field[lastx][lasty + 1] == 0)
                {
                    add(lastx, lasty + 1);
                }
                else
                {
                    choseConsiq(lastx, lasty);
                }
            }
            else
            {
                if(Level.field[lastx][lasty - 1] == 0)
                {
                    add(lastx, lasty - 1);
                }
                else
                {
                    choseConsiq(lastx, lasty);
                }
            }
        }
        else
        {
            if(lastx > prelastx)
            {
                if(Level.field[lastx-1][lasty] == 0)
                {
                    add(lastx - 1, lasty);
                }
                {
                    choseConsiq(lastx, lasty);
                }
            }
            else
            {
                if(Level.field[lastx + 1][lasty] == 0)
                {
                    add(lastx + 1, lasty);
                }
                else
                {
                    choseConsiq(lastx, lasty);
                }
            }
        }        
    }
    
    static private void choseConsiq(int lastx, int lasty)
    {
        if(Level.field[lastx - 1][lasty] == 0)
        {
            body.add(new Point(lastx - 1, lasty));
        }
        else if(Level.field[lastx + 1][lasty] == 0)
        {
            body.add(new Point(lastx + 1, lasty));
        }
        else if(Level.field[lastx][lasty - 1] == 0)
        {
            body.add(new Point(lastx, lasty - 1));
        }
        else if(Level.field[lastx][lasty + 1] == 0)
        {
            body.add(new Point(lastx, lasty + 1));
        }
    }
    
}