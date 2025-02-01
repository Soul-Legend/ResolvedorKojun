package com.Kojun

type Linha[Valor] = Array[Valor]
type Matriz[Valor] = Array[Linha[Valor]]
type Tabuleiro = Matriz[Int]
type Posicao = (Int, Int)

def obterValor(tabuleiro: Tabuleiro, posicao: Posicao) = {
  tabuleiro.lift(posicao._1).flatMap(_.lift(posicao._2)).getOrElse(0)
}

def obterValorDentro(tabuleiro: Tabuleiro, tamanho: Int, posicao: Posicao): Option[Int] =
  if (posicao._1 >= 0 && posicao._1 < tamanho &&
    posicao._2 >= 0 && posicao._2 < tamanho) Some(obterValor(tabuleiro, posicao)) else None

def mostrarMatriz(tabuleiro: Tabuleiro) = {
  tabuleiro.foreach(x => println(x.mkString(",")));
}
