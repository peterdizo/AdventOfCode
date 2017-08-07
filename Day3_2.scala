import scala.io.Source

val input = Source.fromFile("C:\\Users\\pdizo\\Documents\\backup\\General\\reportApi\\Day3inp.txt")

var validTriangles = 0
val lines = input.getLines()

def isValid(x: (Int,Int,Int)) = {
  val a = x._1
  val b = x._2
  val c = x._3

  (a + b > c) && (a + c > b) && (b + c > a)
}

def processLine(line: String) = (line.substring(2,5).trim.toInt, line.substring(5,10).trim.toInt, line.substring(10,15).trim.toInt)

while (lines.hasNext){
  val row1 = processLine(lines.next())
  val row2 = processLine(lines.next())
  val row3 = processLine(lines.next())

  if (isValid(row1._1, row2._1, row3._1)) validTriangles += 1
  if (isValid(row1._2, row2._2, row3._2)) validTriangles += 1
  if (isValid(row1._3, row2._3, row3._3)) validTriangles += 1
}

validTriangles