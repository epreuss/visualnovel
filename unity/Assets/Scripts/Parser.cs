using UnityEngine;
using System.Collections;

public class Parser : MonoBehaviour {

	private GameManager gameManagerScript;
	private string[] lines;
	private int currentLine;


	void Awake () {
		gameManagerScript = GetComponent<GameManager> ();
	}

	public void LoadScene(string sceneName){
		lines = System.IO.File.ReadAllLines ("Assets/Resources/" + sceneName);
		gameManagerScript.ResetScreen();
		currentLine = 0;
	}

	public void ReadCommand(){
		string line = lines [currentLine++];
		bool stopNow = true;

		if (line.StartsWith ("changebg")) {
			ChangeBgCommand (line);
			stopNow = false;
		} else if (line.StartsWith ("addsprite")) {
			AddSpriteCommand (line);
			stopNow = false;
		} else if (line.StartsWith ("removesprite")) {
			RemoveSpriteCommand (line);
			stopNow = false;
		} else if (line.StartsWith ("playsong")) {
			PlaySongCommand (line);
			stopNow = false;
		} else if (line.StartsWith ("stopsong")) {
			StopSongCommand ();
			stopNow = false;
		} else if(line.StartsWith ("choice")){
			ChoiceCommand (line, lines[currentLine], lines[currentLine+1]);
		} else if (line.StartsWith ("dial"))
			DialogCommand (line);
		else if (line.StartsWith ("branch"))
			LoadSceneCommand (line);
		else if (line.StartsWith ("end"))
			EndGameCommand ();
		else // If command is not valid, reads the next one
			ReadCommand ();

		if (!stopNow)
			ReadCommand ();			
	}
	
	void ChangeBgCommand(string line){
		// Get the substring between quotes
		string backgroundString = line.Split (new string[] {"\"", "\""},System.StringSplitOptions.None)[1];
		// Get the substring before the .
		backgroundString = backgroundString.Split (new string[] {"."},System.StringSplitOptions.None)[0];
		gameManagerScript.ChangeBackground (backgroundString);
	}

	void AddSpriteCommand(string line){
		Side side;
		// Get the substring between parenthesis
		string sideString = line.Split (new string[] {"(", ")"},System.StringSplitOptions.None)[1];
		if (sideString == "left")
			side = Side.left;
		else if(sideString == "center")
			side = Side.center;
		else
			side = Side.right;

		// Get the substring between quotes
		string spriteString = line.Split (new string[] {"\"", "\""},System.StringSplitOptions.None)[1];
		// Get the substring before the .
		spriteString = spriteString.Split (new string[] {"."},System.StringSplitOptions.None)[0];

		gameManagerScript.AddSprite (spriteString, side);
	}

	void RemoveSpriteCommand(string line){
		Side side;
		// Get the substring between parenthesis
		string sideString = line.Split (new string[] {"(", ")"},System.StringSplitOptions.None)[1];
		if (sideString == "left")
			side = Side.left;
		else if(sideString == "center")
			side = Side.center;
		else
			side = Side.right;

		gameManagerScript.RemoveSprite (side);
	}

	void DialogCommand(string line){
		string speakerString = line.Split (new string[] {"\"", "\""},System.StringSplitOptions.None)[1];
		string dialogString = line.Split (new string[] {"\"", "\"", "\"","\""},System.StringSplitOptions.None)[3];

		gameManagerScript.ChangeText (speakerString, dialogString);
	}

	void PlaySongCommand(string line){
		// Get the substring between quotes
		string songString = line.Split (new string[] {"\"", "\""},System.StringSplitOptions.None)[1];
		// Get the substring before the .
		songString = songString.Split (new string[] {"."},System.StringSplitOptions.None)[0];

		gameManagerScript.PlaySong (songString);
	}

	void StopSongCommand(){
		gameManagerScript.StopSong ();
	}

	void ChoiceCommand(string questionCommand, string answerCommand1, string answerCommand2){

		string question = questionCommand.Split (new string[] {"\"", "\""},System.StringSplitOptions.None)[1];
		string scene1 = answerCommand1.Split(new string[] {"(", ")"},System.StringSplitOptions.None)[0];
		string scene2 = answerCommand2.Split(new string[] {"(", ")"},System.StringSplitOptions.None)[0];
		string answer1 = answerCommand1.Split(new string[] {"(", ")"},System.StringSplitOptions.None)[1];
		string answer2 = answerCommand2.Split(new string[] {"(", ")"},System.StringSplitOptions.None)[1];

		gameManagerScript.Choice (question, answer1, answer2, scene1, scene2);
	}

	void LoadSceneCommand(string line){
		string sceneString = line.Split (new string[] {"\"", "\""},System.StringSplitOptions.None)[1];		
		LoadScene (sceneString);
		gameManagerScript.ResetScreen();
	}

	void EndGameCommand(){
		gameManagerScript.EndGame ();
	}

}
