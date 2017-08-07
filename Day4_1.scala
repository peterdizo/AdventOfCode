import scala.io.Source

val input = Source.fromFile("C:\\Users\\pdizo\\Documents\\backup\\General\\reportApi\\Day4inp.txt")

var roomIdSum = 0
val lines = input.getLines()

def getId(x: String): Int = {
  val id = x.filter(_.isDigit).toInt
  val checksum = x.substring(x.indexOf("[") + 1, x.indexOf("]")).toCharArray
  val chars = x.substring(0,x.indexOf("[")).replace("-","").filter(!_.isDigit)
  val charMap = chars.toCharArray.foldLeft(Map.empty[Char, Int]){
     (count, letter) => count + (letter -> (count.getOrElse(letter, 0) + 1))
     }
  val charCount = charMap.toArray.sortBy(c => (-c._2, c._1)).take(5).map(_._1)
  if(charCount.sameElements(checksum)) id else 0
}

getId("aaaaa-bbb-z-y-x-123[abxyz]")
getId("a-b-c-d-e-f-g-h-987[abcde]")
getId("not-a-real-room-404[oarel]")
getId("totally-real-room-200[decoy]")

while (lines.hasNext){
  roomIdSum += getId(lines.next)
}

roomIdSum