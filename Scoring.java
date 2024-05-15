import java.util.ArrayList;
public class Scoring {
    String rating;
    Card[] hand;

    public Scoring(Card[] cards) {
        hand = cards;
    }

    public static void main(String[] args)
    {
        Card[] bob = new Card[7];
        bob[0] = new Card(0, 5);
        bob[1] = new Card(0, 10);
        bob[2] = new Card(0, 1);
        bob[3] = new Card(1, 11);
        bob[4] = new Card(2, 12);
        bob[5] = new Card(3, 5);
        bob[6] = new Card(1, 1);
        Scoring joe = new Scoring(bob);
        System.out.println(joe.valualateHand());
        
    }

    public String valualateHand()
    {
        String finalhand = new String("");
        ArrayList<Card> posStraight = new ArrayList<Card>();
        boolean pair = false;
        boolean triple = false;
        boolean dubTrip = false;
        boolean quad = false;
        boolean straight = false;
        boolean flush = false;
        boolean dubPair = false;
        boolean tripPair = false;
        boolean straightFlush = false;
        int paired = -1;
        int trip = -1;
        int four = -1;
        int straightTop = -1;
        int flushFace = -1;
        int doublePair = -1;
        int dubTriple = -1;
        int triplePair = -1;
        int flushSpade = 0;
        int flushHeart = 0;
        int flushDiamond = 0;
        int flushClub = 0;
        int flushHighCard = 0;
       Card[] down = sortHand();
       int m=0;
       while(m<7)
       {
        System.out.println(down[m]);
        m++;
       }
       int highCard = down[0].getValue();
       posStraight.add(down[0]);
       for(int i=1; i<7; i++)
       {
        if(down[i].getValue() != down[i-1].getValue())
        {
            posStraight.add(down[i]);
        }
        if(down[i].getValue() == down[i-1].getValue() && !pair)
        {
            pair = true;
            paired = down[i].getValue();
        }
        else if (down[i].getValue() == down[i-1].getValue() && paired == down[i].getValue() && !dubPair && !triple)
        {
            pair = false;
            paired = -1;
            triple = true;
            trip = down[i].getValue();
        }
        else if (down[i].getValue() == down[i-1].getValue() && paired == down[i].getValue() && !dubPair && triple)
        {
            pair = false;
            paired = -1;
            dubTrip = true;
            dubTriple = down[i].getValue();
        }
        else if (down[i].getValue() == down[i-1].getValue() && paired == down[i].getValue() && dubPair && !tripPair && !triple)
        {
            pair = true;
            paired = doublePair;
            dubPair = false;
            doublePair = -1;
            triple = true;
            trip = down[i].getValue();
        }
        else if (down[i].getValue() == down[i-1].getValue() && paired == down[i].getValue() && dubPair && !tripPair && triple)
        {
            pair = true;
            paired = doublePair;
            dubPair = false;
            doublePair = -1;
            dubTrip = true;
            dubTriple = down[i].getValue();
        }
        else if (down[i].getValue() == down[i-1].getValue() && paired == down[i].getValue() && dubPair && tripPair && !triple)
        {
            pair = true;
            paired = doublePair;
            dubPair = true;
            doublePair = triplePair;
            tripPair = false;
            triplePair = -1;
            triple = true;
            trip = down[i].getValue();
        }
        else if (down[i].getValue() == down[i-1].getValue() && paired == down[i].getValue() && dubPair && tripPair && triple)
        {
            pair = true;
            paired = doublePair;
            dubPair = true;
            doublePair = triplePair;
            tripPair = false;
            triplePair = -1;
            dubTrip = true;
            dubTriple = down[i].getValue();
        }
        else if (down[i].getValue() == down[i-1].getValue() && pair && !dubPair)
        {
           dubPair = true;
           doublePair = down[i].getValue();
        }
        else if (down[i].getValue() == down[i-1].getValue() && doublePair == down[i].getValue() && !tripPair && !triple)
        {
            dubPair = false;
            doublePair = -1;
            triple = true;
            trip = down[i].getValue();
        }
        else if (down[i].getValue() == down[i-1].getValue() && doublePair == down[i].getValue() && !tripPair && triple)
        {
            dubPair = false;
            doublePair = -1;
            dubTrip = true;
            dubTriple = down[i].getValue();
        }
        else if (down[i].getValue() == down[i-1].getValue() && doublePair == down[i].getValue() && tripPair && !triple)
        {
            dubPair = true;
            doublePair = triplePair;
            tripPair = false;
            triplePair = -1;
            triple = true;
            trip = down[i].getValue();
        }
        else if (down[i].getValue() == down[i-1].getValue() && doublePair == down[i].getValue() && tripPair && triple)
        {
            dubPair = true;
            doublePair = triplePair;
            tripPair = false;
            triplePair = -1;
            dubTrip = true;
            dubTriple = down[i].getValue();
        }
        else if (down[i].getValue() == down[i-1].getValue() && pair && dubPair)
        {
            tripPair = true;
            triplePair = hand[i].getValue();
        }
        else if(down[i].getValue() == down[i-1].getValue() && triplePair == down[i].getValue() && !triple)
        {
            tripPair = false;
            triplePair = -1;
            triple = true;
            trip = down[i].getValue();
        }
        else if(down[i].getValue() == down[i-1].getValue() && triplePair == down[i].getValue() && triple)
        {
            tripPair = false;
            triplePair = -1;
            dubTrip = true;
            dubTriple = down[i].getValue();
        }
        else if(down[i].getValue() == down[i-1].getValue() && trip == down[i].getValue() && !dubTrip && !quad)
        {
            triple = false;
            trip = -1;
            quad = true;
            four = down[i].getValue();
        }
        else if(down[i].getValue() == down[i-1].getValue() && trip == down[i].getValue() && dubTrip && !quad)
        {
            triple = true;
            trip = dubTriple;
            quad = true;
            four = down[i].getValue();
        }
        else if(down[i].getValue() == down[i-1].getValue() && dubTriple == down[i].getValue() && !quad)
        {
            dubTrip = false;
            dubTriple = -1;
            quad = true;
            four = down[i].getValue();
        }
        if(down[i].getSuit()==0)
        {
            flushSpade ++;
        }
        else if(down[i].getSuit()==1)
        {
            flushClub ++;
        }
        else if(down[i].getSuit()==2)
        {
            flushDiamond ++;
        }
        else  if(down[i].getSuit()==3)
        {
            flushHeart ++;
        }
    }
       if(flushSpade >= 5)
       {
        flush = true;
        flushFace = 0;
       }
       else if(flushClub >= 5)
       {
        flush = true;
        flushFace = 1;
       }
       else if(flushDiamond >= 5)
       {
        flush = true;
        flushFace = 2;
       }
       else if(flushHeart >= 5)
       {
        flush = true;
        flushFace = 3;
       }
       System.out.println("Straight List " + posStraight.toString());
       int la = 0;
       while(la<7 && down[la].getSuit() != flushFace)
       {
        la ++;
       }
       System.out.println("through loop 1");
       Card[] straightList = new Card[5];
       if(la<7)
       {
       flushHighCard = down[la].getValue();
       }
       if(posStraight.size()>=5)
       {
        if(posStraight.get(0).getValue() == 13 && 
            posStraight.get(posStraight.size()-1).getValue() == 0 && 
            posStraight.get(posStraight.size()-2).getValue() == 1 && 
            posStraight.get(posStraight.size()-3).getValue() == 2 && 
            posStraight.get(posStraight.size()-1).getValue() == 3)
        {
            straight = true;
            straightTop = 4;
        }
        int a=0;
        System.out.println(a<6 && (posStraight.get(a).getValue()-posStraight.get(a+1).getValue())==1 );
        while(a>=6 && (posStraight.get(a).getValue()-posStraight.get(a+1).getValue())!=1);
        {
            straightList[a] = posStraight.get(a);
            a=a+1;
        }
        System.out.println("through loop 2");
        if(a>=5)
        {
            straight = true;
            straightTop = posStraight.get(0).getValue();
        }
        else
        if(a<=1)
        {
            int b = 0;
            posStraight.remove(0);
            straightList = new Card[5];
            if(a==1)
            {
                posStraight.remove(1);
            }
            while(b<4 && posStraight.get(b).getValue()-posStraight.get(b+1).getValue()==1);
            {
                straightList[b] = posStraight.get(b);
                b++;
            }
            if(b==5)
            {
                straight = true;
                straightTop = posStraight.get(0).getValue();
            }
        }
    }
        if(straight)
        {
            int spade = 0;
            int club = 0;
            int diamond = 0;
            int heart = 0;
            int through = 0;
            while(through<4)
            {
                if(straightList[through].getSuit() == 0)
                {
                    spade ++;
                }
                if(straightList[through].getSuit() == 1)
                {
                    club ++;
                }
                if(straightList[through].getSuit() == 2)
                {
                    diamond ++;
                }
                if(straightList[through].getSuit() == 3)
                {
                    heart ++;
                }
            }
            System.out.println("through loop 4");
            if(spade >= 5 || club >= 5 || diamond >= 5 || heart >= 5)
            {
                straightFlush = true;
            }
        }
        System.out.println("got here 2");
        if(straightFlush)
        {
            finalhand = "8 " + straightTop;
        }
        else if(quad)
        {
            finalhand = "7 " + four;
        }
        else if(flush)
        {
            finalhand = "4 " + flushHighCard;
        }
        else if(straight)
        {
            finalhand = "3 " + straightTop;
        }
        else if(triple)
        {
            if(dubTrip)
            {
                if(dubTriple > trip)
                {
                    if(pair)
                    {
                            if(doublePair>triplePair && doublePair>paired)
                            {
                                finalhand = "6 " + dubTriple + " " + doublePair;
                            }
                            else if(doublePair>triplePair && paired>doublePair)
                            {
                                finalhand = "6 " + dubTriple + " " + paired;
                            }
                            else if(triplePair>paired)
                            {
                                finalhand = "6 " + dubTriple + " " + triplePair;
                            }
                    }
                    else
                    {
                        finalhand = "3 " + dubTriple;
                    }
                }
                else
                {
                    if(pair)
                    {
                            if(doublePair>triplePair && doublePair>paired)
                            {
                                finalhand = "6 " + trip + " " + doublePair;
                            }
                            else if(doublePair>triplePair && paired>doublePair)
                            {
                                finalhand = "6 " + trip + " " + paired;
                            }
                            else if(triplePair>paired)
                            {
                                finalhand = "6 " + trip + " " + triplePair;
                            }
                    }
                    else
                    {
                        finalhand = "3 " + trip;
                    }
                }
            }
       }
       else if(pair)
       {
        if(dubPair)
        {
        if(doublePair>triplePair && triplePair<paired)
        {
            finalhand = "2 " + paired + " " + doublePair;
        }
        else if(triplePair>doublePair && doublePair<paired)
        {
            finalhand = "2 " + paired + " " + triplePair;
        }
        else if(triplePair>paired && doublePair>paired)
        {
            finalhand = "2 " + doublePair + " " + triplePair;
        }
       }
       else
       {
        finalhand = "1 " + paired;
       }
    }
    else
    {
        finalhand = "0 " + highCard;
    }

        return finalhand;
    }
//8 is straight flush, 7 is four of a kind, 6 is fullhouse, 5 is flush, 4 is straight, 3 is three of a kind, 2 is two pairs,
//1 is one pair, 0 is high card (i.e. nothing better)
            

