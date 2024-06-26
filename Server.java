import java.io.*;
import java.net.*;

public class Server {
    private int players;
    private ServerThread threads[];
    private Socket sockets[];

    private int bet;
    private int pot;
    private Card[] community;
    private boolean[] foldedPlayers;
    private int numFoldedPlayers;
    private int startingPlayer;

    Deck deck;

    public Server() throws InterruptedException {
        try {
            ServerSocket serverSocket = new ServerSocket(9000);
            players = 3;
            threads = new ServerThread[players];
            sockets = new Socket[players];

            for(int i = 0; i < players; ++i) {
                sockets[i] = serverSocket.accept();
                threads[i] = new ServerThread(sockets[i], this, i);
            }

            startingPlayer = 0;

            while(true) {
                pot = 0;
                bet = 0;
                deck = new Deck();
                community = new Card[5];
                foldedPlayers = new boolean[players];
                numFoldedPlayers = 0;
                for(int i = 0; i < players; ++i) {
                    threads[i].send("reset");
                }

                deal();
                if(!getBets(startingPlayer)) {
                    flop();
                    if(!getBets(startingPlayer)) {
                        turn();
                        if(!getBets(startingPlayer)) {
                            river();
                            getBets(startingPlayer);
                        }
                    }
                }
                int winner = 0;
                for(int i = 0; i < players; ++i) {
                    if(!foldedPlayers[i]) {
                        winner = i;
                    }
                }
                threads[winner].send("win " + pot);

                ++startingPlayer; 
                if(startingPlayer == players) {
                    startingPlayer = 0;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void deal() {
        for(int i = 0; i < players; ++i) {
            Card card = deck.drawCard();
            Card card2 = deck.drawCard();
            threads[i].send("deal " + card.toMessage() + " " + card2.toMessage());
        }
    }

    private void flop() {
        for(int i = 0; i < 3; ++i) {
            community[i] = deck.drawCard();
        }
        for(int i = 0; i < players; ++i) {
            for(int j = 0; j < 3; ++j) {
                threads[i].send("community " + community[j].toMessage());
            }
        }
    }

    private void turn() {
        community[3] = deck.drawCard();
        for(int i = 0; i < players; ++i) {
            threads[i].send("community " + community[3].toMessage());
        }
    }

    private void river() {
        community[4] = deck.drawCard();
        for(int i = 0; i < players; ++i) {
            threads[i].send("community " + community[4].toMessage());
        }
    }

    private synchronized int goAround(int startingPlayer) throws InterruptedException {
        int i = startingPlayer;
        int oldBet = bet;
        threads[i].send("turn " + bet);
        wait();
        if(bet > oldBet) {
            startingPlayer = i;
        }
        ++i;
        if(i == players) {
            i = 0;
        }
        while(i != startingPlayer) {
            if(!foldedPlayers[i]) {
                oldBet = bet;
                threads[i].send("turn " + bet);
                wait();
                if(bet > oldBet) {
                    startingPlayer = i;
                }
            }
            ++i;
            if(i == players) {
                i = 0;
            }
        }
        return bet;
    }

    public synchronized void setBetValue(int bet) {
        notifyAll();
        this.bet = bet;
    }

    public synchronized void fold(int playerNum) {
        notifyAll();
        foldedPlayers[playerNum] = true;
        ++numFoldedPlayers;
    }

    public boolean getBets(int startingPlayer) {
        try {
            pot += goAround(startingPlayer) * (players - numFoldedPlayers);
            for(int i = 0; i < players; ++i) {
                threads[i].send("pot " + pot);
            }
            bet = 0;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(numFoldedPlayers == players - 1) {
            return true;
        }
        return false;
    }
}
