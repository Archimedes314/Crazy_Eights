package Card_Games;

import java.util.ArrayList;

public class Hand {
/*The cards in the players' hand*/
    private  ArrayList<Card> hand= new ArrayList<Card>();
/*Players' name*/
    private  String PlayerName;
/*Players' current score*/
    private int Score;

    public Hand(String PlayerName) {
        this.PlayerName =PlayerName;
        Score=0;
    }
/*Adds card to players hand*/
    public void AddCard(Card c){
        hand.add(c);

    }
/*Converts players' hand to String in the format:
PlayerName:         Score:
1) Card
2) Card
.
.
.
*/
    public void HandToString(){
        System.out.println(PlayerName+":\tScore:"+Score);
        for(int i=0; i<hand.size();i++){
            System.out.println((i+1)+") "+ hand.get(i).CardToString());
        }

    }

/*Removes card from players' hand*/
    public void RemoveCard(int i){
        hand.remove(i-1);

    }
/*calculates the total card value of a players' hand according to Standard Crazy Eight rules*/
    public int GetCardScore(){
        int Score=0;
        for(int i=0; i<hand.size();i++){
            int ScoreCard= hand.get(i).getValue();
            switch (ScoreCard){
                case 8:
                    Score+=50;
                    break;
                case Card.King:
                case Card.Queen:
                case Card.Jack:
                    Score+=10;
                    break;
                default:
                    Score+=ScoreCard;
                    break;

            }

        }
        return Score;

    }
/*Returns true if PLayer has no cards, false if not*/
    public boolean IsEmpty(){
        if(hand.size()==0){
            return true;
        }
        else{
            return false;
        }
    }
    /*Returns card at index i (NOT in array index format, i.e inputting 3 would mean returning the third card if the cards were listed as card1, card2...*/
    public Card getCard(int i){
        return hand.get(i-1);
    }

    public void UpdateScore(int S){
        Score+=S;

    }
/*Returns this players' current score*/
    public int getPlayerScore() {
        return Score;
    }
/*Returns the name of this player*/
    public String getPlayerName() {
        return PlayerName;
    }
/*Returns number of cards in this players' hand*/
    public int getHandSize(){
        return hand.size();
    }
/*Empties all cards in this hand to the JunkPile d*/
    public void EmptyAll(Deck d){
        for(int i=0; i<hand.size();i++){
            d.AddToJunk(hand.get(i));

        }
        hand.clear();

    }

}