    public Card[] sortHand()
    {
        Card[] best = new Card[7];
        int high = hand[0].getValue();
        int c = 0;
        int numMax = 0;
        while( c<7)
        {
            if(hand[c].getValue()>high)
            {
                high = hand[c].getValue();
                numMax = c;
            }
            c++;
        }
        best[0] = hand[numMax];
        hand[numMax] = null;
        for(int i=0; i<hand.length; i++)
        {
            for(int j=1; j<7; j++)
            {
            if(hand[i] == null && i<6)
            {
                i++;
            }
            if(best[j]==null)
            {
                best[j]=hand[i];
                if(i<6)
                i++;
                else
                break;
                j=0;
            }
            else
            if(hand[i].getValue()== best[j].getValue())
            {
                        Card present = best[j];
                        Card next = best[j+1];
                        int pres = j+1;
                        best[j] = hand[i];
                        while(pres<6)
                        {
                            best[pres] = present;
                            present = next;
                            next = best[pres+1];
                            pres++;
                        }
                        best[pres] = present;
                        if(i<6)
                        i++;
                        else
                        break;
                        j=0;
            }
            else if(hand[i].getValue()<best[j].getValue())
            {
                j=j;
            }
            else if(j!=6 && hand[i].getValue()>best[j].getValue())
            {
                Card present = best[j];
                Card next = best[j+1];
                int pres = j+1;
                best[j] = hand[i];
                while(pres<6)
                {
                    best[pres] = present;
                    present = next;
                    next = best[pres+1];
                    pres++;
                }
                if(i<6)
                i++;
                else
                break;
                j=0;
            }
            }
        }
        return best;
    }

}


//sort cards by value first
//level for 5 card set
//make sets of 5 from 7 (hand and down)

