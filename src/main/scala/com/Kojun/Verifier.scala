package com.Kojun

class Verifier(private val regioes: Tabuleiro, private val tamanho: Int) {
  // Obtem o identificador da regiao
  def obterIdentificadorRegiao(posicao: Posicao): Int = {
    obterValor(regioes, posicao)
  }

  // Obtem os valores que estao na mesma regiao
  def obterValoresRegiao(tabuleiro: Tabuleiro, regiao: Int): List[Int] = {
    regioes.zipWithIndex.flatMap { case (row, rowIndex) =>
      row.zipWithIndex.collect {
        case (value, colIndex) if value == regiao => (rowIndex, colIndex)
      }
    }.map { case (pos: Posicao) =>
      obterValor(tabuleiro, pos)
    }.toList
  }

  // Valida se dentro da regiao todos os valores sao unicos
  def checarUnicidade(valoresRegiao: List[Int]): Boolean = {
    val regiaoSemZero = valoresRegiao.filter(a => a != 0)
    regiaoSemZero.toSet.size == regiaoSemZero.size
  }

  def obterTamanhoRegiao(posicao: Posicao): Int = {
    val regiao = obterIdentificadorRegiao(posicao)
    regioes.flatten.count(p => p == regiao)
  }

  def validarNumeroRegiao(tabuleiro: Tabuleiro, posicao: Posicao, numero: Int): Boolean = {
    val tamanho = obterTamanhoRegiao(posicao)
    numero <= tamanho
  }

  // Valida se valores verticalemnte adjacentes na mesma região seguem a regra do menor igual
  def validaAdjacenteAcimaAbaixoNaRegiao(tabuleiro: Tabuleiro, posicao: Posicao, cimaBaixo: (Option[Int], Option[Int]), valor: Int): Boolean = {
    cimaBaixo match {
      case (Some(cima), Some(baixo)) => valor > baixo && valor < cima
      case (Some(cima), None) => valor < cima
      case (None, Some(baixo)) => valor > baixo
      case (None, None) => true
    }
  }

  // Obter os valores acima e abaixo
  def obterAcimaAbaixloNaRegiao(tabuleiro: Tabuleiro, posicao: Posicao): (Option[Int], Option[Int]) = {
    val embaixo = posicao._1 - 1
    val emCima = posicao._1 + 1
    val regiao = obterIdentificadorRegiao(posicao)

    val valorEmCima = obterValorDentro(tabuleiro, tamanho, (emCima, posicao._2))
    val valorEmCimaFiltrado = valorEmCima match {
      case Some(value) if regiao == obterIdentificadorRegiao(emCima, posicao._2) => Some(value)
      case _ => None
    }

    val valorEmbaixo = obterValorDentro(tabuleiro, tamanho, (embaixo, posicao._2))
    val valorEmbaixoFiltrado = valorEmbaixo match {
      case Some(value) if regiao == obterIdentificadorRegiao(embaixo, posicao._2) => Some(value)
      case _ => None
    }

    (valorEmbaixoFiltrado, valorEmCimaFiltrado)  }

  // Obtem os valores ortogonais ao ponto
  def obterValoresOrtogonais(tabuleiro: Tabuleiro, posicao: Posicao): List[Int] = {
    val direcoes = List(
      (-1, 0), // Cima
      (1, 0),  // Baixo
      (0, -1), // Esquerda
      (0, 1)   // Direita
    )

    direcoes.flatMap { case (dlinha, dcoluna) =>
      val novaLinha = posicao._1 + dlinha
      val novaColuna = posicao._2 + dcoluna
      if (novaLinha >= 0 && novaLinha < tamanho && novaColuna >= 0 && novaColuna < tamanho)
        Some(tabuleiro(novaLinha)(novaColuna))
      else
        None
    }.filter(a => a != 0)
  }

  // Recebe a lista de numeros ortogonais e o ponto para resolver se é possível
  def checarNumerosDiferentesOrtogonais(tabuleiro: Tabuleiro, listaOrtogonais: List[Int], ponto: Int) : Boolean = {
    !listaOrtogonais.contains(ponto)
  }

  // A partir do estado do tabuleiro passado e do valor na posicao indicada, verificar se é uma jogada válida
  def validarNovaJogada(tabuleiro: Tabuleiro, posicao: Posicao, valor: Int): Boolean = {
    val valoresRegiao = obterValoresRegiao(tabuleiro, obterIdentificadorRegiao(posicao))
    val unico = checarUnicidade(valoresRegiao :+ valor)
    val valoresOrtogonais = obterValoresOrtogonais(tabuleiro, posicao)
    val unicoOrtogonal = checarNumerosDiferentesOrtogonais(tabuleiro, valoresOrtogonais, valor)
    val cimaBaixo = obterAcimaAbaixloNaRegiao(tabuleiro, posicao)
    val validadoCimaBaixo = validaAdjacenteAcimaAbaixoNaRegiao(tabuleiro, posicao, cimaBaixo, valor)
    val validadoNumero = validarNumeroRegiao(tabuleiro, posicao, valor)
    unico && unicoOrtogonal && validadoCimaBaixo && validadoNumero
  }


  // Backtracking para resolver o tabuleiro
  def solveBoard(tabuleiro: Tabuleiro, linha: Int = 0, coluna: Int = 0): Option[Tabuleiro] = {
    // Terminou tabuleiro
    if (linha == tamanho) {
      mostrarMatriz(tabuleiro)
      Some(tabuleiro)
    } else {
      val (proximaLinha, proximaColuna) = proximaPosicao(linha, coluna)
      if (obterValor(tabuleiro, (linha, coluna)) != 0) {
        solveBoard(tabuleiro, proximaLinha, proximaColuna)
      } else {
        (1 to obterTamanhoRegiao((linha, coluna))).find { num =>
          validarNovaJogada(tabuleiro, (linha, coluna), num) && {
            tabuleiro(linha)(coluna) = num
            val result = solveBoard(tabuleiro, proximaLinha, proximaColuna)
            if (result.isDefined) true
            else {
              tabuleiro(linha)(coluna) = 0
              false
            }
          }
        }
        None
      }
    }
  }

  // Obtem a proxima posicao do tabuleiro
  def proximaPosicao(linha: Int, coluna: Int): (Int, Int) = {
    if (coluna < tamanho - 1) {
      (linha, coluna + 1)
    }
    else (linha + 1, 0)
  }

}
