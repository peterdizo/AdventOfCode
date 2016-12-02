// Start writing your ScalaFiddle code here
val steps = "R2, L5, L4, L5, R4, R1, L4, R5, R3, R1, L1, L1, R4, L4, L1, R4, L4, R4, L3, R5, R4, R1, R3, L1, L1, R1, L2, R5, L4, L3, R1, L2, L2, R192, L3, R5, R48, R5, L2, R76, R4, R2, R1, L1, L5, L1, R185, L5, L1, R5, L4, R1, R3, L4, L3, R1, L5, R4, L4, R4, R5, L3, L1, L2, L4, L3, L4, R2, R2, L3, L5, R2, R5, L1, R1, L3, L5, L3, R4, L4, R3, L1, R5, L3, R2, R4, R2, L1, R3, L1, L3, L5, R4, R5, R2, R2, L5, L3, L1, L1, L5, L2, L3, R3, R3, L3, L4, L5, R2, L1, R1, R3, R4, L2, R1, L1, R3, R3, L4, L2, R5, R5, L1, R4, L5, L5, R1, L5, R4, R2, L1, L4, R1, L1, L1, L5, R3, R4, L2, R1, R2, R1, R1, R3, L5, R1, R4"
//val steps = "R8, R4, R4, R8"
val steps_array = steps.split(", ")
val steps_tuples = steps_array.map(s => (s.head, s.tail.toInt)).toList

//toto sa da spravit aj krajsie asi, posuvanim indexov 4 zlozkoveho pola a modulo 4
def next_orientation(current: Char, turn: Char): Char = turn match {
  case 'R' => current match {
    case 'N' => 'E'
    case 'S' => 'W'
    case 'W' => 'N'
    case 'E' => 'S'
  }
  case 'L' => current match {
    case 'N' => 'W'
    case 'S' => 'E'
    case 'W' => 'S'
    case 'E' => 'N'
  }
}

def move(dir: Char, steps: Int, pos: (Int, Int)): (Int, Int) = dir match {
  case 'N' => (pos._1, pos._2 + steps)
  case 'S' => (pos._1, pos._2 - steps)
  case 'W' => (pos._1 - steps, pos._2)
  case 'E' => (pos._1 + steps, pos._2)
}

def get_path(dir: Char, steps: Int, pos: (Int, Int)): Set[(Int, Int)] = dir match {
  case 'N' => {for(x <- Range(pos._2 + 1, pos._2 + steps + 1)) yield (pos._1, x)}.toSet
  case 'S' => {for(x <- Range(pos._2 - 1, pos._2 - steps - 1, -1)) yield (pos._1, x)}.toSet
  case 'W' => {for(x <- Range(pos._1 - 1, pos._1 - steps - 1, -1)) yield (x, pos._2)}.toSet
  case 'E' => {for(x <- Range(pos._1 + 1, pos._1 + steps + 1)) yield (x, pos._2)}.toSet
}


def make_step(dir: Char, pos: (Int, Int), path: List[(Char, Int)], visited: Set[(Int, Int)]) : (Int, Int) = {
  if (path.isEmpty) pos
  else {
    val step = path.head
    val new_direction = next_orientation(dir, step._1)
    val new_pos = move(new_direction, step._2, pos)
    val new_visited = get_path(new_direction, step._2, pos)
    val solution = visited & new_visited
    if (!solution.isEmpty) println("solution: " + solution)
    make_step(new_direction, new_pos, path.tail, visited ++ new_visited)
  }
}

make_step('N', (0,0), steps_tuples, Set((0,0)))
