package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
    def pascal(c: Int, r: Int): Int = {
      if(c == 0 || r == c)
        1
      else
        pascal(c-1, r) + pascal(c, r-1)
    }


  /**
   * Exercise 2
   */
    def balance(chars: List[Char]): Boolean = {

      def balf(openLevels: Int, chars: List[Char]): Int = {
        if (chars.isEmpty)
          openLevels
        else chars.head match {
          case '(' => balf(openLevels + 1, chars.tail)  // increment open levels
          case ')' => if (openLevels == 0) -1 else balf(openLevels - 1, chars.tail)  // if close paren w/o open, then error, otherwise decrement
          case _ => balf(openLevels, chars.tail) // if no paren, next character
        }
      }

      val openLevels = balf(0, chars)
      if (openLevels == 0) true else false // if no open levels, then balanced
    }
  
  /**
   * Exercise 3
   */
    def countChange(money: Int, coins: List[Int]): Int = {

      def countPermutations(permutations: Int, money: Int, coins: List[Int]): Int = {
        if (coins.isEmpty ) // no change left
          permutations
        else money match {
          case 0 => 1 // match
          case _ => if (coins.head > money) countPermutations(permutations, money, coins.tail) // current denomination too large, try next one down
            else countPermutations(permutations, money - coins.head, coins) + // see what change can be made after subtracting the current denomination
                 countPermutations(permutations, money, coins.tail)  // see what change can be made with the smaller denominations
        }
      }
      countPermutations(0, money, coins.sortWith(_ > _))  // initally, sort list in descending order
    }
  }
