class CPoint {
    private int _x,_y;
    public CPoint(int x, int y) {
    		_x = x; _y = y;
    }
    public void move(int dx, int dy) {
        _x = _x + dx; _y = _y + dy;
    }
    public String toString() {
    	return String.format( "CPoint(%d, %d)", _x, _y );
    }
}

abstract class Command {
    public abstract void execute(CPoint selection);
	public abstract void unexecute();
	public abstract Command clone();
}

class MoveCommand extends Command {
    private CPoint _selection;
	private int _dx, _dy;
    // constructor
    public MoveCommand(int dx, int dy) {
		_dx = dx;
		_dy = dy;
		_selection = null;
	}
	public String toString() {
	    return String.format( "MoveCommand(%d, %d)", _dx,_dy ); 
	}
	@Override
	public void execute(CPoint selection) {	
	    _selection = selection;
		System.out.printf( "\t%s.execute(%s)\n", this, selection );
		if ( _selection != null ) {
			_selection.move(_dx, _dy);
		}
	}
	@Override
	public void unexecute() {
		System.out.printf( "\t%s.unexecute()\n", this );
		if ( _selection != null ) {
			_selection.move(-_dx, -_dy);
		}
	}
	@Override
	public Command clone() {
		System.out.printf("\tMoveCommand.clone()\n");
		return new MoveCommand(_dx, _dy);
	}
};

public class Main
{
	public static void main(String[] args) {
	    MoveCommand mc1 = new MoveCommand(1,1);		System.out.println( mc1 );
	    CPoint p1 = null;
	    mc1.execute(p1);
	    p1 = new CPoint(0,0);   
	    System.out.println( p1 );
	    // БЕЗ использования паттерна
	    p1.move(3,4);		
	    System.out.println( p1 );	
	    // ИСПОЛЬЗУЯ паттерн Command
	    mc1.execute(p1);	    
	    System.out.println( p1 );	
	    mc1.unexecute();		
	    System.out.println( p1 );	
	    MoveCommand mc2 = new MoveCommand(1,-1);		
	    System.out.println( mc2 );
	    mc2.unexecute();		
	    System.out.println( p1 );	
	    mc2.execute(p1);	    
	    System.out.println( p1 );
	    mc2.unexecute();		
	    System.out.println( p1 );
	    mc2.unexecute();		
	    System.out.println( p1 );	
	    // клонирование 
	    Command mc1_ = mc1.clone();   System.out.println( "mc1_ = "+mc1_ );
	    MoveCommand mc2_ = (MoveCommand)mc2.clone(); 	System.out.println( "mc2_ = "+mc2_ );
	} 
}

