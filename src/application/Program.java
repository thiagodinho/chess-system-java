package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		ChessMatch cm = new ChessMatch();
		List<ChessPiece> captured = new ArrayList<>();

		while (!cm.getCheckMate()) {
			try {
				UI.clearScreen();
				UI.printMatch(cm, captured);
				System.out.print("\nSource: ");
				ChessPosition source = UI.readChessPosition(sc);

				boolean[][] possibleMoves = cm.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(cm.getPieces(), possibleMoves);

				System.out.print("\nTarget: ");
				ChessPosition target = UI.readChessPosition(sc);

				ChessPiece capturedPiece = cm.performChessMove(source, target);

				if (captured != null) {
					captured.add(capturedPiece);
				}

			} catch (ChessException e) {
				System.out.println(e.getMessage());
				System.out.println("Press any key to continue.");
				sc.nextLine();
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				System.out.println("Press any key to continue.");
				sc.nextLine();
			}
		}
		UI.clearScreen();
		UI.printMatch(cm, captured);
	}

}
