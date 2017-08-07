import scala.io.Source

val input = Source.fromFile("C:\\Users\\pdizo\\Documents\\backup\\General\\reportApi\\Day4inp.txt")

val lines = input.getLines()
val alphabet = "abcdefghijklmnopqrstuvwxyz"

def shiftChar(c: Char, n: Int): Char = {
  c match {
    case '-' => ' '
    case _ => alphabet((alphabet.indexOf(c) + n) % 26)
  }

}

while (lines.hasNext){
  val x = lines.next
  val id = x.filter(_.isDigit)
  val checksum = x.substring(x.indexOf("[") + 1, x.indexOf("]")).toCharArray
  val chars = x.substring(0,x.indexOf("[")).replace("-","").filter(!_.isDigit)
  val charMap = chars.toCharArray.foldLeft(Map.empty[Char, Int]){
                                                                  (count, letter) => count + (letter -> (count.getOrElse(letter, 0) + 1))
                                                                }
  val msg = x.substring(0,x.indexOf("[")).filter(!_.isDigit).map(c => shiftChar(c, id.toInt))
  val charCount = charMap.toArray.sortBy(c => (-c._2, c._1)).take(5).map(_._1)
  if(charCount.sameElements(checksum) && msg.indexOf("pole") > -1) println(msg + " " + id)
}
