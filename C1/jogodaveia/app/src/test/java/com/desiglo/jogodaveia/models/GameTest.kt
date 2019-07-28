package com.desiglo.jogodaveia.models

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*


class GameTest {

    private lateinit var game: Game
    private lateinit var player: Player
    private lateinit var anotherPlayer: Player

    @Before
    @Throws(Exception::class)
    fun setUp() {
        game = Game("Heider", "William")
        player = game.player1
        anotherPlayer = game.player2
    }

    @Test
    @Throws(Exception::class)
    fun returnTrueIfHasThreeSameDiagonalCellsFromLeft() {
        val cell = Cell(player)
        game.cells[0][0] = cell
        game.cells[1][1] = cell
        game.cells[2][2] = cell
        val hasThreeSameDiagonalCells = game.hasThreeSameDiagonalCells()
        Assert.assertTrue(hasThreeSameDiagonalCells)
    }

    @Test
    @Throws(Exception::class)
    fun returnTrueIfHasThreeSameDiagonalCellsFromRight() {
        val cell = Cell(player)
        game.cells[0][2] = cell
        game.cells[1][1] = cell
        game.cells[2][0] = cell
        val hasThreeSameDiagonalCells = game.hasThreeSameDiagonalCells()
        assertTrue(hasThreeSameDiagonalCells)
    }

    @Test
    @Throws(Exception::class)
    fun returnTrueIfHasThreeSameHorizontalCellsAtRow1() {
        val cell = Cell(player)
        game.cells[0][0] = cell
        game.cells[0][1] = cell
        game.cells[0][2] = cell
        val hasThreeSameHorizontalCells = game.hasThreeSameHorizontalCells()
        assertTrue(hasThreeSameHorizontalCells)
    }

    @Test
    @Throws(Exception::class)
    fun returnTrueIfHasThreeSameHorizontalCellsAtRow2() {
        val cell = Cell(player)
        game.cells[1][0] = cell
        game.cells[1][1] = cell
        game.cells[1][2] = cell
        val hasThreeSameHorizontalCells = game.hasThreeSameHorizontalCells()
        assertTrue(hasThreeSameHorizontalCells)
    }

    @Test
    @Throws(Exception::class)
    fun returnTrueIfHasThreeSameHorizontalCellsAtRow3() {
        val cell = Cell(player)
        game.cells[2][0] = cell
        game.cells[2][1] = cell
        game.cells[2][2] = cell
        val hasThreeSameHorizontalCells = game.hasThreeSameHorizontalCells()
        assertTrue(hasThreeSameHorizontalCells)
    }

    @Test
    @Throws(Exception::class)
    fun returnFalseIfDoesNotHaveThreeSameHorizontalCells() {
        val cell = Cell(player)
        val anotherCell = Cell(anotherPlayer)
        game.cells[0][0] = cell
        game.cells[0][1] = cell
        game.cells[0][2] = anotherCell
        val hasThreeSameHorizontalCells = game.hasThreeSameHorizontalCells()
        assertFalse(hasThreeSameHorizontalCells)
    }

    @Test
    @Throws(Exception::class)
    fun returnTrueIfHasThreeSameVerticalCellsAtColumn1() {
        val cell = Cell(player)
        game.cells[0][0] = cell
        game.cells[1][0] = cell
        game.cells[2][0] = cell
        val hasThreeSameVerticalCells = game.hasThreeSameVerticalCells()
        assertTrue(hasThreeSameVerticalCells)
    }

    @Test
    @Throws(Exception::class)
    fun returnTrueIfHasThreeSameVerticalCellsAtColumn2() {
        val cell = Cell(player)
        game.cells[0][1] = cell
        game.cells[1][1] = cell
        game.cells[2][1] = cell
        val hasThreeSameVerticalCells = game.hasThreeSameVerticalCells()
        assertTrue(hasThreeSameVerticalCells)
    }

    @Test
    @Throws(Exception::class)
    fun returnTrueIfHasThreeSameVerticalCellsAtColumn3() {
        val cell = Cell(player)
        game.cells[0][2] = cell
        game.cells[1][2] = cell
        game.cells[2][2] = cell
        val hasThreeSameVerticalCells = game.hasThreeSameVerticalCells()
        assertTrue(hasThreeSameVerticalCells)
    }

    @Test
    fun endGameIfHasThreeSameHorizontalCells() {
        val cell = Cell(player)
        game.cells[0][0] = cell
        game.cells[0][1] = cell
        game.cells[0][2] = cell
        val hasGameEnded = game.hasGameEnded()
        assertTrue(hasGameEnded)
    }

    @Test
    fun endGameIfHasThreeSameVerticalCells() {
        val cell = Cell(player)
        game.cells[0][0] = cell
        game.cells[1][0] = cell
        game.cells[2][0] = cell
        val hasGameEnded = game.hasGameEnded()
        assertTrue(hasGameEnded)
    }

    @Test
    fun endGameIfHasThreeSameDiagonalCells() {
        val cell = Cell(player)
        game.cells[0][0] = cell
        game.cells[1][1] = cell
        game.cells[2][2] = cell
        val hasGameEnded = game.hasGameEnded()
        assertTrue(hasGameEnded)
    }

    @Test
    fun endGameIfBoardIsFull() {
        val cell1 = Cell(Player("1", "x"))
        val cell2 = Cell(Player("2", "o"))
        val cell3 = Cell(Player("1", "x"))
        val cell4 = Cell(Player("2", "o"))
        val cell5 = Cell(Player("1", "x"))
        val cell6 = Cell(Player("2", "o"))
        val cell7 = Cell(Player("1", "x"))
        val cell8 = Cell(Player("2", "o"))
        val cell9 = Cell(Player("1", "x"))
        game.cells[0][0] = cell1
        game.cells[0][1] = cell2
        game.cells[0][2] = cell3
        game.cells[1][0] = cell4
        game.cells[1][1] = cell5
        game.cells[1][2] = cell6
        game.cells[2][0] = cell7
        game.cells[2][1] = cell8
        game.cells[2][2] = cell9
        val isBoardFull = game.isBoardFull
        assertTrue(isBoardFull)
    }

}