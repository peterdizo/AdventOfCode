import java.security.MessageDigest

val digest = MessageDigest.getInstance("MD5")
val resSet = scala.collection.mutable.Set('0','1','2','3','4','5','6','7')
val result = scala.collection.mutable.MutableList('*','*','*','*','*','*','*','*')
var counter = 0
def getHash(s: String): String = digest.digest(s.getBytes).map("%02x".format(_)).mkString

val inp = "cxdnnyjw"

while(resSet.nonEmpty){
  val hash = getHash(inp + counter.toString)
  if(hash.startsWith("00000")){
    val pos = hash(5)
    val letter = hash(6)
    if (resSet.contains(pos)){
      result(pos.toString.toInt) = letter
      resSet -= pos
      println(result)
    }

  }
  counter += 1
}

result