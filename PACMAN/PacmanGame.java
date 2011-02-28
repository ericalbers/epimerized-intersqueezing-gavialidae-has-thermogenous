import java.awt.event.KeyEvent;


public class PacmanGame extends Game {
	private int pillsLeft, score, timer;
	private Pacman pacman;
	private Ghost[] ghosts;
	
	public PacmanGame() {
		super();
		setUpGame();
		
	}
	
	/**
	 * 
	 */
	public void start() {
		System.out.println("test");
	}
	
	/**
	 * Sets up the map and all the players/items for a new game
	 */
	public void setUpGame() {
		score = 0;
		timer = 180;
		ghosts = new Ghost[4];
		
		setMap(new PacmanMap("PACMAN/pacmanMap.txt")); //set up the PacmanMap
		
		for(int x=0; x<getMap().getX(); x++) { //add LittlePillItems to every Empty Space
			for(int y=0; y<getMap().getY(); y++) {
				getMap().addMappable(new LittlePillItem(x,y));
				pillsLeft++;
			}
		}
		
		pacman = new Pacman(9,9,Direction.UP,3); //add pacman
		getMap().addMappable(pacman);
		addListener(pacman);
		pacman.spawn(getMap());
		
		int i=1; //i is a counter for which ghost is added
		for(Ghost ghost : ghosts) { //add ghosts
			ghost = new Ghost(i+7,5,Direction.UP,i);
			getMap().addMappable(ghost);
			addListener(ghost);
			ghost.spawn(getMap());
			i++;
		}
		
		BigPillItem bigPill = new BigPillItem(1,1); //add big pills
		getMap().addMappable(bigPill);
		bigPill = new BigPillItem(18,1);
		getMap().addMappable(bigPill);
		bigPill = new BigPillItem(1,9);
		getMap().addMappable(bigPill);
		bigPill = new BigPillItem(18,9);
		getMap().addMappable(bigPill);

	}
	
	/**
	 * Handles all the inputs from the keyboard. Inputs are taken from PacmanPanel onKeypress() method
	 * @param keycode passed from the PacmanPanel as a KeyEvent on it
	 */
	public void recieveInput(int keycode) {
		//if keycode is one of the arrow keys... move pacman and ghosts
		if (keycode==KeyEvent.VK_LEFT || keycode==KeyEvent.VK_RIGHT || keycode==KeyEvent.VK_UP || keycode==KeyEvent.VK_DOWN) {
			movePacman(keycode);
			moveGhosts(pacman.getX(),pacman.getY(),pacman.getState());
		}

		switch(keycode) {	
		case KeyEvent.VK_ESCAPE:
			System.out.println("Escape Pressed. Terminate");
			System.exit(0);
			break;
		}
	}
	
	public void movePacman(int keycode) {
		switch (keycode) {
		case KeyEvent.VK_LEFT:
			pacman.setDirection(Direction.LEFT);
			break;
		case KeyEvent.VK_RIGHT:
			pacman.setDirection(Direction.RIGHT);
			break;
		case KeyEvent.VK_UP:
			pacman.setDirection(Direction.UP);
			break;
		case KeyEvent.VK_DOWN:
			pacman.setDirection(Direction.DOWN);
			break;
		}
		pacman.updateLocation(getMap());
	}
	
	
	
	
	public static void main(String args[]) {
		PacmanGame tempGame = new PacmanGame(); //create the game
		new PacmanFrame(new PacmanPanel(tempGame)); //set up the frame
		tempGame.start(); //start the game
	}
	
}
