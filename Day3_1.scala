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

while (lines.hasNext){
  val line = lines.next()
  val triangle = (line.substring(2,5).trim.toInt, line.substring(5,10).trim.toInt, line.substring(10,15).trim.toInt)
  if (isValid(triangle)) validTriangles += 1
}

validTriangles