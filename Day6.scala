import scala.io.Source

val letters = scala.collection.mutable.Map[Char, Int]()

for (i <- 0 to 7) {

  val input = Source.fromFile("C:\\Users\\pdizo\\Documents\\backup\\General\\reportApi\\Day6inp.txt")
  val lines = input.getLines()

  while (lines.hasNext) {
    val line = lines.next
    val c = line(i)
    letters(c) = letters.getOrElse(c, 0) + 1
  }
  println(letters.minBy(_._2))
  letters.clear()
}