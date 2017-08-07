import scala.io.Source

var validCount = 0

val input = Source.fromFile("C:\\Users\\pdizo\\Documents\\backup\\General\\reportApi\\Day7inp.txt")
val lines = input.getLines()

def tls(s: String): Boolean ={

  assert(s.length >= 4)

  def checkChars2(inp: List[Char]): Boolean = inp match {
      case c1::c2::c3::Nil => false
      case c1::c2::c3::c4::rest =>
        if (c1 == c4 && c2 == c3 && c1 != c2) true
        else checkChars2(c2::c3::c4::rest)
      case _ => false
  }
  checkChars2(s.toList)
}

def isValid(inp: String): Boolean = {
  val inpSplit = inp
    .split(Array('[', ']'))
    .zipWithIndex

  def check(v: Boolean, r: List[(String, Int)]): Boolean = {
    r match {
      case Nil => v
      case l::rest =>  l._2 % 2 match {
        case 0 => if(tls(l._1)) check(true, rest) else check(v, rest)
        case 1 => if(tls(l._1)) false else check(v, rest)
      }
    }
  }
  check(false, inpSplit.toList)
}


while (lines.hasNext) {
  val line = lines.next

  if (isValid(line)) validCount += 1

}

isValid("abba[mnop]qrst")
isValid("abcd[bddb]xyyx")
isValid("aaaa[qwer]tyui")
isValid("ioxxoj[asdfgh]zxcvbn")

validCount