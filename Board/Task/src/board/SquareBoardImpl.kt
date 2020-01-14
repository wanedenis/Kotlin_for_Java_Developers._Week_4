package board

import com.sun.xml.internal.bind.v2.TODO

open class SquareBoardImpl(override val width: Int) : SquareBoard {

    private val allCells = mutableListOf<Cell>()

    init {
        for (i in 1..width){
            for (j in 1..width){
                allCells.add(Cell(i,j))
            }
        }
    }


    override fun getCellOrNull(a: Int, b: Int): Cell? {

        if ((a in 1..width) && (b in 1..width)){

            var pos = 0
            for (i in 1..width){
                if (a == i) {
                    for (j in 1..width) {
                        if (b == j){
                            println("getCellorNull  : ${allCells[pos]}")
                            return allCells[pos]
                        }
                        pos++
                    }
                }else{
                    pos += width
                }
            }
        }

        return null
    }

    override fun getCell(a: Int, b: Int): Cell {

        if ((a !in 1..width) || (b !in 1..width)){
            throw IllegalArgumentException()
        }

        var pos = 0
        for (i in 1..width){
            if (a == i) {
                for (j in 1..width) {
                    if (b == j){
                        break
                    }
                    pos++
                }
            }else{
                pos += width
            }
        }
        println("getCell  : ${allCells[pos]}")
        return allCells[pos]
    }

    override fun getAllCells(): Collection<Cell> {
        return allCells
    }

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {

        val list = mutableListOf<Cell>()

       /* println("jRange: $jRange")
        println("jRange First: ${jRange.first}")
        println("jRange Last: ${jRange.last}")*/

        if (jRange.first > jRange.last){
           /* for (j in jRange.first downTo i) {
                list.add(Cell(i, j))
            }*/

            if (jRange.first < width){
                for (j in jRange) {
                    list.add(Cell(i, j))
                }
            }else{
                for (j in jRange.first downTo i) {
                    list.add(Cell(i, j))
                }
            }

        }else {

            if (jRange.last < width){
                for (j in jRange) {
                    list.add(Cell(i, j))
                }
            }else{
                for (j in jRange.first..width) {
                    list.add(Cell(i, j))
                }
            }
        }

        return list
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        val list = mutableListOf<Cell>()

        /*println("iRange: $iRange")
        println("iRange First: ${iRange.first}")
        println("iRange Last: ${iRange.last}")*/

        if (iRange.first > iRange.last){

            if (iRange.last < width){
                for (i in iRange) {
                    list.add(Cell(i, j))
                }
            }else{
                for (i in iRange.first downTo j) {
                    list.add(Cell(i, j))
                }
            }

        }else {

            if (iRange.last < width){
                for (i in iRange) {
                    list.add(Cell(i, j))
                }
            }else{
                for (i in iRange.first..width) {
                    list.add(Cell(i, j))
                }
            }
        }

        return list
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? {

        return when(direction){

            Direction.UP -> if (i > 1) Cell(i-1, j)
            else null
            Direction.DOWN -> if (i < width) Cell(i+1, j)
            else null
            Direction.RIGHT -> if (j < width) Cell(i, j+1)
            else null
            Direction.LEFT -> if (j > 1) Cell(i, j-1)
            else null
        }
    }
}