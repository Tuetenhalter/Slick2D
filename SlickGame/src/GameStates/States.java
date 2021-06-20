package GameStates;

public enum States
{

	STARTMENU(0), GAME(100), PAUSEMENU(2), OPTIONSMENU(3), GAMEOVER(4), SHOP(5), ENDSCREENMENU(110);

	private int state;

	States(int state)
	{
		this.state = state;
	}

	public int getState()
	{
		return state;
	}

}
