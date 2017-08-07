import scala.io.Source

var validCount = 0

val input = Source.fromFile("C:\\Users\\pdizo\\Documents\\backup\\General\\reportApi\\Day7inp.txt")
val lines = input.getLines()

def aba(s: String): Set[String] = {
  assert(s.length >= 3)

  def getAba(inp: List[Char], res: Set[String]): Set[String] = inp match {
    case c1::c2::Nil => res
    case c1::c2::c3::rest =>
      if (c1 == c3 && c1 != c2) getAba(c2::c3::rest, res + (c1.toString + c2.toString))
      else getAba(c2::c3::rest, res)
    case _ => res
  }
  getAba(s.toList, Set.empty)
}

def isValid(inp: String): Boolean = {
  val inpSplit = inp
    .split(Array('[', ']'))
    .zipWithIndex

  def getSets(input: List[(String, Int)], res: (Set[String], Set[String])): (Set[String], Set[String]) = input match {
    case Nil => res
    case l :: rest => l._2 % 2 match {
      case 0 => getSets(rest, (res._1 ++ aba(l._1), res._2))
      case 1 => getSets(rest, (res._1, res._2 ++ aba(l._1)))
    }
  }

  val (sup, hyp) = getSets(inpSplit.toList, (Set.empty, Set.empty))

  (sup & hyp.map(h => h.reverse)).nonEmpty
}


while (lines.hasNext) {
  val line = lines.next

  if (isValid(line)) validCount += 1

}
isValid("xyx[xyx]xyx")
isValid("aba[bab]xyz")
isValid("aaa[kek]eke")
isValid("zazbz[bzb]cdb")



validCount