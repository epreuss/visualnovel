using UnityEngine;
using UnityEngine.UI;
using System.Collections;
using System.Text;


public class GameManager : MonoBehaviour {


	public GameObject leftSprite;
	public GameObject centerSprite;
	public GameObject rightSprite;
	public GameObject background;
	public GameObject textBox;
	public GameObject choiceQuestion;
	public GameObject choiceButton1;
	public GameObject choiceButton2;

	private AudioSource audioPlayer;
	private Parser parser;

	private string choiceScene1;
	private string choiceScene2;

	private bool gameOver = false;
	private bool waitingForChoice = false;
	private bool skipping = false;
	private float skipDelay = 0.15f;
	private float timerSkip = 0;
	

	void Start () {
		parser = GetComponent<Parser> ();
		audioPlayer = GetComponent<AudioSource> ();

		parser.LoadScene("scene0.scene");
		parser.ReadCommand ();	
	}	

	void Update () {
		bool canRead = !gameOver && !waitingForChoice;
		if (Input.GetKeyDown (KeyCode.Space) && canRead)
			parser.ReadCommand ();	
		if (Input.GetKey(KeyCode.LeftControl))
		{
			if (!skipping)
				timerSkip = 0;	
			skipping = true;
		}
		else
		{
			skipping = false;
		}
		if (skipping)
		{
			timerSkip += Time.deltaTime;
			if (timerSkip > skipDelay)
			{
				if (canRead)
					parser.ReadCommand ();	
				timerSkip = 0;
			}
		}		
	}

	private void ShowChoiceMenu(bool state){
		choiceQuestion.SetActive (state);
		choiceButton1.SetActive (state);
		choiceButton2.SetActive (state);
	}

	public void ResetScreen(){
		RemoveSprite (Side.left);
		RemoveSprite (Side.center);
		RemoveSprite (Side.right);
		//StopSong ();
		ChangeBackground ("");
		ChangeText ("", "");
	}

	public void AddSprite(string spriteName, Side side){
		Sprite sprite = Resources.Load<Sprite> ("sprites/" + spriteName);
		Image image;

		if (side == Side.left)
			image = leftSprite.GetComponent<Image> ();
		else if (side == Side.center)
			image = centerSprite.GetComponent<Image> ();
		else 
			image = rightSprite.GetComponent<Image> ();
				
		image.sprite = sprite;
		image.color = Color.white;
	}

	public void RemoveSprite(Side side){
		Image image;

		if (side == Side.left)
			image = leftSprite.GetComponent<Image> ();
		else if (side == Side.center)
			image = centerSprite.GetComponent<Image> ();
		else 
			image = rightSprite.GetComponent<Image> ();

		image.sprite = null;
		image.color = new Color (0,0,0,0);
	}

	public void ChangeBackground(string backgroundName){
		Sprite sprite = Resources.Load<Sprite> ("backgrounds/" + backgroundName);
		background.GetComponent<Image> ().sprite = sprite;
	}

	public void ChangeText(string speaker, string dialog){
		if (speaker == "")
			textBox.GetComponent<Text> ().text = dialog;
		else
			textBox.GetComponent<Text> ().text = speaker + ":\n" + dialog;
	}

	public void PlaySong(string songName){
		if (audioPlayer.isPlaying)
			return;
		AudioClip song = Resources.Load<AudioClip> ("musics/" + songName);

		audioPlayer.PlayOneShot (song);
		audioPlayer.volume = 0.2f;
		audioPlayer.loop = true;
	}

	public void StopSong(){
		audioPlayer.Stop ();
	}

	public void Choice(string question, string answer1, string answer2, string scene1, string scene2){
		ShowChoiceMenu (true);

		choiceQuestion.GetComponent<Text> ().text = question;
		choiceButton1.GetComponentInChildren<Text> ().text = answer1;
		choiceButton2.GetComponentInChildren<Text> ().text = answer2;
		choiceScene1 = scene1;
		choiceScene2 = scene2;

		waitingForChoice = true;
	}

	public void Choice1(){
		ShowChoiceMenu (false);
		waitingForChoice = false;
		parser.LoadScene (choiceScene1);
		parser.ReadCommand ();
	}

	public void Choice2(){
		ShowChoiceMenu (false);
		waitingForChoice = false;
		parser.LoadScene (choiceScene2);
		parser.ReadCommand ();
	}


	public void EndGame(){
		ResetScreen();		
		ChangeText ("", "Game Over");
		gameOver = true;
	}
}
