package Card_Games;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
/*The deck from which players can draw cards*/
    private ArrayList<Card> deck = new ArrayList<Card>();
/*The deck which players discard cards into*/
    private ArrayList<Card> junkPile= new ArrayList<Card>();
/*Deals standard 52 card deck without jokers*/
    public Deck() {


        int i=0;
        for(int s=0; s<4;s++){

            for(int v=1; v<=13; v++){

                deck.add(i,new Card(s,v));
                i++;

            }
        }




    }

/*Deals out the top card in thr deck*/
    public Card dealCard(){
        if(!IsEmpty()) {
            Card temp = deck.get(deck.size() - 1);
            deck.remove(deck.size()-1);
            return temp;
        }
        else{
            System.out.println("Deck is empty!!!");
            return null;
        }


    }
/*Randomizes deck*/
    public void Shuffle(){
        Random rand= new Random();
        for(int i=0; i<deck.size(); i++ ){
            int randomNum= rand.nextInt(deck.size());
            Card temp= deck.get(randomNum);
            deck.set(randomNum,deck.get(i));
            deck.set(i,temp);


        }
    }
/*Returns true if the deck is empty, false if not*/
    public boolean IsEmpty(){
        return deck.isEmpty();
    }


    /*Adds card c to junk pile*/
    public void AddToJunk(Card c){
        junkPile.add(c);
    }
/*Deals multiple(numCards) cards from the deck to a hand h*/
    public void DealToHand(Hand h, int numCards){
        for(int i=0; i<numCards;i++){
            h.AddCard(dealCard());
        }

    }
/*Clear previous deck with new deck containing the 52 standard deck cards (excluding jokers) and clears the junkPile ArrayList, the top card (topJunkCard) of the previous junkPile becomes first card of junkPile after clearing*/
    public void EmptyDeckFailSafe(Card topJunkCard){
        replaceDeck();

        junkPile.clear();
        junkPile.add(topJunkCard);
        deck.remove(topJunkCard);
        Shuffle();



    }
/*Clear previous deck with new deck containing the 52 standard deck cards (excluding jokers)*/
    public void replaceDeck() {
        deck.clear();
        int i=0;
        for(int s=0; s<4;s++){

            for(int v=1; v<=13; v++){

                deck.add(i,new Card(s,v));
                i++;

            }
        }
    }
/*Returns the uppermost card in the junkPile*/
    public Card ReturnTopJunkCard(){
        return junkPile.get(junkPile.size()-1);
    }

/*Creates new deck containing only cards of the standard 52 card deck (without jokers) that are not contained in any of the players' hands, also clears junkPile and previous top card of junkPile becomes first element of cleared junkPile*/
    public void PartialResupplyDeck(Hand[] h, Card TopCard){
        replaceDeck();


        for (Hand hand : h) {
            for (int z = 0; z < hand.getHandSize(); z++) {
                deck.remove(hand.getCard(z));
            }

        }
        junkPile.clear();
        junkPile.add(TopCard);







    }






}
