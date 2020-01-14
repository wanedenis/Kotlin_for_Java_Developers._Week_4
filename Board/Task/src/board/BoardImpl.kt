package board

import board.Direction.*
import com.sun.xml.internal.bind.v2.TODO

fun createSquareBoard(width: Int): SquareBoard = SquareBoardImpl(width)
fun <T> createGameBoard(width: Int): GameBoard<T> = GameBoardImpl(width)