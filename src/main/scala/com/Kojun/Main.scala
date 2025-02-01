package com.Kojun

type Board = Array[Array[Int]]

object Main extends App {
  println("Board 6x6: ")
  val verifier6 = Verifier(board6Areas, 6)
  val board6: Option[Tabuleiro] = verifier6.solveBoard(board6Values)

  println("Board 8x8: ")
  val verifier8 = Verifier(board8Areas, 8)
  val board8: Option[Tabuleiro] = verifier8.solveBoard(board8Values)

  println("Board 10x10: ")
  val verifier10 = Verifier(board10Areas, 10)
  val board10: Option[Tabuleiro] = verifier10.solveBoard(board10Values)
}